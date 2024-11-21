package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameField = findViewById(R.id.usernameField);
        EditText passwordField = findViewById(R.id.passwordField);
        Button loginButton = findViewById(R.id.loginButton);

        MongoHelper mongoHelper = new MongoHelper();

        loginButton.setOnClickListener(view -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Login user
            boolean success = mongoHelper.loginUser(username, password);
            if (success) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                // Navigate to another activity
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}