package com.niit.dao;

import java.util.List;

import org.hibernate.query.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.User;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO
{
@Autowired
private SessionFactory sessionFactory;

@Override
public List<User> getSuggestedUsers(String email) {
try
{
	Session session=sessionFactory.getCurrentSession(); 
	NativeQuery query=session.createSQLQuery("select * from USER_TABLE where email in"
			+ "( select email from USER_TABLE where email!=:email "
			+ " minus (select fromId_email from FRIEND_TABLE where toId_email=:email "
		
			+ " union "
			+ "  select toId_email from FRIEND_TABLE where fromId_email=:email))");
	     
	     query.setParameter("email", email);
	     query.setParameter("email", email);
	     query.setParameter("email", email);
	     query.addEntity(User.class);
	     return query.list();
   
}

catch(Exception e)
{
	e.printStackTrace();
	return null;
}
}

@Override
public void addFriendRequest(Friend friend) 
{
	Session session=sessionFactory.getCurrentSession(); 
	session.save(friend);
	
}

@Override
public List<Friend> getPendingRequests(String email) 
{
	Session session=sessionFactory.getCurrentSession();
	Query query = session.createQuery("from Friend f where f.toId.email=:email and f.status='P'");	
	query.setParameter("email", email);
	List<Friend> pendingRequests=query.list();
	return pendingRequests;
}

@Override
public void acceptFriendRequest(Friend friend) 
{

	Session session=sessionFactory.getCurrentSession();
	session.update(friend);
}

@Override
public void deleteFriendRequest(Friend friend) 
{
	System.out.println("FRIEND ID IS"+friend.getId());
	Session session=sessionFactory.getCurrentSession();
	session.delete(friend);
	
}

@Override
public List<User> listOfFriends(String email) 
{
	Session session=sessionFactory.getCurrentSession();
	Query query1 = session.createQuery("select f.toId from Friend f where f.fromId.email=:email and f.status='A'");
	query1.setParameter("email", email);
	
	List<User> list1 = query1.list();
	// f is alias
	Query query2 = session.createQuery("select f.fromId from Friend f where f.toId.email=:email and f.status='A'");
	query2.setParameter("email", email);
	
	List<User> list2 = query2.list();
	
	list1.addAll(list2);
	return list1;
}
}
