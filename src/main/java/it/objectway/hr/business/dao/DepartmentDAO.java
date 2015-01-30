package it.objectway.hr.business.dao;

import it.objectway.hr.business.Utils;
import it.objectway.hr.dati.Country;
import it.objectway.hr.dati.Department;
import it.objectway.hr.dati.Employee;
import it.objectway.hr.dati.Location;
import it.objectway.hr.dati.ReportDepartment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class DepartmentDAO extends DAO {
		
	public DepartmentDAO(Connection conn) {
		super(conn);
	}
	
	public List<Department> getAllDepartmets() throws SQLException {
		final String  ALL_DEPARTMENTS = "SELECT department_id, department_name FROM departments ";
		List<Department> lista = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(ALL_DEPARTMENTS);
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("department_id"));
				dep.setName(rs.getString("department_name"));
				lista.add(dep);
			}
		} finally {
			Utils.closeObject(st, rs);
		}
		return lista;
	}
	
	public List<ReportDepartment> getReportDepartments() throws SQLException {
		final String  REPORT_DEPARTMENTS = "SELECT " +
				" d.department_id, d.department_name, c.country_name, COUNT(e.employee_id) AS employees_number, ROUND(AVG(e.salary)) AS avg_salary, m.last_name " +
				" FROM departments d, employees e, locations l, countries c, " + 
				"	(SELECT d.manager_id, e.last_name FROM departments d, employees e WHERE e.employee_id = d.manager_id) m "+
				" WHERE	d.department_id = e.department_id " + 
				"	AND d.location_id = l.location_id " + 
				"	AND c.country_id = l.country_id " +
				"	AND d.manager_id = m.manager_id " +
				" GROUP BY d.department_id, d.department_name, c.country_name, m.last_name " +
				" ORDER BY department_id ";
		List<ReportDepartment> lista = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(REPORT_DEPARTMENTS);
			while (rs.next()) {
				ReportDepartment dep = new ReportDepartment();
				dep.setId(rs.getInt("department_id"));
				dep.setName(rs.getString("department_name"));
				Country c = new Country();
				c.setName(rs.getString("country_name"));
				Location location = new Location();
				location.setCountry(c);
				dep.setLocation(location);
				dep.setEmployeeNumber(rs.getString("employees_number"));
				dep.setStipendioMedio(rs.getDouble("avg_salary"));
				Employee e = new Employee();
				e.setLastName(rs.getString("last_name"));
				dep.setManager(e);
				lista.add(dep);
			}
		} finally {
			Utils.closeObject(st, rs);
		}
		return lista;
	}
}
