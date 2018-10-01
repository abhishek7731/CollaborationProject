/*package com.niit.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.PersonDAO;
import com.niit.model.Person;
import com.niit.model.ErrorClazz;

@RestController
public class PersonController {
	@Autowired
	private PersonDAO personDAO;

	public PersonController() {
		System.out.println("PersonController bean is created");
	}

	// RESPONSEBODY WILL CONVERT LIST OF PERSON IN JASON OBJECT JO POSTMAN MEI
	// DIKHTA HAI
	// GET ISSLIYA USE KAR RAHA KYUKI HUMKO DATA RETURN KARNA AGR INSERT KARNA
	// HOTA WE USE POST REQUEST
	// Request body will get data from the body and convert from jason to java
	@RequestMapping(value = "/getallpersons", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPersons() {
		List<Person> persons = personDAO.getAllPersons();
		if (persons.isEmpty()) {
			System.out.println("No record found");
			ErrorClazz errorClazz = new ErrorClazz(2, "No record found");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.NO_CONTENT);
			// 2ND CALL BACK FUNTION IN PERSONCONTROLLER.JS
			// RESPONSE.DATA=errorClazz
			// response.status=404

		} else {
			return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
			// 1st call back function
			// response.data=person[List<Person>]
			// response.status=202
		}

	}

	@RequestMapping(value = "/saveperson", method = RequestMethod.POST)
	public ResponseEntity<?> addPerson(@RequestBody Person person) {
		try {
			personDAO.addPerson(person);
			return new ResponseEntity<Person>(person, HttpStatus.OK); // SUCCESS
		} catch (Exception e) {
			ErrorClazz errorClazz = new ErrorClazz(1, "Unable to insert persondetails..maybe email is null/duplicate");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getperson/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPerson(@PathVariable int id) {
		Person person = personDAO.getPerson(id);

		if (person == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // void is a
																	// class and
																	// return
																	// type is
																	// void
																	// means
																	// there is
																	// nothing
																	// to re	
			}

		else
			return new ResponseEntity<Person>(person, HttpStatus.OK);

	}

@RequestMapping(value="/deleteperson/{id}" ,method=RequestMethod.DELETE)
public ResponseEntity<?> deletePerson(@PathVariable int id){
try{
	
	personDAO.deletePerson(id);
	return new ResponseEntity<Void>(HttpStatus.OK);
}catch (Exception e) {
	ErrorClazz errorClazz=new ErrorClazz(3,"Unable to delete person details with id"+ id+e.getMessage());
	return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
}
	
	
}

@RequestMapping(value="/updateperson",method=RequestMethod.PUT)
public ResponseEntity<?> updatePerson(@RequestBody Person person){
try{
	personDAO.updatePerson(person);
	return new ResponseEntity<Person>(person,HttpStatus.OK);
}
catch(Exception e)
{
	ErrorClazz errorClazz=new ErrorClazz(4, "Unable to update person details"+e.getMessage());
	return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
}


}
}
	

*/