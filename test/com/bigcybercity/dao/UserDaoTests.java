/**
 * 
 */
package com.bigcybercity.dao;

import java.util.List;
import java.util.Random;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.bigcybercity.entity.Comment;
import com.bigcybercity.entity.Friend;
import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;

/**
 * @author wbruschi
 *
 */
public class UserDaoTests extends AbstractTransactionalDataSourceSpringContextTests {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] {"classpath:test-context.xml"};
	}
	
	public void testGetUser() {
		User user = userDao.getUser(1);
		assertEquals("User Id", 1, user.getId());
		User user2 = userDao.getUser(user.getEmail());
		assertEquals("Email: ", user2.getEmail(), user.getEmail());
	}
	
	public void testGetComments() {
		Random rand = new Random();
		int randUserId = rand.nextInt(50);
		List<Comment> comments = userDao.getComments(randUserId, 0, 1000);
		assertEquals("Num of comments", comments.size(), userDao.getNumOfComments(randUserId));
		for(Comment comment: comments) {
			assertNotNull(comment.getComment());
		}		
	}
	
	public void testFriends() {
		Random rand = new Random();
		int randUserId = rand.nextInt(50);
		List<Friend> friends = userDao.getRandomFriends(randUserId, 12);
		int numOfFriends = userDao.getNumOfFriends(randUserId);
		if(numOfFriends > 12)
			assertEquals("Random friends", 12, friends.size());
		else
			assertEquals("Random friends", numOfFriends, friends.size());
		friends = userDao.getFriends(randUserId, 0, 1000);
		assertEquals("Friends list", numOfFriends, friends.size());
		for(Friend friend : friends)
			assertNotNull(friend);		
	}
	
	public void testPhotos() {
		Random rand = new Random();
		int randUserId = rand.nextInt(10);
		List<Photo> photos = userDao.getPhotos(randUserId, 5);
		for(Photo photo: photos)
			assertNotNull(photo);
	}
}
