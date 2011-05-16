/** ------------------------------------------------------------
 * SqlServiceImpl.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import java.util.List;
import java.util.Random;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/** 
 *
 */

@Transactional
public class SqlServiceImpl implements SqlService {

	/**
	 * Spring injected
	 */
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.SqlService#getNearbyZips(java.lang.String,
	 *      int)
	 */
	@SuppressWarnings("unchecked")
	public List<String> getNearbyZips(String zip, int miles) {

		// Find the lat and lon of the zip
		List<Object[]> results = sessionFactory.getCurrentSession()
				.createSQLQuery(
						"SELECT latitude, longitude FROM zips WHERE zip = ?")
				.addScalar("latitude", Hibernate.DOUBLE).addScalar("longitude",
						Hibernate.DOUBLE).setString(0, zip).list();

		String lat = (results.get(0))[0].toString();
		String lon = (results.get(0))[1].toString();

		// Use an algorithm to find the nearest zip codes within <miles>
		String sql = "(SELECT zip FROM zips WHERE "
				+ "3957 * 2 * atan2(sqrt(pow((sin(0.0174*(latitude-" + lat
				+ ")/2)),2)+cos(0.0174*" + lat
				+ ")*cos(0.0174*latitude)*pow((sin(0.0174*(longitude-" + lon
				+ ")/2)),2)), sqrt(1-(pow((sin(0.0174*(latitude-" + lat
				+ ")/2)),2)+cos(0.0174*" + lat
				+ ") * cos(0.0174*latitude)*pow((sin(0.0174*(longitude-" + lon
				+ ")/2)),2)))) < " + miles + ") ";

		return sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addScalar("zip", Hibernate.STRING).list();
	}

	/**
	 * 
	 * @see com.bigcybercity.service.SqlService#addUnverifiedUser(java.lang.String,
	 *      java.lang.String)
	 */
	public long addUnverifiedUser(String email, String password) {
		Random rand = new Random();
		long randNum = Math.abs(rand.nextInt());

		String sql = "INSERT INTO NEW VALUES (NULL, '" + email + "', '"
				+ password + "', " + randNum + ", NULL)";
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();

		return randNum;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.SqlService#confirmNewUser(long)
	 */
	public boolean confirmNewUser(long confirmationNumber) {

		Long id = new Long(0);
		id = (Long) sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT id FROM NEW WHERE rnd = ?").addScalar("id",
				Hibernate.LONG).setLong(0, confirmationNumber).uniqueResult();

		if (id == null || id == 0)
			return false;

		// User user = new User();

		String email = (String) sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT email FROM NEW WHERE rnd = ?")
				.addScalar("email", Hibernate.STRING).setLong(0,
						confirmationNumber).uniqueResult();

		String pass = (String) sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT password FROM NEW WHERE rnd = ?")
				.addScalar("password", Hibernate.STRING).setLong(0,
						confirmationNumber).uniqueResult();

		// user.setEmail(email);
		// user.setPassword(pass);
		//		
		// sessionFactory.getCurrentSession().save(user);
		// sessionFactory.getCurrentSession().flush();

		String sql = "INSERT INTO USERS (email, password) VALUES ('" + email
				+ "', '" + pass + "')";

		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();

		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM NEW WHERE rnd = ?").setLong(0, confirmationNumber)
				.executeUpdate();
		return true;
	}

}
