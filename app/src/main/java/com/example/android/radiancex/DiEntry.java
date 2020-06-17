package com.example.android.radiancex;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "DiEntry")
public class DiEntry {
    @PrimaryKey
    @NonNull
    private String sid;
    @ColumnInfo(name = "ja")
    private String jpn;
    @ColumnInfo(name = "eng")
    private String eng;
    @ColumnInfo(name = "meaning")
    private String meaning;
    @ColumnInfo(name = "vie")
    private String vie;
    @ColumnInfo(name = "favourite")
    private boolean favourite;

    @Ignore
    public DiEntry() {

    }

    @Ignore
    public DiEntry(String id) {
        this.sid = id;
    }

    public DiEntry(String id, String jpn, String eng, String meaning, String vie) {
        this.sid = id;
        this.jpn = jpn;
        this.eng = eng;
        this.meaning = meaning;
        this.vie = vie;
    }

    public DiEntry(String jpn, String eng, String meaning, String vie) {
        this.jpn = jpn;
        this.eng = eng;
        this.meaning = meaning;
        this.vie = vie;
    }

    public String getSid() {
        return this.sid;
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

    public void setSid(String sid) {
        this.sid = sid;
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


}
