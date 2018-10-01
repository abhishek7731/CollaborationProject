package com.niit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Job_Table")
public class Job 
{
	@Id
	@GeneratedValue
private int id;
	@Column(nullable=false)
private String jobtitle;
private String jobdescription;
private String skillrequired;
private String salary;
private String experience;
private String location;
private String companyname;
private Date postedOn;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getJobtitle() {
	return jobtitle;
}
public void setJobtitle(String jobtitle) {
	this.jobtitle = jobtitle;
}
public String getJobdescription() {
	return jobdescription;
}
public void setJobdescription(String jobdescription) {
	this.jobdescription = jobdescription;
}
public String getSkillrequired() {
	return skillrequired;
}
public void setSkillrequired(String skillrequired) {
	this.skillrequired = skillrequired;
}
public String getSalary() {
	return salary;
}
public void setSalary(String salary) {
	this.salary = salary;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) { 
	this.experience = experience;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getCompanyname() {
	return companyname;
}
public void setCompanyname(String companyname) {
	this.companyname = companyname;
}
public Date getPostedOn() {
	return postedOn;
}
public void setPostedOn(Date postedOn) {
	this.postedOn = postedOn;
}
}
