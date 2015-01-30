package it.objectway.hr.presentation.form;

import it.objectway.hr.dati.Employee;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

public class InitRicercaForm extends ActionForm {

	private static final long serialVersionUID = -1L;
	
	private String lastName;
	
	private List<LabelValueBean> departmentList = new ArrayList<>();
	
	private List<LabelValueBean> jobsList = new ArrayList<>();
	
	private List<Employee> employeesList = new ArrayList<>();
	
	private String selectedJob;
	
	private String selectedDepartment;
	
	public List<LabelValueBean> getJobsList() {
		return jobsList;
	}

	public void setJobsList(List<LabelValueBean> jobsList) {
		this.jobsList = jobsList;
	}

	public String getSelectedJob() {
		return selectedJob;
	}

	public void setSelectedJob(String selectedJob) {
		this.selectedJob = selectedJob;
	}

	public List<LabelValueBean> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<LabelValueBean> departmentList) {
		this.departmentList = departmentList;
	}

	public String getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(String selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Employee> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(List<Employee> employeesList) {
		this.employeesList = employeesList;
	}
	
}
