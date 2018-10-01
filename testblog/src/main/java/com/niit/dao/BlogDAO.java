package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;


public interface BlogDAO 
{
	 public boolean addBlog(Blog blog);
//	 public boolean updateBlog(Blog blog);
//	 public boolean deleteBlog(Blog blog);
	 public Blog getBlog(int id);
	 public List<Blog> getApprovedBlogs();
	 List<Blog> getBlogWaitingForApproval();
	 void updateBlog(Blog blog); //updating approval status from false to true
	 void deleteBlog(Blog blog);
	 
}
