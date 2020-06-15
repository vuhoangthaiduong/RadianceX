package com.example.android.radiancex;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DictionaryEntry.class}, version = 1, exportSchema = false)
abstract class DictionaryEntryRoomDatabase extends RoomDatabase {

    public abstract DiEntryDao dictionaryEntryDao();

    private static volatile DictionaryEntryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DictionaryEntryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DictionaryEntryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DictionaryEntryRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}