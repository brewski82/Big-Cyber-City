/** ------------------------------------------------------------
 * UserDao.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.dao;

import java.util.List;

import com.bigcybercity.entity.Alert;
import com.bigcybercity.entity.Comment;
import com.bigcybercity.entity.Friend;
import com.bigcybercity.entity.Mail;
import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;

/**
* Interface for User DAOs
*
*/
public interface UserDao {

	/** 
	 * Finds and returns a user by their unique id
	 * 
	 * @param id the id of the user
	 * @return User a User entity
	 */
	User getUser(long id);

	/**
	 * Finds and returns a user by their unique email address
	 * 
	 * @param email the user's email address
	 * @return a User entity
	 */
	User getUser(String email);

	/** 
	 * Creates a new user
	 * 
	 * @param user
	 */
	void createUser(User user);
	
	/** 
	 * Deletes the user 
	 * 
	 * @param user
	 */
	void deleteUser(long id);
	
	/** 
	 * Updates a user in the database
	 * @param id the user's unique id
	 */
	void updateUser(User user);
	
	/** 
	 * Retrieves a user's comments.  Comments are sorted by date
	 * 
	 * @param id the user's unique id
	 * @param start the beginning comment number
	 * @param end the ending comment number
	 * @return a list of comments
	 */
	List<Comment> getComments(long id, int start, int end);
	
	/** 
	 * Returns the number of comments a user has
	 * @param id the user's unique id
	 * @return the number of comments
	 */
	int getNumOfComments(long id);
	
	/** 
	 * Gets a user's photos
	 * 
	 * @param id the user's unique id
	 * @param howMany the number of photos to return
	 * @return a list of photos
	 */
	List<Photo> getPhotos(long id, int howMany);
	
	/** 
	 * Gets a list of random friends
	 * @param id the user's unique id
	 * @param howMany the number of friends to return
	 * @return a list of random friends
	 */
	List<Friend> getRandomFriends(long id, int howMany);
	
	/** 
	 * Retrieves a user's friends
	 * @param id the user's unique id
	 * @param start the first friend to get
	 * @param end the last friend to get
	 * @return a list of friends
	 */
	List<Friend> getFriends(long id, int start, int end);
	
	/** 
	 * Counts the number of friends a user has
	 * @param id the user's unique id
	 * @return the number of friends the user has
	 */
	int getNumOfFriends(long id);
	
	/** 
	 * Calculates the number of posted comments for the user
	 * 
	 * @param id the user's unique id
	 * @return the number of posted comments
	 */
	List<Comment> getPostedComments(long id, int start, int end);
	
	/** 
	 *  Retrieves a list of the user's posted comments
	 *  
	 * @param id the user's unique id
	 * @param start first comment to return
	 * @param end last comment to return
	 * @return a list of posted comments
	 */
	Integer getNumOfPostedComments(long id);
	
	/** 
	 * Deletes a friend entry from the database
	 * @param id the primary key of the row to delete
	 * @param userId the user's unique id
	 */
	void deleteFriend(long userId, long friendId);
	
	/** 
	 * Delete's a comment from the database
	 * @param id the id of the comment
	 * @param userId the id of the user who wrote or recieved the comment
	 */
	void deleteComment(long id, long userId);
	
	

	/** 
	 * Gets a number of inbox mail for a user
	 * @param id the user's unique id
	 * @param start the first message to get
	 * @param end the last message to get
	 * @return a list of mail items
	 */
	List<Mail> getInboxMail(long id, int start, int end);
	
	/** 
	 * Returns the number of inbox messages a user has
	 * @param id the user's unique id
	 * @return the number of messages the user has
	 */
	Integer getNumOfInbox(long id);
	

	/** 
	 * Gets a number of outbox mail
	 * @param id the user's unique id
	 * @param start the first message to get
	 * @param end the last message to get
	 * @return a list of mail items
	 */
	List<Mail> getOutboxMail(long id, int start, int end);
	
	/** 
	 * Returns the number of outbox messags for a user
	 * @param id the user's unique id
	 * @return the number of outbox messags the user has
	 */
	Integer getNumOfOutbox(long id);
	
	/** 
	 * Sets delFrom to true
	 * @param id the messages's unique id
	 */
	void deleteFrom(long id, long messageId);
	
	/** 
	 * Sets delTo to true
	 * @param id the message's unique id
	 */
	void deleteTo(long id, long messageId);
	
	/** 
	 * Gets a list of the user's alerts
	 * @param id
	 * @return
	 */
	List<Alert> getAlerts(long id);
	
	/** 
	 * Deletes an alert for a user
	 * @param alert
	 */
	void deleteAlert(Alert alert);
	
	/** 
	 * Adds friends
	 * @param f1
	 * @param f2
	 */
	void addFriend(Friend f1, Friend f2);

	
	/** 
	 * Adds an alert for a user
	 * @param alert
	 */
	void addAlert(Alert alert);
	
	/** 
	 * Gets a friend by id
	 * @param id
	 * @return
	 */
	Friend getFriend(long id);
	
	/** 
	 * Posts a comment
	 * @param comment
	 */
	void postComment(Comment comment);
	
	/** 
	 * Update's the last login field to the current date and time
	 * @param user
	 */
	void updateLastLogin(User user);
}
