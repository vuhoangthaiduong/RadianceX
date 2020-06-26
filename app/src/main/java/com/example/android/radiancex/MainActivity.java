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

    private FloatingActionsMenu floatingActionsMenu;
    private TextView tvScreenname;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScreenname = findViewById(R.id.tvScreenName);

        floatingActionsMenu = findViewById(R.id.add_content_menu);
        FloatingActionButton btnNewEntry = findViewById(R.id.action_add_entry);
        FloatingActionButton btnNewPost = findViewById(R.id.action_new_blog_post);
        FloatingActionButton btnNewQuestion = findViewById(R.id.action_new_question);

        btnNewEntry.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNewEntryActivity.class);
            startActivity(intent);
        });
        btnNewQuestion.setOnClickListener(v -> {
            Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show();
        });
        btnNewPost.setOnClickListener(v -> {
            Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show();
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new DictionaryFragment());
        tvScreenname.setText(R.string.dictionary);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(this, "Entry added", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_dictionary:
                    floatingActionsMenu.setVisibility(View.VISIBLE);
                    if (floatingActionsMenu.isExpanded()) floatingActionsMenu.collapse();
                    fragment = new DictionaryFragment();
                    tvScreenname.setText(R.string.dictionary);
                    loadFragment(fragment);
                    return true;
                case R.id.action_ask:
                    floatingActionsMenu.setVisibility(View.VISIBLE);
                    if (floatingActionsMenu.isExpanded()) floatingActionsMenu.collapse();
                    fragment = new AskFragment();
                    tvScreenname.setText(R.string.ask);
                    loadFragment(fragment);
                    return true;
                case R.id.action_blog:
                    floatingActionsMenu.setVisibility(View.VISIBLE);
                    if (floatingActionsMenu.isExpanded()) floatingActionsMenu.collapse();
                    fragment = new BlogFragment();
                    tvScreenname.setText(R.string.blog);
                    loadFragment(fragment);
                    return true;
                case R.id.action_inbox:
                    floatingActionsMenu.setVisibility(View.GONE);
                    fragment = new InboxFragment();
                    tvScreenname.setText(R.string.inbox);
                    loadFragment(fragment);
                    return true;
                case R.id.action_account:
                    floatingActionsMenu.setVisibility(View.GONE);
                    fragment = new AccountFragment();
                    tvScreenname.setText(R.string.account);
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.container_test, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}