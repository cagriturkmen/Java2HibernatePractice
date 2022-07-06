package com.bilgeadam.hibernateexample.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.hibernateexample.entity.Address;
import com.bilgeadam.hibernateexample.entity.Person;

public class HibernateSession {

	private static final SessionFactory sessionFactory= sessionFactory();
	
	
	private static SessionFactory sessionFactory() {
		
		SessionFactory factory = null;
		
		try {
			Configuration configuration = new Configuration();		
			
			//entity classlarımızı buraya eklemeliyiz.
			
			configuration.addAnnotatedClass(Person.class);
			configuration.addAnnotatedClass(Address.class);
			
			factory =configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("Connection to PostgreSQL via Hibernate established.");
			
		
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		return factory;
		
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
}
