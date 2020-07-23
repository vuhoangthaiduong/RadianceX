package com.example.android.radiancex

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.radiancex.database.DiEntry
import com.example.android.radiancex.databinding.ActivityBrowseBinding

class BrowseActivity : AppCompatActivity() {
    @SuppressLint("NewApi")

    lateinit var binding: ActivityBrowseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "Browse"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_browse)
//        val mAdapter = EntryRecyclerViewAdapter()
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        binding.apply {
//            entryRecyclerView.setHasFixedSize(true)
//            entryRecyclerView.adapter = mAdapter
//        }
    }

}