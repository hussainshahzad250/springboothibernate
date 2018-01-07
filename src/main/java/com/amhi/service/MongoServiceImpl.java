/*package com.amhi.service;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.amhi.model.Employee;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
@Service
public class MongoServiceImpl implements MongoService {

	

	@Override
	public boolean saveEmployee(Employee e1) {
		try {
			 MongoClient mongo = new MongoClient("localhost", 27017);
			MongoCredential credential = MongoCredential.createCredential("shahzad",
					"dev", "password".toCharArray());
			MongoDatabase database = mongo.getDatabase("dev");
			MongoCollection<Document> collection = database.getCollection("test");
			System.out.println("Collection sampleCollection selected successfully");
			Document document = new Document("firstName", e1.getFirstName())
					.append("lastName", e1.getLastName())
					.append("age", e1.getAge());
			collection.insertOne(document);
			System.out.println("Document inserted successfully");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
*/