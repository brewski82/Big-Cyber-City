/** ------------------------------------------------------------
 * HomeController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * The Big Cyber City home page
 */
public class HomeController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return new ModelAndView("home");
	}

}
