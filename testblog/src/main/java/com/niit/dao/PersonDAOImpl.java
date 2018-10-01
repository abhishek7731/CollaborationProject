package com.niit.dao;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Person;
@Repository("personDAO")
@Transactional
public class PersonDAOImpl implements PersonDAO 
{
   @Autowired
   SessionFactory sessionFactory;
	@Override
	public List<Person> getAllPersons() 
	{
	
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from Person");
		List<Person> persons=query.list();
		return persons;
		
	}
	@Override
	public boolean addPerson(Person person) 
	{
		try 
		{
			sessionFactory.getCurrentSession().save(person);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public void deletePerson(int id) 
	{
		Session session = sessionFactory.getCurrentSession();
		Person person=(Person)session.get(Person.class, id); //we are making person object persistent --select*from person where id=?
		if(person!=null)
		
		session.delete(person);
		
		
		
	}
	@Override
	public Person getPerson(int id) 
	{
		Session session = sessionFactory.getCurrentSession();
		Person person=(Person)session.get(Person.class, id);
		return person;
		
	}
	@Override
	public void updatePerson(Person person) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.update(person);
	}

}
