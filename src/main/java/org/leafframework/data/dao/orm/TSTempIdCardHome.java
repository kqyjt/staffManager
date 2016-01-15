package org.leafframework.data.dao.orm;

// Generated 2015-8-5 20:24:47 by Hibernate Tools 4.0.0

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
 * Home object for domain model class TSTempIdCard.
 * @see org.leafframework.data.dao.orm.TSTempIdCard
 * @author Hibernate Tools
 */
@Repository
public class TSTempIdCardHome implements IDao {

	private static final Log log = LogFactory.getLog(TSTempIdCardHome.class);

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

	public void persist(TSTempIdCard transientInstance) {
		log.debug("persisting TSTempIdCard instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TSTempIdCard instance) {
		log.debug("attaching dirty TSTempIdCard instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TSTempIdCard instance) {
		log.debug("attaching clean TSTempIdCard instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TSTempIdCard persistentInstance) {
		log.debug("deleting TSTempIdCard instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TSTempIdCard merge(TSTempIdCard detachedInstance) {
		log.debug("merging TSTempIdCard instance");
		try {
			TSTempIdCard result = (TSTempIdCard) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll TSTempIdCard instance");
		try {
			return sessionFactory.getCurrentSession().createQuery("from " + TSTempIdCard.class.getName()).list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public TSTempIdCard findById(java.lang.Integer id) {
		log.debug("getting TSTempIdCard instance with id: " + id);
		try {
			TSTempIdCard instance = (TSTempIdCard) sessionFactory.getCurrentSession()
					.get("org.leafframework.data.dao.orm.TSTempIdCard", id);
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

	public List findByExample(TSTempIdCard instance) {
		log.debug("finding TSTempIdCard instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("org.leafframework.data.dao.orm.TSTempIdCard")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
