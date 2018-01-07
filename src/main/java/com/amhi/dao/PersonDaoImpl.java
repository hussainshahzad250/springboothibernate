package com.amhi.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.amhi.model.Book;
import com.amhi.model.Person;
import com.amhi.model.User;

@Repository
public class PersonDaoImpl implements PersonDao {

	final static Logger logger = Logger.getLogger(PersonDaoImpl.class);

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	@Override
	public boolean savePerson(Person person) {

		try {
			sessionFactory.openSession().save(person);
			logger.info("person saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("something went wrong ERROR :: " + e.getMessage());
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Person getPersonById(long id) {
		Person person = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Person.class);
			person = (Person) criteria.add(Restrictions.eq("id", id)).uniqueResult();
			logger.info("Existing User :: " + person);
			// List<User> users = criteria.add(Restrictions.eq("id",
			// id)).list();
		} catch (Exception e) {
			logger.info("EXCEPTION OCCURS Reason :: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return person;
	}

	@Override
	public boolean deletePersonById(long id) {
		Session session = sessionFactory.openSession();
		Person person = null;
		try {
			Criteria criteria = session.createCriteria(Person.class);
			person = (Person) criteria.add(Restrictions.eq("id", id)).uniqueResult();
			session.delete(person);
			session.flush();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersons() {
		Session session = sessionFactory.openSession();
		List<Person> personList = null;
		try {
			Criteria criteria = session.createCriteria(Person.class);
			personList = criteria.list();
			logger.info("Person LIST :: " + personList);
		} catch (Exception e) {
			logger.info("EXCEPTION OCCURS Reason :: " + e.getMessage());
			return null;
		}
		return personList;
	}

	@Override
	public void updatePerson(Person person) {
		try {
			Session session = sessionFactory.openSession();
			session.saveOrUpdate(person);
			session.flush();
			System.out.println("update Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
