package it.objectway.hr.dati;

import java.io.Serializable;

public class ReportDepartment extends Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String employeeNumber;
	
	private double stipendioMedio;

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public double getStipendioMedio() {
		return stipendioMedio;
	}

	public void setStipendioMedio(double stipendioMedio) {
		this.stipendioMedio = stipendioMedio;
	}
	
	public String getManagerLastName(){
		return super.getManager().getLastName();
	}
	
	public String getCountryName(){
		return super.getLocation().getCountry().getName();
	}

}
