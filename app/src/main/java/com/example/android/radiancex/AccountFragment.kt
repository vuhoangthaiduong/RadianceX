package com.example.android.radiancex

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class AccountFragment : Fragment() {
    var btnGoToAccountSettings: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        btnGoToAccountSettings = view.findViewById(R.id.goToAccountSettings)
        btnGoToAccountSettings.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/vuhoangthaiduong"))
            startActivity(intent)
        })
        return view
    }
}