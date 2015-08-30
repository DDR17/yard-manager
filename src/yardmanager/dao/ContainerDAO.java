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
	
	public void create(Container container) {
		try {
			conn.createStatement().executeUpdate("INSERT INTO Containers (ContainerID, CompanyID, Size, Mass, xPos, yPos, Level, Coverage, Type, ISOCode, Acceptance, Seal, TruckCode, TruckName, DateOfManufacture, TruckLicense, InspectorName, DateIn, DateOut, Comments, Colour, Full) VALUES ('" + 
				container.getContainerID() + "', '" + 
				container.getCompanyID() + "', '" + 
				container.getSize() + "', '" + 
				container.getMass() + "', '" + 
				container.getxPos() + "', '" + 
				container.getyPos() + "', '" + 
				container.getLevel() + "', '" + 
				container.getCoverage() + "', '" + 
				container.getType() + "', '" + 
				container.getIsoCode() + "', '" + 
				container.getAcceptance() + "', '" + 
				container.getSeal() + "', '" + 
				container.getTruckCode() + "', '" + 
				container.getTruckName() + "', '" + 
				container.getInspectorName() + "', '" + 
				container.getDateIn() + "', '" + 
				container.getDateOut() + "', '" + 
				container.getComments() + "', '" + 
				container.getColour() + "', '" + 
				container.isFull() + "')");
		} catch (SQLException e) { System.out.println("Failed to create container: " + e); }
	}
	
	public List<Container> list(int level) {
		List<Container> containers = new ArrayList<Container>();
		
		try{
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Containers WHERE Levels <=" + level);
			
			while(rs.next()) {
				Container container = new Container(
						rs.getString("ContainerID"),
						rs.getString("CompanyID"),
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
					rs.getString("ContainerID"),
					rs.getString("CompanyID"),
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
