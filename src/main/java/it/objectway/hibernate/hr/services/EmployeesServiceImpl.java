package it.objectway.hibernate.hr.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectway.hibernate.hr.dao.EmployeesDao;
import it.objectway.hibernate.hr.dao.JobHistoryDao;
import it.objectway.hibernate.hr.model.Employees;
import it.objectway.hibernate.hr.model.JobHistory;
import it.objectway.hibernate.hr.model.JobHistoryId;

@Component
public class EmployeesServiceImpl implements EmployeesService {
	
	private EmployeesDao employeeDao;
	
	private JobHistoryDao jobHistoryDao;

	@Autowired
	public void setUsersDao(EmployeesDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@Autowired
	public void setJobHistoryDao(JobHistoryDao jobHistoryDao) {
		this.jobHistoryDao = jobHistoryDao;
	}

	@Override
	public Employees getEmployee(Integer id) {
		return employeeDao.getEmployee(id);
	}

	@Override
	public void insert(Employees e) {
		JobHistory jobHistory = new JobHistory();
		jobHistory.setId(new JobHistoryId(e.getEmployeeId(), new Date()));
		jobHistory.setEmployees(e);
		jobHistory.setDepartments(e.getDepartments());
		jobHistory.setJobs(e.getJobs());		
		employeeDao.insert(e);
		jobHistoryDao.insert(jobHistory);
	}

	@Override
	public void update(Employees e) {
		employeeDao.update(e);
	}
 
}
