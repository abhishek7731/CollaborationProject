 package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Blog;

@Repository("blogDAO")
@Transactional

public class BlogDAOImpl implements BlogDAO 
{
	@Autowired
	SessionFactory sessionFactory;


	public boolean addBlog(Blog blog) 
	{
		try 
		{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}

	}
	public void updateBlog(Blog blog) 
	{
		try 
		{
			sessionFactory.getCurrentSession().update(blog);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		
		}

	}
	public void deleteBlog(Blog blog) 
	{
		try 
		{
			sessionFactory.getCurrentSession().delete(blog);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}

	}
	public Blog getBlog(int id) //id is a primary therefor we neendnot to write a query we can directly get from database
	{
		 try
		{
			Session session = sessionFactory.getCurrentSession();
	           Blog blog = (Blog)session.get(Blog.class,id);
		        
		           return blog;
		}
		 catch(Exception e)
			{
				return null;
			}
	}
	public List<Blog> searchBlog(String keyword) 
	{
		
		 try
			{
				Session session = sessionFactory.openSession();
				Query query = session.createQuery("from Blog where blogName=:blogName1 or blogAuthor=:blogAuthor and status='A' ").setParameter("blogName1",keyword).setParameter("blogAuthor", keyword);//it means in h2 database where there is category table fetch all the category data
				List<Blog> listBlog =query.list(); // now put all the data in list categories
				session.close();
		         
			        
			           return listBlog ; // returned to junit test case
			}
			
			catch(Exception e)
			{
				return null;
			}
			
	}
	@Override
	public List<Blog> getApprovedBlogs() 
	{
		try 
		{
		Session session=	sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Blog where approvalStatus=true");
		List<Blog> blogs=query.list();
		return blogs;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

	}
	@Override
	public List<Blog> getBlogWaitingForApproval() {
		try 
		{
		Session session=	sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Blog where approvalStatus=false");
		List<Blog> blogs=query.list();
		return blogs;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

	}
	
	
}
