/** ------------------------------------------------------------
 * UserServiceImpl.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

import com.bigcybercity.dao.UserDao;
import com.bigcybercity.entity.Alert;
import com.bigcybercity.entity.Comment;
import com.bigcybercity.entity.Friend;
import com.bigcybercity.entity.Mail;
import com.bigcybercity.entity.Photo;
import com.bigcybercity.entity.User;
import com.bigcybercity.util.ConvertDate;
import com.bigcybercity.util.ForHTML;
import com.bigcybercity.util.ListItem;
import com.bigcybercity.util.Profile;

@Transactional
public class UserServiceImpl implements UserService {

	/**
	 * Spring injected
	 */
	private SessionFactory sessionFactory;
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getProfile(long)
	 */
	public Profile getProfile(long id, long requesterId) {
		User user = userDao.getUser(id);
		List<Photo> photos = userDao.getPhotos(id, 5);
		if (photos.size() < 1)
			photos = null;

		List<ListItem> commentItems = getComments(id, requesterId, 0);

		List<Friend> friends = userDao.getRandomFriends(id, 20);
		List<ListItem> friendItems = new ArrayList<ListItem>();
		for (Friend friend : friends) {
			User theFriend = userDao.getUser(friend.getFriendId());
			friendItems.add(new ListItem(friend.getId(), friend.getFriendId(),
					theFriend.getDisplayName(), theFriend.getDisplayPicPath()
							+ "s" + theFriend.getDisplayPic(), null, null,
					false));
		}

		return new Profile(user, commentItems, photos, friendItems);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#numOfSearchResults(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public int numOfSearchResults(List<Criterion> criterion) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		for (Criterion crit : criterion)
			criteria.add(crit);
		criteria.setProjection(Projections.rowCount());

		Iterator<Integer> it = criteria.list().iterator();

		if (!it.hasNext())
			return 0;

		return it.next();

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#search(java.util.List, int)
	 */
	@SuppressWarnings("unchecked")
	public List<ListItem> search(List<Criterion> criterion, int pageNumber) {
		if (pageNumber == 0)
			pageNumber = 1;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		for (Criterion crit : criterion)
			criteria.add(crit);
		List<User> users = criteria.addOrder(Order.desc("lastLogin"))
				.setMaxResults(10).setFirstResult(pageNumber * 10 - 10).list();
		List<ListItem> results = new ArrayList<ListItem>();
		for (User user : users) {
			results.add(new ListItem(user.getId(), user.getId(), user
					.getDisplayName(), user.getDisplayPicPath()
					+ user.getDisplayPic(), "", user.getGender() + "<br />"
					+ user.getRelationshipStatus() + "<br />" + user.getAge()
					+ " years old <br />" + user.getLocation(), false));
		}
		return results;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getComments(long, long, int)
	 */
	public List<ListItem> getComments(long id, long requesterId, int pageNum) {
		int start = (pageNum == 0) ? 0 : pageNum * 10 - 10;
		List<Comment> comments = userDao.getComments(id, start, start + 10);
		List<ListItem> commentItems = new ArrayList<ListItem>();
		for (Comment comment : comments) {
			User commentAuthor = userDao.getUser(comment.getFromId());
			boolean canDelete = (requesterId == id || requesterId == comment
					.getFromId());
			commentItems.add(new ListItem(comment.getId(), comment.getFromId(),
					commentAuthor.getDisplayName(), commentAuthor
							.getDisplayPicPath()
							+ "s" + commentAuthor.getDisplayPic(), ConvertDate
							.detailed(comment.getDate()), ForHTML
							.forHTML(comment.getComment()), canDelete));
		}

		return commentItems;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getNumOfComments(long)
	 */
	public int getNumOfComments(long id) {
		return userDao.getNumOfComments(id);
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getUser(long)
	 */
	public User getUser(long id) {
		return userDao.getUser(id);
	}
	
	public User getUser(String email) {
		return userDao.getUser(email);
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getFriends(long, long, int)
	 */
	public List<ListItem> getFriends(long id, long requesterId, int pageNumber) {
		int start = (pageNumber == 0) ? 0 : pageNumber * 10 - 10;
		List<Friend> friends = userDao.getFriends(id, start, start + 10);
		List<ListItem> friendItems = new ArrayList<ListItem>();
		for (Friend friend : friends) {
			User theFriend = userDao.getUser(friend.getFriendId());
			boolean canDelete = (requesterId == id || requesterId == theFriend
					.getId());
			friendItems.add(new ListItem(friend.getId(), friend.getFriendId(),
					theFriend.getDisplayName(), theFriend.getDisplayPicPath()
							+ "s" + theFriend.getDisplayPic(), "", theFriend
							.getGender()
							+ "<br />"
							+ theFriend.getRelationshipStatus()
							+ "<br />"
							+ theFriend.getAge()
							+ " years old <br />" + theFriend.getLocation(),
					canDelete));
		}
		return friendItems;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getNumOfFriends(long)
	 */
	public int getNumOfFriends(long id) {
		return userDao.getNumOfFriends(id);
	}

	/**
	 * 
	 * @see com.bigcybercity.service.SqlService#getNumOfPostedComments(long)
	 */
	public Integer getNumOfPostedComments(long id) {
		return userDao.getNumOfPostedComments(id);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.SqlService#getPostedComments(long, int)
	 */
	public List<ListItem> getPostedComments(long id, int pageNumber) {

		int start = (pageNumber == 0) ? 0 : pageNumber * 10 - 10;
		List<Comment> comments = userDao.getPostedComments(id, start,
				start + 10);
		List<ListItem> commentItems = new ArrayList<ListItem>();
		for (Comment comment : comments) {
			User targetUser = userDao.getUser(comment.getToId());
			boolean canDelete = true;
			commentItems.add(new ListItem(comment.getId(), comment.getToId(),
					targetUser.getDisplayName(), targetUser.getDisplayPicPath()
							+ "s" + targetUser.getDisplayPic(), ConvertDate
							.detailed(comment.getDate()), ForHTML
							.forHTML(comment.getComment()), canDelete));
		}

		return commentItems;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#updateUser(com.bigcybercity.entity.User)
	 */
	public void updateUser(User user) {
		userDao.updateUser(user);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getPhotos(long)
	 */
	public List<Photo> getPhotos(long id) {
		return userDao.getPhotos(id, 5);
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#deleteFriend(long, long)
	 */
	public void deleteFriend(long userId, long id) {
		Friend friend = userDao.getFriend(id);
		userDao.deleteFriend(userId, friend.getFriendId());

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#deleteComment(long, long)
	 */
	public void deleteComment(long id, long userId) {
		userDao.deleteComment(id, userId);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getInboxMail(long, int)
	 */
	public List<ListItem> getInboxMail(long id, int pageNumber) {
		int start = (pageNumber == 0) ? 0 : pageNumber * 10 - 10;
		List<Mail> mail = userDao.getInboxMail(id, start, start + 10);
		List<ListItem> mailItems = new ArrayList<ListItem>();
		for (Mail mes : mail) {
			User from = userDao.getUser(mes.getFromId());
			String subject = "<div class=subject>"
					+ ForHTML.forHTML(mes.getSubject()) + "</div>";
			String body = "<div class=date>"
					+ ConvertDate.detailed(mes.getDate())
					+ "</div><div class=body>" + ForHTML.forHTML(mes.getBody())
					+ "</div><br /><br /><a href='/composeMessage.htm?to="
					+ mes.getFromId() + "&reply=" + mes.getId() + "'>Reply</a>";
			mailItems.add(new ListItem(mes.getId(), mes.getFromId(), from
					.getDisplayName(), from.getDisplayPicPath() + "s"
					+ from.getDisplayPic(), subject, body, true));

		}
		return mailItems;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getNumOfInbox(long)
	 */
	public Integer getNumOfInbox(long id) {
		return userDao.getNumOfInbox(id);
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getNumOfOutbox(long)
	 */
	public Integer getNumOfOutbox(long id) {
		return userDao.getNumOfOutbox(id);
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getOutboxMail(long, int)
	 */
	public List<ListItem> getOutboxMail(long id, int pageNumber) {
		int start = (pageNumber == 0) ? 0 : pageNumber * 10 - 10;
		List<Mail> mail = userDao.getOutboxMail(id, start, start + 10);
		List<ListItem> mailItems = new ArrayList<ListItem>();
		for (Mail mes : mail) {
			User to = userDao.getUser(mes.getToId());
			String subject = "<div class=subject>"
					+ ForHTML.forHTML(mes.getSubject()) + "</div>";
			String body = "<div class=date>"
					+ ConvertDate.detailed(mes.getDate())
					+ "</div><div class=body>" + ForHTML.forHTML(mes.getBody())
					+ "</div>";
			mailItems.add(new ListItem(mes.getId(), mes.getToId(), to
					.getDisplayName(), to.getDisplayPicPath() + "s"
					+ to.getDisplayPic(), subject, body, true));

		}
		return mailItems;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#deleteInboxMessage(long, long)
	 */
	public void deleteInboxMessage(long id, long messageId) {
		userDao.deleteTo(id, messageId);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#deleteOutboxMessage(long, long)
	 */
	public void deleteOutboxMessage(long id, long messageId) {
		userDao.deleteFrom(id, messageId);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#deleteUser(com.bigcybercity.entity.User)
	 */
	public void deleteUser(long id) {
		userDao.deleteUser(id);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#deleteAlert(com.bigcybercity.entity.Alert)
	 */
	public void deleteAlert(Alert alert) {
		userDao.deleteAlert(alert);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#getAlerts(long)
	 */
	public List<Alert> getAlerts(long id) {
		return userDao.getAlerts(id);
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#addFriend(long, long)
	 */
	public void addFriend(long id, long friendId) {
		if (areFriends(id, friendId))
			return;
		
		Friend f1 = new Friend();
		Friend f2 = new Friend();
		f1.setFriendId(id);
		f1.setUserId(friendId);

		f2.setFriendId(friendId);
		f2.setUserId(id);

		userDao.addFriend(f1, f2);
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#addFriendAlert(long, long)
	 */
	public void addFriendAlert(long requesterId, long friendId) {
		if (areFriends(requesterId, friendId))
			return;
		
		Alert alert = new Alert();

		User requester = userDao.getUser(requesterId);
		alert.setFromId(requesterId);
		alert.setFromName(requester.getDisplayName());
		alert.setType("Friend");
		alert.setUserId(friendId);

		userDao.addAlert(alert);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#areFriends(long, long)
	 */
	public boolean areFriends(long id1, long id2) {
		List<Friend> friends = userDao.getFriends(id1, 0, 10000);
		for (Friend friend : friends) {
			if (friend.getFriendId() == id2)
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#postComment(com.bigcybercity.entity.Comment)
	 */
	public void postComment(Comment comment) {
		userDao.postComment(comment);

	}

	/**
	 * 
	 * @see com.bigcybercity.service.UserService#addCommentAlert(long, long)
	 */
	public void addCommentAlert(long fromId, long toId) {
		Alert alert = new Alert();

		User requester = userDao.getUser(fromId);
		alert.setFromId(fromId);
		alert.setFromName(requester.getDisplayName());
		alert.setType("Comment");
		alert.setUserId(toId);

		userDao.addAlert(alert);

	}

}
