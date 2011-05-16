/** ------------------------------------------------------------
 * PhotoDao.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.dao;

import com.bigcybercity.entity.Photo;

/** 
 * Interface for Photo Daos
 */

public interface PhotoDao {
	
	/** 
	 * Gets a photo from the database
	 * 
	 * @param photoId the id of the photo
	 * @return a Photo object
	 */
	Photo getPhoto(long photoId);
	
	long save(Photo photo);
	
	void delete(Photo photo);

}
