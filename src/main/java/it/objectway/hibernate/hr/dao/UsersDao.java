package it.objectway.hibernate.hr.dao;

import it.objectway.hibernate.hr.model.Users;

public interface UsersDao {

	public Users checkAuthentication(String username, String password);

	Users getUser(String userId);

}
