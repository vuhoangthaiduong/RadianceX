package com.example.android.radiancex.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.radiancex.database.DiEntry

@Dao
interface DiEntryDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: DiEntry)

    @Query("DELETE FROM dientries")
    fun deleteAll()

    @Query("SELECT * FROM dientries")
    fun getAllDiEntries(): LiveData<List<DiEntry>>

    @Query("SELECT * FROM dientries WHERE id = :id")
    fun findDiEntryById(id: Long?): LiveData<DiEntry>

    @Query("SELECT COUNT(*) FROM dientries")
    fun getNumberOfEntries(): LiveData<Int>

    @Query("SELECT * FROM dientries")
    fun getAllDiEntriesSynchronous(): List<DiEntry>

    @Query("SELECT * FROM dientries WHERE id = :id")
    fun findDiEntryByIdSynchronous(id: Long): DiEntry

    @Query("SELECT COUNT(*) FROM dientries")
    fun getNumberOfEntriesSynchronous(): Int
}