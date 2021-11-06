package com.example.assignment2_covidfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    // continueBtn unusable unless logged in
    private Button continueBtn;
    private Button loginBtn;
    private Button createAccountBtn;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        email = findViewById(R.id.fieldEmail);
        password = findViewById(R.id.fieldPassword);
        continueBtn = findViewById(R.id.continueBtn);
        loginBtn = findViewById(R.id.loginBtn);
        createAccountBtn = findViewById(R.id.createAccountBtn);

        continueBtn.setEnabled(false);

        // Set event handlers
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(AuthenticationActivity.this, MainActivity.class);
               startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                if (validateForm()) {
                    login(emailStr, passwordStr);
                }
                // if successful, enable continuebtn
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                if (validateForm()) {
                    createAccount(emailStr, passwordStr);
                }
                // if successful, enable continuebtn
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            continueBtn.setEnabled(true);
        }
    }

    /**
     * Attempt create an account
     * @param email
     * @param password
     */
    private void createAccount(String email, String password) {

    }

    /**
     * Attempt login
     * @param email
     * @param password
     */
    private void login(String email, String password) {

    }

    /**
     * Validate email and password fields
     * @return bool
     */
    private boolean validateForm() {
        return true;
    }
}