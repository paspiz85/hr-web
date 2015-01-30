package it.objectway.hr.business.dao;

import it.objectway.hr.business.Utils;
import it.objectway.hr.dati.Department;
import it.objectway.hr.dati.Employee;
import it.objectway.hr.dati.Job;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class EmployeeDAO  extends DAO {
	
	private final String SELECT_ALL_EMPLOYEE = "" +
			"SELECT e.*, j.job_title, j.job_id, d.department_name, d.department_id, e.hire_date " +
			"FROM employees e, departments d, jobs j " +
			"WHERE d.department_id = e.department_id AND j.job_id = e.job_id ";
	
	public EmployeeDAO(Connection conn) {
		super(conn);
	}
	
	public boolean update(Employee e) throws SQLException {
		log.info("Update Query");
		final String UPDATE_EMPLOYEE = "UPDATE employees " + 
				" SET first_name = ? , last_name = ? , email = ? , phone_number = ? , hire_date = ?, " +
				" job_id = ? , salary = ? , commission_pct = ? , manager_id = ? , department_id = ? " +
				" WHERE employee_id = ? ";
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(UPDATE_EMPLOYEE);
			preparedStatement.setString(1, e.getFirstName());
			preparedStatement.setString(2, e.getLastName());
			preparedStatement.setString(3, e.getEmail());
			preparedStatement.setString(4, e.getPhoneNumber());
			Date sqlDate = new Date(e.getHireDate().getTime());
			preparedStatement.setDate(5, sqlDate);
			preparedStatement.setString(6, e.getJob().getId());
			preparedStatement.setDouble(7, e.getSalary());
			preparedStatement.setFloat(8, e.getCommissionPct());
			preparedStatement.setInt(9, e.getManager().getId());
			preparedStatement.setInt(10, e.getDepartment().getId());
			preparedStatement.setInt(11, e.getId());
			rows = preparedStatement.executeUpdate();
		} finally {		
			Utils.closeObject(preparedStatement);
		}
		return rows > 0;
	}
	
	public boolean insert(Employee e) throws SQLException {		
		final String INSERT_EMPLOYEE = "INSERT INTO employees " + 
				" VALUES ( ? , ? , ? , ? , ? , ?, ? , ? , ? , ? , ? ) ";
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try {
			log.info("Inizio Insert Employee");
			preparedStatement = conn.prepareStatement(INSERT_EMPLOYEE);		
			preparedStatement.setInt(1, e.getId());
			preparedStatement.setString(2, e.getFirstName());
			preparedStatement.setString(3, e.getLastName());
			preparedStatement.setString(4, e.getEmail());
			preparedStatement.setString(5, e.getPhoneNumber());
			Date sqlDate = new Date(e.getHireDate().getTime());
			preparedStatement.setDate(6, sqlDate);
			preparedStatement.setString(7, e.getJob().getId());
			preparedStatement.setDouble(8, e.getSalary());
			preparedStatement.setFloat(9, e.getCommissionPct());
			preparedStatement.setInt(10, e.getManager().getId());
			preparedStatement.setInt(11, e.getDepartment().getId());
			rows = preparedStatement.executeUpdate();
			log.info("Insert Employee Eseguito");
		} finally {
			Utils.closeObject(preparedStatement);
		}
		return rows > 0;
	}
	
	public List<Employee> getManager() throws SQLException {
		List<Employee> result = new ArrayList<Employee>();
		Statement st = null; 
		ResultSet rs = null;
		final String SELECT_ALL_MANAGER = "SELECT e.employee_id, e.last_name FROM employees e";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SELECT_ALL_MANAGER);
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("employee_id"));
				employee.setLastName(rs.getString("last_name"));
				result.add(employee);
			}
		} finally {
			Utils.closeObject(st, rs);
		}
		return result;
	}
	
	public List<Employee> getEmployee() throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		List<Employee> result = new ArrayList<Employee>();
		try { 
			st = conn.createStatement();
			rs = st.executeQuery(SELECT_ALL_EMPLOYEE);
			result = creaListaEmployee(rs);
		} finally {
			Utils.closeObject(st, rs);
		}
		return result;
	}
	
	public List<Employee> getEmployee(Employee e) throws SQLException {
		StringBuffer selectSQL = new StringBuffer(SELECT_ALL_EMPLOYEE);
		String SEARCH_BY_ID = " AND e.employee_id = ? ";
		String SEARCH_BY_LAST_NAME = " AND e.last_name = ? ";
		String SEARCH_BY_JOB = " AND j.job_id = ? ";
		String SEARCH_BY_DEPARTMENT = " AND d.department_id = ? ";
		List<Employee> result = new ArrayList<Employee>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if (e.getId() > 0) {
			selectSQL.append(SEARCH_BY_ID);
		}
		if (e.getLastName() != null) {
			selectSQL.append(SEARCH_BY_LAST_NAME);
		}
		if (e.getJob() != null) {
			selectSQL.append(SEARCH_BY_JOB);
		}
		if (e.getDepartment() != null) {
			selectSQL.append(SEARCH_BY_DEPARTMENT);
		}				
		log.info("Query: " + selectSQL);
		try {
			st = conn.prepareStatement(selectSQL.toString());
			int i = 0;
			if (e.getId() > 0) {
				st.setInt(++i,  e.getId());
			}
			if (e.getLastName() != null) {
				st.setString(++i,  e.getLastName());
			}
			if (e.getJob() != null) {
				st.setString(++i,  e.getJob().getId());
			}			
			if (e.getDepartment() != null) {
				st.setInt(++i,  e.getDepartment().getId());
			}
			rs = st.executeQuery();
			result = creaListaEmployee(rs);
		} finally {
			Utils.closeObject(st, rs);
		}
		return result;
		
	}
	
	private List<Employee> creaListaEmployee(ResultSet rs) throws SQLException {
		List<Employee> result = new ArrayList<Employee>();
		while (rs.next()) {
			Employee employee = new Employee();
			employee.setId(rs.getInt("employee_id"));
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));
			employee.setEmail(rs.getString("email"));
			employee.setPhoneNumber(rs.getString("phone_number"));
			java.util.Date date = new java.util.Date(rs.getDate("hire_date").getTime());
			employee.setHireDate(date);
			employee.setSalary(rs.getDouble("salary"));
			employee.setCommissionPct(rs.getFloat("commission_pct"));
			Employee manager = new Employee();
			manager.setId(rs.getInt("manager_id"));
			employee.setManager(manager);
			Job j = new Job();
			j.setTitle(rs.getString("job_title"));
			j.setId(rs.getString("job_id"));
			employee.setJob(j);
			Department d = new Department();
			d.setId(rs.getInt("department_id"));
			d.setName(rs.getString("department_name"));
			employee.setDepartment(d);
			result.add(employee);
		}
		return result;
	}
	
	public boolean login(String user, String password) throws SQLException {
		boolean login = false;
		String SELECT_FOR_LOGIN = "SELECT COUNT(u.user_id) AS TOT " +
				" FROM users u, job_history jh WHERE " + 
				" u.user_id = ? AND u.password = ? AND u.enabled = '1' " + 
				" AND u.EMPLOYEE_ID = jh.EMPLOYEE_ID AND ( jh.END_DATE IS NULL OR jh.END_DATE > SYSDATE ) " ;		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(SELECT_FOR_LOGIN);
			st.setString(1, user);
			st.setString(2, password);
			rs = st.executeQuery();
			if( rs.next() ) {			
				login = rs.getInt("TOT") > 0;
			}
		} finally {
			Utils.closeObject(st, rs);
		}		
		log.info("Login: " + login);		
		return login;
	}
		
}
