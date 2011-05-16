/** ------------------------------------------------------------
 * UserDaoImpl.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.bigcybercity.entity.Alert;
import com.bigcybercity.entity.Comment;
import com.bigcybercity.entity.Friend;
import com.bigcybercity.entity.Mail;
import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;

@Transactional
public class UserDaoImpl implements UserDao {

	/**
	 * Injected by Spring
	 */
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getUser(long)
	 */
	public User getUser(long id) {
		return (User) sessionFactory.getCurrentSession().createQuery(
				"FROM User WHERE id = ?").setLong(0, id).uniqueResult();
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getUser(java.lang.String)
	 */
	public User getUser(String email) {
		return (User) sessionFactory.getCurrentSession().createQuery(
				"FROM User WHERE email = ?").setString(0, email).uniqueResult();
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#createUser(com.bigcybercity.entity.User)
	 */
	public void createUser(User user) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#deleteUser(long)
	 */
	public void deleteUser(long id) {
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM USERS WHERE id = " + id)
				.executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM COMMENTS WHERE fromId = " + id).executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM COMMENTS WHERE toId = " + id)
				.executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM FRIENDS WHERE userId = " + id)
				.executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM FRIENDS WHERE friendId = " + id)
				.executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM MAIL WHERE fromId = " + id)
				.executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM MAIL WHERE toId = " + id)
				.executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM PHOTOS WHERE id = " + id)
				.executeUpdate();
		sessionFactory.getCurrentSession().flush();

	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getComments(long, int, int)
	 */
	public List<Comment> getComments(long id, int start, int end) {
		List<Comment> comments = ((User) sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE id = ?").setLong(0, id)
				.uniqueResult()).getComments();

		Collections.reverse(comments);
		List<Comment> result = new ArrayList<Comment>();

		for (int i = start; i < comments.size() && i < end; i++)
			result.add(comments.get(i));

		return result;
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getRandomFriends(long, int)
	 */
	public List<Friend> getRandomFriends(long id, int howMany) {
		List<Friend> friends = ((User) sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE id = ?").setLong(0, id)
				.uniqueResult()).getFriends();

		Collections.shuffle(friends);
		if (howMany >= friends.size())
			return friends;

		List<Friend> result = new ArrayList<Friend>();

		for (int i = 0; i < howMany; i++) {
			result.add(friends.get(i));
		}

		return result;

	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getPhotos(long, int)
	 */
	public List<Photo> getPhotos(long id, int howMany) {
		List<Photo> photos = ((User) sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE id = ?").setLong(0, id)
				.uniqueResult()).getPhotos();

		List<Photo> result = new ArrayList<Photo>();

		for (int i = 0; i < photos.size() && i < howMany; i++)
			result.add(photos.get(i));

		return result;
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getNumOfComments(long)
	 */
	public int getNumOfComments(long id) {
		return getUser(id).getComments().size();
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getFriends(long, int, int)
	 */
	public List<Friend> getFriends(long id, int start, int end) {
		List<Friend> friends = ((User) sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE id = ?").setLong(0, id)
				.uniqueResult()).getFriends();

		List<Friend> result = new ArrayList<Friend>();

		for (int i = start; i < friends.size() && i < end; i++)
			result.add(friends.get(i));

		return result;
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getNumOfFriends(long)
	 */
	public int getNumOfFriends(long id) {
		return getUser(id).getFriends().size();
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getNumOfPostedComments(long)
	 */
	public Integer getNumOfPostedComments(long id) {
		String sql = "SELECT count(id) as result FROM COMMENTS WHERE fromId = ?";
		return (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addScalar("result", Hibernate.INTEGER).setLong(0, id)
				.uniqueResult();
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getPostedComments(long)
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getPostedComments(long id, int start, int end) {
		String sql = "FROM Comment WHERE fromId = ?";
		List<Comment> comments = sessionFactory.getCurrentSession()
				.createQuery(sql).setLong(0, id).list();
		Collections.reverse(comments);
		List<Comment> result = new ArrayList<Comment>();

		for (int i = start; i < comments.size() && i < end; i++)
			result.add(comments.get(i));

		return result;
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#updateUser(long)
	 */
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		sessionFactory.getCurrentSession().flush();

	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#deleteFriend(long, long)
	 */
	public void deleteFriend(long userId, long friendId) {
		String sql = "DELETE FROM FRIENDS WHERE userId = ? AND friendID = ?";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setLong(0, userId).setLong(1, friendId).executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(sql).setLong(0, friendId).setLong(1, userId).executeUpdate();
		
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#deleteComment(long, long)
	 */
	public void deleteComment(long id, long userId) {
		sessionFactory.getCurrentSession().createSQLQuery(
				"DELETE FROM COMMENTS WHERE id = " + id + " AND (fromId = "
						+ userId + " OR toId = " + userId + ")")
				.executeUpdate();

	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getInboxMail(long, int, int)
	 */
	public List<Mail> getInboxMail(long id, int start, int end) {
		List<Mail> mail = ((User) sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE id = ?").setLong(0, id)
				.uniqueResult()).getRecMail();

		Collections.reverse(mail);

		// Remove all deleted mail
		for (int i = mail.size() - 1; i >= 0; i--) {
			if (mail.get(i).isDelTo())
				mail.remove(i);
		}
		List<Mail> result = new ArrayList<Mail>();

		for (int i = start; i < mail.size() && i < end; i++)
			result.add(mail.get(i));

		return result;
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getNumOfInbox(long)
	 */
	public Integer getNumOfInbox(long id) {
		return (Integer) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT Count(id) AS result FROM MAIL WHERE toId = ? AND delTo = 0")
				.addScalar("result", Hibernate.INTEGER).setLong(0, id)
				.uniqueResult();
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getNumOfOutbox(long)
	 */
	public Integer getNumOfOutbox(long id) {
		return (Integer) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT Count(id) AS result FROM MAIL WHERE fromId = ? AND delFrom = 0")
				.addScalar("result", Hibernate.INTEGER).setLong(0, id)
				.uniqueResult();
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#getOutboxMail(long, int, int)
	 */
	public List<Mail> getOutboxMail(long id, int start, int end) {
		List<Mail> mail = ((User) sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE id = ?").setLong(0, id)
				.uniqueResult()).getSentMail();

		Collections.reverse(mail);

		// Remove all deleted mail
		for (int i = mail.size() - 1; i >= 0; i--) {
			if (mail.get(i).isDelFrom())
				mail.remove(i);
		}
		List<Mail> result = new ArrayList<Mail>();

		for (int i = start; i < mail.size() && i < end; i++)
			result.add(mail.get(i));

		return result;
	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#deleteFrom(long)
	 */
	public void deleteFrom(long id, long messageId) {
		String sql = "UPDATE MAIL SET delFrom = 1 WHERE id = ? AND fromId = ? LIMIT 1";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setLong(0,
				messageId).setLong(1, id).executeUpdate();

	}

	/**
	 * 
	 * @see com.bigcybercity.dao.UserDao#deleteTo(long)
	 */
	public void deleteTo(long id, long messageId) {
		String sql = "UPDATE MAIL SET delTo = 1 WHERE id = ? AND toId = ? LIMIT 1";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setLong(0,
				messageId).setLong(1, id).executeUpdate();

	}

	/** 
	 * 
	 * @see com.bigcybercity.dao.UserDao#deleteAlert(com.bigcybercity.entity.Alert)
	 */
	public void deleteAlert(Alert alert) {
		sessionFactory.getCurrentSession().delete(alert);
		sessionFactory.getCurrentSession().flush();
		
	}

	/** 
	 * 
	 * @see com.bigcybercity.dao.UserDao#getAlerts(long)
	 */
	public List<Alert> getAlerts(long id) {
		List<Alert> alerts = ((User) sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE id = ?").setLong(0, id)
				.uniqueResult()).getAlerts();

		List<Alert> result = new ArrayList<Alert>();

		for (int i = 0; i < alerts.size(); i++)
			result.add(alerts.get(i));

		return result;
	}

	/** 
	 * 
	 * @see com.bigcybercity.dao.UserDao#addFriend(com.bigcybercity.entity.Friend, com.bigcybercity.entity.Friend)
	 */
	public void addFriend(Friend f1, Friend f2) {
		sessionFactory.getCurrentSession().saveOrUpdate(f1);
		sessionFactory.getCurrentSession().saveOrUpdate(f2);
		sessionFactory.getCurrentSession().flush();
		
	}

	/** 
	 * 
	 * @see com.bigcybercity.dao.UserDao#addAlert(com.bigcybercity.entity.Alert)
	 */
	public void addAlert(Alert alert) {
		sessionFactory.getCurrentSession().saveOrUpdate(alert);
		sessionFactory.getCurrentSession().flush();
		
	}

	/** 
	 * 
	 * @see com.bigcybercity.dao.UserDao#getFriend(long)
	 */
	public Friend getFriend(long id) {
		return (Friend) sessionFactory.getCurrentSession().createQuery("FROM Friend WHERE id = ?").setLong(0, id).uniqueResult();
	}

	/** 
	 * 
	 * @see com.bigcybercity.dao.UserDao#postComment(com.bigcybercity.entity.Comment)
	 */
	public void postComment(Comment comment) {
		sessionFactory.getCurrentSession().saveOrUpdate(comment);
		sessionFactory.getCurrentSession().flush();
		
	}

	/** 
	 * 
	 * @see com.bigcybercity.dao.UserDao#updateLastLogin(com.bigcybercity.entity.User)
	 */
	public void updateLastLogin(User user) {
		Calendar cal = Calendar.getInstance();
		user.setLastLogin(cal.getTime());
		updateUser(user);
		
	}


}
