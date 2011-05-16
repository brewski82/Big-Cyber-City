/** ------------------------------------------------------------
 * SqlService.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import java.util.List;

/**
 * Methods to interact with databases records directly
 */

public interface SqlService {

	/**
	 * Finds zip codes near the given zip code
	 * 
	 * @param zip
	 *            the source zip code
	 * @param miles
	 *            the number of miles away from the given zip code to search for
	 *            nearby zip codes
	 * @return a list of nearby zip codes
	 */
	List<String> getNearbyZips(String zip, int miles);

	/**
	 * Saves an email and password for a newly registered user
	 * 
	 * @param email
	 * @param password
	 * @return the random number used for email verification
	 */
	long addUnverifiedUser(String email, String password);

	/**
	 * Check's for the confirmation number in the table of unverified accounts.
	 * If found, creates a new user
	 * 
	 * @param accountNumber
	 * @return
	 */
	boolean confirmNewUser(long confirmationNumber);

}
