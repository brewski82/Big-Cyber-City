/** ------------------------------------------------------------
 * PhotoController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
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

import com.bigcybercity.entity.Photo;
import com.bigcybercity.security.UserId;
import com.bigcybercity.service.PhotoService;
import com.bigcybercity.util.ViewPhoto;

/** 
 *
 */

public class PhotoController extends AbstractCommandController {

	/**
	 * injected by Spring
	 */
	private PhotoService photoService;

	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}

	public PhotoController() {
		setCommandClass(PhotoId.class);
		setCommandName("id");
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractCommandController#handle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {

		final SecurityContext sc = SecurityContextHolder.getContext();
		final Authentication auth = sc.getAuthentication();
		UserId userSession = null;

		long requesterId = 0;
		if (auth != null) {
			userSession = (UserId) auth.getPrincipal();
			requesterId = userSession.getId();
		}

		PhotoId id = (PhotoId) arg2;
		ViewPhoto photoPage = photoService
				.getPhotoPage(id.getId(), requesterId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", photoPage.getUser());
		model.put("photo", photoPage.getPhoto());
		model.put("photoUrl", photoPage.getPhoto().getUrl()
				+ Photo.largePrefix + photoPage.getPhoto().getFileName());

		return new ModelAndView("photo", model);

	}
}

class PhotoId {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}