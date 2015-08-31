/**
 * 
 */
package yardmanager.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yardmanager.Interchange;

/**
 * @author maxetron
 *
 */
public class InterchangeDAO {
	private Connection conn;
	private ContainerDAO containerDAO;
	private CompanyDAO companyDAO;
	
	/**
	 * @param conn
	 * @param containerDAO
	 * @param companyDAO
	 */
	public InterchangeDAO(Connection conn, ContainerDAO containerDAO,
			CompanyDAO companyDAO) {
		this.conn = conn;
		this.containerDAO = containerDAO;
		this.companyDAO = companyDAO;
	}
	
	public Interchange find(String id) {
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Interchanges WHERE Id='" + id + "'");
			
			if(rs.next()) {	
				Interchange interchange = new Interchange(
						rs.getString("Id"),
						containerDAO.find("ContainerId"),
						companyDAO.find("CompanyId"),
						rs.getString("CSCExpiry"),
						rs.getString("InspectorName"),
						rs.getString("Location"),
						rs.getString("ReleaseAcceptance"),
						rs.getString("TruckLicense"),
						rs.getString("Comments"),
						rs.getDate("Date"),
						rs.getBoolean("OnHireSurvey"),
						rs.getBoolean("In"));
				
				rs.close();
				
				return interchange;
			}
		} catch (SQLException e) { System.out.println("Failed to retrieve interchange: " + e); }
		
		return null;
	}
	
	public List<Interchange> list() {
		List<Interchange> interchanges = new ArrayList<Interchange>();
		
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Interchanges");
			
			while(rs.next()) {
				Interchange interchange = new Interchange(
						rs.getString("Id"),
						containerDAO.find("ContainerId"),
						companyDAO.find("CompanyId"),
						rs.getString("CSCExpiry"),
						rs.getString("InspectorName"),
						rs.getString("Location"),
						rs.getString("ReleaseAcceptance"),
						rs.getString("TruckLicense"),
						rs.getString("Comments"),
						rs.getDate("Date"),
						rs.getBoolean("OnHireSurvey"),
						rs.getBoolean("In"));
				
				interchanges.add(interchange);
			}
			
			rs.close();
		} catch (SQLException e) { System.out.println("Failed to retrieve interchanges: " + e); }

		return interchanges;
	}
	
	public void create(Interchange interchange) {
		try {
			conn.createStatement().executeUpdate("INSERT INTO Interchanges (Id, ContainerId, CompanyId, CSCExpiry, InspectorName, Location, ReleaseAcceptance, TruckLicense, Comments, Date, OnHireSurvey, In) VALUES ('" + 
				interchange.getId() + "', '" +
				interchange.getContainer().getId() + "', '" +
				interchange.getCompany().getId() + "', '" +
				interchange.getCSCExpiry() + "', '" +
				interchange.getInspectorName() + "', '" +
				interchange.getLocation() + "', '" +
				interchange.getReleaseAcceptance() + "', '" +
				interchange.getTruckLicense() + "', '" +
				interchange.getComments() + "', '" +
				interchange.getDate() + "', '" +
				interchange.isOnHireSurvey() + "', '" +
				interchange.isIn() + "')");
		} catch (SQLException e) { System.out.println("Failed to create interchange: " + e); }
	}
}