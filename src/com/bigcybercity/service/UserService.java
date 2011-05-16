/** ------------------------------------------------------------
 * UserService.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import java.util.List;

import org.hibernate.criterion.Criterion;

import com.bigcybercity.entity.Alert;
import com.bigcybercity.entity.Comment;
import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;
import com.bigcybercity.util.ListItem;
import com.bigcybercity.util.Profile;

/**
 * Provides methods to interact with Big Cyber City users
 */

/** 
 *
 */
public interface UserService {

	/**
	 * Gets information to display a user's profile
	 * 
	 * @param id
	 *            the users unique id
	 * @param requesterId
	 *            the unique id of the person making the request, if any
	 * @return a profile object containing the users information
	 */
	Profile getProfile(long id, long requesterId);

	/**
	 * Finds and returns a list of ten users that match the criteria
	 * 
	 * @param criterion
	 *            the criteria for the search
	 * @param pageNumber
	 *            the set of 10 users to return. page number 1 will return users
	 *            0 to 9, page number 2 will return 10 to 19 etc
	 * @return a list of 10 users
	 */
	List<ListItem> search(List<Criterion> criterion, int pageNumber);

	/**
	 * Returns the number of users in the database that match the given criteria
	 * 
	 * @param criterion
	 *            the criteria for the search
	 * @return the number of uses that match the search
	 */
	int numOfSearchResults(List<Criterion> criterion);

	/**
	 * Retrieves a user's comments
	 * 
	 * @param id
	 *            the unique id of the user
	 * @param requesterId
	 *            the unique id of the user making the request
	 * @param pageNum
	 *            the page of the comments
	 * @return a list of comments
	 */
	List<ListItem> getComments(long id, long requesterId, int pageNum);

	/**
	 * Counts the number of comments for the user
	 * 
	 * @param id
	 *            the user's unique id
	 * @return the number of comments
	 */
	int getNumOfComments(long id);

	/**
	 * Retrieve's a user's friends
	 * 
	 * @param id
	 *            the unique id of the user
	 * @param requesterId
	 *            the id of the user who make the request
	 * @param pageNumber
	 *            the page number of the friends
	 * @return a list of friends
	 */
	List<ListItem> getFriends(long id, long requesterId, int pageNumber);

	/**
	 * Counts the number of friends for a user
	 * 
	 * @param id
	 *            the user's unique id
	 * @return the number of friends
	 */
	int getNumOfFriends(long id);

	/**
	 * Retrieve a use from the database
	 * 
	 * @param id
	 *            the user's unique id
	 * @return a user
	 */
	User getUser(long id);
	
	User getUser(String email);

	/**
	 * Retrieves the user's posted comments
	 * 
	 * @param id
	 *            the user's unique id
	 * @param pageNumber
	 *            the page number to start
	 * @return a list of 10 posted comments
	 */
	List<ListItem> getPostedComments(long id, int pageNumber);

	/**
	 * Finds the number of posted comments for a user
	 * 
	 * @param id
	 *            the user's unique id
	 * @return the number of posted comments
	 */
	Integer getNumOfPostedComments(long id);

	/**
	 * Updates a user in the database
	 * 
	 * @param user
	 *            the user's unique id
	 */
	void updateUser(User user);

	/**
	 * Retrieve's a user's photos from the database
	 * 
	 * @param id
	 *            the user's unique id
	 * @return a list of photos
	 */
	List<Photo> getPhotos(long id);

	/**
	 * "Unfriends" a person - removes both people from each other's friends list
	 * 
	 * @param id
	 *            the id of the row in the friends table
	 * @param userId
	 *            the unique id of the person doing the removing
	 */
	void deleteFriend(long userId, long id);

	/**
	 * Removes a comment
	 * 
	 * @param id
	 *            the id of the comment
	 * @param userId
	 *            the id of the user who wrote or received the comment
	 */
	void deleteComment(long id, long userId);
	
	/** 
	 * Get a list of inbox mail
	 * @param id the user's unique id
	 * @param pageNumber which messages to get
	 * @return a list of messages
	 */
	List<ListItem> getInboxMail(long id, int pageNumber);
	
	/** 
	 * Returns the number of inbox messages a user has
	 * @param id the user's unique id
	 * @return the number of messages the user has
	 */
	Integer getNumOfInbox(long id);
	
	/** 
	 * Gets a list of outbox mail 
	 * @param id the user's unique id
	 * @param pageNumber which messages to get
	 * @return a list of outbox messages
	 */
	List<ListItem> getOutboxMail(long id, int pageNumber);
	
	/** 
	 * Returns the number of outbox messags for a user
	 * @param id the user's unique id
	 * @return the number of outbox messags the user has
	 */
	Integer getNumOfOutbox(long id);
	
	/** 
	 * Deletes an object from the user's inbox
	 * @param id the user's unique id
	 * @param messageId the message's unique id
	 */
	void deleteInboxMessage(long id, long messageId);
	
	/** 
	 * Deletes an object from the user's outbox
	 * @param id the user's unique id
	 * @param messageId the message's unique id
	 */
	void deleteOutboxMessage(long id, long messageId);
	
	/** 
	 * Deletes a user account from Big Cyber City!
	 * @param user the user to be deleted
	 */
	void deleteUser(long id); 
	
	/** 
	 * Gets a user's alerts
	 * @param id
	 * @return
	 */
	List<Alert> getAlerts(long id);
	
	
	/** 
	 * Delete's alerts for a user
	 * @param alert
	 */
	void deleteAlert(Alert alert);
	
	/** 
	 * Adds two friends
	 * @param id
	 * @param friendId
	 */
	void addFriend(long id, long friendId);
	
	/** 
	 * Adds a friend alert for a user
	 * @param requesterId
	 * @param friendId
	 */
	void addFriendAlert(long requesterId, long friendId);
	
	/** 
	 * Returns true if the two user's are friends
	 * @param id1
	 * @param id2
	 * @return
	 */
	boolean areFriends(long id1, long id2);
	
	/** 
	 * Posts a comment
	 * @param comment
	 */
	void postComment(Comment comment);
	
	/** 
	 * Adds a new comment alert for toId
	 * @param fromId
	 * @param toId
	 */
	void addCommentAlert(long fromId, long toId);
}
