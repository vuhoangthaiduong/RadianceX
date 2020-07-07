package com.example.android.radiancex.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dientries")
data class DiEntry(
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
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long = 0L

    @ColumnInfo(name = "favourite")
    var isFavourite: Boolean = false
}