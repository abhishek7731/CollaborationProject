package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Forum_Table")
public class Forum 
{
@Id
@GeneratedValue
private int Forumid;
private String ForumContent;
private String ForumTitle;
private String username;
private Date ForumDate;
private String ForumStatus;
public int getForumid() {
	return Forumid;
}
public void setForumid(int forumid) {
	Forumid = forumid;
}
public String getForumContent() {
	return ForumContent;
}
public void setForumContent(String forumContent) {
	ForumContent = forumContent;
}
public String getForumTitle() {
	return ForumTitle;
}
public void setForumTitle(String forumTitle) {
	ForumTitle = forumTitle;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Date getForumDate() {
	return ForumDate;
}
public void setForumDate(Date forumDate) {
	ForumDate = forumDate;
}
public String getForumStatus() {
	return ForumStatus;
}
public void setForumStatus(String forumStatus) {
	ForumStatus = forumStatus;
}

}
