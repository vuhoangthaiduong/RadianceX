package com.example.android.radiancex;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "dientries")
public class DiEntry {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "ja")
    private String jpn;
    @ColumnInfo(name = "meaning")
    private String meaning;
    @ColumnInfo(name = "eng")
    private String eng;
    @ColumnInfo(name = "vie")
    private String vie;
    @ColumnInfo(name = "note")
    private String note;
    @ColumnInfo(name = "favourite")
    private boolean favourite;

    @Ignore
    public DiEntry() {

    }

    @Ignore
    public DiEntry(String id) {
        this.id = id;
    }

    public DiEntry(String id, String jpn, String meaning, String eng, String vie, String note) {
        this.id = id;
        this.jpn = jpn;
        this.meaning = meaning;
        this.eng = eng;
        this.vie = vie;
        this.note = note;
    }

    public String getId() {
        return this.id;
    }

    public String getJpn() {
        return this.jpn;
    }

    public String getVie() {
        return this.vie;
    }

    public String getEng() {
        return this.eng;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public boolean isFavourite() {
        return this.favourite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJpn(String jpn) {
        this.jpn = jpn;
    }

    public void setVie(String vie) {
        this.vie = vie;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
