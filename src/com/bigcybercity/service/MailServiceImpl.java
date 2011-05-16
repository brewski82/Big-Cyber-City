/** ------------------------------------------------------------
 * MailServiceImpl.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 20, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.bigcybercity.entity.User;

/** 
 *
 */

public class MailServiceImpl implements MailService {

	private MailSender mailSender;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.MailService#sendEmail(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void sendEmail(String to, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setText(body);
		message.setSubject(subject);
		message.setFrom("support@bigcybercity.com");
		mailSender.send(message);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.MailService#sendCommentEmail(long)
	 */
	public void sendCommentEmail(long userId) {
		String body = "Dear "
				+ getName(userId)
				+ "\n\n\n"
				+ "Someone wrote a comment on your profile.  To find out who, log into http://www.bigcybercity.com"
				+ "\n\n\nSincerely,\nBig Cyber City Support";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(getEmail(userId));
		message.setText(body);
		message.setSubject("You have new comments!");
		message.setFrom("support@bigcybercity.com");
		mailSender.send(message);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.MailService#sendFriendReqEmail(long)
	 */
	public void sendFriendReqEmail(long userId) {
		String body = "Dear "
				+ getName(userId)
				+ "\n\n\n"
				+ "Someone in Big Cyber City wants to be your friend.  To find out who, log into http://www.bigcybercity.com"
				+ "\n\n\nSincerely,\nBig Cyber City Support";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(getEmail(userId));
		message.setText(body);
		message.setSubject("You have new friend requests!");
		message.setFrom("support@bigcybercity.com");
		mailSender.send(message);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.MailService#sendMessageEmail(long)
	 */
	public void sendMessageEmail(long userId) {
		String body = "Dear "
				+ getName(userId)
				+ "\n\n\n"
				+ "Someone sent you a message in Big Cyber City.  To find out who, log into http://www.bigcybercity.com"
				+ "\n\n\nSincerely,\nBig Cyber City Support";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(getEmail(userId));
		message.setText(body);
		message.setSubject("You have new messages!");
		message.setFrom("support@bigcybercity.com");
		mailSender.send(message);

	}

	private String getEmail(long userId) {
		return userService.getUser(userId).getEmail();
	}

	private String getName(long userId) {
		return userService.getUser(userId).getDisplayName();
	}

	/**
	 * 
	 * @see com.bigcybercity.service.MailService#sendPassword(java.lang.String)
	 */
	public void sendPassword(String email) {
		User user = userService.getUser(email);
		if (user == null)
			return;

		String body = "Dear " + user.getDisplayName() + "\n\n\n"
				+ "Here is your Big Cyber City password: " + user.getPassword()
				+ "\n\n http://www.bigcybercity.com"
				+ "\n\n\nSincerely,\nBig Cyber City Support";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setText(body);
		message.setSubject("Big Cyber City Account Information");
		message.setFrom("support@bigcybercity.com");
		mailSender.send(message);

	}

}
