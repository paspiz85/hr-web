package it.objectway.hr.business;

import java.util.List;

import it.objectway.hr.dati.Department;
import it.objectway.hr.dati.Employee;
import it.objectway.hr.dati.JobHistory;
import it.objectway.hr.dati.Job;
import it.objectway.hr.dati.ReportDepartment;
import it.objectway.hr.dati.User;

public interface Manager {
	
	List<Employee> getEmployee() throws Exception;
	
	List<Employee> getEmployee(Employee e) throws Exception;
	
	Employee getEmployeeById(Employee e) throws Exception;
	
	boolean login(String username, String password) throws Exception;
	
	boolean update(Employee e) throws Exception;
	
	boolean insert(Employee e) throws Exception;;
	
	List<Employee> getManager() throws Exception;;
	
	List<Department> getDipartimenti() throws Exception;;
	
	List<ReportDepartment> getReportDipartimenti() throws Exception;;
	
	List<Job> getJobs() throws Exception;;

	List<JobHistory> getJobHistory(int id) throws Exception;;
	
	// Metodi per user
	List<User> getUsers() throws Exception;;
	
	boolean insertUser(User u) throws Exception;;
	
	boolean updateUser(User u) throws Exception;;
	
	boolean deleteUser(int id) throws Exception;;
	
	public User getUser(User u) throws Exception;
	
	
}
