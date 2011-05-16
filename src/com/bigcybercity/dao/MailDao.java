/** ------------------------------------------------------------
 * MailDao.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 20, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.dao;

import com.bigcybercity.entity.Mail;

/** 
 * Contains methods for accessing mail objects
 */

public interface MailDao {

	Mail getMessage(long id, long userId);
	
	void sendMessage(Mail message);
}
