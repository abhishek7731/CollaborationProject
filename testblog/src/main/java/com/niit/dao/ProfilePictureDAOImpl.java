package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ProfilePicture;

@Repository("profilepictureDAO")
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO
{
     @Autowired
     private SessionFactory sessionFactory;
	@Override
	public void uploadProfilePicture(ProfilePicture profilePicture) 
	{
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);
		
	}

	@Override
	public ProfilePicture getProfilePicture(String email) //we are passing email id and getting image
	{
		Session session=sessionFactory.getCurrentSession();
       ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, email);
	return profilePicture;
	
	}

}
