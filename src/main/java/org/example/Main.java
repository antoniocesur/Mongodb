package org.example;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("myMongoDb");
        mongoClient.listDatabaseNames().forEach(System.out::println);
        database.createCollection("customers");
        database.listCollectionNames().forEach(System.out::println);
        MongoCollection<Document> collection = database.getCollection("customers");



        Document query = new Document();
        query.put("name", "Shubham");

        Document newDocument = new Document();
        newDocument.put("name", "John");

        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);

    }

    public void guardar(){
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("myMongoDb");
        MongoCollection<Document> collection = database.getCollection("customers");
        Document document = new Document();
        document.put("name", "Antonio");
        document.put("company", "Salinas");
        collection.insertOne(document);
    }

    public void modificar(){
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("myMongoDb");

        MongoCollection<Document> collection = database.getCollection("customers");

        Document query = new Document();
        query.put("name", "Shubham");

        Document newDocument = new Document();
        newDocument.put("name", "John");

        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);
    }


}