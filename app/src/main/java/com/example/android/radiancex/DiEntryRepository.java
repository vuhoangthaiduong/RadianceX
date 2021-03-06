package com.example.android.radiancex;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class DiEntryRepository {

    private DiEntryDao mDictionaryEntryDao;
    private LiveData<List<DiEntry>> mAllEntries;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    DiEntryRepository(Application application) {
        DiEntryRoomDatabase db = DiEntryRoomDatabase.getDatabase(application);
        mDictionaryEntryDao = db.dictionaryEntryDao();
        mAllEntries = mDictionaryEntryDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<DiEntry>> getAllWords() {
        return mAllEntries;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final DiEntry entry) {
        DiEntryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDictionaryEntryDao.insert(entry);
        });
    }
}