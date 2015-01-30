package it.objectway.hibernate.hr.dao;

import java.io.Serializable;

import it.objectway.hibernate.hr.model.JobHistory;

public interface JobHistoryDao  {

	Serializable insert(JobHistory jobHistory);
	
}
