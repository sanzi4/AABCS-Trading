package com.example.aabcs_trading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.EventListener;

public class Blog extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ArticleData> articles;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);



        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.BaseArticle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        articles = new ArrayList<ArticleData>();
        myAdapter = new MyAdapter(Blog.this,articles);

        recyclerView.setAdapter(myAdapter);

        EventListener();


        // Initalize and Assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.blog);

        // Perform Selection Item Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), Home_page.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.blog:
                        return true;
                }
                return false;
            }
        });
    }
    private void EventListener() {
        db.collection("Articles").orderBy("Author",Query.Direction.ASCENDING)
                .addSnapshotListener(new com.google.firebase.firestore.EventListener<QuerySnapshot>(){
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error){
                        if(error != null){

                            Log.e("FireStone error", error.getMessage());
                            return;
                        }
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                        for(DocumentChange dc: value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                articles.add(dc.getDocument().toObject(ArticleData.class));
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}