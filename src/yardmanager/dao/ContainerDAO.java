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
	private YardDAO yardDAO;
	
	public ContainerDAO(Connection conn, CompanyDAO companyDAO, YardDAO yardDAO) {
		this.conn = conn;
		this.companyDAO = companyDAO;
		this.yardDAO = yardDAO;
	}

	public List<Container> list() {
		List<Container> containers = new ArrayList<Container>();
		
		try{
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Containers");

			while(rs.next()) {
				Container container = new Container(
						rs.getString("Id"),
						yardDAO.find(rs.getString("YardId")),
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
	
	public List<Container> listByYard(String id) {
		List<Container> containers = new ArrayList<Container>();
		
		try{
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Containers WHERE YardId='" + id + "'");

			while(rs.next()) {
				Container container = new Container(
						rs.getString("Id"),
						yardDAO.find(rs.getString("YardId")),
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
	
	public Container find(String id) {
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Containers WHERE Id='" + id + "'");
			
			if(rs.next()) {
				Container container = new Container(
						rs.getString("Id"),
						yardDAO.find(rs.getString("YardId")),
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
	
	public void create(Container container) {
		try {
			conn.createStatement().executeUpdate("INSERT INTO Containers (Id, YardId ,CustomerId, Type, ISOCode, SealNumber, Colour, Size, Mass, xPos, yPos, Level, Coverage, DateManufactored, Full) VALUES ('" + 
				container.getId() + "', '" +
				container.getYard().getId() + "', '" +
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
					"', YardId='" + container.getYard().getId() + 
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
}
