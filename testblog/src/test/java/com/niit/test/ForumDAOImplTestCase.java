package com.niit.test;

import static org.junit.Assert.*;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;


import com.niit.dao.ForumDAO;
import com.niit.model.Blog;
import com.niit.model.Forum;


public class ForumDAOImplTestCase 
{

	static ForumDAO forumDAO; 
	
	
	@BeforeClass
	public static  void executeFirst()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh(); 
		
		forumDAO = (ForumDAO)context.getBean("forumDAO");	
	}
@Test
public void addForumTest()
{
	Forum forum= new Forum();
	{
		forum.setForumContent("Niit Private Lin=mited");
		forum.setForumDate(new Date());
		forum.setForumTitle("Niit");
		forum.setForumStatus("A");
		forum.setUsername("abhishek7731");
		assertTrue("problem in adding forum", forumDAO.addForum(forum));
	}
}

}

