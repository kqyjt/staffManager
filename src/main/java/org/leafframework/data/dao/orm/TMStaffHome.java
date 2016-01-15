package org.leafframework.data.dao.orm;

// Generated 2015-7-21 14:43:12 by Hibernate Tools 4.3.1

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.leafframework.data.dao.IDao;

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class TMStaff.
 * @see org.leafframework.data.dao.orm.TMStaff
 * @author Hibernate Tools
 */
@Repository
public class TMStaffHome implements IDao {

	private static final Log log = LogFactory.getLog(TMStaffHome.class);

	@Autowired
	private final SessionFactory sessionFactory = null;/*getSessionFactory();
														
														protected SessionFactory getSessionFactory() {
														try {
														return (SessionFactory) new InitialContext().lookup("SessionFactory");
														}
														catch (Exception e) {
														log.error("Could not locate SessionFactory in JNDI", e);
														throw new IllegalStateException("Could not locate SessionFactory in JNDI");
														}
														}*/

	public void persist(TMStaff transientInstance) {
		log.debug("persisting TMStaff instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TMStaff instance) {
		log.debug("attaching dirty TMStaff instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TMStaff instance) {
		log.debug("attaching clean TMStaff instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TMStaff persistentInstance) {
		log.debug("deleting TMStaff instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TMStaff merge(TMStaff detachedInstance) {
		log.debug("merging TMStaff instance");
		try {
			TMStaff result = (TMStaff) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll TMStaff instance");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + TMStaff.class.getName()).list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public TMStaff findById(java.lang.Integer id) {
		log.debug("getting TMStaff instance with id: " + id);
		try {
			TMStaff instance = (TMStaff) sessionFactory.getCurrentSession()
					.get("org.leafframework.data.dao.orm.TMStaff", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TMStaff instance) {
		log.debug("finding TMStaff instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("org.leafframework.data.dao.orm.TMStaff")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
