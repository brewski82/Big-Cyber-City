/** ------------------------------------------------------------
 * PostCommentController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Jan 13, 2009 ]
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

import com.bigcybercity.entity.Comment;
import com.bigcybercity.entity.User;
import com.bigcybercity.security.UserId;
import com.bigcybercity.service.MailService;
import com.bigcybercity.service.UserService;

/**
 * Controller for posting a comment
 */

public class PostCommentController extends AbstractFormController {

	UserService userService;
	private MailService mailService;

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public PostCommentController() {
		setCommandClass(Comment.class);
		setCommandName("comment");
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
		Comment comment = (Comment) arg2;

		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			arg1.sendRedirect("/login.jsp");
			return null;
		}

		UserId userSession = (UserId) auth.getPrincipal();
		comment.setFromId(userSession.getId());
		comment.setType("profile");
		comment.setApproved(true);
		Calendar cal = Calendar.getInstance();
		comment.setDate(cal.getTime());
		if (userService.areFriends(comment.getFromId(), comment.getToId())
				|| comment.getFromId() == comment.getToId()) {
			userService.postComment(comment);
			userService.addCommentAlert(comment.getFromId(), comment.getToId());
		}

		mailService.sendCommentEmail(comment.getToId());
		arg1.sendRedirect("/profile.htm?id=" + comment.getToId());

		// TODO Auto-generated method stub
		return null;
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

		String idString = arg0.getParameter("id");
		long id = 0;
		if (idString != null) {
			try {
				id = (long) Integer.parseInt(idString);
			} catch (Exception e) {
				arg1.sendRedirect("/login.jsp");
				return null;
			}
		}

		Map<String, Object> model = new HashMap<String, Object>();
		User user = userService.getUser(id);
		if (user == null) {
			arg1.sendRedirect("/login.jsp");
			return null;
		}
		model.put("user", user);

		// ensure they are friends
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			arg1.sendRedirect("/login.jsp");
			return null;
		}

		UserId userSession = (UserId) auth.getPrincipal();
		if (!(userService.areFriends(user.getId(), userSession.getId()))
				&& user.getId() != userSession.getId())
			model.put("error", true);

		return new ModelAndView("postComment", model);

	}

}
