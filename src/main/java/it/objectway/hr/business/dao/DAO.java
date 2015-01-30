package it.objectway.hr.business.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

@Deprecated
public abstract class DAO {
	
	public final Logger log = Logger.getLogger(getClass());
	
	protected Connection conn;
	
	public DAO(Connection conn) {
		this.conn = conn;
	}

}
