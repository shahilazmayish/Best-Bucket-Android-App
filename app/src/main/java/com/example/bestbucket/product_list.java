package com.example.bestbucket;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

public class product_list extends AppCompatActivity {

    RecyclerView recView;
    myAdapter adapter;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Intent intent = getIntent();
        String searchText = intent.getStringExtra(MainActivity.SearchContext);

        progressDialog = new ProgressDialog(product_list.this);
        progressDialog.show();
        progressDialog.setMessage("Loading Products Please Wait...");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {public void run() {progressDialog.dismiss(); }}, 3000); // 3000 milliseconds delay


        recView = findViewById(R.id.recview);
        recView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products").child("accessories").orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff"), model.class)
                        .build();

        adapter = new myAdapter(options);
        recView.setAdapter(adapter);
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}