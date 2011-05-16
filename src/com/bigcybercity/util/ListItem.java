/** ------------------------------------------------------------
 * ListItem.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 7, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.util;

/**
 * Contains general information used to display a list of friends, comments, etc
 */

public class ListItem {
	long id, userId;
	String userName, info, contents, photo;
	boolean canDelete;

	public ListItem(long id, long userId, String userName, String photo,
			String info, String contents, boolean canDelete) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.photo = photo;
		this.info = info;
		this.contents = contents;
		this.canDelete = canDelete;
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
