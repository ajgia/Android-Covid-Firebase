package com.example.giasson_waterson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    // continueBtn unusable unless logged in
    private Button continueBtn;
    private Button loginBtn;
    private Button createAccountBtn;
    private Button logoutBtn;
    private EditText fieldEmail;
    private EditText fieldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        fieldEmail = findViewById(R.id.fieldEmail);
        fieldPassword = findViewById(R.id.fieldPassword);
        continueBtn = findViewById(R.id.continueBtn);
        loginBtn = findViewById(R.id.loginBtn);
        createAccountBtn = findViewById(R.id.createAccountBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

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
                String emailStr = fieldEmail.getText().toString();
                String passwordStr = fieldPassword.getText().toString();
                login(emailStr, passwordStr);
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = fieldEmail.getText().toString();
                String passwordStr = fieldPassword.getText().toString();
                createAccount(emailStr, passwordStr);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    private void logout() {
        mAuth.signOut();
        updateUI(null);
    }

    /**
     * Attempt create an account
     * @param email
     * @param password
     */
    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        Task<AuthResult> taskCreateAccount = mAuth.createUserWithEmailAndPassword(email, password);
        taskCreateAccount.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (taskCreateAccount.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else {
                    Toast.makeText(getBaseContext(), R.string.auth_failure, Toast.LENGTH_SHORT)
                        .show();
                    updateUI(null);
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            continueBtn.setEnabled(true);
        }
        else {
            continueBtn.setEnabled(false);
        }
    }

    /**
     * Attempt login
     * @param email
     * @param password
     */
    private void login(String email, String password) {
        if (!validateForm()) {
            return;
        }

        Task<AuthResult> loginTask = mAuth.signInWithEmailAndPassword(email, password);
        loginTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (loginTask.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
                else {
                    Toast.makeText(getBaseContext(), R.string.auth_failure, Toast.LENGTH_SHORT)
                            .show();
                    updateUI(null);
                }
            }
        });
    }

    /**
     * Validate email and password fields
     * @return bool
     */
    private boolean validateForm() {
        boolean valid = true;

        String email = fieldEmail.getText().toString();
        String password = fieldPassword.getText().toString();

        if (email.isEmpty()) {
            fieldEmail.setError("Required");
            valid = false;
        }

        if (password.isEmpty()) {
            fieldPassword.setError("Required");
            valid = false;
        }

        return valid;
    }
}