package it.objectway.hibernate.hr.dao;

import java.util.List;

import it.objectway.hibernate.hr.model.Users;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Users checkAuthentication(String username, String password) {
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
	}

}
