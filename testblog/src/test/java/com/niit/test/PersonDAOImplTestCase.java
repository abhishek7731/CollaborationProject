package com.niit.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.PersonDAO;
import com.niit.model.Person;

public class PersonDAOImplTestCase {
	static PersonDAO personDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		personDAO = (PersonDAO) context.getBean("personDAO");

	}

	@Ignore
	@Test
	public void addPersonTest() {
		Person person = new Person();
		
			person.setPhonenumber("7839129293");
			person.setEmail("abhi96255@gmail.com");
			person.setFirstaname("Abhishek");
			person.setLastname("Bajpai");

			assertTrue("problem in adding person", personDAO.addPerson(person));
		
	}

	

}