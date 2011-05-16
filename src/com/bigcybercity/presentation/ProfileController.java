/** ------------------------------------------------------------
 * ProfileController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.bigcybercity.security.UserId;
import com.bigcybercity.service.MailService;
import com.bigcybercity.service.UserService;
import com.bigcybercity.util.Profile;

/**
 * The Big Cyber City user profile page
 */
public class ProfileController extends AbstractCommandController {

	/**
	 * injected by Spring
	 */
	private UserService userService;
	private MailService mailService;

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ProfileController() {
		setCommandClass(ProfileId.class);
		setCommandName("id");
	}

	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {

		long requesterId = getRequesterId();

		ProfileId id = (ProfileId) arg2;
		Profile profile = userService.getProfile(id.getId(), requesterId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", profile.getUser());
		model.put("photos", profile.getPhotos());
		model.put("comments", profile.getComments());
		model.put("friends", profile.getFriends());
		


		return new ModelAndView("profile", model);
	}

	public String comment() {
		return null;

	}
	
	public long getRequesterId() {
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		UserId userSession = null;

		long requesterId = 0;
		if(auth != null) {
			userSession = (UserId) auth.getPrincipal();
			requesterId = userSession.getId();
		} 
		
		return requesterId;
	}
	
	public void requestFriend(long friendId) {
		
		long requesterId = getRequesterId();
		
		if(requesterId == 0 || requesterId == friendId)
			return;
		
		userService.addFriendAlert(requesterId, friendId);
		mailService.sendFriendReqEmail(friendId);
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

class ProfileId {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
