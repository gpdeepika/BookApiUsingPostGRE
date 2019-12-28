package com.sample.springmvc.postGre.BookApiUsingPostGRE.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

//@PropertySource -  will tell the spring container where the property file is located
@Configuration
@PropertySource("classpath:hibernate.cfg.xml")
@EnableTransactionManagement
public class AppConfig {

	//@Autowired -  spring container will create an object for us 
	@Autowired
	private Environment env;
	
	//Purpose - to set LocalSessionFactoryBean
	@Bean
	public LocalSessionFactoryBean getSessionFactory()
	{
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		
		//Setting JDBC properties
		// import static org.hibernate.cfg.Environment.*; - this will give DRIVER 
		
		System.out.println("hibernate.connection.driver_class: " + env.getProperty("hibernate.connection.driver_class"));
		
		props.put(DRIVER,env.getProperty("hibernate.connection.driver_class"));
		props.put(URL,env.getProperty("hibernate.connection.url"));
		props.put(USER,env.getProperty("hibernate.connection.username"));
		props.put(PASS,env.getProperty("hibernate.connection.password"));
		
		
		//Setting Hibernate Properties
		
		props.put(SHOW_SQL, env.getProperty("show_sql"));
		props.put(HBM2DDL_AUTO,env.getProperty("hibernate.hbm2ddl.auto"));
		props.put(DIALECT, env.getProperty("hibernate.dialect"));
		

		
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.spring.model");
		
		return factoryBean;
		
	}
	
	//Purpose - to set SessionFactory
	@Bean
	public HibernateTransactionManager getTransactionManager()
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		
		return transactionManager;
	}
	
}
