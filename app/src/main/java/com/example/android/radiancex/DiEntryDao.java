package com.example.android.radiancex;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiEntryDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DiEntry entry);

    @Query("DELETE FROM dientries")
    void deleteAll();

    /*
     * LiveData should be chosen for most use cases as running on the main thread will result in the error described on the other method
     */
    @Query("SELECT * FROM dientries")
    LiveData<List<DiEntry>> getAllDiEntries();

    /*
     * If you attempt to call this method on the main thread, you will receive the following error:
     *
     * Caused by: java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long periods of time.
     *  at android.arch.persistence.room.RoomDatabase.assertNotMainThread(AppDatabase.java:XXX)
     *  at android.arch.persistence.room.RoomDatabase.query(AppDatabase.java:XXX)
     *
     */
    @Query("SELECT * FROM dientries")
    List<DiEntry> getAllDiEntriesSynchronous();

    @Query("SELECT * FROM dientries WHERE id = :id")
    LiveData<DiEntry> findDiEntryById(String id);

    @Query("SELECT * FROM dientries WHERE id = :id")
    DiEntry findDiEntryByIdSynchronous(String id);

    @Query("SELECT COUNT(*) FROM dientries")
    int getNumberOfEntriesSynchronous();

}