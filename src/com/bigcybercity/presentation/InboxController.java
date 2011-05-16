/** ------------------------------------------------------------
 * InboxController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 20, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.bigcybercity.security.UserId;
import com.bigcybercity.service.UserService;
import com.bigcybercity.util.ListItem;

/** 
 * Inbox
 */

public class InboxController extends ListItemsController {

	/**
	 * injected by Spring
	 */
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractCommandController#handle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
				
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		UserId userSession = null;

		long requesterId = 0;
		if(auth != null) {
			userSession = (UserId) auth.getPrincipal();
			requesterId = userSession.getId();
		} 
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", userService.getUser(requesterId));
		model.put("page", "Inbox");
		model.put("viewOutbox", true);
		
		return new ModelAndView("inbox", model);
	}
	
	public String viewPage(int pageNumber) {

		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		UserId userSession = null;

		long requesterId = 0;
		if(auth != null) {
			userSession = (UserId) auth.getPrincipal();
			requesterId = userSession.getId();
		} 

		
		List<ListItem> results = new ArrayList<ListItem>();
		results = userService.getInboxMail(requesterId, pageNumber);
				
		return makeList(results, pageNumber,
				(int) userService.getNumOfInbox(requesterId));
	}
	
	public void deleteMessage(long id) {
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		UserId userSession = null;

		long requesterId = 0;
		if(auth != null) {
			userSession = (UserId) auth.getPrincipal();
			requesterId = userSession.getId();
		} 
		
		userService.deleteInboxMessage(requesterId, id);
	}

}
