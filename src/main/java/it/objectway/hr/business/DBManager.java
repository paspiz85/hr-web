package it.objectway.hr.business;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import it.objectway.hr.business.dao.DepartmentDAO;
import it.objectway.hr.business.dao.EmployeeDAO;
import it.objectway.hr.business.dao.JobHistoryDAO;
import it.objectway.hr.business.dao.JobsDAO;
import it.objectway.hr.business.dao.UserDAO;
import it.objectway.hr.dati.Department;
import it.objectway.hr.dati.Employee;
import it.objectway.hr.dati.JobHistory;
import it.objectway.hr.dati.Job;
import it.objectway.hr.dati.ReportDepartment;
import it.objectway.hr.dati.User;

@Deprecated
public class DBManager implements Manager {
	
public static final Logger log = Logger.getLogger(DBManager.class);
	
	@Override
	public Employee getEmployeeById(Employee e) throws Exception {
		List<Employee> list = getEmployee(e);
		Employee employee = new Employee();
		if(list.size() == 1){
			employee = list.get(0);
		} else if(list.size() > 1){
			throw new Exception("Errore durante la ricerca: più utenti hanno lo stesso Id");
		}
		return employee;
	}
	
	public List<Employee> getEmployee() throws Exception {
		return getEmployee(null);
	}

	public List<Employee> getEmployee(Employee employee) throws Exception {
		Connection conn = new MyConnection().getConnection();
		EmployeeDAO dao = new EmployeeDAO(conn);
		List<Employee> lista = new ArrayList<>();
		try {
			lista = employee == null ? dao.getEmployee() : dao.getEmployee(employee);
		} catch (Exception e) {
			throw new Exception("Errore ricerca impiegato", e);
		} finally {
			closeConnection(conn);
		}
		return lista;		
	}
	
	@Override
	public boolean login(String username, String password) throws Exception {
		Connection conn = new MyConnection().getConnection();
		EmployeeDAO dao = new EmployeeDAO(conn);
		boolean login = false;
		try {
			login = dao.login(username, password);
		} catch (Exception e) {
			throw new Exception("Errore login", e);
		} finally {
			closeConnection(conn);
		}
		return login;
	}
	
	@Override
	public List<Department> getDipartimenti() throws Exception {
		Connection conn = new MyConnection().getConnection();
		DepartmentDAO dao = new DepartmentDAO(conn);
		List<Department> lista = new ArrayList<>();
		try {
			lista = dao.getAllDepartmets();
		} catch (Exception e){
			throw new Exception("Errore ricerca dipartimenti", e);
		} finally {
			closeConnection(conn);
		}
		return lista;
	}
	
	@Override
	public List<Job> getJobs() throws Exception {
		Connection conn = new MyConnection().getConnection();
		JobsDAO dao = new JobsDAO(conn);
		List<Job> lista = new ArrayList<>();
		try {
			lista = dao.getAllJobs();
		} catch (Exception e){
			throw new Exception("Errore ricerca Jobs", e);
		} finally {
			closeConnection(conn);
		}
		return lista;
	}
	
	@Override
	public List<Employee> getManager() throws Exception {
		Connection conn = new MyConnection().getConnection();
		EmployeeDAO dao = new EmployeeDAO(conn);
		List<Employee> lista = new ArrayList<>();
		try {
			lista = dao.getManager();
		} catch (Exception e){
			throw new Exception("Errore ricerca dipartimenti", e);
		} finally {
			closeConnection(conn);
		}
		return lista;
	}
	
	private void closeConnection(Connection conn) throws Exception {
		if ( conn != null ) {
			try {
				conn.close();
			} catch (Exception e) {
				throw new Exception("Errore durante la chiusura della connessione.", e);
			}
		}
	}

	@Override
	public boolean update(Employee e) throws Exception {
		boolean update = false;
		Connection conn = new MyConnection().getConnection();
		EmployeeDAO dao = new EmployeeDAO(conn);
		try {
			update = dao.update(e);
		} catch (Exception e1) {
			throw new Exception("Errore durante l'update", e1);
		} finally {
			closeConnection(conn);
		}
		return update;
	}

