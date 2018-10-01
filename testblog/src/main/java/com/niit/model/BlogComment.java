package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="BlogComment_TABLE")
public class BlogComment 
{
@Id
@GeneratedValue
private int id;
@ManyToOne
private Blog blogPost;
@ManyToOne
private User commentedBy;
private String commentTxt;
private Date commentedOn;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Blog getBlogPost() {
	return blogPost;
}
public void setBlogPost(Blog blogPost) {
	this.blogPost = blogPost;
}
public User getCommentedBy() {
	return commentedBy;
}
public void setCommentedBy(User commentedBy) {
	this.commentedBy = commentedBy;
}
public String getCommentTxt() {
	return commentTxt;
}
public void setCommentTxt(String commentTxt) {
	this.commentTxt = commentTxt;
}
public Date getCommentedOn() {
	return commentedOn;
}
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}


}
