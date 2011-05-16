/** ------------------------------------------------------------
 * ViewPhoto.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.util;

import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;

/** 
 * Information displayed while viewing a photo
 */

public class ViewPhoto {
	User user;
	Photo photo;
	
	/** 
	 *
	 * @param user
	 * @param photo
	 */
	public ViewPhoto(User user, Photo photo) {
		super();
		this.user = user;
		this.photo = photo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}
