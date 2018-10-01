package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogComment;

@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addBlogComment(BlogComment blogComment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(blogComment);

	}

	@Override
	public List<BlogComment> getBlogComments(int blogPostId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BlogComment where blogPost.id=:blogPostId");
		query.setParameter("blogPostId",blogPostId);
		List<BlogComment> blogComments = query.list();
		return blogComments;
	}

}
