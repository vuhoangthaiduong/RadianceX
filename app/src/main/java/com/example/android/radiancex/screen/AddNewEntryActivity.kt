package com.example.android.radiancex.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.radiancex.R
import com.example.android.radiancex.databinding.ActivityNewEntryBinding
import com.example.android.radiancex.model.Sentence
import com.example.android.radiancex.viewmodel.SentenceViewModel

class AddNewEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewEntryBinding
    private lateinit var sentenceViewModel: SentenceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_entry)
        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "Add new sentence"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        sentenceViewModel = ViewModelProvider(this).get(SentenceViewModel::class.java)
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
            val japanese = binding.tvJapanese.editText?.text.toString().trim()
            val english = binding.tvEnglish.editText?.text.toString().trim()
            val vietnamese = binding.tvVietnamese.editText?.text.toString().trim()
            val note = binding.tvNote.editText?.text.toString().trim()

            if (TextUtils.isEmpty(japanese)) {
                binding.tvJapanese.error = "Japanese required"
                return false
            }
            sentenceViewModel.insert(Sentence(0, japanese, english, vietnamese, note))
            Toast.makeText(this, "New sentence added", Toast.LENGTH_SHORT).show()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}