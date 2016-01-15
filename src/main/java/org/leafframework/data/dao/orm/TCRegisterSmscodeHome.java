package org.leafframework.data.dao.orm;

// Generated 2015-7-8 20:07:01 by Hibernate Tools 4.0.0

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
 * Home object for domain model class TCRegisterSmscode.
 * @see org.leafframework.data.dao.orm.TCRegisterSmscode
 * @author Hibernate Tools
 */
@Repository
public class TCRegisterSmscodeHome implements IDao {

	private static final Log log = LogFactory.getLog(TCRegisterSmscodeHome.class);

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

	public void persist(TCRegisterSmscode transientInstance) {
		log.debug("persisting TCRegisterSmscode instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TCRegisterSmscode instance) {
		log.debug("attaching dirty TCRegisterSmscode instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TCRegisterSmscode instance) {
		log.debug("attaching clean TCRegisterSmscode instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TCRegisterSmscode persistentInstance) {
		log.debug("deleting TCRegisterSmscode instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TCRegisterSmscode merge(TCRegisterSmscode detachedInstance) {
		log.debug("merging TCRegisterSmscode instance");
		try {
			TCRegisterSmscode result = (TCRegisterSmscode) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll TCRegisterSmscode instance");
		try {
			return sessionFactory.getCurrentSession().createQuery("from " + TCRegisterSmscode.class.getName()).list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public TCRegisterSmscode findById(java.lang.Integer id) {
		log.debug("getting TCRegisterSmscode instance with id: " + id);
		try {
			TCRegisterSmscode instance = (TCRegisterSmscode) sessionFactory.getCurrentSession().get(
					"org.leafframework.data.dao.orm.TCRegisterSmscode", id);
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

	public List findByExample(TCRegisterSmscode instance) {
		log.debug("finding TCRegisterSmscode instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("org.leafframework.data.dao.orm.TCRegisterSmscode")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
