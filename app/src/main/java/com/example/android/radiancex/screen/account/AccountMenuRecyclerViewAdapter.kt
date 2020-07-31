package com.example.android.radiancex.screen.account

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.radiancex.databinding.AccountMenuItemBinding

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 */
class AccountMenuRecyclerViewAdapter  // Provide a suitable constructor (depends on the kind of dataset)
    : RecyclerView.Adapter<AccountMenuRecyclerViewAdapter.MenuViewHolder>() {
    private var menuItems: List<String> = arrayListOf("Statistics", "Contact", "About us", "Collaboration")

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class MenuViewHolder(private var binding: AccountMenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItem: String) {
            binding.menuItem.text = menuItem
            binding.menuItem.setOnClickListener { v ->
                Toast.makeText(v.context, "${binding.menuItem.text} function coming soon!", Toast.LENGTH_SHORT).show()
            }
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        // create a new view
        return MenuViewHolder(AccountMenuItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.tag = menuItems[position]
        holder.bind(menuItems[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return menuItems.size
    }
}