/** ------------------------------------------------------------
 * MailService.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 20, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

/** 
 * Controls the handling of internal mail 
 */

public interface MailService {
	
	public void sendEmail(String to, String subject, String body);
	
	public void sendCommentEmail(long userId);
	
	public void sendMessageEmail(long userId);
	
	public void sendFriendReqEmail(long userId);
	
	public void sendPassword(String email);

}
