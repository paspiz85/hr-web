package it.objectway.hibernate.hr.services;

import it.objectway.hibernate.hr.dao.LoginDao;
import it.objectway.hibernate.hr.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public boolean getLogin(String username, String password) {
		System.out.println("Tentativo di login " + username);
		// TODO Auto-generated method stub
		Users user = loginDao.checkAuthentication(username, password);
		return user != null;
	}

}
