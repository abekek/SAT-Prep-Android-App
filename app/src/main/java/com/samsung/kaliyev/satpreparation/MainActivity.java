package com.samsung.kaliyev.satpreparation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    SharedPref sharedPref;

    ImageView sat_r, sat_s, words, universities, resources, interviews;

    @Override
    protected void onStart() { //создание защищенного метода onStart - вызывается перед тем, как Activity будет видно пользователю
        super.onStart(); //вызываем наследованный метод onStart
        mAuth.addAuthStateListener(mAuthListener); //для переменной mAuth добавляем Listener
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); //создаем объект toolbar, которому принадлежит toolbar
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() { //создаем новый объект с Listener для него
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { //метод, который отвечает за логин, если поменялся user
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, GoogleLogin.class)); //открывается Activity с логином
                }
            }
        };

        sat_r = (ImageView) findViewById(R.id.sat_r);
        sat_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SATReasoning.class));
            }
        });

        words = (ImageView) findViewById(R.id.words);
        words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Vocabulary.class));
            }
        });

        sat_s = (ImageView) findViewById(R.id.sat_s);
        sat_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SATSubject.class));
            }
        });

        resources = (ImageView) findViewById(R.id.resources);
        resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Resources.class));
            }
        });

        universities = (ImageView) findViewById(R.id.universities);
        universities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Universities.class));
            }
        });

        interviews = (ImageView) findViewById(R.id.interviews);
        interviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, Interviews.class));
                Toast.makeText(MainActivity.this, "Скоро!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.donate:
                startActivity(new Intent(this, Support.class));
                return true;
            case R.id.settings:
                finish();
                startActivity(new Intent(this, Settings.class));
                return true;
            case R.id.logout:
                mAuth.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
