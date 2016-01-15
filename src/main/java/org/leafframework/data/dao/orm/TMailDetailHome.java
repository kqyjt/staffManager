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
 * Home object for domain model class TMailDetail.
 * @see org.leafframework.data.dao.orm.TMailDetail
 * @author Hibernate Tools
 */
@Repository
public class TMailDetailHome implements IDao {

	private static final Log log = LogFactory.getLog(TMailDetailHome.class);

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

	public void persist(TMailDetail transientInstance) {
		log.debug("persisting TMailDetail instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TMailDetail instance) {
		log.debug("attaching dirty TMailDetail instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TMailDetail instance) {
		log.debug("attaching clean TMailDetail instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TMailDetail persistentInstance) {
		log.debug("deleting TMailDetail instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TMailDetail merge(TMailDetail detachedInstance) {
		log.debug("merging TMailDetail instance");
		try {
			TMailDetail result = (TMailDetail) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll TMailDetail instance");
		try {
			return sessionFactory.getCurrentSession().createQuery("from " + TMailDetail.class.getName()).list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public TMailDetail findById(java.lang.Integer id) {
		log.debug("getting TMailDetail instance with id: " + id);
		try {
			TMailDetail instance = (TMailDetail) sessionFactory.getCurrentSession().get("org.leafframework.data.dao.orm.TMailDetail", id);
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

	public List findByExample(TMailDetail instance) {
		log.debug("finding TMailDetail instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("org.leafframework.data.dao.orm.TMailDetail")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
