package com.example.android.radiancex.database

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.radiancex.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = arrayOf(Sentence::class), version = 3, exportSchema = false)
internal abstract class SentenceRoomDatabase : RoomDatabase() {
    abstract fun sentenceDao(): SentenceDao

    companion object {
        @Volatile
        private var INSTANCE: SentenceRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope, resources: Resources): SentenceRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SentenceRoomDatabase::class.java,
                        "sentences"
                ).fallbackToDestructiveMigration()
                        .addCallback(SentenceRoomDatabaseCallback(scope, resources))
//                        .createFromAsset("dictionary.db")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }


    private class SentenceRoomDatabaseCallback(
            private val scope: CoroutineScope,
            private val resources: Resources
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val sentenceDao = database.sentenceDao()
                    withContext(Dispatchers.Default) {
//                        sentenceDao.deleteAll()
                        prePopulateDatabase(sentenceDao) // 3
                    }
                }
            }
        }

        private suspend fun prePopulateDatabase(sentenceDao: SentenceDao) {
            // 1
            val jsonString = resources.openRawResource(R.raw.sentence_collection_mockup).bufferedReader().use {
                it.readText()
            }
            // 2
            val typeToken = object : TypeToken<List<Sentence>>() {}.type
            val sentences = Gson().fromJson<List<Sentence>>(jsonString, typeToken)
            // 3
            for (sentence in sentences) {
                sentenceDao.insert(sentence)
            }

        }

    }
}
