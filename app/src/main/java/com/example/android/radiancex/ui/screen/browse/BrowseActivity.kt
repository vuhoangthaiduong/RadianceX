package com.example.android.radiancex.ui.screen.browse

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.android.radiancex.R
import com.example.android.radiancex.model.Sentence
import com.example.android.radiancex.databinding.ActivityBrowseBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class BrowseActivity : AppCompatActivity() {
    @SuppressLint("NewApi")

    lateinit var binding: ActivityBrowseBinding
    private lateinit var fStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_browse)
        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "Browse"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val mAdapter = EntryRecyclerViewAdapter()
//        mAdapter.setEntries(generateRandomDataset())
        val mDataset = mutableListOf<Sentence>()

        binding.progressBar.visibility = View.VISIBLE

        fStore = FirebaseFirestore.getInstance()
        val sentencesRef: CollectionReference = fStore.collection("sentences")
        sentencesRef.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
            if (task.isSuccessful) {
                if (task.result!!.isEmpty) binding.tvNoItem.visibility = View.VISIBLE
                else {
                    for (document in task.result!!) {
                        val sentence = document.toObject(Sentence::class.java)
                        mDataset.add(sentence)
                        Log.i("Sentence:", "$sentence")
                    }
                }
                mAdapter.setEntries(mDataset)
                binding.progressBar.visibility = View.GONE
            }
        })

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
            myDataset.add(Sentence(id = 0, japanese = jpn, english = eng, vietnamese = vie, note = ""))
        }
        return myDataset
    }
}