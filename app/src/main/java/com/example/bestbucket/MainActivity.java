package com.example.bestbucket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    public static final String SearchContext="com.example.bestbucket.SearchContext";
    EditText mSearchText;
    ImageView mSearchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchText = findViewById(R.id.searchtext);
        mSearchBtn = findViewById(R.id.searchbtn1);


        mSearchBtn.setOnClickListener(v -> {
            String searchBox = mSearchText.getText().toString().trim();
            if(TextUtils.isEmpty(searchBox)){
                mSearchText.setError("What are you looking for");
                return;
            }
            mSearchText = findViewById(R.id.searchtext);
            String searchText = mSearchText.getText().toString();
            Intent intent = new Intent(this, product_list.class);
            intent.putExtra(SearchContext,searchText);
            startActivity(intent);
        });
    }
}