package com.example.android.radiancex

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.android.radiancex.DiEntryRoomDatabase.Companion.getDatabase

class DiEntryRepository(private val diEntryDao: DiEntryDao) {

    val allDiEntries: LiveData<List<DiEntry>> = diEntryDao.getAllDiEntries()

    val allDiEntriesSynchronous: List<DiEntry>
        get() = diEntryDao.getAllDiEntriesSynchronous()

    fun findDiEntryById(id: String?): LiveData<DiEntry> {
        return diEntryDao.findDiEntryById(id)
    }

    fun findDiEntryByIdSynchronous(id: String): DiEntry {
        return diEntryDao.findDiEntryByIdSynchronous(id)
    }

    val numberOfEntriesSynchronous: Int
        get() = diEntryDao.getNumberOfEntriesSynchronous()

    suspend fun deleteAllEntries() {
        diEntryDao.deleteAll()
    }

    suspend fun insert(entry: DiEntry) {
        diEntryDao.insert(entry)
    }
}