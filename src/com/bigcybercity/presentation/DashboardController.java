/** ------------------------------------------------------------
 * DashboardController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 13, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bigcybercity.entity.Alert;
import com.bigcybercity.entity.User;
import com.bigcybercity.security.UserId;
import com.bigcybercity.service.UserService;

/** 
 *
 */

public class DashboardController implements Controller {
	
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/** 
	 * 
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null)
			arg1.sendRedirect("/login.htm");
		UserId userSession = (UserId) auth.getPrincipal();
		User user = userService.getUser(userSession.getId());
		List<Alert> alerts = userService.getAlerts(userSession.getId());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		model.put("alerts", alerts);
		return new ModelAndView("dashboard", model);
	}
	
	public void deleteAlert(long alertId) {

		Alert alert = getAlert(alertId);
		
		if (alert == null)
			return;
		
		userService.deleteAlert(alert);
		
	}
	
	public void addFriend(long alertId) {
		Alert alert = getAlert(alertId);
		
		if (alert == null)
			return;
		
		userService.addFriend(alert.getUserId(), alert.getFromId());
		deleteAlert(alertId);		
	}
	
	private Alert getAlert(long alertId) {
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null)
			return null;
		UserId userSession = (UserId) auth.getPrincipal();
		
		Alert alert = null;
		List<Alert> alerts = userService.getAlerts(userSession.getId());
		
		for (Alert a : alerts) {
			if (a.getUserId() == userSession.getId() && a.getId() == alertId)
				alert = a;
		}
		return alert;
	}

}
