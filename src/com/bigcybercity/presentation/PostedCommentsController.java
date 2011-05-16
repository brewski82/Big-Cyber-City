/** ------------------------------------------------------------
 * PostedCommentsController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 13, 2008 ]
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
 * View all of a user's posted comments
 */

public class PostedCommentsController extends ListItemsController {
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
		if (auth == null)
			arg1.sendRedirect("/login.htm");
		
		
		UserId userSession = (UserId) auth.getPrincipal();;

		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", userService.getUser(userSession.getId()));
		model.put("page", "Posted Comments");
		model.put("viewComments", true);
		
		return new ModelAndView("postedComments", model);
	}
	
	public String viewPage(int id, int pageNumber) {

		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null)
			return "Please log in to continue";
		
		
		UserId userSession = (UserId) auth.getPrincipal();;

		
		List<ListItem> results = new ArrayList<ListItem>();
		results = userService.getPostedComments(userSession.getId(), pageNumber);
				
		return makeList(results, pageNumber,
				userService.getNumOfPostedComments(userSession.getId()));
	}
	
	public void deleteComment(long id) {
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null)
			return;
		
		UserId userSession = (UserId) auth.getPrincipal();
		userService.deleteComment(id, userSession.getId());
	}

}
