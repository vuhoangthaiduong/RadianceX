package com.example.android.radiancex

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.android.radiancex.DiEntryRoomDatabase.Companion.getDatabase

class DiEntryRepository(application: Application?) {
    private val mDiEntryDao: DiEntryDao

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allDiEntries: LiveData<List<DiEntry>>

    val allDiEntriesSynchronous: List<DiEntry>
        get() = mDiEntryDao.allDiEntriesSynchronous

    fun findDiEntryById(id: String?): LiveData<DiEntry> {
        return mDiEntryDao.findDiEntryById(id)
    }

    fun findDiEntryByIdSynchronous(id: String?): DiEntry {
        return mDiEntryDao.findDiEntryByIdSynchronous(id)
    }

    val numberOfEntriesSynchronous: Int
        get() = mDiEntryDao.numberOfEntriesSynchronous

    fun deleteAllEntries() {
        mDiEntryDao.deleteAll()
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(entry: DiEntry?) {
        mDiEntryDao.insert(entry)
    } //    void insert(final DiEntry entry) {

    //        mDiEntryDao.insert(entry);
    //    }
    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    init {
        val db = getDatabase(application!!)
        mDiEntryDao = db!!.dictionaryEntryDao()
        allDiEntries = mDiEntryDao.allDiEntries
    }
}