package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;
import com.niit.model.Person;

@Repository("jobDAO")
@Transactional

public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveJob(Job job) 
	{
		try{
	
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Job> getAllJobs() 
	{
		Session session=sessionFactory.getCurrentSession();
		 Query query=session.createQuery("from Job");
		 List<Job> jobs=query.list();
	        return jobs;
		 

	}

	

}
