package com.sample.springmvc.postGre.BookApiUsingPostGRE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springmvc.postGre.BookApiUsingPostGRE.model.Book;
import com.sample.springmvc.postGre.BookApiUsingPostGRE.service.BookService;



//@CrossOrigin("*") - declared so these get and post mappings can be used in angular 
@CrossOrigin("*")
@RestController
public class BookController {

	
	@Autowired
	private BookService bookService;
	
	
	//Get all books
	@GetMapping("/api/books")
	public ResponseEntity<List<Book>> list() {
		List<Book> list = bookService.list();
		return ResponseEntity.ok().body(list);
	}
	
	
	//Save a book record
	/*
	 * JSON request format
	 * 
	 * 	{
	 * 		"title" : "qwerty" ,
	 * 		"author" : "qwerty-author"
	 *  }
	 * 
	 * 
	 * 
	 */
	@PostMapping("/api/book")
	public ResponseEntity<?> save(@RequestBody Book book){
		long id = bookService.save(book);
		return ResponseEntity.ok().body("Book created with id: " + id);
	}
	
	
	//Get Book details from ID
	/*
	 * your GetMapping - {id} and @PathVarable - ("id")should be same name
	 */
	@GetMapping("/api/book/{id}")
	public ResponseEntity<Book> get(@PathVariable("id")long id)
	{
		 Book book = bookService.get(id);
		 return ResponseEntity.ok().body(book);
		
	}
	
	
	//update book
	@PutMapping("/api/book/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id , @RequestBody Book book)
	{
		bookService.update(id, book);
		return ResponseEntity.ok().body("Book has been updated");
	}
	
	
	//delete book
	@DeleteMapping("/api/book/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id)
	{
		bookService.delete(id);
		return ResponseEntity.ok().body("Book with id:" + id + "has been deleted");
	}
}
