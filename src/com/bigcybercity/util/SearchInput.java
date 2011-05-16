/** ------------------------------------------------------------
 * SearchInput.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */

package com.bigcybercity.util;

/** 
 * A helper class to bundle a user's search criteria
 */

public class SearchInput {
	int miles, startAge, endAge, pageNumber;
	String gender, status, zip;
	
	public int getStartAge() {
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SearchInput() {}

	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
