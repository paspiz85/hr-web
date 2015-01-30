package it.objectway.hr.dati;

import java.io.Serializable;

@Deprecated
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private int employeeId;
	
	private String password;
	
	private String nome;
	
	private String cognome;
	
	private boolean enabled;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}	
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
}
