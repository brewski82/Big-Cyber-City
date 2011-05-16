/** ------------------------------------------------------------
 * PhotoDaoImpl.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 12, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.bigcybercity.entity.Photo;

/** 
 *
 */
@Transactional
public class PhotoDaoImpl implements PhotoDao {
	/** 
	 * Injected by Spring
	 */
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/** 
	 * 
	 * @see com.bigcybercity.dao.PhotoDao#getPhoto(long)
	 */
	public Photo getPhoto(long photoId) {
		return (Photo) sessionFactory.getCurrentSession().createQuery(
		"FROM Photo WHERE photoId = ?").setLong(0, photoId).uniqueResult();
	}
	/** 
	 * 
	 * @see com.bigcybercity.dao.PhotoDao#save(com.bigcybercity.entity.Photo)
	 */
	public long save(Photo photo) {
		sessionFactory.getCurrentSession().saveOrUpdate(photo);
		sessionFactory.getCurrentSession().flush();
		return photo.getPhotoId();
		
	}
	/** 
	 * 
	 * @see com.bigcybercity.dao.PhotoDao#delete(com.bigcybercity.entity.Photo)
	 */
	public void delete(Photo photo) {
		sessionFactory.getCurrentSession().delete(photo);
		sessionFactory.getCurrentSession().flush();
		
	}

}
