package com.example.bestbucket;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import com.google.firebase.auth.FirebaseAuth;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPassword2;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.regUsername);
        mEmail = findViewById(R.id.regEmail);
        mPassword = findViewById(R.id.regPassword);
        mPassword2 = findViewById(R.id.regPassword2);
        mRegisterBtn = findViewById(R.id.regSignupbtn);
        mLoginBtn = findViewById(R.id.regLoginhere);
        progressBar = findViewById(R.id.regProgressBar);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(v -> {   //After clicking SIGN UP button it will put into these variable
            final String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String password2 = mPassword2.getText().toString().trim();


            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is Required.");
                return;
            }
            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is required.");
                return;
            }
            if(!password.equals(password2)){
                mPassword2.setError("Password does not match");
                return;
            }
            if(password.length() < 6){
                mPassword.setError("Password Must be at least 6 Characters");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            // register the user in firebase
            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(register.this, "Account Successfully Created.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        mLoginBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),login.class)));

    }
}
