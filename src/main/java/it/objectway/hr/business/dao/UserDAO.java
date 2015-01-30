package it.objectway.hr.business.dao;

import it.objectway.hr.business.Utils;
import it.objectway.hr.dati.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {

	public UserDAO(Connection conn) {
		super(conn);
	}
	
	public List<User> getUsers(User u) throws SQLException {
		final String ALL_USERS = " SELECT u.*, e.first_name, e.last_name "
				+ " FROM users u, employees e "
				+ " WHERE u.employee_id = e.employee_id ";
		List<User> listaUsers = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			if( u != null ){
				st = conn.prepareStatement(ALL_USERS + " AND u.employee_id = ? ");
				((PreparedStatement)st).setInt(1, u.getEmployeeId());
				rs = ((PreparedStatement)st).executeQuery();
			} else { 
				st = conn.createStatement();
				rs = st.executeQuery(ALL_USERS);
			}
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("user_id"));
				user.setEmployeeId(rs.getInt("employee_id"));
				user.setPassword(rs.getString("password"));
				user.setNome(rs.getString("first_name"));
				user.setCognome(rs.getString("last_name"));
				user.setEnabled(rs.getBoolean("enabled"));
				listaUsers.add(user);
			}
		} finally {
			Utils.closeObject(rs, st);
		}
		return listaUsers;
	}
	
	public boolean deleteUser(int id)throws SQLException {
		final String DELETE = "DELETE FROM users WHERE employee_id = ? ";
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(DELETE);
			preparedStatement.setInt(1, id);
			rows = preparedStatement.executeUpdate();
		} finally {
			Utils.closeObject(preparedStatement);
		}
		return rows > 0;
	}
	
	public boolean updateUser(User user)throws SQLException {
		final String UPDATE = "UPDATE users SET user_id = ?, password = ? , enabled = ? WHERE employee_id = ? ";
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(UPDATE);
			preparedStatement.setString(1, user.getId());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setBoolean(3, user.getEnabled());
			preparedStatement.setInt(4, user.getEmployeeId());
			rows = preparedStatement.executeUpdate();
		} finally {
			Utils.closeObject(preparedStatement);
		}
		return rows > 0;
	}
	
	public boolean insertUser(User user)throws SQLException {
		final String UPDATE = "INSERT INTO users VALUES (?, ?, ?, ?) ";
		int rows = 0;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(UPDATE);
			preparedStatement.setString(1, user.getId());
			preparedStatement.setInt(2, user.getEmployeeId());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setBoolean(4, user.getEnabled());
			rows = preparedStatement.executeUpdate();
		} finally {
			Utils.closeObject(preparedStatement);
		}
		return rows > 0;
	}

}
