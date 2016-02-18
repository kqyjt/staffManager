package org.leafframework.data.dao.orm;

// Generated 2016-2-17 17:18:46 by Hibernate Tools 3.4.0.CR1

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Salarymgr.
 * @see org.leafframework.data.dao.orm.Salarymgr
 * @author Hibernate Tools
 */
@Repository
public class SalarymgrHome {

	private static final Log log = LogFactory.getLog(SalarymgrHome.class);

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

	public void persist(Salarymgr transientInstance) {
		log.debug("persisting Salarymgr instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Salarymgr instance) {
		log.debug("attaching dirty Salarymgr instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Salarymgr instance) {
		log.debug("attaching clean Salarymgr instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Salarymgr persistentInstance) {
		log.debug("deleting Salarymgr instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Salarymgr merge(Salarymgr detachedInstance) {
		log.debug("merging Salarymgr instance");
		try {
			Salarymgr result = (Salarymgr) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll Salarymgr instance");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + Salarymgr.class.getName()).list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public Salarymgr findById(int id) {
		log.debug("getting Salarymgr instance with id: " + id);
		try {
			Salarymgr instance = (Salarymgr) sessionFactory.getCurrentSession()
					.get("org.leafframework.data.dao.orm.Salarymgr", id);
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

	public List findByExample(Salarymgr instance) {
		log.debug("finding Salarymgr instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("org.leafframework.data.dao.orm.Salarymgr")
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
