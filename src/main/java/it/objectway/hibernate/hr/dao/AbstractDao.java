package it.objectway.hibernate.hr.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public abstract class AbstractDao<E> {

	@Autowired
	protected SessionFactory sessionFactory;

	public E getById(Class<E> c, String id) {
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
