/** ------------------------------------------------------------
 * RegisterController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Jan 7, 2009 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bigcybercity.service.MailService;
import com.bigcybercity.service.SqlService;

/** 
 *
 */

public class RegisterController implements Controller {

	private SqlService sqlService;
	private MailService mailService;

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		return new ModelAndView("register");
	}

	public String reg(String email, String password) {
		if (password.length() < 5)
			return "Password must be 5 or more characters";

		long rnd = sqlService.addUnverifiedUser(email, password);

		// Add email here!
		String subject = "Confirm your Big Cyber City Account!";
		String body = "Your confirmation code: " + rnd + "  " 
				+ "\n\n\n Go to http://www.bigcybercity.com/confirmAccount.htm and enter your confirmation code to confirm your account!";

		mailService.sendEmail(email, subject, body);
		
		return "Check your email to complete the registration";
	}

}
