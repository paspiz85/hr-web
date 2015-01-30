package it.objectway.hr.presentation.form;

import org.apache.struts.action.ActionForm;

public class NewUserForm extends ActionForm {

	private static final long serialVersionUID = 1L;
		
	private String id;
	
	private String employeeId;
	
	private String password;
	
	private String nome;
	
	private String cognome;
		
	private String enabled;
	
	private String selectedEmployee;
	
	private String azione;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getAzione() {
		return azione;
	}

	public void setAzione(String azione) {
		this.azione = azione;
	}

	public String getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(String selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	
	public String getEnabled(){
		return enabled;
	}
	
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}

}
