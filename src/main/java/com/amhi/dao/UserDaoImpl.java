package com.amhi.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.amhi.model.User;
import com.amhi.model.Users;

@Repository
public class UserDaoImpl implements UserDao {

	final static Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	@Override
	public boolean saveUser(User user) {

		try {
			sessionFactory.openSession().save(user);
			logger.info("saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("something went wrong ERROR :: " + e.getMessage());
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {
		User user = null;
		try {
			Session session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(User.class);
			user = (User) criteria.add(Restrictions.eq("email", email))
					.uniqueResult();

			List<User> users = criteria.add(Restrictions.eq("email", email))
					.list();
			System.out.println("Existing User :: " + users);
			logger.info("Existing User :: " + user);

		} catch (Exception e) {
			logger.info("EXCEPTION OCCURS Reason :: " + e.getMessage());
			e.printStackTrace();
			return user;
		}
		return user;
	}

	@Override
	public User login(String email, String password) {
		User user = null;
		try {

			Session session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(User.class);
			// criteria.add(Restrictions.eq("email", email));
			// criteria.add(Restrictions.eq("password", password));

			user = (User) criteria.add(Restrictions.eq("email", email))
					.add(Restrictions.eq("password", password)).uniqueResult();

			Criterion criterion1 = Restrictions.eq("email", email);
			Criterion criterion2 = Restrictions.eq("password", password);
			LogicalExpression and = Restrictions.and(criterion1, criterion2);

			System.out.println(and);

			logger.info("Existing User :: " + user);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("EXCEPTION OCCURS Reason :: " + e.getMessage());
			return null;
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.openSession();
		List<User> users = null;
		try {
			Criteria ctr = session.createCriteria(User.class);
			users = ctr.list();
			logger.info("USERS LIST :: " + users);

		} catch (Exception e) {
			logger.info("EXCEPTION OCCURS Reason :: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return users;
	}

	@Override
	public boolean deletetUserById(String id) {
		Session session = sessionFactory.openSession();
		try {
			session.delete(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveHeros(Users users) {

		try {
			sessionFactory.openSession().save(users);
			logger.info("saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("something went wrong ERROR :: " + e.getMessage());
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
