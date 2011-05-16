/** ------------------------------------------------------------
 * SqlServiceTests.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 13, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/** 
 *
 */

public class SqlServiceTests extends
		AbstractTransactionalDataSourceSpringContextTests {
	
	private SqlService sqlService;

	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] {"classpath:test-context.xml"};
	}
	
	public void testGetNearbyZips() {
		List<String> zips = sqlService.getNearbyZips("04444", 10);
		assertEquals(zips.size(), 7);
	}
}
