/** ------------------------------------------------------------
 * PhotoService.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import com.bigcybercity.entity.Photo;
import com.bigcybercity.util.ViewPhoto;

/** 
 * Methods for the presentation layer to work with photos
 */

public interface PhotoService {
	
	ViewPhoto getPhotoPage(long photoId, long requesterId);
	
	long save(Photo photo);
	
	void delete(long photoId, long id);
	
	void makeDefault(long photoId, long id);

}
