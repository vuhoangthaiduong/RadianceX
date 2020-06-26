package com.example.android.radiancex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class DictionaryFragment extends Fragment {

    final int ADD_NEW_ENTRY_ACTIVITY = 1;
    private CardView cvWordOfTheDay;
    private TextView tvWordOfTheDay;
    private String wordOfTheDay;
    private DiEntryViewModel diEntryViewModel;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    public static DictionaryFragment newInstance() {
        DictionaryFragment fragment = new DictionaryFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_dictionary, container, false);

        CardView cvWordOfTheDay = view.findViewById(R.id.cvWordOfTheDay);
        Button btnDailyTraining = view.findViewById(R.id.btnDailyTraining);
        Button btnBrowse = view.findViewById(R.id.btnBrowse);

        diEntryViewModel = new ViewModelProvider(this).get(DiEntryViewModel.class);

//        new Thread(() -> {
//            wordOfTheDay = diEntryViewModel.findDiEntryByIdSynchronous(6 + "").getJpn();
//            tvWordOfTheDay.setText(wordOfTheDay);
//        }).start();

        cvWordOfTheDay.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
        });

        btnDailyTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DailyTrainingActivity.class);
                startActivity(intent);
            }
        });

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BrowseActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NEW_ENTRY_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                Snackbar.make(this.getView(), data.getStringExtra("jpn"), Snackbar.LENGTH_LONG)
                        .setAction("Close", null).show();
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                Snackbar.make(this.getView(), "No data received", Snackbar.LENGTH_LONG).setAction("Close", null).show();
            }

        }
    }
}
