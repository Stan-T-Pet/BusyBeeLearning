package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Example of getting username from intent
        String username = getIntent().getStringExtra("username");
        if (username != null) {
            Toast.makeText(this, "Welcome, " + username + "!", Toast.LENGTH_SHORT).show();
            TextView welcomeMessage = findViewById(R.id.welcomeMessage);
            welcomeMessage.setText("Welcome, " + username + "!");
        } else {
            Toast.makeText(this, "Welcome to the Dashboard!", Toast.LENGTH_SHORT).show();
        }
    }
}
