package com.example.android.radiancex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle(R.string.ask);

        loadFragment(new DictionaryFragment());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_dictionary:
                    toolbar.setTitle(R.string.dictionary);
                    fragment = new DictionaryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_ask:
                    toolbar.setTitle(R.string.ask);
                    fragment = new AskFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_blog:
                    toolbar.setTitle(R.string.blog);
                    fragment = new BlogFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_inbox:
                    toolbar.setTitle(R.string.inbox);
                    fragment = new InboxFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_account:
                    toolbar.setTitle(R.string.account);
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