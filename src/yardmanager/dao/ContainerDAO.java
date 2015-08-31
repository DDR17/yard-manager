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
	private CompanyDAO companyDAO;
	
	public ContainerDAO(Connection conn, CompanyDAO companyDAO) {
		this.conn = conn;
		this.companyDAO = companyDAO;
	}
	
	public void create(Container container) {
		try {
			conn.createStatement().executeUpdate("INSERT INTO Containers (Id, CustomerId, Type, ISOCode, SealNumber, Colour, Size, Mass, xPos, yPos, Level, Coverage, DateManufactored, Full) VALUES ('" + 
				container.getId() + "', '" + 
				container.getCustomer().getId() + "', '" + 
				container.getType() + "', '" + 
				container.getISOCode() + "', '" + 
				container.getSealNumber() + "', '" + 
				container.getColour() + "', '" + 
				container.getSize() + "', '" + 
				container.getMass() + "', '" + 
				container.getxPos() + "', '" + 
				container.getyPos() + "', '" + 
				container.getLevel() + "', '" + 
				container.getCoverage() + "', '" + 
				container.getDateManufactured() + "', '" + 
				container.isFull() + "')");
		} catch (SQLException e) { System.out.println("Failed to create container: " + e); }
	}
	
	public void update(Container container) {
		try {
			conn.createStatement().executeUpdate("UPDATE Containers SET CustomerId='" + container.getCustomer().getId() + 
					"', Type='" + container.getType() + 
					"', ISOCode='" + container.getISOCode() + 
					"', SealNumber='" + container.getSealNumber() + 
					"', Colour='" + container.getColour() + 
					"', Size='" + container.getSize() + 
					"', Mass='" + container.getMass() + 
					"', xPos='" + container.getxPos() + 
					"', yPos='" + container.getyPos() + 
					"', Level='" + container.getLevel() + 
					"', Coverage='" + container.getCoverage() + 
					"', DateManufactored='" + container.getDateManufactured() + 
					"', Full='" + container.isFull() + 
					"' WHERE Id='" + container.getId() + "'");
		} catch (SQLException e) { System.out.println("Failed to update container: " + e); }
	}
	
	public List<Container> list() {
		List<Container> containers = new ArrayList<Container>();
		
		try{
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Containers");

			while(rs.next()) {
				Container container = new Container(
						rs.getString("Id"),
						companyDAO.find(rs.getString("CustomerId")),
						rs.getString("Type"),
						rs.getString("ISOCode"),
						rs.getString("SealNumber"),
						rs.getString("Colour"),
						rs.getInt("Size"),
						rs.getInt("Mass"),
						rs.getInt("xPos"),
						rs.getInt("yPos"),
						rs.getInt("Level"),
						rs.getInt("Coverage"),
						rs.getDate("DateManufactured"),
						rs.getBoolean("Full"));
				
				containers.add(container);
			}
			
			rs.close();
		} catch(SQLException e) { System.out.println("Failed to retrieve containers: " + e); }
					
		return containers;
	}
	
	public Container find(int id) {
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Containers WHERE Id='" + id + "' AND Status='still in'");
			
			if(rs.next()) {
				Container container = new Container(
						rs.getString("Id"),
						companyDAO.find(rs.getString("CustomerId")),
						rs.getString("Type"),
						rs.getString("ISOCode"),
						rs.getString("SealNumber"),
						rs.getString("Colour"),
						rs.getInt("Size"),
						rs.getInt("Mass"),
						rs.getInt("xPos"),
						rs.getInt("yPos"),
						rs.getInt("Level"),
						rs.getInt("Coverage"),
						rs.getDate("DateManufactured"),
						rs.getBoolean("Full"));
				
				rs.close();
				
				return container;
			}
		} catch (SQLException e) { System.out.println("Failed to retrieve container: " + e); }
		
		return null;
	}
}
