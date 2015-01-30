package it.objectway.hr.business.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.objectway.hr.business.Utils;
import it.objectway.hr.dati.Department;
import it.objectway.hr.dati.Employee;
import it.objectway.hr.dati.JobHistory;
import it.objectway.hr.dati.Job;

@Deprecated
public class JobHistoryDAO extends DAO {
	
	public JobHistoryDAO(Connection conn) {
		super(conn);
	}

	private final String JOB_HISTORY = " SELECT jh.*, d.department_name, j.job_title, e.first_name, e.last_name " +
			"FROM employees e, departments d, jobs j, job_history jh " +			
			"WHERE d.department_id = jh.department_id AND j.job_id = jh.job_id AND jh.employee_id = e.employee_id AND jh.employee_id = ?";
	
	public List<JobHistory> getJobHistory(int id) throws SQLException {
		List<JobHistory> jobHistoryList = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = conn.prepareStatement(JOB_HISTORY);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();					
			if( rs.next() ) {			
				JobHistory row = new JobHistory();				
				Date dateSql = rs.getDate("start_date");
				java.util.Date starDate = dateSql != null ? new java.util.Date(dateSql.getTime()) : null;				
				dateSql = rs.getDate("end_date");
				java.util.Date endDate = dateSql != null ? new java.util.Date(dateSql.getTime()) : null;
				row.setStartDate(starDate);
				row.setEndDate(endDate);
				Employee e = new Employee();
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				row.setEmployee(e);
				Job j = new Job();
				j.setTitle(rs.getString("job_title"));
				row.setJob(j);
				Department d = new Department();
				d.setName(rs.getString("department_name"));
				row.setDepartment(d);
				jobHistoryList.add(row);
			}
		} finally {
			Utils.closeObject(rs, preparedStatement);
		}
		return jobHistoryList;
	}
	
	public boolean insert(Employee e) throws SQLException {
		final String INSERT_JOB_HISTORY = "INSERT INTO job_history VALUES (?, ?, NULL, ?, ?) ";
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try {
			log.info("Inizio Insert JobHistory");
			preparedStatement = conn.prepareStatement(INSERT_JOB_HISTORY);
			preparedStatement.setInt(1, e.getId());
			Date sqlDate = new Date(e.getHireDate().getTime());
			preparedStatement.setDate(6, sqlDate);
			preparedStatement.setDate(2, sqlDate);
			preparedStatement.setString(3, e.getJob().getId());
			preparedStatement.setInt(4, e.getDepartment().getId());			
			rows = preparedStatement.executeUpdate();
			log.info("Insert JobHistory Eseguito");			
		} finally {
			Utils.closeObject(preparedStatement);
		}		
		return rows > 0;
	}

}
