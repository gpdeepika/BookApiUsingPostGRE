package com.sample.springmvc.postGre.BookApiUsingPostGRE.DAO;

import java.util.List;

import com.sample.springmvc.postGre.BookApiUsingPostGRE.model.Book;


public interface BookDAO {
	
	//Save the record
	long save(Book book);
	
	//Get a single record
	Book get(long id);
	
	//Get all the records
	List<Book> list();
	
	//Update the record
	void update(long id,Book book);
	
	//Delete a record
	void delete(long id);

}
