/** ------------------------------------------------------------
 * ConfirmAccountController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Jan 15, 2009 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

import com.bigcybercity.service.SqlService;

/**
 * Prompts a user for their confirmation number after creating an account
 */

public class ConfirmAccountController extends AbstractFormController {

	private SqlService sqlService;

	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}

	public ConfirmAccountController() {
		setCommandClass(AccountNumber.class);
		setCommandName("accountNumber");
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

		String confirmNumStr = arg0.getParameter("confirmNumber");

		Map<String, Object> model = new HashMap<String, Object>();

		try {
			if (sqlService.confirmNewUser(Integer.parseInt(confirmNumStr)))
				model.put("success", true);
			else {
				model.put("error", true);
				model.put("num", "returned false");
			}
		} catch (Exception e) {
			model.put("error", true);
			model.put("num", "except");
		}

		//model.put("num", confirmNumStr);
		return new ModelAndView("confirmAccount", model);
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
		return new ModelAndView("confirmAccount");
	}

}

class AccountNumber {
	private long num;

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

}
