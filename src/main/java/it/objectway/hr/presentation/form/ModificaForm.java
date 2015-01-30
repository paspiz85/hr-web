package it.objectway.hr.presentation.form;

import java.util.ResourceBundle;

import org.apache.struts.action.ActionForm;

public class ModificaForm extends ActionForm {
	
	static {
		ResourceBundle res = ResourceBundle.getBundle("MessageResouces");
		AZIONE_INSERT = res.getString("azione.insert");
		AZIONE_UPDATE = res.getString("azione.modify");
		ESITO_OK = res.getString("azione.esito.ok");
		ESITO_KO = res.getString("azione.esito.ko");
	}
	
	public static final String AZIONE_INSERT;
	
	public static final String AZIONE_UPDATE;
	
	public static final String ESITO_OK;
	
	public static final String ESITO_KO;

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String hireDate;
	private String salary;
	private String commissionPct;
	private String selectedDepartment;
	private String selectedJob;
	private String selectedManager;
	private String message;
	private String title;
	private String button;
	private String action;
	private boolean readOnly;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(String commissionPct) {
		this.commissionPct = commissionPct;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(String selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

	public String getSelectedJob() {
		return selectedJob;
	}

	public void setSelectedJob(String selectedJob) {
		this.selectedJob = selectedJob;
	}

	public String getSelectedManager() {
		return selectedManager;
	}

	public void setSelectedManager(String selectedManager) {
		this.selectedManager = selectedManager;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
		setTitle(action);
		setButton(action);
	}
	
}
