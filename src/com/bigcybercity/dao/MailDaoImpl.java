/** ------------------------------------------------------------
 * MailDaoImpl.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 20, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.bigcybercity.entity.Alert;
import com.bigcybercity.entity.Mail;

/** 
 *
 */

@Transactional
public class MailDaoImpl implements MailDao {
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.MailDao#getMessage(long, long)
	 */
	public Mail getMessage(long id, long userId) {
		Mail message = (Mail) sessionFactory.getCurrentSession().createQuery(
				"FROM Mail WHERE id = ? and fromId = ?").setLong(0, id)
				.setLong(1, userId).uniqueResult();

		if (message == null)
			message = (Mail) sessionFactory.getCurrentSession().createQuery(
					"FROM Mail WHERE id = ? and toId = ?").setLong(0, id)
					.setLong(1, userId).uniqueResult();

		return message;
	}
	
	public void sendMessage(Mail message) {
		Alert alert = new Alert();
		alert.setUserId(message.getToId());
		alert.setFromId(message.getFromId());
		alert.setFromName("");
		alert.setType("Mail");
		sessionFactory.getCurrentSession().save(alert);
		sessionFactory.getCurrentSession().save(message);
		sessionFactory.getCurrentSession().flush();
	}

}
