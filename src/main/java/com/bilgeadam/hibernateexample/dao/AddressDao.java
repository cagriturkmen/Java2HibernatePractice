package com.bilgeadam.hibernateexample.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.hibernateexample.entity.Address;

import jakarta.persistence.TypedQuery;

public class AddressDao implements IRepository<Address>{

	@Override
	public void create(Address address) {
		Session session = null;
		try {
			
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(address);
			session.getTransaction().commit();
			System.out.println("Address data is added to DB.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding address data.");		
		} finally {
			session.close();
		}
		
	}
	@Override
	public void update(long id,Address address) {
		Session session = null;
		try {
			Address updateAddress = find(id);
			if(updateAddress != null) {				
				updateAddress.setStreet(address.getStreet());
				updateAddress.setDoorNumber(address.getDoorNumber());
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updateAddress);
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
			Address deleteAddress = find(id);
			if(deleteAddress != null) {
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteAddress);
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
	public Address find(long id) {
		
		Session session = databaseConnectionHibernate();
		Address address;
		
		try {
		address	= session.find(Address.class, id);
		
		if(address != null) {
			System.out.println("Found address : "+ address);
			return address;
		}else {
			System.out.println("Address not found !!!");
			return address;
		}
		} catch (Exception e) {
			System.out.println("Some problems has occured during find operation.");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}
	
	@Override
	public  ArrayList<Address> listAll() {
		
		Session session = databaseConnectionHibernate();
		
		String hql = "select adr from Address as adr where adr.id >=:key";
		
		TypedQuery<Address> typedQuery = session.createQuery(hql,Address.class);
		
		long id = 2L;
		typedQuery.setParameter("key", id);
		
		ArrayList<Address> addressList = (ArrayList<Address>) typedQuery.getResultList();
		
		return addressList;
	}
	
	
	
	
}
