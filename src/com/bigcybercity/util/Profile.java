/** ------------------------------------------------------------
 * Profile.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.util;

import java.util.List;

import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;

/** 
 * Information in a Big Cyber City profile
 */

public class Profile {
	User user;
	List<ListItem> comments;
	List<Photo> photos;
	List<ListItem> friends;
	/** 
	 *
	 * @param user
	 * @param comments
	 * @param friends
	 */
	/** 
	 *
	 * @param user
	 * @param comments
	 * @param photos
	 * @param friends
	 */
	public Profile(User user, List<ListItem> comments,
			List<Photo> photos, List<ListItem> friends) {
		super();
		this.user = user;
		this.comments = comments;
		this.photos = photos;
		this.friends = friends;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<ListItem> getComments() {
		return comments;
	}
	public void setComments(List<ListItem> comments) {
		this.comments = comments;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public List<ListItem> getFriends() {
		return friends;
	}
	public void setFriends(List<ListItem> friends) {
		this.friends = friends;
	}


}
