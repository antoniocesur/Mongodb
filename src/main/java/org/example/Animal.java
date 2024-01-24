package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Animal {
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> collection;
    public static void main(String[] args) {

    }
}
