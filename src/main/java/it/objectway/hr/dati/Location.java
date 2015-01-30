package it.objectway.hr.dati;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private int id;
	
	private String streetAddress;
	
	private String postalCode;
	
	private String city;
	
	private String stadeProvince;
	
	private Country country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStadeProvince() {
		return stadeProvince;
	}

	public void setStadeProvince(String stadeProvince) {
		this.stadeProvince = stadeProvince;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
