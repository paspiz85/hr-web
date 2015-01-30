package it.objectway.hibernate.hr.dao;

import it.objectway.hibernate.hr.model.Users;

public interface LoginDao {

	public Users checkAuthentication(String username, String password);

}
