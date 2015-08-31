/**
 * 
 */
package yardmanager.dao;


import yardmanager.Address;
import yardmanager.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author maxetron
 *
 */
public class CompanyDAO {
	private Connection conn;

	/**
	 * @param conn
	 */
	public CompanyDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Company> list() {
		List<Company> companies = new ArrayList<Company>();
		
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Companies");
			
			while(rs.next()) {
				Company company = new Company(
					rs.getString("Id"),
					rs.getString("Name"),
					new Address(
						rs.getString("City"),
						rs.getString("Country"),
						rs.getString("PostalCode"),
						rs.getString("Street"),
						rs.getString("StreetNumber")
					));
				
				companies.add(company);
			}
			
			rs.close();
		} catch (SQLException e) { System.out.println("Failed to retrieve companies: " + e); }

		return companies;
	}

	public Company find(String name) {
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Companies WHERE Name='" + name + "'");
			
			if(rs.next()) {
				Company company = new Company(
						rs.getString("Id"),
						rs.getString("Name"),
						new Address(
							rs.getString("City"),
							rs.getString("Country"),
							rs.getString("PostalCode"),
							rs.getString("Street"),
							rs.getString("StreetNumber")
						));
				
				rs.close();
				
				return company;
			}
		} catch (SQLException e) { System.out.println("Failed to retrieve company: " + e); }
		
		return null;
	}
	
	public void create(Company company) {
		try {
			conn.createStatement().executeUpdate("INSERT INTO Companies (Id, Name, City, Country, PostalCode, Street, StreetNumber) VALUES ('" + 
				company.getId() + "', '" + 
				company.getName() + "', '" + 
				company.getAddress().getCity() + "', '" +
				company.getAddress().getCountry() + "', '" + 
				company.getAddress().getPostalCode() + "', '" + 
				company.getAddress().getStreet() + "', '" + 
				company.getAddress().getStreetNumber() + "')");
		} catch (SQLException e) { System.out.println("Failed to create company: " + e); }
	}
	
	public void update(Company company) {
		try {
			conn.createStatement().executeUpdate("UPDATE Companies SET Name='" + company.getName() + 
					"', Id='" + company.getId() + 
					"', City='" + company.getAddress().getCity() + 
					"', Country='" + company.getAddress().getCountry() + 
					"', PostalCode='" + company.getAddress().getPostalCode() + 
					"', Street='" + company.getAddress().getStreet() + 
					"', StreetNumber='" + company.getAddress().getStreetNumber() + 
					"' WHERE Id='" + company.getId() + "'");
		} catch (SQLException e) { System.out.println("Failed to update company: " + e); }
	}
	
	public void delete(String name) {
		try {
			conn.createStatement().executeUpdate("DELETE FROM Companies WHERE Name='" + name + "'");
		} catch(SQLException e) { System.out.println("Failed to delete company: " + e); }
	}
}
