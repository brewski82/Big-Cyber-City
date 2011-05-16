package com.bigcybercity.presentation;

import junit.framework.TestCase;

import org.springframework.web.servlet.ModelAndView;

import com.bigcybercity.presentation.HomeController;


public class HomeControllerTests extends TestCase {
    public void testHandleRequestView() throws Exception{		
        HomeController controller = new HomeController();
        ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("home", modelAndView.getViewName());
    }


}
