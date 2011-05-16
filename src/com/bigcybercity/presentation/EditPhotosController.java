/** ------------------------------------------------------------
 * EditPhotosController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 14, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;
import com.bigcybercity.security.UserId;
import com.bigcybercity.service.PhotoService;
import com.bigcybercity.service.UserService;
import com.bigcybercity.util.ImageInfo;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Allows the user to edit their photos
 */

//@SuppressWarnings()
public class EditPhotosController extends AbstractFormController {
	/**
	 * injected by Spring
	 */
	private UserService userService;
	private PhotoService photoService;

	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public EditPhotosController() {
		setCommandClass(User.class);
		setCommandName("user");
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#processFormSubmission(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request,
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

		List<Photo> photos = userService.getPhotos(userSession.getId());
		int numOfPhotos = photos.size();
		if (numOfPhotos > 4) {
			arg1.sendRedirect("/login.htm");
			return null;
		}
		User user = userService.getUser(userSession.getId());

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(20000000);

		try {
			// Parse the request/ FileItem /
			java.util.List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					Photo photo = new Photo();
					photo.setId(user.getId());
					photo.setPath(Photo.defaultPath);
					photo.setType(Photo.profile);
					photo.setUrl(Photo.defaultUrl);
					// If this is the user's first photo it will be the default
					if (numOfPhotos == 0)
						photo.setDisplayPic(true);
					else
						photo.setDisplayPic(false);

					InputStream uploadedStream = item.getInputStream();
					ImageInfo ii = new ImageInfo();

					/* Picture dimensions */
					int picWidth, picHeight;
					short[] largeDim = new short[2];
					short[] normalDim = new short[2];
					short[] medDim = new short[2];
					short[] smallDim = new short[2];

					ii.setInput(uploadedStream);
					if (ii.check() && ii.getFormatName().equals("JPEG")) {
						/* item is image, proceed-Add to photo table */
						/*
						 * First get dimensions for the 4 photos of various
						 * sizes we will save to the server
						 */
						picWidth = ii.getWidth();
						picHeight = ii.getHeight();

						/* Full sized photo for viewPhoto page */
						if (picWidth > 890) {
							largeDim[0] = (short) (691 * ((float) picWidth / (float) picHeight));
							largeDim[1] = 691;
							if (largeDim[0] > 890)
								largeDim[0] = 890;
						} else {
							largeDim[0] = (short) picWidth;
							largeDim[1] = (short) picHeight;
						}

						/* Normal photo for display pic results */
						normalDim[0] = (short) (210 * ((float) picWidth / (float) picHeight));
						normalDim[1] = 210;
						if (normalDim[0] > 270)
							normalDim[0] = 270;

						/* Medium sized for viewprofile page's friends */
						medDim[0] = (short) (167 * ((float) picWidth / (float) picHeight));
						medDim[1] = 167;
						if (medDim[0] > 215)
							medDim[0] = 215;

						/* Small sized for viewprofile page's comments */
						smallDim[0] = (short) (100 * ((float) picWidth / (float) picHeight));
						smallDim[1] = 100;
						if (smallDim[0] > 129)
							smallDim[0] = 129;

						// Save the photo
						long id = photoService.save(photo);

						// The id will be for the filename
						StringBuffer fileName = new StringBuffer(String
								.valueOf(id));
						fileName.append(".");
						fileName.append(ii.getFormatName());
						photo.setFileName(fileName.toString());

						// Save the file name changes
						photoService.save(photo);

						// If this is the default pic, change the User table
						if (photo.isDisplayPic()) {
							user.setDisplayPic(photo.getFileName());
							user.setDisplayPicPath(photo.getUrl());
							userService.updateUser(user);
						}

						/* Save the photo to the destination */
						File uploadedFile = new File(Photo.defaultPath
								+ fileName);
						item.write(uploadedFile);
						item.delete();

						Image image = Toolkit.getDefaultToolkit().getImage(
								Photo.defaultPath + fileName);
						MediaTracker mediaTracker = new MediaTracker(
								new Panel());
						mediaTracker.addImage(image, 0);
						mediaTracker.waitForID(0);

						/* Make normal sized image */
						BufferedImage scaledImage = new BufferedImage(
								normalDim[0], normalDim[1],
								BufferedImage.TYPE_INT_RGB);
						Graphics2D graphics2D = scaledImage.createGraphics();
						graphics2D.setRenderingHint(
								RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						graphics2D.drawImage(image, 0, 0, normalDim[0],
								normalDim[1], null);
						BufferedOutputStream out = new BufferedOutputStream(
								new FileOutputStream(Photo.defaultPath
										+ fileName));
						JPEGImageEncoder encoder = JPEGCodec
								.createJPEGEncoder(out);
						JPEGEncodeParam param = encoder
								.getDefaultJPEGEncodeParam(scaledImage);
						param.setQuality(0.75f, false);
						encoder.setJPEGEncodeParam(param);
						encoder.encode(scaledImage);
						out.close();

						/* Make large sized image */
						scaledImage = new BufferedImage(largeDim[0],
								largeDim[1], BufferedImage.TYPE_INT_RGB);
						graphics2D = scaledImage.createGraphics();
						graphics2D.setRenderingHint(
								RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						graphics2D.drawImage(image, 0, 0, largeDim[0],
								largeDim[1], null);
						out = new BufferedOutputStream(new FileOutputStream(
								Photo.defaultPath + "l" + fileName));
						encoder = JPEGCodec.createJPEGEncoder(out);
						param = encoder.getDefaultJPEGEncodeParam(scaledImage);
						param.setQuality(0.75f, false);
						encoder.setJPEGEncodeParam(param);
						encoder.encode(scaledImage);
						out.close();

						/* Make medium sized image */
						scaledImage = new BufferedImage(medDim[0], medDim[1],
								BufferedImage.TYPE_INT_RGB);
						graphics2D = scaledImage.createGraphics();
						graphics2D.setRenderingHint(
								RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						graphics2D.drawImage(image, 0, 0, medDim[0], medDim[1],
								null);
						out = new BufferedOutputStream(new FileOutputStream(
								Photo.defaultPath + "m" + fileName));
						encoder = JPEGCodec.createJPEGEncoder(out);
						param = encoder.getDefaultJPEGEncodeParam(scaledImage);
						param.setQuality(0.75f, false);
						encoder.setJPEGEncodeParam(param);
						encoder.encode(scaledImage);
						out.close();

						/* Make small sized image */
						scaledImage = new BufferedImage(smallDim[0],
								smallDim[1], BufferedImage.TYPE_INT_RGB);
						graphics2D = scaledImage.createGraphics();
						graphics2D.setRenderingHint(
								RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						graphics2D.drawImage(image, 0, 0, smallDim[0],
								smallDim[1], null);
						out = new BufferedOutputStream(new FileOutputStream(
								Photo.defaultPath + "s" + fileName));
						encoder = JPEGCodec.createJPEGEncoder(out);
						param = encoder.getDefaultJPEGEncodeParam(scaledImage);
						param.setQuality(0.75f, false);
						encoder.setJPEGEncodeParam(param);
						encoder.encode(scaledImage);
						out.close();
						model.put("added", true);

					} else {
						model.put("photoError", true);
					}
					uploadedStream.close();

				}
			}
		} catch (FileUploadException ex) {
		} catch (Exception e) {
		}
		
		photos = userService.getPhotos(userSession.getId());
		boolean showForm = (photos.size() >= 5) ? false : true;
		model.put("user", user);
		model.put("photos", photos);
		model.put("showForm", showForm);
		return new ModelAndView("editPhotos", model);

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

		String action = arg0.getParameter("action");

		if (action != null && action.equals("delete")) {
			long photoId;
			try {
				photoId = Integer.parseInt(arg0.getParameter("photoId"));
			} catch (Exception e) {
				arg1.sendRedirect("/");
				return null;
			}
			
			
			
			List<Photo> photos = userService.getPhotos(userSession.getId());
			try {
				for (Photo photo: photos) {
					if (photo.getPhotoId() == photoId) {
						(new File(photo.getPath() + photo.getFileName())).delete();
						(new File(photo.getPath() + Photo.smallPrefix + photo.getFileName())).delete();
						(new File(photo.getPath() + Photo.mediumPrefix + photo.getFileName())).delete();
						(new File(photo.getPath() + Photo.largePrefix + photo.getFileName())).delete();
					}
				}
				
				
			} catch (Exception e) {	}
				
			photoService.delete(photoId, userSession.getId());
			model.put("deleted", true);
			
			
			
			
		} else if (action != null && action.equals("makeDefault")) {
			long photoId;
			try {
				photoId = Integer.parseInt(arg0.getParameter("photoId"));
			} catch (Exception e) {
				arg1.sendRedirect("/");
				return null;
			}
			photoService.makeDefault(photoId, userSession.getId());
			model.put("changedDefault", true);
		}

		List<Photo> photos = userService.getPhotos(userSession.getId());
		boolean showForm = (photos.size() >= 5) ? false : true;
		User user = userService.getUser(userSession.getId());
		model.put("user", user);
		model.put("photos", photos);
		model.put("showForm", showForm);
		return new ModelAndView("editPhotos", model);
	}

}
