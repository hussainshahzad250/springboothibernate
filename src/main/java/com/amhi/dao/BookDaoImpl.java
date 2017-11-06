package com.amhi.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.amhi.model.Book;

@Repository
public class BookDaoImpl implements BookDao {

	final static Logger logger = Logger.getLogger(BookDaoImpl.class);

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	@Override
	public boolean saveBook(Book book) {

		try {
			sessionFactory.openSession().save(book);
			logger.info("saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("something went wrong ERROR :: " + e.getMessage());
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {
		List<Book> books = null;
		try {
			Session session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Book.class);
			books = criteria.list();
		} catch (Exception e) {
			return null;
		}
		return books;
	}

	@Override
	public Book getBook(long id) {
		Book book = null;
		Session session = sessionFactory.openSession();
		try {
			book = (Book) session.get(Book.class, id);
			System.out.println("done");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return book;

	}

	@Override
	public boolean deleteBook(long id) {

		Session session = sessionFactory.openSession();
		try {
			Book object = (Book) session.get(Book.class, id);
			if (object == null) {
				return false;
			}
			session.delete(object);
			session.flush();
		
			System.out.println("done");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		return true;

	}
}
