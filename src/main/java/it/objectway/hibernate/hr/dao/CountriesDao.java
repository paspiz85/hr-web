package it.objectway.hibernate.hr.dao;

import it.objectway.hibernate.hr.model.Countries;

public interface CountriesDao {

	Countries getCountry(String countryId);

}
