package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MongoHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MongoHelper
        MongoHelper mongoHelper = new MongoHelper();

        // Initialize UI elements
        EditText usernameField = findViewById(R.id.usernameField);
        EditText passwordField = findViewById(R.id.passwordField);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        // Set click listener for login button
        loginButton.setOnClickListener(view -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = mongoHelper.loginUser(username, password);
            if (success) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
