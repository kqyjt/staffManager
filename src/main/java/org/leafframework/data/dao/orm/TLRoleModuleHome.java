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
 * Home object for domain model class TLRoleModule.
 * @see org.leafframework.data.dao.orm.TLRoleModule
 * @author Hibernate Tools
 */
@Repository
public class TLRoleModuleHome implements IDao {

	private static final Log log = LogFactory.getLog(TLRoleModuleHome.class);

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

	public void persist(TLRoleModule transientInstance) {
		log.debug("persisting TLRoleModule instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TLRoleModule instance) {
		log.debug("attaching dirty TLRoleModule instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TLRoleModule instance) {
		log.debug("attaching clean TLRoleModule instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TLRoleModule persistentInstance) {
		log.debug("deleting TLRoleModule instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TLRoleModule merge(TLRoleModule detachedInstance) {
		log.debug("merging TLRoleModule instance");
		try {
			TLRoleModule result = (TLRoleModule) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("findAll TLRoleModule instance");
		try {
			return sessionFactory.getCurrentSession().createQuery("from " + TLRoleModule.class.getName()).list();
		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	public TLRoleModule findById(java.lang.Integer id) {
		log.debug("getting TLRoleModule instance with id: " + id);
		try {
			TLRoleModule instance = (TLRoleModule) sessionFactory.getCurrentSession()
					.get("org.leafframework.data.dao.orm.TLRoleModule", id);
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

	public List findByExample(TLRoleModule instance) {
		log.debug("finding TLRoleModule instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("org.leafframework.data.dao.orm.TLRoleModule")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
