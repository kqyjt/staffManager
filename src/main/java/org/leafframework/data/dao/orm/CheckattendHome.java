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
 * Home object for domain model class Checkattend.
 * @see org.leafframework.data.dao.orm.Checkattend
 * @author Hibernate Tools
 */
@Repository
public class CheckattendHome {

	private static final Log log = LogFactory.getLog(CheckattendHome.class);

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

	public void persist(Checkattend transientInstance) {
		log.debug("persisting Checkattend instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Checkattend instance) {
		log.debug("attaching dirty Checkattend instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Checkattend instance) {
		log.debug("attaching clean Checkattend instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Checkattend persistentInstance) {
		log.debug("deleting Checkattend instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Checkattend merge(Checkattend detachedInstance) {
		log.debug("merging Checkattend instance");
		try {
			Checkattend result = (Checkattend) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll Checkattend instance");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + Checkattend.class.getName()).list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public Checkattend findById(int id) {
		log.debug("getting Checkattend instance with id: " + id);
		try {
			Checkattend instance = (Checkattend) sessionFactory
					.getCurrentSession().get(
							"org.leafframework.data.dao.orm.Checkattend", id);
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

	public List findByExample(Checkattend instance) {
		log.debug("finding Checkattend instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.leafframework.data.dao.orm.Checkattend")
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
