package com.amhi.service;

import java.util.List;

import com.amhi.model.Person;

public interface PersonService {

	boolean savePerson(Person person);

	Person getPersonById(long id);

	List<Person> getPersons();

	boolean deletePersonById(long id);

	void updatePerson(Person person);

}
