package com.example.android.radiancex.screen.browse

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.android.radiancex.R
import com.example.android.radiancex.database.Sentence
import com.example.android.radiancex.databinding.ActivityBrowseBinding

class BrowseActivity : AppCompatActivity() {
    @SuppressLint("NewApi")

    lateinit var binding: ActivityBrowseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_browse)
        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "Browse"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val mAdapter = EntryRecyclerViewAdapter()
        mAdapter.setEntries(generateRandomDataset())
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.apply {
            entryRecyclerView.setHasFixedSize(true)
            entryRecyclerView.adapter = mAdapter
        }
    }

    private fun generateRandomDataset(): ArrayList<Sentence> {
        val sampleStrings = arrayOfNulls<String>(4)
        val myDataset = ArrayList<Sentence>()
        var jpn: String?
        var eng: String?
        var mea: String?
        var vie: String?
        sampleStrings[0] = "Lorem ipsum dolor sit amet"
        sampleStrings[1] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
        sampleStrings[2] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt"
        sampleStrings[3] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt, ut labore et dolore magna aliqua"
        for (i in 1..100) {
            jpn = sampleStrings[(Math.random() * 4).toInt()]
            eng = sampleStrings[(Math.random() * 4).toInt()]
            mea = sampleStrings[(Math.random() * 4).toInt()]
            vie = sampleStrings[(Math.random() * 4).toInt()]
            myDataset.add(Sentence(id = 0, jpn = jpn, eng = eng, vie = vie, note = ""))
        }
        return myDataset
    }
}