/** ------------------------------------------------------------
 * Mail.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 20, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** 
 * Represents mail in the database
 */

@Entity
@Table(name = "MAIL")
public class Mail {
	long id, fromId, toId;
	String subject, body;
	boolean isRead, delFrom, delTo;
	Date date;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public long getToId() {
		return toId;
	}
	public void setToId(long toId) {
		this.toId = toId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Column(name = "isRead")
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public boolean isDelFrom() {
		return delFrom;
	}
	public void setDelFrom(boolean delFrom) {
		this.delFrom = delFrom;
	}
	public boolean isDelTo() {
		return delTo;
	}
	public void setDelTo(boolean delTo) {
		this.delTo = delTo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
