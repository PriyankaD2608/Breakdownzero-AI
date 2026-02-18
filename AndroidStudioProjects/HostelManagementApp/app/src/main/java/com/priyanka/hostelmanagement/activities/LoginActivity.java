package com.priyanka.hostelmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.priyanka.hostelmanagement.R;
import com.priyanka.hostelmanagement.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etName, etRollNumber, etPhone, etRoom, etBlock;
    private Button btnLogin, btnRegister;
    private TextView tvToggle;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Check if already logged in
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);
        etRollNumber = findViewById(R.id.etRollNumber);
        etPhone = findViewById(R.id.etPhone);
        etRoom = findViewById(R.id.etRoom);
        etBlock = findViewById(R.id.etBlock);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        tvToggle = findViewById(R.id.tvToggle);

        toggleMode();
    }

    private void setupClickListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        tvToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoginMode = !isLoginMode;
                toggleMode();
            }
        });
    }

    private void toggleMode() {
        if (isLoginMode) {
            etName.setVisibility(View.GONE);
            etRollNumber.setVisibility(View.GONE);
            etPhone.setVisibility(View.GONE);
            etRoom.setVisibility(View.GONE);
            etBlock.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.GONE);
            tvToggle.setText("Don't have an account? Register");
        } else {
            etName.setVisibility(View.VISIBLE);
            etRollNumber.setVisibility(View.VISIBLE);
            etPhone.setVisibility(View.VISIBLE);
            etRoom.setVisibility(View.VISIBLE);
            etBlock.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
            btnRegister.setVisibility(View.VISIBLE);
            tvToggle.setText("Already have an account? Login");
        }
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    updateFCMToken();
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(LoginActivity.this, "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String rollNumber = etRollNumber.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String room = etRoom.getText().toString().trim();
        String block = etBlock.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || name.isEmpty() ||
                rollNumber.isEmpty() || phone.isEmpty() || room.isEmpty() || block.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser firebaseUser = authResult.getUser();
                    if (firebaseUser != null) {
                        User user = new User(
                                firebaseUser.getUid(),
                                name,
                                email,
                                rollNumber,
                                1, // Default year
                                room,
                                block,
                                phone,
                                "student"
                        );

                        db.collection("users")
                                .document(firebaseUser.getUid())
                                .set(user)
                                .addOnSuccessListener(aVoid -> {
                                    updateFCMToken();
                                    Toast.makeText(LoginActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(LoginActivity.this, "Error saving user: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(LoginActivity.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    private void updateFCMToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(token -> {
                    FirebaseUser currentUser = auth.getCurrentUser();
                    if (currentUser != null) {
                        String userId = currentUser.getUid();
                        db.collection("users")
                                .document(userId)
                                .update("fcmToken", token);
                    }
                });
    }
}