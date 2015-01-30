package it.objectway.hibernate.hr.dao;

import it.objectway.hibernate.hr.model.Users;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Component;

@Component
public class UsersDaoImpl extends AbstractDao<Users, String> implements UsersDao {

	@Override
	public Users checkAuthentication(String username, String password) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session
					.createQuery("select u from Users u where u.userId = :username and u.password = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			@SuppressWarnings("unchecked")
			List<Users> result = query.list();
			if (result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (HibernateException ex) {
			throw SessionFactoryUtils.convertHibernateAccessException(ex);
		}
	}
	
	@Override
	public Users getUser(String userId) {
		return getById(Users.class, userId);
	}

}
