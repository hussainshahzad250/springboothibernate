package com.amhi.dao;

import java.util.List;

import com.amhi.model.Book;

public interface BookDao {

	boolean saveBook(Book book);

	List<Book> getAllBooks();

	Book getBook(long id);

	boolean deleteBook(long id);
}
