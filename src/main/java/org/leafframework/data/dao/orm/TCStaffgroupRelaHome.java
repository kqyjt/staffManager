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
 * Home object for domain model class TCStaffgroupRela.
 * @see org.leafframework.data.dao.orm.TCStaffgroupRela
 * @author Hibernate Tools
 */
@Repository
public class TCStaffgroupRelaHome implements IDao {

	private static final Log log = LogFactory
			.getLog(TCStaffgroupRelaHome.class);

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

	public void persist(TCStaffgroupRela transientInstance) {
		log.debug("persisting TCStaffgroupRela instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TCStaffgroupRela instance) {
		log.debug("attaching dirty TCStaffgroupRela instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TCStaffgroupRela instance) {
		log.debug("attaching clean TCStaffgroupRela instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TCStaffgroupRela persistentInstance) {
		log.debug("deleting TCStaffgroupRela instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TCStaffgroupRela merge(TCStaffgroupRela detachedInstance) {
		log.debug("merging TCStaffgroupRela instance");
		try {
			TCStaffgroupRela result = (TCStaffgroupRela) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll TCStaffgroupRela instance");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + TCStaffgroupRela.class.getName())
					.list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public TCStaffgroupRela findById(java.lang.Integer id) {
		log.debug("getting TCStaffgroupRela instance with id: " + id);
		try {
			TCStaffgroupRela instance = (TCStaffgroupRela) sessionFactory
					.getCurrentSession().get(
							"org.leafframework.data.dao.orm.TCStaffgroupRela",
							id);
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

	public List findByExample(TCStaffgroupRela instance) {
		log.debug("finding TCStaffgroupRela instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.leafframework.data.dao.orm.TCStaffgroupRela")
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