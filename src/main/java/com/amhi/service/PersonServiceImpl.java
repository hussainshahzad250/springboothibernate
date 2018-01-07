package com.amhi.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amhi.dao.PersonDao;
import com.amhi.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	final static Logger logger = Logger.getLogger(PersonServiceImpl.class);

	@Override
	public boolean savePerson(Person person) {

		return personDao.savePerson(person);
	}

	@Override
	public Person getPersonById(long id) {
		return personDao.getPersonById(id);
	}

	@Override
	public List<Person> getPersons() {
		return personDao.getPersons();
	}

	@Override
	public boolean deletePersonById(long id) {
		return personDao.deletePersonById(id);
	}

	@Override
	public void updatePerson(Person person) {
		
		personDao.updatePerson(person);
	}

}
