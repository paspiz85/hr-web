package it.objectway.hr.business;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

@Deprecated
public class MyConnection {

	public static final Logger log = Logger.getLogger(MyConnection.class);
	
	public Connection getConnection() throws Exception {		
        Context ctx = null;
        Connection con = null;    
        try {
			ctx = new InitialContext();
	    	DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
	    	con = ds.getConnection();
        } finally {
        	ctx.close();
        }
        if ( con == null ){
        	throw new Exception("La connessione e' null");
        }
		return con;
	}
}
