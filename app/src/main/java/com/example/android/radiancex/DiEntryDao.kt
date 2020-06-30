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
    suspend fun deleteAll()

    @Query("SELECT * FROM dientries")
    fun getAllDiEntries(): LiveData<List<DiEntry>>

    @Query("SELECT * FROM dientries WHERE id = :id")
    fun findDiEntryById(id: String?): LiveData<DiEntry>

    @Query("SELECT * FROM dientries")
    fun getAllDiEntriesSynchronous(): List<DiEntry>

    @Query("SELECT * FROM dientries WHERE id = :id")
    fun findDiEntryByIdSynchronous(id: String): DiEntry

    @Query("SELECT COUNT(*) FROM dientries")
    fun getNumberOfEntriesSynchronous(): Int
}