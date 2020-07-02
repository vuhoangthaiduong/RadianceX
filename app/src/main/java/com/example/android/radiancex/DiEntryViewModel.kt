package com.example.android.radiancex

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DiEntryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DiEntryRepository
    private var _currentDeck = MutableLiveData(ArrayList<DiEntry>())
    private val _currentSentence = MutableLiveData(DiEntry("", "", "", "", "", ""))

    var allEntries: LiveData<List<DiEntry>>
    var numberOfEntries: LiveData<Int>
    var currentDeck: LiveData<ArrayList<DiEntry>> = _currentDeck
    var currentSentence: LiveData<DiEntry> = _currentSentence


    init {
        val dientriesDao = DiEntryRoomDatabase.getDatabase(application, viewModelScope).dictionaryEntryDao()
        repository = DiEntryRepository(dientriesDao)
        allEntries = repository.allDiEntries
        numberOfEntries = repository.numberOfEntries
        currentDeck = getNewDeck()
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
        val tempDeck: MutableLiveData<ArrayList<DiEntry>> = MutableLiveData(ArrayList<DiEntry>())
        for (i in 0..19) {
            tempDeck.value?.set(i, repository.findDiEntryByIdSynchronous((Random().nextInt(numberOfEntries.value!!)).toString()))
        }
        return tempDeck
    }

    fun onNext() {
        _currentSentence.postValue(_currentDeck.value?.get(Random().nextInt(_currentDeck.value!!.size)))
    }

    fun onGetNewDeck() {
        _currentDeck.value?.clear()
        _currentDeck.postValue(getNewDeck().value)
        onNext()
    }
}