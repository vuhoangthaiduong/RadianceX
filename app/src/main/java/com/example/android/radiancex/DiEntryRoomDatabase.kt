package com.example.android.radiancex

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(entities = arrayOf(DiEntry::class), version = 2, exportSchema = false)
internal abstract class DiEntryRoomDatabase : RoomDatabase() {
    abstract fun dictionaryEntryDao(): DiEntryDao

    companion object {
        @Volatile
        private var INSTANCE: DiEntryRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): DiEntryRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DiEntryRoomDatabase::class.java,
                        "dientries"
                ).fallbackToDestructiveMigration()
                        .addCallback(DiEntryRoomDatabaseCallback(scope))
//                        .createFromAsset("dictionary.db")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }


    private class DiEntryRoomDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var dictionaryEntryDao = database.dictionaryEntryDao()

                    // Delete all content here.
                    dictionaryEntryDao.deleteAll()

                    // Add sample words.
                    val sampleStrings = arrayOfNulls<String>(4)
                    var jpn: String?
                    var eng: String?
                    var mea: String?
                    var vie: String?
                    sampleStrings[0] = "Lorem ipsum dolor sit amet"
                    sampleStrings[1] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
                    sampleStrings[2] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt"
                    sampleStrings[3] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt, ut labore et dolore magna aliqua"
                    for (i in 1..100) {
                        jpn = sampleStrings[(Math.random() * 4).toInt()]
                        eng = sampleStrings[(Math.random() * 4).toInt()]
                        mea = sampleStrings[(Math.random() * 4).toInt()]
                        vie = sampleStrings[(Math.random() * 4).toInt()]
                        dictionaryEntryDao.insert(DiEntry(i.toString() + "", jpn, mea, eng, vie, ""))
                    }
                }
            }
        }
    }
}