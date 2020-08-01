package com.example.android.radiancex.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.radiancex.model.Sentence

@Dao
interface SentenceDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Sentence)

    @Query("DELETE FROM sentences")
    suspend fun deleteAll()

    @Query("SELECT * FROM sentences")
     fun getAllDiEntries(): LiveData<List<Sentence>>

    @Query("SELECT COUNT(*) FROM sentences")
     fun getNumberOfEntries(): LiveData<Int>

    @Query("SELECT * FROM sentences WHERE id = :id")
     fun findDiEntryById(id: Int?): LiveData<Sentence>
}