package it.objectway.hibernate.hr.dao;

import it.objectway.hibernate.hr.model.Countries;

import org.springframework.stereotype.Component;

@Component
public class CountriesDaoImpl extends AbstractDao<Countries, String> implements CountriesDao {

	@Override
	public Countries getCountry(String countryId) {
		return getById(Countries.class, countryId);
	}

}
