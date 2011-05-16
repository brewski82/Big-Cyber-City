/** ------------------------------------------------------------
 * CommentsController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 11, 2008 ]
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
 * View all of a user's comments
 */

public class CommentsController extends ListItemsController {
	
	/**
	 * injected by Spring
	 */
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	/** 
	 * 
	 * 
	 */
	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		
		Id id = (Id) arg2;
		
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		UserId userSession = null;

		long requesterId = 0;
		if(auth != null) {
			userSession = (UserId) auth.getPrincipal();
			requesterId = userSession.getId();
		} 
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", userService.getUser(id.getId()));
		model.put("page", "Comments");
		boolean viewYourComments = false;
		if (requesterId == id.getId())
			viewYourComments = true;
		model.put("viewYourComments", viewYourComments);
		
		return new ModelAndView("comments", model);
	}
	
	public String viewPage(long id, int pageNumber) {

		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		UserId userSession = null;

		long requesterId = 0;
		if(auth != null) {
			userSession = (UserId) auth.getPrincipal();
			requesterId = userSession.getId();
		} 

		
		List<ListItem> results = new ArrayList<ListItem>();
		results = userService.getComments(id, requesterId, pageNumber);
				
		return makeList(results, pageNumber,
				userService.getNumOfComments(id));
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