	@Override
	public List<JobHistory> getJobHistory(int id) throws Exception {
		List<JobHistory> jobHistory = new ArrayList<JobHistory>();
		Connection conn = new MyConnection().getConnection();
		JobHistoryDAO dao = new JobHistoryDAO(conn);
		try {
			jobHistory = dao.getJobHistory(id);
		} catch (Exception e) {
			throw new Exception("Errore durante caricamento job history.", e);
		} finally {
			closeConnection(conn);
		}
		return jobHistory;
	}

	@Override
	public boolean insert(Employee e) throws Exception {
		boolean insert = false;
		Connection conn = new MyConnection().getConnection();
		EmployeeDAO daoEmp = new EmployeeDAO(conn);
		JobHistoryDAO jobHDao = new JobHistoryDAO(conn);
		try {
			conn.setAutoCommit(false);			
			insert = daoEmp.insert(e) & jobHDao.insert(e);			
			conn.commit();
			log.info("Commit Eseguito");
		} catch (Exception e1) {
			log.warn("Rollback su inserimento eseguito");
			conn.rollback();
			throw new Exception("Errore durante l'inserimento.", e1);
		} finally {
			closeConnection(conn);
		}
		return insert;
	}

	@Override
	public List<ReportDepartment> getReportDipartimenti() throws Exception {
		List<ReportDepartment> lista = new ArrayList<>();
		Connection conn = new MyConnection().getConnection();
		DepartmentDAO dao = new DepartmentDAO(conn);
		try {
			lista = dao.getReportDepartments();
		} catch (Exception e) {
			throw new Exception("Errore durante la richiesta dei dipartimenti.", e);
		} finally {
			closeConnection(conn);
		}
		return lista;
	}

	@Override
	public List<User> getUsers() throws Exception{
		return getUsers(null);
	}
	
	@Override
	public User getUser(User u) throws Exception {
		List<User> lista = getUsers(u);
		User user = new User();
		if( lista.size() == 1 )
			user = lista.get(0);
		else if (lista.size() > 1) {
			throw new Exception("Errore : piu' utenti hanno lo stesso id");
		}
		return user;
	}
	
	private List<User> getUsers(User u) throws Exception {
		List<User> lista = new ArrayList<>();
		Connection conn = new MyConnection().getConnection();
		UserDAO dao = new UserDAO(conn);
		try {
			lista = dao.getUsers(u);
		} catch (Exception e) {
			throw new Exception("Errore durante la richiesta degli utenti." , e);
		} finally {
			closeConnection(conn);
		}
		return lista;
	}

	@Override
	public boolean insertUser(User u) throws Exception {
		boolean insert = false;
		Connection conn = new MyConnection().getConnection();
		UserDAO dao = new UserDAO(conn);
		try {
			insert = dao.insertUser(u);
		} catch (Exception e) {
			throw new Exception("Errore durante l'inserimento." , e);
		} finally {
			closeConnection(conn);
		}
		return insert;
	}

	@Override
	public boolean updateUser(User u) throws Exception {
		boolean insert = false;
		Connection conn = new MyConnection().getConnection();
		UserDAO dao = new UserDAO(conn);
		try {
			insert = dao.updateUser(u);
		} catch (Exception e) {
			throw new Exception("Errore durante l'aggiornamento ." , e);
		} finally {
			closeConnection(conn);
		}
		return insert;
	}

	@Override
	public boolean deleteUser(int id) throws Exception {
		boolean delete = false;
		Connection conn = new MyConnection().getConnection();
		UserDAO userDao = new UserDAO(conn);
		try {
			delete = userDao.deleteUser(id);
		} catch (Exception e) {
			throw new Exception("Errore durante la cancellazione." , e);
		} finally {
			closeConnection(conn);
		}
		return delete;
	}

}
