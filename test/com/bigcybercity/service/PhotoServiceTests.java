/** ------------------------------------------------------------
 * PhotoServiceTests.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.bigcybercity.util.ViewPhoto;

/** 
 *
 */

public class PhotoServiceTests extends
		AbstractTransactionalDataSourceSpringContextTests {
	
	private PhotoService photoService;
	
	
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}


	@Override
	protected String[] getConfigLocations() {
		return new String[] {"classpath:test-context.xml"};
	}
	
	public void testGetPhotoPage() {
		ViewPhoto viewPhoto = photoService.getPhotoPage(9, 11);
		assertEquals(viewPhoto.getUser().getId(), 11);
		assertEquals(viewPhoto.getPhoto().getPhotoId(), 9);
	}

}
