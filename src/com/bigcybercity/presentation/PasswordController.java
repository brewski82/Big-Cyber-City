/** ------------------------------------------------------------
 * PasswordController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Feb 3, 2009 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bigcybercity.service.MailService;

/** 
 *
 */

public class PasswordController implements Controller {
	private MailService mailService;

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView("password");
	}
	
	public String sendPassword(String email) {
		mailService.sendPassword(email);
		return "Please check your email for your password!";
	}

}
