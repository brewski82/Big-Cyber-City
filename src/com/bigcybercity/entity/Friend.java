/** ------------------------------------------------------------
 * Friend.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 7, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * Represents a friend in the database
 */

@Entity
@Table(name = "FRIENDS")
public class Friend {
	long id, userId, friendId;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}
	

}
