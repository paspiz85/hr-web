package it.objectway.hr.dati;

import java.io.Serializable;
import java.util.Date;

@Deprecated
public class JobHistory implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Employee employee;
	
	private Date startDate;
	
	private Date endDate;
	
	private Job job;
	
	private Department department;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
