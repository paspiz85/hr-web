package it.objectway.hibernate.hr.services;

import it.objectway.hibernate.hr.dao.UsersDao;
import it.objectway.hibernate.hr.model.Users;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessResourceFailureException;

public class LoginServiceTest {

	private LoginServiceImpl loginService;

	@Before
	public void init() {
		UsersDao usersDao = EasyMock.createMock(UsersDao.class);
		loginService = new LoginServiceImpl();
		loginService.setUsersDao(usersDao);
		Users user = new Users();
		EasyMock.expect(
				usersDao.checkAuthentication(EasyMock.eq("admin"),
						EasyMock.eq("admin"))).andReturn(user).anyTimes();
		EasyMock.expect(
				usersDao.checkAuthentication(EasyMock.eq("admin"),
						EasyMock.anyString())).andReturn(null).anyTimes();
		EasyMock.expect(
				usersDao.checkAuthentication(EasyMock.eq("error"),
						EasyMock.anyString()))
				.andThrow(
						new DataAccessResourceFailureException(
								"connection error")).anyTimes();
		EasyMock.expect(
				usersDao.checkAuthentication(EasyMock.anyString(),
						EasyMock.anyString())).andReturn(null).anyTimes();
		EasyMock.replay(usersDao);
	}

	@Test
	public void test001() throws Exception {
		boolean result = loginService.getLogin("admin", "admin");
		Assert.assertTrue(result);
	}

	@Test
	public void test002() throws Exception {
		boolean result = loginService.getLogin("admin", "wrong");
		Assert.assertFalse(result);
	}

	@Test
	public void test003() throws Exception {
		boolean result = loginService.getLogin(null, "wrong");
		Assert.assertFalse(result);
	}

	@Test
	public void test004() throws Exception {
		boolean result = loginService.getLogin("error", "wrong");
		Assert.assertFalse(result);
	}

}
