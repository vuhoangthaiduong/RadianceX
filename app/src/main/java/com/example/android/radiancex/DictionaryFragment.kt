package com.example.android.radiancex

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.radiancex.databinding.ActivityDailyTrainingBinding
import com.example.android.radiancex.databinding.FragmentDictionaryBinding
import com.google.android.material.snackbar.Snackbar

class DictionaryFragment : Fragment() {
    val ADD_NEW_ENTRY_ACTIVITY = 1
    private var diEntryViewModel: DiEntryViewModel? = null
    private lateinit var binding: FragmentDictionaryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        diEntryViewModel = ViewModelProvider(this).get(DiEntryViewModel::class.java)

        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {

            cvWordOfTheDay.setOnClickListener { v: View -> Toast.makeText(v.context, "Coming soon!", Toast.LENGTH_SHORT).show() }

            btnDailyTraining.setOnClickListener {
                val intent = Intent(activity, DailyTrainingActivity::class.java)
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
                Snackbar.make(this.view!!, data!!.getCharSequenceExtra("jpn")!!, Snackbar.LENGTH_LONG)
                        .setAction("Close", null).show()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Snackbar.make(this.view!!, "No data received", Snackbar.LENGTH_LONG).setAction("Close", null).show()
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