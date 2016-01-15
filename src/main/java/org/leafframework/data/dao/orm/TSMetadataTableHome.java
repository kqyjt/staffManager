package org.leafframework.data.dao.orm;
// Generated 2014-11-8 21:08:48 by Hibernate Tools 4.3.1

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
 * Home object for domain model class TSMetadataTable.
 * @see org.leafframework.data.dao.orm.TSMetadataTable
 * @author Hibernate Tools
 */
@Repository
public class TSMetadataTableHome {

	private static final Log log = LogFactory.getLog(TSMetadataTableHome.class);

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

	public void persist(TSMetadataTable transientInstance) {
		log.debug("persisting TSMetadataTable instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TSMetadataTable instance) {
		log.debug("attaching dirty TSMetadataTable instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TSMetadataTable instance) {
		log.debug("attaching clean TSMetadataTable instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TSMetadataTable persistentInstance) {
		log.debug("deleting TSMetadataTable instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TSMetadataTable merge(TSMetadataTable detachedInstance) {
		log.debug("merging TSMetadataTable instance");
		try {
			TSMetadataTable result = (TSMetadataTable) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	public List findAll() {
		log.debug("findAll TSMetadataTable instance");
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + TSMetadataTable.class.getName())
					.list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}
	public TSMetadataTable findById(java.lang.Integer id) {
		log.debug("getting TSMetadataTable instance with id: " + id);
		try {
			TSMetadataTable instance = (TSMetadataTable) sessionFactory
					.getCurrentSession().get(
							"org.leafframework.data.dao.orm.TSMetadataTable",
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

	public List findByExample(TSMetadataTable instance) {
		log.debug("finding TSMetadataTable instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.leafframework.data.dao.orm.TSMetadataTable")
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
