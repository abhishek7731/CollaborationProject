package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;

@Repository("userDAO")
@Transactional

public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void registration(User user) {
		try {
			System.out.println("UserDaoImpl bean is created");
			System.out.println(user.getLastname());
			Session session = sessionFactory.getCurrentSession();
			session.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isEmailUnique(String email) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User where email=:email");
			query.setParameter("email", email);
			User user = (User) query.uniqueResult();
			System.out.println(user != null);
			if (user != null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

public User login(User user)
{
	Session session=sessionFactory.getCurrentSession();
	Query query=session.createQuery("from User where email=:email and password=:password")
			.setParameter("email",user.getEmail())
			.setParameter("password", user.getPassword());
	      return (User)query.uniqueResult();
	
}

@Override
public void updateUser(User user) 
{
	Session session=sessionFactory.getCurrentSession();
	session.update(user);
	
	
}
 
@Override
public User getUser(String email) 
{
	Session session=sessionFactory.getCurrentSession();
	User user=(User)session.get(User.class,email);
	return user;
}


}
