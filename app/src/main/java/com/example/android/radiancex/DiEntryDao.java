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

    @Query("DELETE FROM DiEntry")
    void deleteAll();

    @Query("SELECT * FROM DiEntry")
    LiveData<List<DiEntry>> getAll();

}