package com.example.android.radiancex;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<DiEntry> myDataset;
    private DiEntryViewModel mDiEntryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        getSupportActionBar().setTitle("Browse");

        recyclerView = (RecyclerView) findViewById(R.id.entry_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //
        mDiEntryViewModel = new ViewModelProvider(this).get(DiEntryViewModel.class);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        String[] sampleStrings = new String[4];

        //Test code
        String jpn;
        String eng;
        String mea;
        String vie;
        sampleStrings[0] = "Lorem ipsum dolor sit amet";
        sampleStrings[1] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        sampleStrings[2] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt";
        sampleStrings[3] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt, ut labore et dolore magna aliqua";
//        for (int i = 1; i <= 100; i++) {
//            jpn = sampleStrings[(int)(Math.random() * 4)];
//            eng = sampleStrings[(int)(Math.random() * 4)];
//            mea = sampleStrings[(int)(Math.random() * 4)];
//            vie = sampleStrings[(int)(Math.random() * 4)];
//            myDataset.add(new DiEntry(i + "", jpn, mea, eng, vie, ""));
//        }
        myDataset.addAll(mDiEntryViewModel.getAllEntriesSynchronous());

        mAdapter = new EntryRecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
    }
}