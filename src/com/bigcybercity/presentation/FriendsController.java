/** ------------------------------------------------------------
 * FriendsController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
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
 *
 */

public class FriendsController extends ListItemsController {
	/**
	 * injected by Spring
	 */
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 */
	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		Id id = (Id) arg2;
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", userService.getUser(id.getId()));
		model.put("page", "Friends");
		
		return new ModelAndView("friends", model);
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
		results = userService.getFriends(id, requesterId, pageNumber);
				
		return makeList(results, pageNumber,
				userService.getNumOfFriends(id));
	}
	
	public void deleteFriend(long id) {
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null)
			return;
		
		UserId userSession = (UserId) auth.getPrincipal();
		userService.deleteFriend(userSession.getId(), id);


	}

}
