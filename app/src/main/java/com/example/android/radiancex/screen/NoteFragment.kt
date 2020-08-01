package com.example.android.radiancex.screen

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.android.radiancex.R
import com.example.android.radiancex.viewmodel.NoteViewModel

class NoteFragment : Fragment() {

    companion object {
        fun newInstance() = NoteFragment()
    }

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window? = this.activity?.window
            window?.statusBarColor = ContextCompat.getColor(this.requireContext(), R.color.white);
        }

        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}