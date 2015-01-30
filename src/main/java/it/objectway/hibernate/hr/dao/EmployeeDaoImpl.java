package it.objectway.hibernate.hr.dao;

import it.objectway.hibernate.hr.model.Employees;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoImpl extends AbstractDao<Employees,Integer> implements EmployeesDao {	
	
	@Override
	public Employees getEmployee(Integer userId) {
		return getById(Employees.class, userId);
	}

	
	@Override
	public boolean update(Employees e){
		//TODO implementare aggiornamento
		return false;
	}
	
}
