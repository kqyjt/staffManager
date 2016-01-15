package org.leafframework.data.dao.orm;

// Generated 2015-12-14 16:20:41 by Hibernate Tools 4.3.1

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
 * Home object for domain model class TOAssignResult.
 * @see org.leafframework.data.dao.orm.TOAssignResult
 * @author Hibernate Tools
 */
@Repository
public class TOAssignResultHome implements IDao {

	private static final Log log = LogFactory.getLog(TOAssignResultHome.class);

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

	public void persist(TOAssignResult transientInstance) {
		log.debug("persisting TOAssignResult instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TOAssignResult instance) {
		log.debug("attaching dirty TOAssignResult instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TOAssignResult instance) {
		log.debug("attaching clean TOAssignResult instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TOAssignResult persistentInstance) {
		log.debug("deleting TOAssignResult instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TOAssignResult merge(TOAssignResult detachedInstance) {
		log.debug("merging TOAssignResult instance");
		try {
			TOAssignResult result = (TOAssignResult) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll TOAssignResult instance");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + TOAssignResult.class.getName())
					.list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public TOAssignResult findById(java.lang.Integer id) {
		log.debug("getting TOAssignResult instance with id: " + id);
		try {
			TOAssignResult instance = (TOAssignResult) sessionFactory
					.getCurrentSession()
					.get("org.leafframework.data.dao.orm.TOAssignResult", id);
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

	public List findByExample(TOAssignResult instance) {
		log.debug("finding TOAssignResult instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.leafframework.data.dao.orm.TOAssignResult")
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
