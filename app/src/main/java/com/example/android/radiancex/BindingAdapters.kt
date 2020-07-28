package com.example.android.radiancex

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.radiancex.database.Sentence

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Sentence>?) {
    val adapter = recyclerView.adapter as EntryRecyclerViewAdapter
    adapter.setEntries(data)
}