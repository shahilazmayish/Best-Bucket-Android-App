package com.example.bestbucket;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class welcomeScreen extends AppCompatActivity {
    Button mWelcomeJoinBtn;
    TextView mWelcomeLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mWelcomeJoinBtn = findViewById(R.id.welcomeJoinBtn);
        mWelcomeLogin = findViewById(R.id.welcomeLogin);

        mWelcomeJoinBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),register.class)));
        mWelcomeLogin.setOnClickListener(v1 -> startActivity(new Intent(getApplicationContext(),login.class)));
    }
}