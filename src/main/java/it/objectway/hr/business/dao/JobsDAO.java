package it.objectway.hr.business.dao;

import it.objectway.hr.business.Utils;
import it.objectway.hr.dati.Job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JobsDAO extends DAO {
	
	private final String  ALL_JOBS = "SELECT job_id, job_title FROM jobs ";
	
	public JobsDAO(Connection conn) {
		super(conn);
	}	

	public List<Job> getAllJobs() throws SQLException {	
		List<Job> lista = new ArrayList<>();
		Statement st = null; 
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(ALL_JOBS);		
			while (rs.next()) {
				Job job = new Job();
				job.setId(rs.getString("job_id"));
				job.setTitle(rs.getString("job_title"));
				lista.add(job);
			}
		} finally {
			Utils.closeObject(st, rs);
		}
		return lista;
	}
}
