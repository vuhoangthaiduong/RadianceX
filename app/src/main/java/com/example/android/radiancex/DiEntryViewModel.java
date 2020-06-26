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
        mAllEntries = mRepository.getAllDiEntries();
    }

    LiveData<List<DiEntry>> getAllEntries() {
        return mAllEntries;
    }

    List<DiEntry> getAllEntriesSynchronous() {
        return mRepository.getAllDiEntriesSynchronous();
    }

    LiveData<DiEntry> findDiEntryById(String id) {
        return mRepository.findDiEntryById(id);
    }

    DiEntry findDiEntryByIdSynchronous(String id) {
        return mRepository.findDiEntryByIdSynchronous(id);
    }

    int getNumberOfEntriesSynchronous() {
        return mRepository.getNumberOfEntriesSynchronous();
    }

    public void insert(DiEntry sentence) {
        mRepository.insert(sentence);
    }

    public void deleteAllSentences() {
        mRepository.deleteAllEntries();
    }
}