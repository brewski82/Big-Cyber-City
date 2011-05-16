/** ------------------------------------------------------------
 * EditProfileController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 13, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

import com.bigcybercity.entity.User;
import com.bigcybercity.security.UserId;
import com.bigcybercity.service.UserService;
import com.bigcybercity.util.ConvertDate;
import com.bigcybercity.util.ForHTML;

/** 
 *
 */

public class EditProfileController extends AbstractFormController {
	/**
	 * injected by Spring
	 */
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public EditProfileController() {
		setCommandClass(User.class);
		setCommandName("user");
	}
	

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#processFormSubmission(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			arg1.sendRedirect("/login.htm");
			return null;
		}
		UserId userSession = (UserId) auth.getPrincipal();
		Map<String, Object> model = new HashMap<String, Object>();
		User user = userService.getUser(userSession.getId());
		User updateUser = (User) arg2;
		user.setDisplayName(ForHTML.forHTML(updateUser.getDisplayName()));
		if (updateUser.getGender().equals("Male"))
			user.setGender("Male");
		else
			user.setGender("Female");
		user.setLocation(ForHTML.forHTML(updateUser.getLocation()));
		if (updateUser.getRelationshipStatus().equals("Single"))
			user.setRelationshipStatus("Single");
		else if (updateUser.getRelationshipStatus().equals("Taken"))
			user.setRelationshipStatus("Taken");
		else 
			user.setRelationshipStatus("Married");
		if (updateUser.getZip().matches("\\d\\d\\d\\d\\d"))
			user.setZip(updateUser.getZip());
		user.setAbout(updateUser.getAbout());
			
		String day =	arg0.getParameter("day");
		String month = arg0.getParameter("month");
		String year = arg0.getParameter("year");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		user.setBirthdate(cal);
		userService.updateUser(user);
		model.put("day", day);
		model.put("year", year);
		model.put("month", month);
		model.put("user", user);
		model.put("updated", true);
		model.put("birthdate", ConvertDate.birthdate(user.getBirthdate()));
		return new ModelAndView("editProfile", model);
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#showForm(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView showForm(HttpServletRequest arg0,
			HttpServletResponse arg1, BindException arg2) throws Exception {
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null)
			arg1.sendRedirect("/login.htm");
		UserId userSession = (UserId) auth.getPrincipal();
		Map<String, Object> model = new HashMap<String, Object>();
		User user = userService.getUser(userSession.getId());
		Calendar cal = user.getBirthdate();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		model.put("day", day);
		model.put("year", year);
		model.put("month", month);
		model.put("user", user);
		return new ModelAndView("editProfile", model);
	}

}
