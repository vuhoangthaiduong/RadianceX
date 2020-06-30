package com.example.android.radiancex

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [DiEntry::class], version = 1, exportSchema = false)
internal abstract class DiEntryRoomDatabase : RoomDatabase() {
    abstract fun dictionaryEntryDao(): DiEntryDao

    companion object {
        @Volatile
        private var INSTANCE: DiEntryRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        @JvmStatic
        fun getDatabase(context: Context): DiEntryRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(DiEntryRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                DiEntryRoomDatabase::class.java, "dientries")
                                .fallbackToDestructiveMigration()
                                .addCallback(sRoomDatabaseCallback)
                                .createFromAsset("dictionary.db")
                                .build()
                    }
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                databaseWriteExecutor.execute {
                    val dao = INSTANCE!!.dictionaryEntryDao()
                    dao.deleteAll()
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
                        dao.insert(DiEntry(i.toString() + "", jpn, mea, eng, vie, ""))
                    }
                }
            }
        }
    }
}