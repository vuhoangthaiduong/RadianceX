package com.example.android.radiancex

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.radiancex.database.DiEntry
import com.example.android.radiancex.databinding.ActivityDailyTrainingBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*

class DailyTrainingActivity() : AppCompatActivity() {
    lateinit var mDiEntryViewModel: DiEntryViewModel
    lateinit var progressDialog: ProgressDialog
    lateinit var handler: Handler
    private var currentDeck: ArrayList<DiEntry>? = null
    private var currentSentence: DiEntry? = null
    private var entryCount = 0
    private val DECK_SIZE = 20
    private val ID_FIELD_CODE = 0
    private val JAPANESE_FIELD_CODE = 1
    private val VIETNAMESE_FIELD_CODE = 2
    private val NOTE_FIELD_CODE = 3

    private lateinit var binding: ActivityDailyTrainingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDiEntryViewModel = ViewModelProvider(this).get(DiEntryViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_daily_training)
        binding.viewmodel = mDiEntryViewModel

        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "Daily Training"
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

            btnNext.setOnClickListener { v: View? -> mDiEntryViewModel.onGoToNext() }

            switchShowJpn.setOnClickListener { v: View? ->
                if (currentSentence != null) {
                    tvJaSentence.text = if ((switchShowJpn.isChecked)) currentSentence!!.jpn else ""
                }
            }
            switchShowTranslation.setOnClickListener { v: View? ->
                if (currentSentence != null) {
                    translation.text = if ((switchShowTranslation.isChecked)) currentSentence!!.vie else ""
                }
            }
            switchShowHint.setOnClickListener { v: View? ->
                if (currentSentence != null) {
                    hint.text = if ((switchShowHint.isChecked)) currentSentence!!.meaning else ""
                }
            }
        }


    }

//    @Throws(IOException::class)
//    fun populateDatabaseFromFile() {
//        handler!!.post {
//            progressDialog = ProgressDialog(this)
//            progressDialog!!.setCancelable(false)
//            progressDialog!!.setMessage("Populating database")
//            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
//            progressDialog!!.show()
//        }
//        try {
//            BufferedReader(InputStreamReader(this.assets.open("BST Câu.tsv"), StandardCharsets.UTF_8)).use { bufferedReader ->
//                var line: String
//                var fields: Array<String>
//                var id: String
//                var japanese: String
//                var meaning: String?
//                var english: String?
//                var vietnamese: String
//                var note: String
//                var count: Int = 0
//                while ((bufferedReader.readLine().also { line = it }) != null) {
//                    fields = line.split("\t".toRegex()).toTypedArray()
//                    id = if (fields.size >= 1) fields.get(ID_FIELD_CODE) else ""
//                    japanese = if (fields.size >= 2) fields.get(JAPANESE_FIELD_CODE) else ""
//                    vietnamese = if (fields.size >= 3) fields.get(VIETNAMESE_FIELD_CODE) else ""
//                    note = if (fields.size >= 4) fields.get(NOTE_FIELD_CODE) else ""
//                    mDiEntryViewModel!!.insert(DiEntry(japanese, "", "", vietnamese, note))
//                    Log.d("Fields ----", "$id|$japanese|$vietnamese|$note")
//                    //                    mDiEntryViewModel.insert(new DiEntry(count + "", "", "", "", "", ""));
////                    Log.e("Entry count", "finished, count: " + mDiEntryViewModel.getNumberOfEntriesSynchronous());
//                    count++
//                }
//                entryCount = mDiEntryViewModel!!.numberOfEntriesSynchronous
//                handler!!.post(Runnable {
//                    progressDialog!!.dismiss()
//                    Toast.makeText(this, entryCount.toString() + " entries imported", Toast.LENGTH_SHORT).show()
//                })
//                Thread(Runnable { Log.d("Database population - ", "Finished, count: " + entryCount) }).start()
//            }
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
//    }

}