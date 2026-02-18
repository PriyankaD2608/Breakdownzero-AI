package com.priyanka.hostelmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.priyanka.hostelmanagement.R;
import com.priyanka.hostelmanagement.utils.NotificationHelper;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private CardView cardMessMenu, cardPass, cardWaterTiming, cardActivities,
            cardTimetable, cardVendors, cardAntiRagging, cardWifiMap,
            cardRoomManagement, cardLogout;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        NotificationHelper.createNotificationChannel(this);

        initializeViews();
        loadUserData();
        setupClickListeners();
    }

    private void initializeViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        cardMessMenu = findViewById(R.id.cardMessMenu);
        cardPass = findViewById(R.id.cardPass);
        cardWaterTiming = findViewById(R.id.cardWaterTiming);
        cardActivities = findViewById(R.id.cardActivities);
        cardTimetable = findViewById(R.id.cardTimetable);
        cardVendors = findViewById(R.id.cardVendors);
        cardAntiRagging = findViewById(R.id.cardAntiRagging);
        cardWifiMap = findViewById(R.id.cardWifiMap);
        cardRoomManagement = findViewById(R.id.cardRoomManagement);
        cardLogout = findViewById(R.id.cardLogout);
    }

    private void loadUserData() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            db.collection("users").document(userId).get()
                    .addOnSuccessListener(doc -> {
                        if (doc.exists()) {
                            String name = doc.getString("name");
                            if (name != null) {
                                tvWelcome.setText("Welcome, " + name + "!");
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error loading user data", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    private void setupClickListeners() {
        cardMessMenu.setOnClickListener(v ->
                startActivity(new Intent(this, MessMenuActivity.class))
        );

        cardPass.setOnClickListener(v ->
                startActivity(new Intent(this, PassRequestActivity.class))
        );

        cardWaterTiming.setOnClickListener(v ->
                startActivity(new Intent(this, WaterTimingActivity.class))
        );

        cardActivities.setOnClickListener(v ->
                startActivity(new Intent(this, ActivitiesActivity.class))
        );

        cardTimetable.setOnClickListener(v ->
                startActivity(new Intent(this, TimetableActivity.class))
        );

        cardVendors.setOnClickListener(v ->
                startActivity(new Intent(this, VendorsActivity.class))
        );

        cardAntiRagging.setOnClickListener(v ->
                startActivity(new Intent(this, AntiRaggingActivity.class))
        );

        cardWifiMap.setOnClickListener(v ->
                startActivity(new Intent(this, WifiMapActivity.class))
        );

        cardRoomManagement.setOnClickListener(v ->
                startActivity(new Intent(this, RoomManagementActivity.class))
        );

        cardLogout.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}