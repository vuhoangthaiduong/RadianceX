package com.example.android.radiancex.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dientries")
data class DiEntry(
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "jpn")
        var jpn: String? = null,

        @ColumnInfo(name = "meaning")
        var meaning: String? = null,

        @ColumnInfo(name = "eng")
        var eng: String? = null,

        @ColumnInfo(name = "vie")
        var vie: String? = null,

        @ColumnInfo(name = "note")
        var note: String? = null
) {
    @ColumnInfo(name = "favourite")
    var isFavourite: Boolean = false
}