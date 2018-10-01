package com.niit.restcontroller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDAO;
import com.niit.dao.UserDAO;
import com.niit.model.ErrorClazz;
import com.niit.model.Job;
import com.niit.model.User;

@RestController
public class JobController {
	@Autowired
	private JobDAO jobDAO;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/addjob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		//CHECK FOR AUTHENTICATION
    	if(email==null)
    	{
			ErrorClazz errorClazz=new ErrorClazz(4,"Unauthorized access... please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
    	//CHECK FOR AUTHORIZATION[ROLE]
    	User user=userDAO.getUser(email);
    	if(!user.getRole().equals("ADMIN"))
    	{
    		ErrorClazz errorClazz=new ErrorClazz(5,"Access Denied...You are not authorized to post a job");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
    	}
    	try
    	{
    		
        job.setPostedOn(new Date());		
    	jobDAO.saveJob(job);
    	}
    	catch(Exception e)
    	{
    		ErrorClazz errorClazz=new ErrorClazz(6,"Unable to post job details");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
@RequestMapping(value="/getalljobs",method=RequestMethod.GET)	
public ResponseEntity<?> getAllJobs(HttpSession session)
{
	String email=(String)session.getAttribute("loggedInUser");
	
	
	if(email==null)
	{
		ErrorClazz errorClazz=new ErrorClazz(4,"Unauthorized access... please login..");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}


List<Job>jobs=jobDAO.getAllJobs();
return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
}

}