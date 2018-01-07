/*package com.amhi.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amhi.model.Employee;
import com.amhi.service.MongoService;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

@RestController
public class MongoController {

	@Autowired
	private MongoService mongoService;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Employee e1) {

		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoCredential credential = MongoCredential.createCredential(
				"shahzad", "dev", "password".toCharArray());
		// MongoDatabase database = mongo.getDatabase("dev");
		MongoDatabase database = mongo.getDatabase(credential.getSource());
		MongoCollection<Document> collection = database.getCollection("test");
		System.out.println("Collection sampleCollection selected successfully");
		Document document = new Document("firstName", e1.getFirstName())
				.append("lastName", e1.getLastName())
				.append("age", e1.getAge());
		collection.insertOne(document);
		return new ResponseEntity<Object>("Document inserted successfully",
				HttpStatus.OK);
	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll() {

		List<Employee> list = new ArrayList<Employee>();
		Employee emp = null;
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoCredential credential = MongoCredential.createCredential(
				"shahzad", "dev", "password".toCharArray());
		System.out.println("credential :: " + credential);
		MongoDatabase database = mongo.getDatabase("dev");
		MongoCollection<Document> collection = database.getCollection("test");
		FindIterable<Document> iterDoc = collection.find();
		Iterator it = iterDoc.iterator();
		while (it.hasNext()) {
			Object next = it.next();
			Gson gson = new Gson();
			String jsonInString = gson.toJson(next);
			System.out.println(jsonInString);
			emp = gson.fromJson(jsonInString, Employee.class);
			list.add(emp);
			System.out.println(list);
		}
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public ResponseEntity<Object> delete() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoCredential credential = MongoCredential.createCredential(
				"shahzad", "dev", "password".toCharArray());
		System.out.println("credential :: " + credential);
		MongoDatabase database = mongo.getDatabase("dev");
		System.out.println(database.listCollectionNames());
		System.out.println(database.listCollectionNames());
		MongoCollection<Document> collection = database.getCollection("test");

		// Deleting the documents
		DeleteResult deleteOne = collection.deleteOne(Filters.eq("firstName",
				"Shahzad"));
		if (deleteOne.getDeletedCount() == 0) {
			return new ResponseEntity<Object>("Document Not Found",
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>("Document deleted successfully",
				HttpStatus.OK);
	}

}
*/