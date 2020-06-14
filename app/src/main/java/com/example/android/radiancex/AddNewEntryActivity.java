package com.example.android.radiancex;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AddNewEntryActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private EditText etJapanese;
    private EditText etMeaning;
    private EditText etVietnamese;
    private EditText etEnglish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        toolbar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etJapanese = findViewById(R.id.etJapanese);
        etMeaning = findViewById(R.id.etMeaning);
        etEnglish = findViewById(R.id.etEnglish);
        etVietnamese = findViewById(R.id.etVietnamese);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_entry_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_entry) {
            if (etJapanese.getText().toString().isEmpty()) {
                Toast.makeText(this, "Japanese is empty!", Toast.LENGTH_SHORT).show();
            } else {
                String jpn = etJapanese.getText().toString().trim();
                String eng = etEnglish.getText().toString().trim();
                String meaning = etMeaning.getText().toString().trim();
                String vie = etVietnamese.getText().toString().trim();
                Intent intent = new Intent(AddNewEntryActivity.this, DictionaryFragment.class);
                intent.putExtra("jpn", jpn);
                intent.putExtra("eng", eng);
                intent.putExtra("meaning", meaning);
                intent.putExtra("vie", vie);
                setResult(RESULT_OK, intent);
                AddNewEntryActivity.this.finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
