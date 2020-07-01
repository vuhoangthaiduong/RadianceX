package com.example.android.radiancex

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.getbase.floatingactionbutton.FloatingActionButton
import com.getbase.floatingactionbutton.FloatingActionsMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var floatingActionsMenu: FloatingActionsMenu
    private lateinit var tvScreenname: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvScreenname = findViewById(R.id.tvScreenName)
        floatingActionsMenu = findViewById(R.id.add_content_menu)
        val btnNewEntry = findViewById<FloatingActionButton>(R.id.action_add_entry)
        val btnNewPost = findViewById<FloatingActionButton>(R.id.action_new_blog_post)
        val btnNewQuestion = findViewById<FloatingActionButton>(R.id.action_new_question)
        btnNewEntry.setOnClickListener { v: View? ->
            val intent = Intent(this, AddNewEntryActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
        btnNewQuestion.setOnClickListener { v: View? -> Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show() }
        btnNewPost.setOnClickListener { v: View? -> Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show() }
        val bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadFragment(DictionaryFragment())
        tvScreenname.setText(R.string.dictionary)
    }

    override fun onResume() {
        super.onResume()
        if (floatingActionsMenu.isExpanded) floatingActionsMenu.collapse()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Entry added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment
        when (item.itemId) {
            R.id.action_dictionary -> {
                floatingActionsMenu.visibility = View.VISIBLE
                if (floatingActionsMenu.isExpanded) floatingActionsMenu.collapse()
                fragment = DictionaryFragment()
                tvScreenname.setText(R.string.dictionary)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_ask -> {
                floatingActionsMenu.visibility = View.VISIBLE
                if (floatingActionsMenu.isExpanded) floatingActionsMenu.collapse()
                fragment = AskFragment()
                tvScreenname.setText(R.string.ask)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_blog -> {
                floatingActionsMenu.visibility = View.VISIBLE
                if (floatingActionsMenu.isExpanded) floatingActionsMenu.collapse()
                fragment = BlogFragment()
                tvScreenname.setText(R.string.blog)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_inbox -> {
                floatingActionsMenu.visibility = View.GONE
                fragment = InboxFragment()
                tvScreenname.setText(R.string.inbox)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_account -> {
                floatingActionsMenu.visibility = View.GONE
                fragment = AccountFragment()
                tvScreenname.setText(R.string.account)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.container_test, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }
}