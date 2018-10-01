package com.niit.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDAO;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@RestController
public class UserController {
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		try {
			System.out.println(user.getFirstname());
			// checking if email is not unique
			if (!userDAO.isEmailUnique(user.getEmail())) {
				ErrorClazz errorClazz = new ErrorClazz(2, "Email id already exist");
				return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			userDAO.registration(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			ErrorClazz errorClazz = new ErrorClazz(1, "Something went wrong" + e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.PUT)

	// MIDDLEWARE WILL GET DATA FROM ANGULAR JS CLIENT
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User validUser = userDAO.login(user);
		if (validUser == null) // invalid credentials
		{
			ErrorClazz errorClazz = new ErrorClazz(4, "Invalid email/password...");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.UNAUTHORIZED); // errorClazz
																						// object
		} else // valid credentials
		{
			validUser.setOnline(true);
			userDAO.updateUser(validUser);
			session.setAttribute("loggedInUser", validUser.getEmail());
			System.out.println("Session Id" + session.getId());
			System.out.println("Session Attribute" + session.getAttribute("loggedInUser"));
			System.out.println("Created On" + session.getCreationTime());
			return new ResponseEntity<User>(validUser, HttpStatus.OK);
		}
	}

	/*@RequestMapping(value = "/getalljobs", method = RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session) {
		System.out.println("Session Id" + session.getId());
		System.out.println("Session Attribute" + session.getAttribute("loggedInUser"));
		System.out.println("Created On" + session.getCreationTime());
		String email = (String) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClazz errorClazz = new ErrorClazz(4, "Unauthorized access...please login...");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);

	}*/

	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session) {
		String email = (String) session.getAttribute("loggedInUser");

		if (email == null) {
			ErrorClazz errorClazz = new ErrorClazz(4, "Unauthorized access...please login...");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.UNAUTHORIZED);

		}

		User user = userDAO.getUser(email);
		user.setOnline(false);
		userDAO.updateUser(user);
		session.removeAttribute("loggedInUser");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/updateprofile", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfile(@RequestBody User user, HttpSession session) {
		// check for authentication
		String email = (String) session.getAttribute("loggedInUser");
		if (email == null) {
			ErrorClazz errorClazz = new ErrorClazz(4, "Unauthorized access...please login...");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.UNAUTHORIZED);

		}
		try {
			userDAO.updateUser(user);
		} catch (Exception e) {
			ErrorClazz errorClazz = new ErrorClazz(5, "Unable to update user profile details...");

		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

}
