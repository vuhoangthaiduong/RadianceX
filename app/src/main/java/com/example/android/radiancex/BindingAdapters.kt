package com.example.android.radiancex

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.radiancex.EntryRecyclerViewAdapter
import com.example.android.radiancex.database.DiEntry

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DiEntry>?) {
    val adapter = recyclerView.adapter as EntryRecyclerViewAdapter
    adapter.setEntries(data)
}