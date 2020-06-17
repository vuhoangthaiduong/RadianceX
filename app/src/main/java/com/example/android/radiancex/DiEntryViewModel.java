package com.example.android.radiancex;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DiEntryViewModel extends AndroidViewModel {

    private DiEntryRepository mRepository;

    private LiveData<List<DiEntry>> mAllEntries;

    public DiEntryViewModel(Application application) {
        super(application);
        mRepository = new DiEntryRepository(application);
        mAllEntries = mRepository.getAllEntries();
    }

    LiveData<List<DiEntry>> getAllEntries() {
        return mAllEntries;
    }


    public void insert(DiEntry sentence) {
        mRepository.insert(sentence);
    }

    public void deleteAllSentences() {
        mRepository.deleteAllEntries();
    }
}