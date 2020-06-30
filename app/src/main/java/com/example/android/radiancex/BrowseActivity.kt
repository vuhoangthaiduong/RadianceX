package com.example.android.radiancex

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BrowseActivity : AppCompatActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.setTitle("Browse")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val mAdapter = EntryRecyclerViewAdapter()
        val recyclerView = findViewById<View>(R.id.entry_recycler_view) as RecyclerView
        // use a linear layout manager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        val mDiEntryViewModel = ViewModelProvider(this).get(DiEntryViewModel::class.java)
        mDiEntryViewModel.allEntries.observe(this, Observer<List<DiEntry>> { diEntries: List<DiEntry> ->
            // Update the cached copy of the words in the adapter.
            mAdapter.setEntries(diEntries)
        })

        //Test code
//        myDataset = new ArrayList<>();
//        String[] sampleStrings = new String[4];
//        String jpn;
//        String eng;
//        String mea;
//        String vie;
//        sampleStrings[0] = "Lorem ipsum dolor sit amet";
//        sampleStrings[1] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
//        sampleStrings[2] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt";
//        sampleStrings[3] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt, ut labore et dolore magna aliqua";
//        for (int i = 1; i <= 100; i++) {
//            jpn = sampleStrings[(int)(Math.random() * 4)];
//            eng = sampleStrings[(int)(Math.random() * 4)];
//            mea = sampleStrings[(int)(Math.random() * 4)];
//            vie = sampleStrings[(int)(Math.random() * 4)];
//            myDataset.add(new DiEntry(i + "", jpn, mea, eng, vie, ""));
//        }
//        myDataset.addAll(mDiEntryViewModel.getAllEntriesSynchronous());
    }
}