/** ------------------------------------------------------------
 * ConvertDate.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 7, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Converts various date objects into various string representations
 */

public class ConvertDate {

	static String[] monthName = { "January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };

	public static final String detailed(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);		

		int year = cal.get(Calendar.YEAR);
		String month = monthName[cal.get(Calendar.MONTH)];
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hrs = cal.get(Calendar.HOUR);
		if (hrs == 0)
			hrs = 12;
		int mins = cal.get(Calendar.MINUTE);
		String am_pm = cal.get(Calendar.AM_PM) == Calendar.AM ? "am" : "pm";

		return month + " " + day + " " + year + " at " + hrs + ":" + mins
				+ am_pm;
	}
	
	public static final String birthdate(Calendar cal) {
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return month + "-" + day + "-" + year;
	}

}
