package com.bigcybercity.presentation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bigcybercity.security.UserId;



@Transactional(readOnly = true)
public class TestController implements Controller{
	
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String testvalue = null;
		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		if(auth == null) testvalue = "nope";
		else testvalue = auth.getName();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("details", auth.getDetails());
		model.put("p", auth.getPrincipal());
		UserId user = (UserId) auth.getPrincipal();
		model.put("id", user.getId());
//		List<Photo> photos = userDAO.test();
//		model.put("photos", photos);
//		model.put("x", photos.get(0));
		model.put("x", testvalue);
		model.put("y", "Begin Output");
		return new ModelAndView("testing", model);
	}

}
