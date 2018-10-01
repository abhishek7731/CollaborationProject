package com.niit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Person_Table")
public class Person 
{
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getFirstaname() {
		return firstaname;
	}
	public void setFirstaname(String firstaname) {
		this.firstaname = firstaname;
	}

	public String getLastname() 
	{
		return lastname;
	}
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPhonenumber() 
	{
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) 
	{
		this.phonenumber = phonenumber;
	}
	@Id
	@GeneratedValue
private int id;
private String firstaname;

private String lastname;
@Column(unique=true,nullable=false) //iska matlab email hamesha unique hona chaiya and empty nhi hona chaiya
private String email;
private String phonenumber;
}
