/**
 * 
 */
package yardmanager.dao;

import java.awt.Polygon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Yards WHERE Id='" + id + "'");
			
			if (rs.next()) {
				ResultSet points = stmt.executeQuery("SELECT * FROM YardsPoints WHERE YardId='" + id + "'");
				Polygon boundaries = new Polygon();
				
				while (points.next()) {
					boundaries.addPoint(points.getInt("xPos"), points.getInt("yPos"));
				}
				
				Yard yard = new Yard(
						rs.getString("Id"),
						containerDAO.listByYard(id),
						boundaries,
						rs.getDate("LastEdited"));
				
				points.close();
				rs.close();
				stmt.close();
				return yard;
			}
		} catch (SQLException e) { System.out.println("Failed to retrieve yard: " + e); }
		
		return null;
	}
	
	public List<Yard> list() {
		List<Yard> yards = new ArrayList<Yard>();
		
		try{
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Yards");

			while (rs.next()) {
				ResultSet points = stmt.executeQuery("SELECT * FROM YardsPoints WHERE YardId='" + rs.getString("Id") + "'");
				Polygon boundaries = new Polygon();
				
				while (points.next()) {
					boundaries.addPoint(points.getInt("xPos"), points.getInt("yPos"));
				}
				
				Yard yard = new Yard(
						rs.getString("Id"),
						containerDAO.listByYard(rs.getString("Id")),
						boundaries,
						rs.getDate("LastEdited"));
				
				yards.add(yard);
				
				points.close();
			}
			
			stmt.close();
			rs.close();
		} catch(SQLException e) { System.out.println("Failed to retrieve yards: " + e); }
					
		return yards;
	}
	
	public void create(Yard yard) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate("INSERT INTO Yards (Id, LastEdited) VALUES ('" + 
				yard.getId() + "', '" + 
				yard.getLastEdited() + "')");
			
			for (int i = 0; i < yard.getBoundaries().npoints; i ++) {
				stmt.executeUpdate("INSERT INTO YardsPoints (YardId, xPos, yPos) VALUES ('" +
					yard.getId() + "', '" +
					yard.getBoundaries().xpoints[i] + "', '" +
					yard.getBoundaries().ypoints[i] + "')");
			}
			
			stmt.close();
		} catch (SQLException e) { System.out.println("Failed to create yard: " + e); }
	}
	
	public void update(Yard yard) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate("UPDATE Yards SET LastEdited='" + yard.getLastEdited() + 
					"' WHERE Id='" + yard.getId() + "'");
			
			stmt.executeUpdate("DELETE FROM YardsPoints WHERE YardId='" + yard.getId() + "'");
			
			for (int i = 0; i < yard.getBoundaries().npoints; i ++) {
				stmt.executeUpdate("INSERT INTO YardsPoints (YardId, xPos, yPos) VALUES ('" +
					yard.getId() + "', '" +
					yard.getBoundaries().xpoints[i] + "', '" +
					yard.getBoundaries().ypoints[i] + "')");
			}
			
			stmt.close();
		} catch (SQLException e) { System.out.println("Failed to update yard: " + e); }
	}
	
	public void delete(String id) {
		try {
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate("DELETE FROM Yards WHERE Id='" + id + "'");
			stmt.executeUpdate("DELETE FROM YardsPoints WHERE YardId='" + id + "'");
			
			stmt.close();
		} catch(SQLException e) { System.out.println("Failed to delete yard: " + e); }
	}
}
