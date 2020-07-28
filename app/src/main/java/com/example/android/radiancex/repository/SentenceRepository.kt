package com.example.android.radiancex.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.android.radiancex.database.Sentence
import com.example.android.radiancex.database.SentenceDao

class SentenceRepository(private val mSentenceDao: SentenceDao) {

    val allDiEntries: LiveData<List<Sentence>> = mSentenceDao.getAllDiEntries()

    val numberOfEntries: LiveData<Int> = mSentenceDao.getNumberOfEntries()

    fun findDiEntryById(id: Int?): LiveData<Sentence> {
        return mSentenceDao.findDiEntryById(id)
    }

    @WorkerThread
    suspend fun deleteAllEntries() {
        mSentenceDao.deleteAll()
    }

    @WorkerThread
    suspend fun insert(entry: Sentence) {
        mSentenceDao.insert(entry)
    }
}