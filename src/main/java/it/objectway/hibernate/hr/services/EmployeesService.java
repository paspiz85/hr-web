package it.objectway.hibernate.hr.services;

import it.objectway.hibernate.hr.model.Employees;

public interface EmployeesService {
	
	public Employees getEmployee(Integer id);

	public void insert(Employees newEmployee);

	public void update(Employees newEmployee);

}
