package com.example.android.radiancex

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.android.radiancex.model.SentenceViewModel

class AddNewEntryActivity : AppCompatActivity() {
    private lateinit var etJapanese: EditText
    private lateinit var etMeaning: EditText
    private lateinit var etVietnamese: EditText
    private lateinit var etEnglish: EditText
    private lateinit var diEntryViewModel: SentenceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_entry)
        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "Add new entry"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        etJapanese = findViewById(R.id.etJapanese)
        etMeaning = findViewById(R.id.etMeaning)
        etEnglish = findViewById(R.id.etEnglish)
        etVietnamese = findViewById(R.id.etVietnamese)
        diEntryViewModel = ViewModelProvider(this).get(SentenceViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.add_entry_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.save_entry) {
            if (etJapanese.text.toString().isEmpty()) {
                Toast.makeText(this, "Japanese is empty!", Toast.LENGTH_SHORT).show()
            } else {
                val jpn = etJapanese.text.toString().trim { it <= ' ' }
                val eng = etEnglish.text.toString().trim { it <= ' ' }
                val meaning = etMeaning.text.toString().trim { it <= ' ' }
                val vie = etVietnamese.text.toString().trim { it <= ' ' }
                Thread {
//                    diEntryViewModel.insert(Sentence((diEntryViewModel.numberOfEntriesSynchronous + 1).toString(), jpn, meaning, eng, vie, ""))
                }
                val replyIntent = Intent()
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}