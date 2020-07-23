package com.example.android.radiancex

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.radiancex.database.DiEntry
import com.example.android.radiancex.database.DiEntryRoomDatabase
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class DiEntryViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: DiEntryRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _currentDeck = MutableLiveData<ArrayList<DiEntry>>()
    private var _currentSentence = MutableLiveData<DiEntry>()
    var currentDeck: LiveData<ArrayList<DiEntry>>
        get() = _currentDeck
    var currentSentence: LiveData<DiEntry>
        get() = _currentSentence

    private lateinit var cardViewHistoryStack: Stack<Int>

    lateinit var allEntries: LiveData<List<DiEntry>>
//    var numberOfEntries = Transformations.map(allEntries) { entries ->
//        entries.size
//    }

    init {
        initialize()
        val dientriesDao = DiEntryRoomDatabase.getDatabase(application, viewModelScope).dictionaryEntryDao()
        repository = DiEntryRepository(dientriesDao)
        allEntries = repository.allDiEntries
        currentDeck = _currentDeck
        currentSentence = _currentSentence
    }

    private fun initialize() {
        uiScope.launch {
//            _currentDeck.value = getNewDeckFromDatabase()
//            _currentSentence.value = getNewCurrentSentence()
            val deferredResult: Deferred<ArrayList<DiEntry>> = uiScope.async {
                getNewDeckFromDatabase()
            }
            _currentDeck.value = deferredResult.await()
            _currentSentence.value = getNewCurrentSentence()
        }
    }


    fun findDiEntryByIdSynchronous(id: Int): DiEntry {
        return repository.findDiEntryByIdSynchronous(id)
    }

    val numberOfEntriesSynchronous: Int
        get() = repository.numberOfEntriesSynchronous

    fun insert(sentence: DiEntry) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(sentence)
    }

    fun deleteAllSentences() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllEntries()
    }

    private suspend fun getNewDeckFromDatabase(): ArrayList<DiEntry> {
        return withContext(Dispatchers.IO) {
            val deckSize: Int = 20
            val tempDeck: ArrayList<DiEntry> = ArrayList<DiEntry>(deckSize)
//            val sampleStrings = arrayOfNulls<String>(4)
//            var jpn: String?
//            var eng: String?
//            var mea: String?
//            var vie: String?
//            sampleStrings[0] = "Lorem ipsum dolor sit amet"
//            sampleStrings[1] = "iouKU3bqlG0m6Vm5X7rp9IkxRFttd8iHiQmRBiMYo6HnD95pGb"
//            sampleStrings[2] = "AEzFGnXmdHJzmZH3KXh81oGQQepK7ppQgHoNbaWmAQ92PxCImx0aIJb7Jafax5pXK9fLtC"
//            sampleStrings[3] = "qd3RbIAcn3qn2YsY6Y18QiiOqpdCQIKM9fF3gPRsxW14qfuutuVYZjwz50LfqpzXQOdZYqvSqWPR3Dr1Q284EJDMGAFiDOhArvOG\n"
//            for (i in 0 until deckSize) {
//                jpn = sampleStrings[(Math.random() * 4).toInt()]
//                eng = sampleStrings[(Math.random() * 4).toInt()]
//                mea = sampleStrings[(Math.random() * 4).toInt()]
//                vie = sampleStrings[(Math.random() * 4).toInt()]
////                tempDeck.add(DiEntry(jpn, mea, eng, vie))
//            }
            for (i in 0 until deckSize) {
                tempDeck.add(findDiEntryByIdSynchronous((0..numberOfEntriesSynchronous).random()))
            }
            Log.d("tempDeckLiveData size: ", tempDeck.size.toString())
            return@withContext tempDeck
        }
    }

    private fun getNewCurrentSentence(): DiEntry? {
        return _currentDeck.value?.get((0.._currentDeck.value!!.size).random())
    }

    suspend fun onGetNewDeck() {
        _currentDeck.value?.clear()
        _currentDeck.postValue(getNewDeckFromDatabase())
        onGoToNext()
    }

    fun onGoToNext() {
        cardViewHistoryStack.push(currentSentence.value?.id)
        _currentSentence.value = getNewCurrentSentence()
    }

    //TODO
    fun onGoToPrevious() {
        cardViewHistoryStack.push(currentSentence.value?.id)
        _currentSentence.value = getNewCurrentSentence()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}