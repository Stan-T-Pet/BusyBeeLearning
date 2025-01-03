package com.example.myapplication;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

public class MongoHelper {

    private static final String CONNECTION_STRING = "mongodb+srv://<db_username>:<db_password>@learn.y3o2t.mongodb.net/?retryWrites=true&w=majority&appName=Learn";
    private static final String DATABASE_NAME = "UserDB";
    private static final String COLLECTION_NAME = "users";

    private MongoDatabase database;
    private MongoCollection<Document> userCollection;

    public MongoHelper() {
        // Replace <db_username> and <db_password> with your actual MongoDB credentials
        String connectionString = CONNECTION_STRING.replace("<db_username>", "yourUsername")
                .replace("<db_password>", "yourPassword");

        // Initialize the MongoDB client
        MongoClient mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(DATABASE_NAME);
        userCollection = database.getCollection(COLLECTION_NAME);
    }

    // Register a new user
    public boolean registerUser(String username, String email, String password) {
        // Check if the user already exists
        Document existingUser = userCollection.find(new Document("username", username)).first();
        if (existingUser != null) {
            return false; // User already exists
        }

        // Insert the new user
        Document newUser = new Document("username", username)
                .append("email", email)
                .append("password", password); // Use hashed password in production
        userCollection.insertOne(newUser);
        return true;
    }

    // Validate login credentials
    public boolean loginUser(String username, String password) {
        // Check if a user exists with the given username and password
        Document user = userCollection.find(new Document("username", username)
                .append("password", password)).first();
        return user != null; // Return true if a matching user is found
    }
}
