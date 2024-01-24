package org.example;


import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

public class MiMongodb {
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> collection;

    public static void main(String[] args) {

        MiMongodb miMongodb=new MiMongodb();

        miMongodb.guardar("María", "Microsoft");
        miMongodb.guardar2("Benigna", "Cisco");
        miMongodb.modificar("María", "Julia");

        miMongodb.modificarCompany("Cisco", "Cisco inc");

        miMongodb.listarTodos();
        miMongodb.encontrarCompany("Microsoft");

        miMongodb.borrar("Julia");

        //https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
        //https://www.baeldung.com/java-mongodb-filters3
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

    public void guardar2(String nombre, String company){
        Document doc = new Document("name", nombre)
                .append("company", company);
        collection.insertOne(doc);
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

    public void modificarCompany(String companyBuscar, String companyGuardar){
        //Cambia solo uno
        collection.updateOne(Filters.eq("company", companyBuscar), new Document("$set", new Document("company", companyGuardar)));

        //Cambiarlos todos e indicar cuántos han cambiado
        UpdateResult updateResult = collection.updateMany(Filters.eq("company", companyBuscar), new Document("$set", new Document("company", companyGuardar)));
        System.out.println(updateResult.getModifiedCount());
    }

    public void listarTodos(){
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }
    }

    public void encontrarCompany(String company){
        //Encontrar el primero
        System.out.println("El primero de esa compañia");
        Document doc = collection.find(Filters.eq("company", company)).first();
        System.out.println(doc.toJson());

        //Mostrarlos todos los de esa companía
        System.out.println("Muestro todos los de una compañía");
        MongoCursor<Document> cursor= collection.find(Filters.eq("company", company)).iterator();
        while(cursor.hasNext()){
            System.out.println(cursor.next().toString());
        }
    }

    public void borrar(String nombre){
        //Borrar 1
        collection.deleteOne(Filters.eq("name", nombre));

        //Borrar todos
        DeleteResult deleteResult = collection.deleteMany(Filters.gte("name", nombre));
        System.out.println(deleteResult.getDeletedCount());
    }

}