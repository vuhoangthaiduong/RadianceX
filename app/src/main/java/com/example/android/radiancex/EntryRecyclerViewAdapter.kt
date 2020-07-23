package com.example.android.radiancex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.radiancex.EntryRecyclerViewAdapter.EntryViewHolder
import com.example.android.radiancex.database.DiEntry
import com.example.android.radiancex.databinding.EntryCardBinding

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 */
class EntryRecyclerViewAdapter  // Provide a suitable constructor (depends on the kind of dataset)
    : RecyclerView.Adapter<EntryViewHolder>() {
    private var mEntries: List<DiEntry>? = null

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class EntryViewHolder(private var binding: EntryCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: DiEntry) {
            binding.entry = entry
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        // create a new view
        return EntryViewHolder(EntryCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.tag = mEntries!![position]
        holder.bind(mEntries!![position])
//        holder.btnEdit.setOnClickListener { v -> Toast.makeText(v.context, "Coming soon!", Toast.LENGTH_SHORT).show() }
    }

    fun setEntries(entries: List<DiEntry>?) {
        mEntries = entries
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return if (mEntries != null) mEntries!!.size else 0
    }
}