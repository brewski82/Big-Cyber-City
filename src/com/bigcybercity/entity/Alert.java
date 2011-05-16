/** ------------------------------------------------------------
 * Alert.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Jan 10, 2009 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * Holds alerts for user's, such as new friend requests, new mail, etc
 */

@Entity
@Table(name = "ALERTS")
public class Alert {
	private long id, userId, fromId;
	private String type, fromName;
	

	@Id
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
	public long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
}
