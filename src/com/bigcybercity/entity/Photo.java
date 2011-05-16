/** ------------------------------------------------------------
 * Photo.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.entity;

import javax.persistence.*;

/**
 * Represents a Photo in the database
 * 
 */

@Entity
@Table(name = "PHOTOS")
public class Photo {
	public static String defaultUrl = "/photos/";
	public static String defaultPhoto = "noPhoto.GIF";
	public static String defaultPath = "/var/www/photos/";
	public static String profile = "profile";
	public static String largePrefix = "l";
	public static String mediumPrefix = "m";
	public static String smallPrefix = "s";

	private long photoId, id;
	private String url, fileName, path, caption, type;
	private boolean displayPic;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDisplayPic() {
		return displayPic;
	}

	public void setDisplayPic(boolean displayPic) {
		this.displayPic = displayPic;
	}

	@Transient
	public String getSrc() {
		return getUrl() + getFileName();
	}
}
