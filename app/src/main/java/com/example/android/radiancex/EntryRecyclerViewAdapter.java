package com.example.android.radiancex;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.radiancex.dummy.DummyContent.DummyItem;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 */
public class EntryRecyclerViewAdapter extends RecyclerView.Adapter<EntryRecyclerViewAdapter.EntryViewHolder> {

    private ArrayList<DiEntry> entries;

    // Provide a suitable constructor (depends on the kind of dataset)
    public EntryRecyclerViewAdapter(ArrayList<DiEntry> entries) {
        this.entries = entries;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        public TextView tvJpn, tvVie;
        public ImageButton btnEdit;
        public EntryViewHolder(View v) {
            super(v);
            tvJpn = v.findViewById(R.id.tvJpn);
            tvVie = v.findViewById(R.id.tvVie);
            btnEdit = v.findViewById(R.id.btnEdit);

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EntryRecyclerViewAdapter.EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_card, parent, false);
        EntryViewHolder vh = new EntryViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.setTag(entries.get(position));
        holder.tvJpn.setText(entries.get(position).getJpn());
        holder.tvVie.setText(entries.get(position).getVie());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return entries.size();
    }
}