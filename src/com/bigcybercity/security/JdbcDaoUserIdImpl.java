/** ------------------------------------------------------------
 * JdbcDaoUserIdImpl.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.security;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.jdbc.JdbcDaoImpl;

import com.bigcybercity.dao.UserDao;
import com.bigcybercity.entity.User;

/**
 * Puts the user's unique id in the session object so it can be retrieved with
 * every web request
 */
public class JdbcDaoUserIdImpl extends JdbcDaoImpl {

	/**
	 * Injected by spring
	 */
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * Returns a UserDetails object that also contains the user's unique id
	 * 
	 * @see org.springframework.security.userdetails.jdbc.JdbcDaoImpl#createUserDetails(java.lang.String,
	 *      org.springframework.security.userdetails.UserDetails,
	 *      org.springframework.security.GrantedAuthority[])
	 */
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery,
			GrantedAuthority[] combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (!isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}

		User user = userDao.getUser(username);
		userDao.updateLastLogin(user);

		return new UserId(user.getId(), returnUsername, userFromUserQuery
				.getPassword(), userFromUserQuery.isEnabled(), true, true,
				true, combinedAuthorities);
	}

}
