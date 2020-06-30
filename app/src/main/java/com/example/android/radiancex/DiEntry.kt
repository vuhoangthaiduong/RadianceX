package com.example.android.radiancex

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "dientries")
class DiEntry {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = null

    @ColumnInfo(name = "ja")
    var jpn: String? = null

    @ColumnInfo(name = "meaning")
    var meaning: String? = null

    @ColumnInfo(name = "eng")
    var eng: String? = null

    @ColumnInfo(name = "vie")
    var vie: String? = null

    @ColumnInfo(name = "note")
    var note: String? = null

    @ColumnInfo(name = "favourite")
    var isFavourite = false

    @Ignore
    constructor() {
    }

    @Ignore
    constructor(id: String) {
        this.id = id
    }

    constructor(id: String, jpn: String?, meaning: String?, eng: String?, vie: String?, note: String?) {
        this.id = id
        this.jpn = jpn
        this.meaning = meaning
        this.eng = eng
        this.vie = vie
        this.note = note
    }

}