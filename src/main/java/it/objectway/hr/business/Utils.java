package it.objectway.hr.business;

import java.sql.SQLException;

public class Utils {	
	
	public static void closeObject(AutoCloseable... closeable) throws SQLException {
		for(AutoCloseable c: closeable){
			if(c != null){
				try {
					c.close();
				} catch (Exception e){
					throw new SQLException(e);
				}
			}
		}
	}


}
