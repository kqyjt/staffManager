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
 * Home object for domain model class TMRoles.
 * @see org.leafframework.data.dao.orm.TMRoles
 * @author Hibernate Tools
 */
@Repository
public class TMRolesHome implements IDao {

	private static final Log log = LogFactory.getLog(TMRolesHome.class);

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

	public void persist(TMRoles transientInstance) {
		log.debug("persisting TMRoles instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TMRoles instance) {
		log.debug("attaching dirty TMRoles instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TMRoles instance) {
		log.debug("attaching clean TMRoles instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TMRoles persistentInstance) {
		log.debug("deleting TMRoles instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TMRoles merge(TMRoles detachedInstance) {
		log.debug("merging TMRoles instance");
		try {
			TMRoles result = (TMRoles) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll TMRoles instance");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + TMRoles.class.getName()).list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public TMRoles findById(java.lang.Integer id) {
		log.debug("getting TMRoles instance with id: " + id);
		try {
			TMRoles instance = (TMRoles) sessionFactory.getCurrentSession()
					.get("org.leafframework.data.dao.orm.TMRoles", id);
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

	public List findByExample(TMRoles instance) {
		log.debug("finding TMRoles instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("org.leafframework.data.dao.orm.TMRoles")
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
