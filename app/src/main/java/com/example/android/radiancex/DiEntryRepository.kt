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

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(entry: DiEntry?) {
        diEntryDao.insert(entry)
    } //    void insert(final DiEntry entry) {

    //        mDiEntryDao.insert(entry);
    //    }
    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
}