/**
 * 
 */
package yardmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.net.URI;

/**
 * @author Home
 *
 */
public class Database {
	Connection conn;
		
	public Database(URI source) {
		String path = source.getPath();
		//String dburl = "jdbc:ucanaccess://C:/Users/Home/My Documents/java programs/containers.mdb";  
		
		try { 
			this.conn = DriverManager.getConnection(path);	
		} catch (Exception e) { 
			System.out.println("Failed to connect: " + e); 
		} 
	}
}
