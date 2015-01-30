package it.objectway.hr.dati;

import java.io.Serializable;
import java.util.Date;

@Deprecated
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	private Date hireDate;
	
	private double salary;
	
	private float commissionPct;
	
	private Job job;
	
	private Employee manager;
	
	private Department department;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public float getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(float commissionPct) {
		this.commissionPct = commissionPct;
	}
	
}
