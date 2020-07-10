package com.example.android.radiancex.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiEntryDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: DiEntry)

    @Query("DELETE FROM dientries")
    fun deleteAll()

    @Query("SELECT * FROM dientries")
    fun getAllDiEntries(): LiveData<List<DiEntry>>

    @Query("SELECT * FROM dientries WHERE id = :id")
    fun findDiEntryById(id: Int?): LiveData<DiEntry>

    @Query("SELECT COUNT(*) FROM dientries")
    fun getNumberOfEntries(): LiveData<Int>

    @Query("SELECT * FROM dientries")
    fun getAllDiEntriesSynchronous(): List<DiEntry>

    @Query("SELECT * FROM dientries WHERE id = :id")
    fun findDiEntryByIdSynchronous(id: Int): DiEntry

    @Query("SELECT COUNT(*) FROM dientries")
    fun getNumberOfEntriesSynchronous(): Int
}