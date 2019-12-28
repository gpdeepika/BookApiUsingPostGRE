package com.sample.unitTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.sample.springmvc.postGre.BookApiUsingPostGRE.model.Book;



public class Runner {
	
	
	
	 @Test
	    public void crud() {
	        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        create(session);
	      
	        session.close();
	    }
	 
	 
	 private void create(Session session)
	 {
		 System.out.println("Creating a new book record...");
		 Book book = new Book();
		 book.setId((long)10000001);
		 book.setTitle("frompostGreTitle");
		 book.setAuthor("frompostGre");
		 session.beginTransaction();
		 session.save(book);
		 session.getTransaction().commit();
	 }
	 


}
