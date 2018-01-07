package com.amhi.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amhi.model.Person;
import com.amhi.model.Response;
import com.amhi.service.PersonService;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class PersonController {

	@Autowired
	private PersonService personService;
	final static Logger logger = Logger.getLogger(MyController.class);

	@RequestMapping(value = "/savePerson", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = ("content-type=multipart/*"))
	public ResponseEntity<Object> savePerson(@RequestBody Person person) {
		Response response = null;
		try {
			if(person.getId() !=0){
				System.out.println("Updating");
				personService.updatePerson(person);
				response = new Response();
				response.setMessage("Person Successfully saved");
				logger.info("Person Successfully updated");
			}else{
				System.out.println("Inserting");
				personService.savePerson(person);
				response = new Response();
				response.setMessage("Person Successfully saved");
				logger.info("Person Successfully saved");

			
			}
		} catch (Exception e) {
			logger.info("EXCEPTION OCCURS :: " + e.getMessage());
			return new ResponseEntity<Object>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/personList", method = RequestMethod.GET)
	public ResponseEntity<Object> getPersonList() {
		Response response = null;
		List<Person> allPersons = null;
		try {
			logger.debug("going to Save ....");
			allPersons = personService.getPersons();
			if(allPersons==null){
				response = new Response();
				response.setMessage("Not Found");
				return new ResponseEntity<Object>("Not Found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response = new Response();
			response.setMessage("Bad Request");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(allPersons, HttpStatus.OK);

	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPerson(@PathVariable("id") long id) {
		Response response = null;
		Person person = null;
		try {
			logger.debug("going to Save .personListpersonList...");
			person = personService.getPersonById(id);
			if (person == null) {
				response = new Response();
				response.setMessage("Not Found");
				return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response = new Response();
			response.setMessage("Bad Request");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(person, HttpStatus.OK);

	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> deletePerson(@PathVariable("id") long id) {
		Response response = null;
		try {
			logger.debug("going to Save ....");
			boolean deletePerson = personService.deletePersonById(id);			
			if (!deletePerson) {
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
	@RequestMapping(value = "/deletePerson/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") long id) {
		Response response = null;
		try {
			logger.debug("going to Save ....");
			boolean deletePerson = personService.deletePersonById(id);			
			if (!deletePerson) {
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
