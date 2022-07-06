package com.bilgeadam.hibernateexample;

import java.util.ArrayList;
import java.util.List;

import com.bilgeadam.hibernateexample.dao.AddressDao;
import com.bilgeadam.hibernateexample.dao.PersonDao;
import com.bilgeadam.hibernateexample.entity.Address;
import com.bilgeadam.hibernateexample.entity.Person;

public class Test {

	public static void main(String[] args) {
		
		List<Person> people = new ArrayList<>();
		
		Address address = new Address();		
		address.setStreet("Besiktas");
		
		Address address2 = new Address();
		address2.setStreet("CaglayanDere");
		address2.setDoorNumber(30);
		address2.setPeople(people);
		
		Person person = new Person();
		person.setFirstName("Cagri");
		person.setLastName("Turkmen");
		person.setAddress(address);
		
		Person person2 = new Person();	
		person2.setFirstName("Babur");
		person2.setLastName("Somer");
		person2.setAddress(address);
		
		
		people.add(person);
		
		
		
		
		AddressDao addressDao = new AddressDao();
		PersonDao personDao = new PersonDao();
		
		
		addressDao.create(address);
		addressDao.create(address2);
		personDao.create(person);
		personDao.create(person2);
		
		
		Address newAddress = new Address();
		newAddress.setStreet("Bagcılar");
		newAddress.setDoorNumber(68);
		addressDao.create(newAddress);
		//addressDao.find(3);
		//addressDao.update(1, newAddress);
	//	addressDao.delete(1);
		System.out.println(addressDao.listAll());;
		
		Person updatePerson = person;
		updatePerson.setAddress(newAddress);
		
		personDao.update(1,updatePerson );
		

		
	}

}
