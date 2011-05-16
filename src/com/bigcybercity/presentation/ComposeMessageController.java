/** ------------------------------------------------------------
 * ComposeMessageController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 20, 2008 ]
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

import com.bigcybercity.dao.MailDao;
import com.bigcybercity.entity.Mail;
import com.bigcybercity.entity.User;
import com.bigcybercity.security.UserId;
import com.bigcybercity.service.MailService;
import com.bigcybercity.service.UserService;

/**
 * Controller for creating messages
 */

public class ComposeMessageController extends AbstractFormController {
	MailDao mailDao;
	UserService userService;
	private MailService mailService;

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}

	public ComposeMessageController() {
		setCommandClass(Mail.class);
		setCommandName("mail");
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

		Mail message = (Mail) arg2;
		
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			arg1.sendRedirect("/login.jsp");
			return null;
		}
		UserId userSession = (UserId) auth.getPrincipal();
		message.setFromId(userSession.getId());
		Calendar cal = Calendar.getInstance();
		message.setDate(cal.getTime());
		mailDao.sendMessage(message);
		mailService.sendMessageEmail(message.getToId());
		arg1.sendRedirect("/outbox.htm");
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
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if (auth == null) {
			arg1.sendRedirect("/login.jsp");
			return null;
		}
		UserId userSession = (UserId) auth.getPrincipal();
		Map<String, Object> model = new HashMap<String, Object>();

		// Check to see if we are replying to a message
		String reply = arg0.getParameter("reply");
		String to = arg0.getParameter("to");
		long replyId = 0, toId = 0;
		if (reply != null) {
			try {
				replyId = (long) Integer.parseInt(reply);
			} catch (Exception e) {
				arg1.sendRedirect("/login.jsp");
				return null;
			}
		}

		if (to == null) {
			arg1.sendRedirect("/login.jsp");
			return null;
		}

		try {
			toId = (long) Integer.parseInt(to);
		} catch (Exception e) {
			arg1.sendRedirect("/login.jsp");
			return null;
		}

		if (replyId != 0) {
			Mail message = mailDao.getMessage(replyId, userSession.getId());
			model.put("reply", true);
			model.put("message", message.getBody());
		}
		
		User user = userService.getUser(toId);
		if (user == null) {
			arg1.sendRedirect("/login.jsp");
			return null;
		}
		model.put("user", user);

		return new ModelAndView("composeMessage", model);
	}

}
