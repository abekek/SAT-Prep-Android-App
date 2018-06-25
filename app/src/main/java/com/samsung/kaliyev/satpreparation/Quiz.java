package com.samsung.kaliyev.satpreparation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Random;

public class Quiz extends AppCompatActivity {
    private static final String TAG = "Firelog";
    TextView tv_att_counter;
    TextView tv_answ_counter;
    TextView tv_question;
    Button btn_skip;
    Words question;

    private FirebaseFirestore mFirestore;

    private int count, attempt, skip;

    List<Words> wordsList;
    Button[] buttons = new Button[4];;
    String[] ans = new String[4];;

    public static final Random random = new Random();

    SharedPref sharedPref;

    private AdView mAdView;

    GoogleApiClient myClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quiz");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_att_counter = findViewById(R.id.tv_att_counter);
        tv_answ_counter = findViewById(R.id.tv_answ_counter);
        tv_question = findViewById(R.id.tv_question);
        btn_skip = findViewById(R.id.btn_skip);
        buttons[0] = findViewById(R.id.btn_answ1);
        buttons[1] = findViewById(R.id.btn_answ2);
        buttons[2] = findViewById(R.id.btn_answ3);
        buttons[3] = findViewById(R.id.btn_answ4);

        count = 0; attempt = 3; skip = 1;

        mFirestore = FirebaseFirestore.getInstance();

        tv_question.setMovementMethod(new ScrollingMovementMethod());

        wordsList = (List<Words>) getIntent().getExtras().get("list");

        updateViews();

        for (final Button button : buttons) {

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Check(ans, button);
                    updateViews();
                }
            });
        }

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateViews();
                skip--;
                if(skip == 0){
                    btn_skip.setEnabled(false);
                }
            }
        });

        mFirestore.collection("words").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                    return;
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        Words words = doc.getDocument().toObject(Words.class);
                        wordsList.add(words);
                    }
                }
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void updateViews(){
        question = getRandomWord(null);
        tv_question.setText(question.getDescription());
        ans[0] = question.getName();
        ans[1] = getRandomWord(question).getName();
        ans[2] = getRandomWord(question).getName();
        ans[3] = getRandomWord(question).getName();

        /*for (Button button : buttons) {
            button.setText(ans[random.nextInt(4)]);
        }*/

        int y = random.nextInt(4);
        if(y == 0){
            buttons[0].setText(ans[0]);
            buttons[1].setText(ans[1]);
            buttons[2].setText(ans[2]);
            buttons[3].setText(ans[3]);
        }
        if(y == 1){
            buttons[0].setText(ans[1]);
            buttons[1].setText(ans[0]);
            buttons[2].setText(ans[2]);
            buttons[3].setText(ans[3]);
        }
        if(y == 2){
            buttons[0].setText(ans[2]);
            buttons[1].setText(ans[1]);
            buttons[2].setText(ans[0]);
            buttons[3].setText(ans[3]);
        }
        if(y == 3){
            buttons[0].setText(ans[3]);
            buttons[1].setText(ans[1]);
            buttons[2].setText(ans[2]);
            buttons[3].setText(ans[0]);
        }
    }

    public Words getRandomWord(Words correct){
        Words x = wordsList.get(random.nextInt(wordsList.size()));

        if (correct != null){
            while (correct.getName().equals(x.getName())){
                x = wordsList.get(random.nextInt(wordsList.size()));
            }
        }

        return x;
    }

    public void Check(String[] ans, Button button){
        String answer = (String) button.getText();
        if(answer.equals(ans[0])) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            count++;
            tv_answ_counter.setText(String.valueOf(count));
        }
        else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            attempt--;
            if(attempt == 0) {
                AlertDialog.Builder builder;
                if(sharedPref.loadNightModeState() == true){
                    builder = new AlertDialog.Builder(Quiz.this, R.style.AlertDialogDark);
                }
                else builder = new AlertDialog.Builder(Quiz.this, R.style.AlertDialogLight);
                builder.setTitle("У вас закончились попытки! ")
                        .setMessage("Идите учить слова или начните тест заново")
                        .setCancelable(false)
                        .setPositiveButton("Начать заново",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        recreate();
                                    }
                                })
                        .setNegativeButton("Учить слова",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();

            }
            tv_att_counter.setText(String.valueOf(attempt) + "/3");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
