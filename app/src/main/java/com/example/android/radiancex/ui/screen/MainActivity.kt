package com.example.android.radiancex.ui.screen

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.radiancex.R
import com.example.android.radiancex.databinding.ActivityMainBinding
import com.example.android.radiancex.ui.screen.account.AccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dictionaryFragment = DictionaryFragment.newInstance()
    private val noteFragment = NoteFragment.newInstance()
    private val accountFragment = AccountFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            setTransparentStatusBar()
//        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.let {
            it.actionAddEntry.setOnClickListener { v: View? ->
                val intent = Intent(this, AddNewEntryActivity::class.java)
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
            }
            it.actionNewQuestion.setOnClickListener { v: View? -> Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show() }
            it.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
            loadFragment(DictionaryFragment())
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
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        when (item.itemId) {
            R.id.action_learn -> {
                if (currentFragment is DictionaryFragment) return@OnNavigationItemSelectedListener false
                binding.apply {
                    addContentMenu.visibility = View.VISIBLE
                    if (addContentMenu.isExpanded) addContentMenu.collapse()
                }
                fragment = dictionaryFragment
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_note -> {
                if (currentFragment is NoteFragment) return@OnNavigationItemSelectedListener false
                binding.apply {
                    addContentMenu.visibility = View.VISIBLE
                    if (addContentMenu.isExpanded) addContentMenu.collapse()
                }
                fragment = noteFragment
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.action_account -> {
                if (currentFragment is AccountFragment) return@OnNavigationItemSelectedListener false
                binding.addContentMenu.visibility = View.GONE
                fragment = accountFragment
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.fragment_container, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun Activity.setTransparentStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        }
    }

    companion object {
        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }
}