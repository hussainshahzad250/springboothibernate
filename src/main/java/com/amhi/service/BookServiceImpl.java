package com.amhi.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amhi.dao.BookDao;
import com.amhi.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	final static Logger logger = Logger.getLogger(BookServiceImpl.class);

	@Override
	public boolean saveBook(Book book) {

		logger.info("going to save, now at service level");
		return bookDao.saveBook(book);

	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
		
	}

	@Override
	public Book getBook(long id) {
		return bookDao.getBook(id);
		
	}

	@Override
	public boolean deleteBook(long id) {
		
		return bookDao.deleteBook(id);
	}
}
