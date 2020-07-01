package com.example.android.radiancex

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiEntryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DiEntryRepository
    val allEntries: LiveData<List<DiEntry>>

    init {
        val dientriesDao = DiEntryRoomDatabase.getDatabase(application, viewModelScope).dictionaryEntryDao()
        repository = DiEntryRepository(dientriesDao)
        allEntries = repository.allDiEntries
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
}