package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Notification;

@Transactional
@Repository("notificationDAO")
public class NotificationDAOImpl implements NotificationDAO
{
	@Autowired
	SessionFactory sessionFactory;


	@Override
	public void addNotification(Notification notification) 
	{
		try 
		{
			sessionFactory.getCurrentSession().save(notification);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}

	}


	@Override
	public List<Notification> getNotificationNotViewed(String email) 
	{
		Session session =sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Notification where viewed=0 and email=:email").setParameter("email", email);
			
			return query.list();
		
	

	}

	@Override
	public Notification getNotification(int id) 
	{
		
		Session session =sessionFactory.getCurrentSession();
         Notification notification=(Notification)session.get(Notification.class, id);
         return notification;
	}


	@Override
	public void updateNotification(int id) 
	{
		Session session =sessionFactory.getCurrentSession();
        Notification notification=(Notification)session.get(Notification.class, id);
        notification.setViewed(true);
        session.update(notification);
		
	}

}
