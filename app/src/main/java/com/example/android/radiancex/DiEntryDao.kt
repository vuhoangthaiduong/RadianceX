package com.example.android.radiancex

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiEntryDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entry: DiEntry?)

    @Query("DELETE FROM dientries")
    fun deleteAll()

    /*
     * LiveData should be chosen for most use cases as running on the main thread will result in the error described on the other method
     */
    @get:Query("SELECT * FROM dientries")
    val allDiEntries: LiveData<List<DiEntry?>?>?

    /*
     * If you attempt to call this method on the main thread, you will receive the following error:
     *
     * Caused by: java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long periods of time.
     *  at android.arch.persistence.room.RoomDatabase.assertNotMainThread(AppDatabase.java:XXX)
     *  at android.arch.persistence.room.RoomDatabase.query(AppDatabase.java:XXX)
     *
     */
    @get:Query("SELECT * FROM dientries")
    val allDiEntriesSynchronous: List<DiEntry?>?

    @Query("SELECT * FROM dientries WHERE id = :id")
    fun findDiEntryById(id: String?): LiveData<DiEntry?>?

    @Query("SELECT * FROM dientries WHERE id = :id")
    fun findDiEntryByIdSynchronous(id: String?): DiEntry?

    @get:Query("SELECT COUNT(*) FROM dientries")
    val numberOfEntriesSynchronous: Int
}