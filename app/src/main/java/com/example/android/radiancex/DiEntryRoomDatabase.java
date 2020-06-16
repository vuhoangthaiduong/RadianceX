package com.example.android.radiancex;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DiEntry.class}, version = 1, exportSchema = false)
abstract class DiEntryRoomDatabase extends RoomDatabase {

    public abstract DiEntryDao dictionaryEntryDao();

    private static volatile DiEntryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DiEntryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DiEntryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DiEntryRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}