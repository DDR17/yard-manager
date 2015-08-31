/**
 * 
 */
package yardmanager.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yardmanager.Yard;

/**
 * @author maxetron
 *
 */
public class YardDAO {

	private Connection conn;
	private ContainerDAO containerDAO;
	
	public YardDAO(Connection conn, ContainerDAO containerDAO) {
		this.conn = conn;
		this.containerDAO = containerDAO;
	}
	
	public Yard find(String id) {
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Yards WHERE Id='" + id + "'");
			
			if(rs.next()) {
				Yard yard = new Yard(
						rs.getString("Id"),
						containerDAO.listByYard(id),
						rs.getDate("LastEdited"));
				
				rs.close();
				
				return yard;
			}
		} catch (SQLException e) { System.out.println("Failed to retrieve yard: " + e); }
		
		return null;
	}
	
	public List<Yard> list() {
		List<Yard> yards = new ArrayList<Yard>();
		
		try{
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Yards");

			while(rs.next()) {
				Yard yard = new Yard(
						rs.getString("Id"),
						containerDAO.listByYard(rs.getString("Id")),
						rs.getDate("LastEdited"));
				
				yards.add(yard);
			}
			
			rs.close();
		} catch(SQLException e) { System.out.println("Failed to retrieve yards: " + e); }
					
		return yards;
	}
	
	public void create(Yard yard) {
		try {
			conn.createStatement().executeUpdate("INSERT INTO Yards (Id, LastEdited) VALUES ('" + 
				yard.getId() + "', '" + 
				yard.getLastEdited() + "')");
		} catch (SQLException e) { System.out.println("Failed to create yard: " + e); }
	}
	
	public void update(Yard yard) {
		try {
			conn.createStatement().executeUpdate("UPDATE Yards SET LastEdited='" + yard.getLastEdited() + 
					"' WHERE Id='" + yard.getId() + "'");
		} catch (SQLException e) { System.out.println("Failed to update yard: " + e); }
	}
	
	public void delete(String id) {
		try {
			conn.createStatement().executeUpdate("DELETE FROM Yards WHERE Id='" + id + "'");
		} catch(SQLException e) { System.out.println("Failed to delete yard: " + e); }
	}
}
