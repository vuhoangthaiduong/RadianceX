package com.example.android.radiancex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private FloatingActionsMenu floatingActionsMenu;
    private FloatingActionButton btnNewEntry;
    private FloatingActionButton btnNewQuestion;
    private FloatingActionButton btnNewPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        floatingActionsMenu = findViewById(R.id.fab_main_activity);
        btnNewEntry = findViewById(R.id.btn_fab_new_entry);
        btnNewPost = findViewById(R.id.btn_fab_new_post);
        btnNewQuestion = findViewById(R.id.btn_fab_new_question);

        btnNewEntry.setOnClickListener(v -> {
            Intent intent = new Intent(this, BrowseActivity.class);
            startActivity(intent);
        });
        btnNewQuestion.setOnClickListener(v -> {
            Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT);
        });
        btnNewPost.setOnClickListener(v -> {
            Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT);
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new DictionaryFragment());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_dictionary:
                    floatingActionsMenu.setVisibility(View.VISIBLE);
                    fragment = new DictionaryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_ask:
                    fragment = new AskFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_blog:
                    floatingActionsMenu.setVisibility(View.VISIBLE);
                    fragment = new BlogFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_inbox:
                    floatingActionsMenu.setVisibility(View.VISIBLE);
                    floatingActionsMenu.setVisibility(View.GONE);
                    fragment = new InboxFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_account:
                    floatingActionsMenu.setVisibility(View.GONE);
                    fragment = new AccountFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}