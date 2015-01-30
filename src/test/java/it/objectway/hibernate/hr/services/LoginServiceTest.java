package it.objectway.hibernate.hr.services;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.objectway.hibernate.hr.dao.UsersDao;
import it.objectway.hibernate.hr.model.Users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/it/objectway/hibernate/hr/dao/mockContext.xml",
		"/it/objectway/hibernate/hr/services/applicationContext.xml" })
public class LoginServiceTest {

	@Component
	public static class Init implements InitializingBean {

		@Autowired
		private UsersDao usersDao;

		@Override
		public void afterPropertiesSet() throws Exception {
			Users user = new Users();
			expect(
					usersDao.checkAuthentication(eq("admin"),
							eq("admin"))).andReturn(user).anyTimes();
			expect(
					usersDao.checkAuthentication(eq("admin"),
							anyString())).andReturn(null).anyTimes();
			expect(
					usersDao.checkAuthentication(eq("error"),
							anyString()))
					.andThrow(
							new DataAccessResourceFailureException(
									"connection error")).anyTimes();
			expect(
					usersDao.checkAuthentication(anyString(),
							anyString())).andReturn(null).anyTimes();
			replay(usersDao);
		}

	}

	@Autowired
	private LoginServiceImpl loginService;

	@Autowired
	private UsersDao usersDao;

	@Test
	public void test001() throws Exception {
		boolean result = loginService.getLogin("admin", "admin");
		assertTrue(result);
	}

	@Test
	public void test002() throws Exception {
		boolean result = loginService.getLogin("admin", "wrong");
		assertFalse(result);
	}

	@Test
	public void test003() throws Exception {
		boolean result = loginService.getLogin(null, "wrong");
		assertFalse(result);
	}

	@Test
	public void test004() throws Exception {
		boolean result = loginService.getLogin("error", "wrong");
		assertFalse(result);
	}

}
