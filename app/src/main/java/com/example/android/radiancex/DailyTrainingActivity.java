package com.example.android.radiancex;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
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

    private ArrayList<DiEntry> fullList = new ArrayList<>();
    private ArrayList<DiEntry> shortList;
    private DiEntry currentSentence;


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

//        mDiEntryViewModel.getAllEntries().observe(this, new Observer<List<DiEntry>>() {
//            @Override
//            public void onChanged(@Nullable final List<DiEntry> sentences) {
//                // Update the cached copy of the sentences in the adapter.
//                totalNumberOfCards.setText((int) (mDiEntryViewModel.getAllEntries().getValue().size()) + "");
//            }
//        });

        btnLoadFile.setOnClickListener(v -> {
            if (fullList.isEmpty()) {
                readExcelFileFromAssets();
                getNewCollection();
                goToNextWord();
            }
            totalNumberOfCards.setText(fullList.size() + "");
            btnLoadFile.setEnabled(false);
            btnGetNewCollection.setEnabled(true);
            btnNextWord.setEnabled(true);
        });

        btnGetNewCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewCollection();
                goToNextWord();
            }
        });

        btnNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextWord();
            }
        });

        switchShowJA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSentence != null) {
                    jaSentence.setText((switchShowJA.isChecked()) ? currentSentence.getJpn() : "");
                }
            }
        });

        switchShowTranslation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSentence != null) {
                    translation.setText((switchShowTranslation.isChecked()) ? currentSentence.getVie() : "");
                }
            }
        });

        switchShowHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSentence != null) {
                    hint.setText((switchShowHint.isChecked()) ? currentSentence.getMeaning() : "");
                }
            }
        });

    }


    public void readExcelFileFromAssets() {
        try {
            InputStream myInput;
            // initialize asset manager
            AssetManager assetManager = getAssets();
            //  open excel sheet
            myInput = assetManager.open("BST Câu.xlsx");
            OPCPackage pkg = OPCPackage.open(myInput);
            XSSFWorkbook sourceWb = new XSSFWorkbook(pkg);
            // Get the first sheet from workbook
            XSSFSheet sheet = sourceWb.getSheetAt(0);
            // We now need something to iterate through the cells.
            int rowCount = 1;
            XSSFRow row = sheet.getRow(rowCount);
            while (!row.getCell(1).getStringCellValue().isEmpty()) {
                String id = row.getCell(0).getNumericCellValue() + "";
                String jaSentence = row.getCell(1).getStringCellValue();
                String translation = row.getCell(2).getStringCellValue();
                String hint = row.getCell(3).getStringCellValue();
                fullList.add(new DiEntry(id + "", jaSentence, "", translation, hint));
                row = sheet.getRow(++rowCount);
            }
        } catch (Exception e) {
            Log.e("aaa", "error " + e.toString());
        }
    }

    public void getNewCollection() {
        shortList = new ArrayList<DiEntry>();
        for (int i = 0; i < 20; i++) {
            shortList.add(fullList.get((int) (fullList.size() * Math.random())));
        }
    }

    public void goToNextWord() {
        currentSentence = shortList.get((int) (Math.random() * shortList.size()));
        jaSentence.setText((switchShowJA.isChecked()) ? currentSentence.getJpn() : "");
        translation.setText((switchShowTranslation.isChecked()) ? currentSentence.getVie() : "");
        hint.setText((switchShowHint.isChecked()) ? currentSentence.getMeaning() : "");
        id.setText(currentSentence.getSid());
    }


}