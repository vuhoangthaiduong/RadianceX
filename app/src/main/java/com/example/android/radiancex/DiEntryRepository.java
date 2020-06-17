package com.example.android.radiancex;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class DiEntryRepository {

    private DiEntryDao mDiEntryDao;
    private LiveData<List<DiEntry>> mAllEntries;
    private List<DiEntry> mAllEntriesSynchronous;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public DiEntryRepository(Application application) {
        DiEntryRoomDatabase db = DiEntryRoomDatabase.getDatabase(application);
        mDiEntryDao = db.dictionaryEntryDao();
        mAllEntries = mDiEntryDao.getAllDiEntries();
        mAllEntriesSynchronous = new ArrayList<>();
        mAllEntriesSynchronous.add(new DiEntry("", "", "", "", "", ""));
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<DiEntry>> getAllDiEntries() {
        return mAllEntries;
    }

    List<DiEntry> getAllDiEntriesSynchronous() {
        return mAllEntriesSynchronous;
    }

    LiveData<DiEntry> findDiEntryById(String id) {
        return mDiEntryDao.findDiEntryById(id);
    }

    DiEntry findDiEntryByIdSynchronous(String id) {
        return mDiEntryDao.findDiEntryByIdSynchronous(id);
    }

    int getNumberOfEntries() {
        return mDiEntryDao.getNumberOfEntries();
    }


    void deleteAllEntries() {
        mDiEntryDao.deleteAll();
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final DiEntry entry) {
        DiEntryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDiEntryDao.insert(entry);
        });
    }
}