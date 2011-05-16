/** ------------------------------------------------------------
 * PhotoServiceImpl.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bigcybercity.dao.PhotoDao;
import com.bigcybercity.dao.UserDao;
import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;
import com.bigcybercity.util.ViewPhoto;

/** 
 *
 */
@Transactional
public class PhotoServiceImpl implements PhotoService {
	
	private PhotoDao photoDao;
	private UserDao userDao;

	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/** 
	 * 
	 * @see com.bigcybercity.service.PhotoService#getPhotoPage(long, long)
	 */
	public ViewPhoto getPhotoPage(long photoId, long requesterId) {
		Photo photo = photoDao.getPhoto(photoId);
		User user = userDao.getUser(photo.getId());
		return new ViewPhoto(user, photo);
	}

	/** 
	 * 
	 * @see com.bigcybercity.service.PhotoService#save(com.bigcybercity.entity.Photo)
	 */
	public long save(Photo photo) {
		
		return photoDao.save(photo);
		
	}

	/** 
	 * 
	 * @see com.bigcybercity.service.PhotoService#delete(long, long)
	 */
	public void delete(long photoId, long id) {
		Photo photo = photoDao.getPhoto(photoId);
		if (photo == null || photo.getId() != id)
			return;
		
		// See if we have to change the default pic
		boolean changeDefault = photo.isDisplayPic();
		
		// Delete the photo
		photoDao.delete(photo);
		
		// Change the default pic if necessary
		if (changeDefault) {
			List<Photo> photos = userDao.getPhotos(id, 100);
			if (photos.size() > 0) {
				Photo defaultPhoto = photos.get(0);
				makeDefault(defaultPhoto.getPhotoId(), id);
			} else {
				makeDefault(0, id);
			}
		}
		
	}

	/** 
	 * 
	 * @see com.bigcybercity.service.PhotoService#makeDefault(long, long)
	 */
	public void makeDefault(long photoId, long id) {
		// If no more pictures remain
		if (photoId == 0) {
			User user = userDao.getUser(id);
			user.setDisplayPic(Photo.defaultPhoto);
			user.setDisplayPicPath(Photo.defaultUrl);
			userDao.updateUser(user);
			return;
		}
		
		Photo photo = photoDao.getPhoto(photoId);
		if (photo == null || photo.getId() != id)
			return;
		
		photo.setDisplayPic(true);
		photoDao.save(photo);
		
		List<Photo> photos = userDao.getPhotos(id, 100);
		for (Photo p : photos) {
			if (p.getPhotoId() != photo.getPhotoId()) {
				p.setDisplayPic(false);
				photoDao.save(p);
			}
		}
		
		User user = userDao.getUser(id);
		user.setDisplayPic(photo.getFileName());
		user.setDisplayPicPath(photo.getUrl());
		userDao.updateUser(user);
		
		
		
	}

}
