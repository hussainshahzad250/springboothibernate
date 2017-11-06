package com.amhi.service;

import java.util.List;

import com.amhi.model.Book;

public interface BookService {

	boolean saveBook(Book book);

	List<Book> getAllBooks();

	Book getBook(long id);

	boolean deleteBook(long id);

	
}
