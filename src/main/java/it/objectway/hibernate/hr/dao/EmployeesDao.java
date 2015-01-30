package it.objectway.hibernate.hr.dao;

import it.objectway.hibernate.hr.model.Employees;

public interface EmployeesDao {
	
	Employees getEmployee(Integer userId);
	
	Integer insert(Employees e);
	
	boolean update(Employees e);
	
}
