package com.example.android.radiancex

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dientries")
class DiEntry {
    @PrimaryKey
    @ColumnInfo(name = "id")
    lateinit var id: String

    @ColumnInfo(name = "jpn")
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

    constructor()

    constructor(_id: String, _jpn: String?, _meaning: String?, _eng: String?, _vie: String?, _note: String?) {
        this.id = _id
        this.jpn = _jpn
        this.meaning = _meaning
        this.eng = _eng
        this.vie = _vie
        this.note = _note
    }

}