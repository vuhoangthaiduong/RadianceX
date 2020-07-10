package com.example.android.radiancex

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.android.radiancex.database.DiEntry
import com.example.android.radiancex.database.DiEntryDao

class DiEntryRepository(private val diEntryDao: DiEntryDao) {

    val allDiEntries: LiveData<List<DiEntry>> = diEntryDao.getAllDiEntries()

    val numberOfEntries: LiveData<Int> = diEntryDao.getNumberOfEntries()

    val allDiEntriesSynchronous: List<DiEntry>
        get() = diEntryDao.getAllDiEntriesSynchronous()

    val numberOfEntriesSynchronous: Int
        get() = diEntryDao.getNumberOfEntriesSynchronous()

    fun findDiEntryById(id: Int?): LiveData<DiEntry> {
        return diEntryDao.findDiEntryById(id)
    }

    fun findDiEntryByIdSynchronous(id: Int): DiEntry {
        return diEntryDao.findDiEntryByIdSynchronous(id)
    }

     fun deleteAllEntries() {
        diEntryDao.deleteAll()
    }

    @WorkerThread
    suspend fun insert(entry: DiEntry) {
        diEntryDao.insert(entry)
    }
}