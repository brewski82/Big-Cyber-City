/** ------------------------------------------------------------
 * AccountController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Jan 2, 2009 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bigcybercity.entity.User;
import com.bigcybercity.security.UserId;
import com.bigcybercity.service.UserService;

/** 
 *
 */

public class AccountController implements Controller {
	
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		return new ModelAndView("account");
	}
	
	public String changeEmail(String email, String password) {
		
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			return "Please log in to continue";
		}
		
		UserId userSession = (UserId) auth.getPrincipal();
		User user = userService.getUser(userSession.getId());
		
		if (!password.equals(user.getPassword()))
			return "Check your password and try again";
		
		user.setEmail(email);
		userService.updateUser(user);
		
		return "Email Changed!";
	}
	
	public String changePassword(String oldPassword, String newPassword, String newPassword2) {
		if (!newPassword.equals(newPassword2))
			return "New passwords do not match!";
		
		if (newPassword.length() < 5)
			return "New password must be 5 or more characters";
		
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			return "Please log in to continue";
		}
		
		UserId userSession = (UserId) auth.getPrincipal();
		User user = userService.getUser(userSession.getId());
		
		if (!oldPassword.equals(user.getPassword()))
			return "Check your password and try again";
		
		user.setPassword(newPassword);
		userService.updateUser(user);
		
		return "Password Changed!";
	}
	
	public String deleteAccount() {
		
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			return "Please log in to continue";
		}
		
		UserId userSession = (UserId) auth.getPrincipal();
		
		userService.deleteUser(userSession.getId());
		
		return "Account Deleted! Please close the browser to end your session!";
	}

}
