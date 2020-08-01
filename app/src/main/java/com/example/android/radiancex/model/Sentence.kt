package com.example.android.radiancex.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentences")
data class Sentence(
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "jpn")
        var japanese: String? = null,

        @ColumnInfo(name = "eng")
        var english: String? = null,

        @ColumnInfo(name = "vie")
        var vietnamese: String? = null,

        @ColumnInfo(name = "note")
        var note: String? = null
) {
    @ColumnInfo(name = "favourite")
    var isImportant: Boolean = false
}