package it.objectway.hibernate.hr.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public abstract class AbstractDao<E, I> {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected SessionFactory sessionFactory;

	public E getById(Class<E> c, Serializable id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			E result = (E) session.get(c, id);
			return result;
		} catch (HibernateException ex) {
			throw SessionFactoryUtils.convertHibernateAccessException(ex);
		}
	}

}
