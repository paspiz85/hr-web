package it.objectway.hibernate.hr.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public abstract class AbstractDao<E, I> {

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
	
	@SuppressWarnings("unchecked")
	public I insert(E entity) {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (I) session.save(entity);
		} catch (HibernateException ex) {
			throw SessionFactoryUtils.convertHibernateAccessException(ex);
		}
	}

}
