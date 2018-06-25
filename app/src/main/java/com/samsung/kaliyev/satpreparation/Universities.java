package com.samsung.kaliyev.satpreparation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Universities extends AppCompatActivity {
    private RecyclerView mMainList;
    private FirebaseFirestore mFirestore;
    public static final String TAG = "Firelog";


    private List<Posts> postsList;
    private PostsListAdapter postsListAdapter;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        postsList = new ArrayList<>();
        postsListAdapter = new PostsListAdapter(getApplicationContext(), postsList);

        Toolbar toolbar = findViewById(R.id.toolbar); //создаем объект toolbar, которому принадлежит toolbar
        toolbar.setTitle("USA Universities");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mMainList = (RecyclerView) findViewById(R.id.recycler_view);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(postsListAdapter);
        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("posts_u").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                    return;
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        Posts posts = doc.getDocument().toObject(Posts.class);
                        postsList.add(posts);

                        postsListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
