package com.example.android.radiancex

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.android.radiancex.DiEntryRoomDatabase.Companion.getDatabase

class DiEntryRepository(private val diEntryDao: DiEntryDao) {

    val allDiEntries: LiveData<List<DiEntry>> = diEntryDao.getAllDiEntries()

    val numberOfEntries: LiveData<Int> = diEntryDao.getNumberOfEntries()

    val allDiEntriesSynchronous: List<DiEntry>
        get() = diEntryDao.getAllDiEntriesSynchronous()

    val numberOfEntriesSynchronous: Int
        get() = diEntryDao.getNumberOfEntriesSynchronous()

    fun findDiEntryById(id: String?): LiveData<DiEntry> {
        return diEntryDao.findDiEntryById(id)
    }

    fun findDiEntryByIdSynchronous(id: String): DiEntry {
        return diEntryDao.findDiEntryByIdSynchronous(id)
    }

    suspend fun deleteAllEntries() {
        diEntryDao.deleteAll()
    }

    suspend fun insert(entry: DiEntry) {
        diEntryDao.insert(entry)
    }
}