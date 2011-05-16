/** ------------------------------------------------------------
 * UserServiceTests.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 13, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.bigcybercity.util.ListItem;
import com.bigcybercity.util.Profile;

/** 
 *
 */

public class UserServiceTests extends
		AbstractTransactionalDataSourceSpringContextTests {
	
	private UserService userService;
	private SqlService sqlService;
	
	
	
	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] {"classpath:test-context.xml"};
	}
	
	public void testGetProfile() {
		Profile profile = userService.getProfile(1, 1);
		assertNotNull(profile);
		List<ListItem> comments = profile.getComments();
		for (ListItem comment : comments)
			assertNotNull(comment.getContents());
		List<ListItem> friends = profile.getFriends();
		for (ListItem friend: friends)
			assertNotNull(friend.getUserName());
		assertEquals(1, profile.getUser().getId());
		
	}
	
	public void testSearch() {
		
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("gender", "female"));
		crit.add(Restrictions.eq("relationshipStatus", "single"));
		crit.add(Restrictions.in("zip", sqlService.getNearbyZips("04444", 10)));
		assertEquals("Number of results", userService.numOfSearchResults(crit), 8);
		assertEquals("Search results", userService.search(crit, 1).size(), 8);
	}
	
	public void testGetFriends() {
		List<ListItem> friends = userService.getFriends(1, 1, 2);
		assertEquals(friends.size(), 10);
		for(ListItem friend: friends) 
			assertNotNull(friend);
	}

}
