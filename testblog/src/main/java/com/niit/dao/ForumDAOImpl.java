package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Forum;
@Repository("forumDAO")
@Transactional


public class ForumDAOImpl implements ForumDAO
{
	@Autowired
	SessionFactory sessionFactory;


	public boolean addForum(Forum forum) 
	{
		try 
		{
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}

	}
	public boolean updateForum(Forum forum) 
	{
		try 
		{
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}

	}
	public boolean deleteForum(Forum forum) 
	{
		try 
		{
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}

	}
	public Forum getForum(int id) 
	{
		 try
		{
			Session session = sessionFactory.getCurrentSession();
	           Forum forum = (Forum)session.get(Forum.class,id);
		        
		           return forum;
		}
		 catch(Exception e)
			{
				return null;
			}
	}

	public List<Forum> listForum() 
	{
		 try
			{
				Session session = sessionFactory.openSession();
				Query query = session.createQuery("from Forum"); //it means in h2 database where there is category table fetch all the category data
				List<Forum> listForums =query.list(); // now put all the data in list categories
				session.close();
		         
			        
			           return listForums ; // returned to junit test case
			}
			
			catch(Exception e)
			{
				return null;
			}
			
			}
}
