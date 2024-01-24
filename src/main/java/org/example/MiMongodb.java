package org.example;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MiMongodb {
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> collection;

    public static void main(String[] args) {

        MiMongodb miMongodb=new MiMongodb();

        miMongodb.guardar("María", "Microsoft");
        miMongodb.modificar("María", "Julia");

        /*Document query = new Document();
        query.put("name", "Shubham");

        Document newDocument = new Document();
        newDocument.put("name", "John");

        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);*/

    }
    public MiMongodb(){
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("myMongoDb");
        mongoClient.listDatabaseNames().forEach(System.out::println);
        database.createCollection("customers");
        database.listCollectionNames().forEach(System.out::println);
        collection = database.getCollection("customers");
    }
    public void guardar(String nombre, String company){
        Document document = new Document();
        document.put("name", nombre);
        document.put("company", company);
        collection.insertOne(document);
    }

    public void modificar(String nombreBuscar, String nombreGuardar){
        Document query = new Document();
        query.put("name", nombreBuscar);

        Document newDocument = new Document();
        newDocument.put("name", nombreGuardar);

        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);
    }


}