package com.niit.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDAO;
import com.niit.model.ErrorClazz;
import com.niit.model.ProfilePicture;

@RestController
public class ProfilePictureController 
{
@Autowired
private ProfilePictureDAO profilePictureDAO;

//call this url directly from html
@RequestMapping(value="/uploadprofilepicture",method=RequestMethod.POST)
public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image, HttpSession session)
{
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null)
	{
		ErrorClazz errorClazz=new ErrorClazz(5, "Unauthorized access please login again....");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}	

	ProfilePicture profilePicture =new ProfilePicture();
	profilePicture.setEmail(email);
	profilePicture.setImage(image.getBytes());
	profilePictureDAO.uploadProfilePicture(profilePicture);
	return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
	
}

@RequestMapping(value="/getimage" ,method=RequestMethod.GET)
public @ResponseBody byte[] getProfilePicture(@RequestParam String email,HttpSession session)
{
	String authEmail = (String)session.getAttribute("loggedInUser");
	if(authEmail==null)
	{
		return null;
	}
    ProfilePicture profilePicture=profilePictureDAO.getProfilePicture(email);
    if(profilePicture==null)
    {
    	return null;
    }
    else
    {
    	return profilePicture.getImage();
    }
}
}


