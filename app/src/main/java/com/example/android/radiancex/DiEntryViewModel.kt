package com.example.android.radiancex

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class DiEntryViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mRepository: DiEntryRepository
    val allEntries: LiveData<List<DiEntry>>

    val allEntriesSynchronous: List<DiEntry>
        get() = mRepository.allDiEntriesSynchronous

    fun findDiEntryById(id: String?): LiveData<DiEntry> {
        return mRepository.findDiEntryById(id)
    }

    fun findDiEntryByIdSynchronous(id: String?): DiEntry {
        return mRepository.findDiEntryByIdSynchronous(id)
    }

    val numberOfEntriesSynchronous: Int
        get() = mRepository.numberOfEntriesSynchronous

    fun insert(sentence: DiEntry?) {
        mRepository.insert(sentence)
    }

    fun deleteAllSentences() {
        mRepository.deleteAllEntries()
    }

    init {
        mRepository = DiEntryRepository(application)
        allEntries = mRepository.allDiEntries
    }
}