package com.bilgeadam.hibernateexample.dao;

import org.hibernate.Session;

import com.bilgeadam.hibernateexample.entity.Person;


public class PersonDao implements IRepository<Person>{

	@Override
	public void create(Person person) {
		
		try {
			
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			System.out.println(session);
			session.merge(person);
			session.getTransaction().commit();
			System.out.println("Person data is added to DB.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Person data.");		
		}
		
	}
	@Override
	public void update(long id,Person person) {
		Session session = null;
		try {
			Person updatePerson = find(id);
			if(updatePerson != null) {				
				updatePerson.setFirstName(person.getFirstName());
				updatePerson.setLastName(person.getLastName());
				updatePerson.setAddress(person.getAddress());
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updatePerson);
				session.getTransaction().commit();
				System.out.println("Successfully updated. Welldone.");
				
			}
			
		} catch (Exception e) {
			System.out.println("Some problem has occured while UPDATE.");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Person deletePerson = find(id);
			if(deletePerson != null) {
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deletePerson);
				session.getTransaction().commit();

				System.out.println("Successfully deleted.");
					
			}
			
		} catch (Exception e) {
		System.out.println("Some problem occured while DELETE opertaion.");
		e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	@Override
	public Person find(long id) {
		
		Session session = databaseConnectionHibernate();
		Person person;
		
		try {
			person	= session.find(Person.class, id);
		
		if(person != null) {
			System.out.println("Found address : "+ person);
			return person;
		}else {
			System.out.println("Address not found !!!");
			return person;
		}
		} catch (Exception e) {
			System.out.println("Some problems has occured during find operation.");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}
	
	
}
