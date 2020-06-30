package com.example.android.radiancex

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class DictionaryFragment : Fragment() {
    val ADD_NEW_ENTRY_ACTIVITY = 1
    private val cvWordOfTheDay: CardView? = null
    private val tvWordOfTheDay: TextView? = null
    private val wordOfTheDay: String? = null
    private var diEntryViewModel: DiEntryViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dictionary, container, false)
        val cvWordOfTheDay: CardView = view.findViewById(R.id.cvWordOfTheDay)
        val btnDailyTraining = view.findViewById<Button>(R.id.btnDailyTraining)
        val btnBrowse = view.findViewById<Button>(R.id.btnBrowse)
        diEntryViewModel = ViewModelProvider(this).get(DiEntryViewModel::class.java)

//        new Thread(() -> {
//            wordOfTheDay = diEntryViewModel.findDiEntryByIdSynchronous(6 + "").getJpn();
//            tvWordOfTheDay.setText(wordOfTheDay);
//        }).start();
        cvWordOfTheDay.setOnClickListener { v: View -> Toast.makeText(v.context, "Coming soon!", Toast.LENGTH_SHORT).show() }
        btnDailyTraining.setOnClickListener {
            val intent = Intent(activity, DailyTrainingActivity::class.java)
            startActivity(intent)
        }
        btnBrowse.setOnClickListener {
            val intent = Intent(activity, BrowseActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NEW_ENTRY_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                Snackbar.make(this.view!!, data!!.getStringExtra("jpn"), Snackbar.LENGTH_LONG)
                        .setAction("Close", null).show()
            }
            if (resultCode == Activity.RESULT_CANCELED) {
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