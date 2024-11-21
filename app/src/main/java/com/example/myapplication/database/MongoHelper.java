package com.example.myapplication;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class MongoHelper {

    private static final String CONNECTION_STRING = "mongodb+srv://<username>:<password>@<cluster-url>/test";
    private static final String DATABASE_NAME = "UserDB";
    private static final String COLLECTION_NAME = "users";

    private MongoDatabase database;
    private MongoCollection<Document> userCollection;

    public MongoHelper() {
        // Initialize MongoDB connection
        MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
        database = mongoClient.getDatabase(DATABASE_NAME);
        userCollection = database.getCollection(COLLECTION_NAME);
    }

    public boolean registerUser(String username, String email, String password) {
        // Check if user already exists
        Document existingUser = userCollection.find(new Document("username", username)).first();
        if (existingUser != null) {
            return false; // User already exists
        }

        // Insert new user
        Document newUser = new Document("username", username)
                .append("email", email)
                .append("password", password); // Hash password in production
        userCollection.insertOne(newUser);
        return true;
    }

    public boolean loginUser(String username, String password) {
        // Find user by username and password
        Document user = userCollection.find(new Document("username", username)
                .append("password", password)).first();
        return user != null;
    }
}
