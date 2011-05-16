/** ------------------------------------------------------------
 * User.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
* Represents a User in the database
*
*/

@Entity
@Table(name = "USERS")
public class User {
	private long id;
	private String email, password, displayName, age, location,
			relationshipStatus, gender, zip;
	private String displayPic, displayPicPath, about;
	private Calendar birthdate;
	private List<Photo> photos;
	private List<Comment> comments;
	private List<Friend> friends;
	private List<Mail> sentMail, recMail;
	private List<Alert> alerts;
	private Date lastLogin;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Column(name = "pic")
	public String getDisplayPic() {
		return displayPic;
	}

	public void setDisplayPic(String displayPic) {
		this.displayPic = displayPic;
	}

	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Temporal(TemporalType.DATE)
	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
		Integer age = Calendar.getInstance().get(Calendar.YEAR)
				- birthdate.get(Calendar.YEAR);
		if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) < birthdate
				.get(Calendar.DAY_OF_YEAR))
			age--;
		setAge(age.toString());
	}

	@Transient
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "relationship_status")
	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAbout() {
		return about;
	}

	@OneToMany(mappedBy = "toId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	public void setDisplayPicPath(String displayPicPath) {
		this.displayPicPath = displayPicPath;
	}
	
	@Column(name = "pic_path")
	public String getDisplayPicPath() {
		return displayPicPath;
	}

	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	@OneToMany(targetEntity = Mail.class, mappedBy = "fromId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Mail> getSentMail() {
		return sentMail;
	}

	public void setSentMail(List<Mail> sentMail) {
		this.sentMail = sentMail;
	}

	@OneToMany(targetEntity = Mail.class, mappedBy = "toId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Mail> getRecMail() {
		return recMail;
	}

	public void setRecMail(List<Mail> recMail) {
		this.recMail = recMail;
	}
	
	@OneToMany(targetEntity = Alert.class, mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Alert> getAlerts() {
		return alerts;
	}
	
	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	@Column(name = "last_login")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


}
