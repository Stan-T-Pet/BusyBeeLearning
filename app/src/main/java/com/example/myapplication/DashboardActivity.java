package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        Button logoutButton = findViewById(R.id.logoutButton);

        // Get username from intent
        String username = getIntent().getStringExtra("username");
        if (username != null) {
            welcomeMessage.setText("Welcome, " + username + "!");
        } else {
            welcomeMessage.setText("Welcome to the Dashboard!");
        }

        logoutButton.setOnClickListener(view -> {
            // Navigate back to the Login screen
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the Dashboard
        });
    }
}
