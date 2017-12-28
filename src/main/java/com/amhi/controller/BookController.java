package com.amhi.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amhi.model.Book;
import com.amhi.model.Response;
import com.amhi.service.BookService;
import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.Quorum;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.StoreValue.Option;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class BookController {

	@Autowired
	private BookService bookService;
	final static Logger logger = Logger.getLogger(MyController.class);

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = ("content-type=multipart/*"))
	public ResponseEntity<Object> saveBook(
			@RequestParam("file") MultipartFile image) {
		Response response = null;
		try {
			logger.debug("going to Save ....");
			byte[] bytes = image.getBytes();
			
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(image.getOriginalFilename())));
			stream.write(bytes);
			stream.close();

			Book book = new Book();
			book.setName(image.getOriginalFilename());
			book.setImage(bytes);

			bookService.saveBook(book);
			logger.info("Register Successfully");
			response = new Response();
			response.setMessage("Register Successfully");

		} catch (Exception e) {
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}
	@RequestMapping(value = "/saveFile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = ("content-type=multipart/*"))
	public ResponseEntity<Object> saveFile(
			@RequestParam("file") MultipartFile image) {
		Response response = null;
		try {
			logger.debug("going to Save ....");
			byte[] bytes = image.getBytes();
			
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(image.getOriginalFilename())));
			stream.write(bytes);
			stream.close();
			
			com.amhi.dto.Book book = new com.amhi.dto.Book();
			book.setName(image.getOriginalFilename());
			book.setImage(bytes);
			
			
			RiakClient riakClient =RiakClient.newClient("172.16.27.9");
			Gson javaObjectToJson = new Gson();
			String finalJsonObject = javaObjectToJson
					.toJson("shahzad");
			Namespace namespace = new Namespace("discountBucketType", "BUCKET");
			Location Newlocation = new Location(namespace, "1");
			RiakObject riakObject = new RiakObject();
			riakObject.setContentType("application/json").setValue(
					BinaryValue.create(finalJsonObject));
			StoreValue store = new StoreValue.Builder(riakObject)
					.withLocation(Newlocation)
					.withOption(Option.W, new Quorum(3)).build();
			riakClient.execute(store);
			System.out.println("Calculated Result saved Successfully");			
			response = new Response();
			response.setMessage("Register Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/bookList", method = RequestMethod.GET)
	public ResponseEntity<Object> getBooks() {
		Response response = null;
		List<Book> allBooks =null;
		try {
			logger.debug("going to Save ....");			
			allBooks = bookService.getAllBooks();			
			
		} catch (Exception e) {
			response = new Response();
			response.setMessage("Bad Request");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(allBooks, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBook(@PathVariable("id") long id) {
		Response response = null;
		Book book =null;
		try {
			logger.debug("going to Save ....");			
			book = bookService.getBook(id);
			if(book ==null){
				response = new Response();
				response.setMessage("Not Found");
				return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response = new Response();
			response.setMessage("Bad Request");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(book, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteBook(@PathVariable("id") long id) {
		Response response = null;
		try {
			logger.debug("going to Save ....");			
			boolean deleteBook = bookService.deleteBook(id);
			System.out.println("Herere");
			if(!deleteBook){
				response = new Response();
				response.setMessage("not Found");
				return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
			}
			response = new Response();
			response.setMessage("Delete Success");
		} catch (Exception e) {
			response = new Response();
			response.setMessage("Bad Request");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}

}
