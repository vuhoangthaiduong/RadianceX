package com.example.android.radiancex

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DiEntryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DiEntryRepository
    private var _currentDeck = MutableLiveData(ArrayList<DiEntry>())
    private var _currentSentence = MutableLiveData(DiEntry("", "", "", "", "", ""))
    var currentDeck: LiveData<ArrayList<DiEntry>>
        get() = _currentDeck
    var currentSentence: LiveData<DiEntry>
        get() = _currentSentence

    var allEntries: LiveData<List<DiEntry>>
    var numberOfEntries: LiveData<Int>

    init {
        val dientriesDao = DiEntryRoomDatabase.getDatabase(application, viewModelScope).dictionaryEntryDao()
        repository = DiEntryRepository(dientriesDao)
        allEntries = repository.allDiEntries
        numberOfEntries = repository.numberOfEntries
        _currentDeck = getNewDeck()
        Log.d("tempDeckLiveData size: ", _currentDeck.value!!.size.toString())
        _currentSentence = getNewCurrentSentence()
        currentDeck = _currentDeck
        currentSentence = _currentSentence
    }

    val allEntriesSynchronous: List<DiEntry?>?
        get() = repository.allDiEntriesSynchronous

    fun findDiEntryById(id: String?): LiveData<DiEntry> {
        return repository.findDiEntryById(id)
    }

    fun findDiEntryByIdSynchronous(id: String): DiEntry {
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

    private fun getNewDeck(): MutableLiveData<ArrayList<DiEntry>> {
        val deckSize: Int = 20
        val tempDeck: ArrayList<DiEntry> = ArrayList<DiEntry>(deckSize)
        val tempDeckLiveData: MutableLiveData<ArrayList<DiEntry>> = MutableLiveData(ArrayList<DiEntry>())
        val sampleStrings = arrayOfNulls<String>(4)
        var jpn: String?
        var eng: String?
        var mea: String?
        var vie: String?
        sampleStrings[0] = "Lorem ipsum dolor sit amet"
        sampleStrings[1] = "iouKU3bqlG0m6Vm5X7rp9IkxRFttd8iHiQmRBiMYo6HnD95pGb"
        sampleStrings[2] = "AEzFGnXmdHJzmZH3KXh81oGQQepK7ppQgHoNbaWmAQ92PxCImx0aIJb7Jafax5pXK9fLtC"
        sampleStrings[3] = "qd3RbIAcn3qn2YsY6Y18QiiOqpdCQIKM9fF3gPRsxW14qfuutuVYZjwz50LfqpzXQOdZYqvSqWPR3Dr1Q284EJDMGAFiDOhArvOG\n"
        for (i in 0 until deckSize) {
            jpn = sampleStrings[(Math.random() * 4).toInt()]
            eng = sampleStrings[(Math.random() * 4).toInt()]
            mea = sampleStrings[(Math.random() * 4).toInt()]
            vie = sampleStrings[(Math.random() * 4).toInt()]
            tempDeck.add(DiEntry(i.toString() + "a", jpn, mea, eng, vie, ""))
        }
        tempDeckLiveData.value = tempDeck
        Log.d("tempDeckLiveData size: ", tempDeckLiveData.value!!.size.toString())
        return tempDeckLiveData
    }

    private fun getNewCurrentSentence(): MutableLiveData<DiEntry> {
        val tempCurrentSentence: MutableLiveData<DiEntry> = MutableLiveData(DiEntry())
        _currentSentence.value = (_currentDeck.value?.get(Random().nextInt(_currentDeck.value!!.size)))
        return tempCurrentSentence
    }

    fun onGoToNext() {
        _currentSentence.postValue(_currentDeck.value?.get(Random().nextInt(_currentDeck.value!!.size)))
    }

    fun onGetNewDeck() {
        _currentDeck.value?.clear()
        _currentDeck.postValue(getNewDeck().value)
        onGoToNext()
    }
}