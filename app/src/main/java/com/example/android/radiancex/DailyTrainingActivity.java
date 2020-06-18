package com.example.android.radiancex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DailyTrainingActivity extends AppCompatActivity {

    TextView jaSentence;
    TextView translation;
    TextView hint;
    TextView id;
    TextView totalNumberOfCards;
    Button btnLoadFile;
    Button btnGetNewCollection;
    Button btnNextWord;
    Switch switchShowJA;
    Switch switchShowTranslation;
    Switch switchShowHint;

    DiEntryViewModel mDiEntryViewModel;

    private ArrayList<DiEntry> currentDeck;
    private DiEntry currentSentence;
    private int entryCount;

    final int DECK_SIZE = 20;
    final int ID_FIELD_CODE = 0;
    final int JAPANESE_FIELD_CODE = 1;
    final int VIETNAMESE_FIELD_CODE = 2;
    final int NOTE_FIELD_CODE = 3;


    static {
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);

        getSupportActionBar().setTitle(R.string.daily_training);

        jaSentence = findViewById(R.id.jaSentence);
        translation = findViewById(R.id.translation);
        hint = findViewById(R.id.hint);
        id = findViewById(R.id.id);
        totalNumberOfCards = findViewById(R.id.totalNumberOfCards);
        btnLoadFile = findViewById(R.id.loadFile);
        btnGetNewCollection = findViewById(R.id.getNewCollection);
        btnNextWord = findViewById(R.id.nextWord);
        switchShowJA = findViewById(R.id.showJAswitch);
        switchShowTranslation = findViewById(R.id.showTranslationSwitch);
        switchShowHint = findViewById(R.id.showHintSwitch);

        mDiEntryViewModel = new ViewModelProvider(this).get(DiEntryViewModel.class);

//        entryCount = mDiEntryViewModel.getNumberOfEntries();

//        initializeData();

//        mDiEntryViewModel.getAllEntries().observe(this, new Observer<List<DiEntry>>() {
//            @Override
//            public void onChanged(@Nullable final List<DiEntry> sentences) {
//                // Update the cached copy of the sentences in the adapter.
//                entryCount = mDiEntryViewModel.getAllEntriesSynchronous().size();
//                totalNumberOfCards.setText(entryCount);
//            }
//        });

        btnLoadFile.setOnClickListener(v -> {
            Log.d("a------------------", "yay");
            Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show();
            new ProcessPopulateDatabaseInBackground().execute();
//            totalNumberOfCards.setText(entryCount);
            btnLoadFile.setEnabled(false);
            btnGetNewCollection.setEnabled(true);
            btnNextWord.setEnabled(true);
        });

        btnGetNewCollection.setOnClickListener(v -> {
            generateNewDeck();
            goToNextWord();
        });

        btnNextWord.setOnClickListener(v -> goToNextWord());

        switchShowJA.setOnClickListener(v -> {
            if (currentSentence != null) {
                jaSentence.setText((switchShowJA.isChecked()) ? currentSentence.getJpn() : "");
            }
        });

        switchShowTranslation.setOnClickListener(v -> {
            if (currentSentence != null) {
                translation.setText((switchShowTranslation.isChecked()) ? currentSentence.getVie() : "");
            }
        });

        switchShowHint.setOnClickListener(v -> {
            if (currentSentence != null) {
                hint.setText((switchShowHint.isChecked()) ? currentSentence.getMeaning() : "");
            }
        });

    }

//    private void initializeData() {
//        if (mDiEntryViewModel.getAllEntriesSynchronous().isEmpty()) {
//            try {
//                populateDatabaseFromFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Toast.makeText(this, "Populated database from file", Toast.LENGTH_SHORT).show();
//        }
//        generateNewDeck();
//    }


//    public void populateDatabaseFromFile() throws IOException {
//        FileReader fileReader = new FileReader("BST Câu.tsv");
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        String line;
//        String[] fields;
//        String id;
//        String japanese;
//        String meaning;
//        String english;
//        String vietnamese;
//        String note;
//        while ((line = bufferedReader.readLine()) != null) {
//            fields = line.split("\t");
//            id = fields[ID_FIELD_CODE];
//            japanese = fields[JAPANESE_FIELD_CODE];
//            vietnamese = fields[VIETNAMESE_FIELD_CODE];
//            note = fields[NOTE_FIELD_CODE];
//            mDiEntryViewModel.insert(new DiEntry(id, japanese, "", "", vietnamese, note));
//        }
//        bufferedReader.close();
//        fileReader.close();
//        Log.e("populate database", "it ran");
//    }

    private class ProcessPopulateDatabaseInBackground extends AsyncTask<Integer, Integer, Integer> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(DailyTrainingActivity.this);
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            try (FileReader fileReader = new FileReader("BST Câu.tsv")) {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                String[] fields;
                String id;
                String japanese;
                String meaning;
                String english;
                String vietnamese;
                String note;
                while ((line = bufferedReader.readLine()) != null) {
                    fields = line.split("\t");
                    id = fields[ID_FIELD_CODE];
                    japanese = fields[JAPANESE_FIELD_CODE];
                    vietnamese = fields[VIETNAMESE_FIELD_CODE];
                    note = fields[NOTE_FIELD_CODE];
                    mDiEntryViewModel.insert(new DiEntry(id, japanese, "", "", vietnamese, note));
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            progressDialog.dismiss();
        }
    }


    public void generateNewDeck() {
        currentDeck = new ArrayList<DiEntry>();
        for (int i = 0; i < DECK_SIZE; i++) {
            currentDeck.add(mDiEntryViewModel.findDiEntryByIdSynchronous((int) (Math.random() * entryCount) + ""));
        }
    }

    public void goToNextWord() {
        currentSentence = currentDeck.get((int) (Math.random() * currentDeck.size()));
        jaSentence.setText((switchShowJA.isChecked()) ? currentSentence.getJpn() : "");
        translation.setText((switchShowTranslation.isChecked()) ? currentSentence.getVie() : "");
        hint.setText((switchShowHint.isChecked()) ? currentSentence.getMeaning() : "");
        id.setText(currentSentence.getId());
    }


}