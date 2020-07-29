package com.example.android.radiancex

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.android.radiancex.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.let {
            it.actionAddEntry.setOnClickListener { v: View? ->
                val intent = Intent(this, AddNewEntryActivity::class.java)
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
            }
            it.actionNewQuestion.setOnClickListener { v: View? -> Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show() }
            it.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            loadFragment(DictionaryFragment())
            it.tvScreenName.setText(R.string.dictionary)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.let { if (it.addContentMenu.isExpanded) it.addContentMenu.collapse() }
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
            R.id.action_learn -> {
                binding.let { if (it.addContentMenu.isExpanded) it.addContentMenu.collapse() }
                fragment = DictionaryFragment.newInstance()
                binding.tvScreenName.setText(R.string.learn)
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_note -> {
                binding.let { if (it.addContentMenu.isExpanded) it.addContentMenu.collapse() }
                fragment = NoteFragment.newInstance()
                binding.tvScreenName.setText(R.string.note)
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