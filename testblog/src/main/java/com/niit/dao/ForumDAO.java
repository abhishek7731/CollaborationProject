package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;

import com.niit.model.Forum;

public interface ForumDAO 
{
	 public boolean addForum(Forum forum);
	 public boolean updateForum(Forum forum);
	 public boolean deleteForum(Forum forum);
	 public Forum getForum(int id);
	 public  List<Forum> listForum();
}
