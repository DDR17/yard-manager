/**
 * 
 */
package yardmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Home
 *
 */
public class Database {
	Connection conn;
		
	public Database(String source) {
		try { 
			this.conn = DriverManager.getConnection(source);	
		} catch (Exception e) { 
			System.out.println("Failed to connect: " + e); 
		} 
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}