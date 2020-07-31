package com.example.android.radiancex

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.radiancex.databinding.FragmentDictionaryBinding
import com.example.android.radiancex.model.SentenceViewModel
import com.example.android.radiancex.screen.browse.BrowseActivity
import com.google.android.material.snackbar.Snackbar

class DictionaryFragment : Fragment() {
    val ADD_NEW_ENTRY_ACTIVITY = 1
    private var diEntryViewModel: SentenceViewModel? = null
    private lateinit var binding: FragmentDictionaryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        diEntryViewModel = ViewModelProvider(this).get(SentenceViewModel::class.java)

        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        val view = binding.root

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window? = this.activity?.window
            window?.statusBarColor = ContextCompat.getColor(this.requireContext(), R.color.white);
        }

        binding.apply {

            cvWordOfTheDay.setOnClickListener { v: View -> Toast.makeText(v.context, "Coming soon!", Toast.LENGTH_SHORT).show() }

            btnDailyTraining.setOnClickListener {
                val intent = Intent(activity, LearnActivity::class.java)
                startActivity(intent)
            }
            btnBrowse.setOnClickListener {
                val intent = Intent(activity, BrowseActivity::class.java)
                startActivity(intent)
            }
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NEW_ENTRY_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                Snackbar.make(this.requireView(), data!!.getCharSequenceExtra("jpn")!!, Snackbar.LENGTH_LONG)
                        .setAction("Close", null).show()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Snackbar.make(this.requireView(), "No data received", Snackbar.LENGTH_LONG).setAction("Close", null).show()
            }
        }
    }

    companion object {
        fun newInstance(): DictionaryFragment {
            val fragment = DictionaryFragment()
            val args = Bundle()
            return fragment
        }
    }
}