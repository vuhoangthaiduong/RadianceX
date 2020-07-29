package com.example.android.radiancex

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.radiancex.database.Sentence
import com.example.android.radiancex.databinding.ActivityLearnBinding
import com.example.android.radiancex.model.SentenceViewModel
import java.io.IOException
import java.util.*

class LearningActivity() : AppCompatActivity() {
    lateinit var mSentenceViewModel: SentenceViewModel
    lateinit var progressDialog: ProgressDialog
    lateinit var handler: Handler
    private var currentDeck: ArrayList<Sentence>? = null
    private var currentSentence: Sentence? = null
    private var entryCount = 0

    private lateinit var binding: ActivityLearnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSentenceViewModel = ViewModelProvider(this).get(SentenceViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_learn)
        binding.viewmodel = mSentenceViewModel

        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "Learn"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        handler = Handler()
        currentDeck = ArrayList()
//        initializeData()

        binding.apply {
            btnLoadFile.setOnClickListener {
                btnLoadFile.isEnabled = false
                btnGetNewDeck.isEnabled = true
                btnNext.isEnabled = true
                try {
//                    populateDatabaseFromFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            btnGetNewDeck.setOnClickListener(View.OnClickListener {
//                mDiEntryViewModel.onGetNewDeck()
                Toast.makeText(applicationContext, "New deck generated", Toast.LENGTH_SHORT).show()
            })

            btnNext.setOnClickListener { v: View? -> mSentenceViewModel.onGoToNext() }

            swShowJpn.setOnClickListener { v: View? ->
                if (currentSentence != null) {
                    tvJaSentence.text = if ((swShowJpn.isChecked)) currentSentence!!.jpn else ""
                }
            }
            swShowVietnamese.setOnClickListener { v: View? ->
                if (currentSentence != null) {
                    tvVietnamese.text = if ((swShowVietnamese.isChecked)) currentSentence!!.vie else ""
                }
            }
            swShowEnglish.setOnClickListener { v: View? ->
                if (currentSentence != null) {
                    tvEnglish.text = if ((swShowEnglish.isChecked)) currentSentence!!.vie else ""
                }
            }
            swShowNote.setOnClickListener { v: View? ->
                if (currentSentence != null) {
                    tvNote.text = if ((swShowNote.isChecked)) currentSentence!!.note else ""
                }
            }
        }
    }
}