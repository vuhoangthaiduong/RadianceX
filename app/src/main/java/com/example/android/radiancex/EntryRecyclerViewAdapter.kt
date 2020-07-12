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

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 */
class EntryRecyclerViewAdapter  // Provide a suitable constructor (depends on the kind of dataset)
    : RecyclerView.Adapter<EntryViewHolder>() {
    private var mEntries: List<DiEntry>? = null

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class EntryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvJpn: TextView
        var tvVie: TextView
        var btnEdit: ImageButton

        init {
            tvJpn = v.findViewById(R.id.tvJpn)
            tvVie = v.findViewById(R.id.tvVie)
            btnEdit = v.findViewById(R.id.btnEdit)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.entry_card, parent, false) as View
        return EntryViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.tag = mEntries!![position]
        holder.tvJpn.text = mEntries!![position].jpn
        holder.tvVie.text = mEntries!![position].vie
        holder.btnEdit.setOnClickListener { v -> Toast.makeText(v.context, "Coming soon!", Toast.LENGTH_SHORT).show() }
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