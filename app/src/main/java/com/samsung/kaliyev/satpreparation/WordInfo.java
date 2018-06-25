package com.samsung.kaliyev.satpreparation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class WordInfo extends AppCompatActivity {
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_word_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Word Info");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();

        TextView nameText = findViewById(R.id.nameText);
        nameText.setText(intent.getStringExtra("name"));
        TextView typeText = (TextView) findViewById(R.id.typeText);
        typeText.setText(intent.getStringExtra("type"));
        TextView descriptionText = (TextView) findViewById(R.id.descriptionText);
        descriptionText.setText(intent.getStringExtra("description"));
        TextView translationText = (TextView) findViewById(R.id.translationText);
        translationText.setText(intent.getStringExtra("translation"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
