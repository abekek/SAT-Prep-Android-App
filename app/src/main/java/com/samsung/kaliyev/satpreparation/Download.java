package com.samsung.kaliyev.satpreparation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Download extends AppCompatActivity {
    SharedPref sharedPref;

    private AdView mAdView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_download);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Download");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Intent intent = getIntent();

        TextView nameText = findViewById(R.id.nameText);
        nameText.setText(intent.getStringExtra("name"));

        ImageView test_d = findViewById(R.id.test_d);
        test_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent download_intent = new Intent(Intent.ACTION_VIEW);
                download_intent.setData(Uri.parse(intent.getStringExtra("test_url")));
                startActivity(download_intent);
            }
        });

        ImageView essay_d = findViewById(R.id.essay_d);
        essay_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent download_intent = new Intent(Intent.ACTION_VIEW);
                download_intent.setData(Uri.parse(intent.getStringExtra("essay_url")));
                startActivity(download_intent);
            }
        });

        ImageView answers_d = findViewById(R.id.answers_d);
        answers_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent download_intent = new Intent(Intent.ACTION_VIEW);
                download_intent.setData(Uri.parse(intent.getStringExtra("answ_url")));
                startActivity(download_intent);
            }
        });

        ImageView explanations_d = findViewById(R.id.explanations_d);
        explanations_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent download_intent = new Intent(Intent.ACTION_VIEW);
                download_intent.setData(Uri.parse(intent.getStringExtra("exp_url")));
                startActivity(download_intent);
            }
        });

        /*MobileAds.initialize(this, "ca-app-pub-5797803964516419~8572131614");*/

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
