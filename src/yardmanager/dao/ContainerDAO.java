/**
 * 
 */
package yardmanager.dao;

import yardmanager.Container;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author maxetron
 *
 */
public class ContainerDAO {
	private Connection conn;
	
	public ContainerDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Container> list(int level) {
		List<Container> containers = new ArrayList<Container>();
		
		try{
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Categories WHERE Levels <=" + level);
			
			while(rs.next()) {
				Container container = new Container(
						rs.getInt("ContainerID"),
						rs.getInt("Size"),
						rs.getInt("Mass"),
						rs.getInt("xPos"),
						rs.getInt("yPos"),
						rs.getInt("Level"),
						rs.getInt("Coverage"),
						rs.getString("Type"),
						rs.getString("ISOCode"),
						rs.getString("Acceptance"),
						rs.getString("Seal"),
						rs.getString("CustomerCode"),
						rs.getString("CustomerName"),
						rs.getString("TruckCode"),
						rs.getString("TruckName"),
						rs.getString("DateOfManufacture"),
						rs.getString("TruckLicense"),
						rs.getString("InspectorName"),
						rs.getString("DateIn"),
						rs.getString("DateOut"),
						rs.getString("Comments"),
						rs.getString("Colour"),
						rs.getBoolean("Full"));
				
				containers.add(container);
			}
			
			rs.close();
		} catch(SQLException e) { System.out.println("Failed to retrieve containers: " + e); }
					
		return containers;
	}
	
	public Container find(int containerID) {
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Containers WHERE ContainerID='" + containerID + "' AND Status='still in'");
			
			if(rs.next()) {
				Container container = new Container(
					rs.getInt("ContainerID"),
					rs.getInt("Size"),
					rs.getInt("Mass"),
					rs.getInt("xPos"),
					rs.getInt("yPos"),
					rs.getInt("Level"),
					rs.getInt("Coverage"),
					rs.getString("Type"),
					rs.getString("ISOCode"),
					rs.getString("Acceptance"),
					rs.getString("Seal"),
					rs.getString("CustomerCode"),
					rs.getString("CustomerName"),
					rs.getString("TruckCode"),
					rs.getString("TruckName"),
					rs.getString("DateOfManufacture"),
					rs.getString("TruckLicense"),
					rs.getString("InspectorName"),
					rs.getString("DateIn"),
					rs.getString("DateOut"),
					rs.getString("Comments"),
					rs.getString("Colour"),
					rs.getBoolean("Full"));
				
				rs.close();
				
				return container;
			}
		} catch (SQLException e) { System.out.println("Failed to retrieve container: " + e); }
		
		return null;
	}
}
