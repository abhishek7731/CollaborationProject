package com.niit.restcontroller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDAO;
import com.niit.dao.NotificationDAO;
import com.niit.dao.UserDAO;
import com.niit.model.Blog;
import com.niit.model.ErrorClazz;
import com.niit.model.Notification;
import com.niit.model.User;


//@Controller+@RequestMapping
@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	NotificationDAO notificationDAO;
	
	/*@GetMapping("/demo")
	public ResponseEntity<?> demo(){
		Blog blog=new Blog();
		blog.setId(12);
		blog.setBlogName("API Controller");
		blog.setBlogContent("abcdefghi anska");
		blog.setStatus("NA");
		blog.setCreatedDate(new Date());
		blog.setAuthor("Abhishek001");
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}*/
@RequestMapping(value="/addblog",method=RequestMethod.POST)
public ResponseEntity<?> addBlog(@RequestBody Blog blog,HttpSession session)
{
	System.out.println(blog.getBlogcontent());
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null)
	{
		ErrorClazz errorClazz=new ErrorClazz(5, "Unauthorized access please login again....");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
     blog.setPostedOn(new Date());
     blog.setPostedBy(userDAO.getUser(email));
     
     try
     {
    	 blogDAO.addBlog(blog);
     }
catch(Exception e)
     {

	ErrorClazz errorClazz=new ErrorClazz(6,"Unable to inserted blog details..."+e.getMessage());
	return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
}
     return new ResponseEntity<Blog>(blog,HttpStatus.OK);
}

@RequestMapping(value="/approvedblogs",method=RequestMethod.GET)
public ResponseEntity<?> getApprovedBlogs(HttpSession session){
	
	
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null)
	{
		ErrorClazz errorClazz=new ErrorClazz(5, "Unauthorized access please login again....");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}

    List<Blog> approvedBlogs= blogDAO.getApprovedBlogs();
    return new ResponseEntity<List<Blog>>(approvedBlogs,HttpStatus.OK);
}

@RequestMapping(value="/getblog/{id}",method=RequestMethod.GET)
public ResponseEntity<?> getBlog(@PathVariable int id,HttpSession session){
	

	String email=(String)session.getAttribute("loggedInUser");
	if(email==null)
	{
		ErrorClazz errorClazz=new ErrorClazz(5, "Unauthorized access please login again....");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}

	Blog blog=blogDAO.getBlog(id);
	return new ResponseEntity<Blog>(blog,HttpStatus.OK);
}

@RequestMapping(value="/blogswaitingforapproval",method=RequestMethod.GET)
public ResponseEntity<?> getBlogWaitingForApproval(HttpSession session){
	
	
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null)
	{
		ErrorClazz errorClazz=new ErrorClazz(5, "Unauthorized access please login again....");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}

   //AUTHORIZATION-ONLY ADMIN CAN VIEW LIST OF BLOGS WAITING FOR APPROVAL
	User  user=userDAO.getUser(email);
	if(!user.getRole().equals("ADMIN")){
		ErrorClazz errorClazz=new ErrorClazz(6,"Access Denied...");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	List<Blog> blogWaitingforapproval=blogDAO.getBlogWaitingForApproval();
	return new ResponseEntity<List<Blog>>(blogWaitingforapproval,HttpStatus.OK );
}

@RequestMapping(value="/approveblogpost",method=RequestMethod.PUT)
public ResponseEntity<?> updateBlog(@RequestBody Blog blog,HttpSession session)
{
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null)
	{
		ErrorClazz errorClazz=new ErrorClazz(5, "Unauthorized access please login again....");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}	
 
	User  user=userDAO.getUser(email);
	if(!user.getRole().equals("ADMIN")){
		ErrorClazz errorClazz=new ErrorClazz(6,"Access Denied...");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}

	//how to update approvalstatus
	blog.setApprovalStatus(true);
	blogDAO.updateBlog(blog);
	
	Notification notification=new Notification();
	notification.setApprovalStatus("Approved");
	notification.setblogName(blog.getBlogName());
	notification.setEmail(blog.getPostedBy().getEmail());
	notificationDAO.addNotification(notification);
	
	
	
	return new ResponseEntity<Blog>(blog,HttpStatus.OK);
}

@RequestMapping(value="/rejectblogpost",method=RequestMethod.PUT)
public ResponseEntity<?> deleteBlog(@RequestBody Blog blog,@RequestParam String rejectionReason, HttpSession session)
{
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null)
	{
		ErrorClazz errorClazz=new ErrorClazz(5, "Unauthorized access please login again....");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}	
	Notification notification=new Notification();
	notification.setApprovalStatus("rejected");
	notification.setblogName(blog.getBlogName());
	notification.setEmail(blog.getPostedBy().getEmail());
	notification.setRejectionReason(rejectionReason);
	notificationDAO.addNotification(notification);
	
	blogDAO.deleteBlog(blog);
	
	return new ResponseEntity<Void>(HttpStatus.OK);
	
}
}
