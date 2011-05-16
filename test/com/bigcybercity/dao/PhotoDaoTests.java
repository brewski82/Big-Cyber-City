/** ------------------------------------------------------------
 * PhotoDaoTests.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.dao;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.bigcybercity.entity.Photo;

/** 
 *
 */

public class PhotoDaoTests extends
		AbstractTransactionalDataSourceSpringContextTests {
	
	private PhotoDao photoDao;
	
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] {"classpath:test-context.xml"};
	}
	
	public void testGetPhoto() {
		Photo photo = photoDao.getPhoto(9);
		assertNotNull(photo);
		assertEquals(9, photo.getPhotoId());
		assertEquals(photo.getFileName(), "9.JPEG");
	}

}
