/** ------------------------------------------------------------
 * AdminController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Jan 29, 2009 ]
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

import com.bigcybercity.security.UserId;
import com.bigcybercity.service.UserService;

/** 
 *
 */

public class AdminController implements Controller {
	
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

		return new ModelAndView("admin");
	}
	
	public String deleteAccount(long id) {
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			return "Please log in to continue";
		}
		
		UserId userSession = (UserId) auth.getPrincipal();
		
		if (userSession.getId() != 1)
			return "Error";
		
		userService.deleteUser(id);
		
		return "Successfully deleted account " + id;
	}

}
