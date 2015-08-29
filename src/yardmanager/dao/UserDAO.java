/**
 * 
 */
package yardmanager.dao;

import yardmanager.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author maxetron
 *
 */
public class UserDAO {
	private Connection conn;
	
	public UserDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<User> list() {
		List<User> users = new ArrayList<User>();
		
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Users");
			
			while(rs.next()) {
				User user = new User(
					rs.getString("Username"),
					rs.getString("Password"),
					rs.getString("Clearance"),
					rs.getString("FirstName"),
					rs.getString("LastName"));
				
				users.add(user);
			}
			
			rs.close();
		} catch (SQLException e) { System.out.println("Failed to retrieve users: " + e); }

		return users;
	}
	
	public void create(User user) {
		try {
			conn.createStatement().executeUpdate("INSERT INTO Users (Username, Password, Clearance, FirstName, LastName) VALUES ('" + 
				user.getUsername() + "', '" + 
				user.getPassword() + "', '" + 
				user.getClearance() + "', '" + 
				user.getFirstName() + "', '" + 
				user.getLastName() + "')");
		} catch (SQLException e) { System.out.println("Failed to create user: " + e); }
	}
	
	public User authenticate(String username, String password) {
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Users WHERE Username='" + username + "' and Password='" + password +"'");
			
			if (rs.next()) {
				User user = new User(
					rs.getString("Username"),
					rs.getString("Password"),
					rs.getString("Clearance"),
					rs.getString("FirstName"),
					rs.getString("LastName"));
				
				rs.close();
				
				return user;
			}
		} catch (SQLException e) { System.out.println("Failed to authenticate credentials: " + e); }
		
		return null;
	}
	
	public void delete(String username) {
		try {
			conn.createStatement().executeUpdate("DELETE FROM Users WHERE Username='" + username + "'");
		} catch(SQLException e) { System.out.println("Failed to delete user: " + e); }
	}
}
