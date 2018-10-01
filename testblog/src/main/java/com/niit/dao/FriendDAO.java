package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDAO 
{
	//email id of logged in user 
List<User> getSuggestedUsers(String email);

void addFriendRequest(Friend friend);
List<Friend> getPendingRequests(String email);
void acceptFriendRequest(Friend friend);
void deleteFriendRequest(Friend friend);
List<User> listOfFriends(String email);

} 
