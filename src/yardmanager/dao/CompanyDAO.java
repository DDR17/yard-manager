/**
 * 
 */
package yardmanager.dao;

import yardmanager.Address;
import yardmanager.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Companies");
			
			while (rs.next()) {
				Company company = new Company(
					rs.getString("Id"),
					rs.getString("Name"),
					rs.getString("Type"),
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
			stmt.close();
		} catch (SQLException e) { System.out.println("Failed to retrieve companies: " + e); }

		return companies;
	}
	
	public List<Company> list(String type) {
		List<Company> companies = new ArrayList<Company>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Companies WHERE Type='" + type + "'");
			
			while (rs.next()) {
				Company company = new Company(
					rs.getString("Id"),
					rs.getString("Name"),
					rs.getString("Type"),
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
			stmt.close();
		} catch (SQLException e) { System.out.println("Failed to retrieve companies: " + e); }

		return companies;
	}

	public Company find(String id) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Companies WHERE Id='" + id + "'");
			
			if (rs.next()) {
				Company company = new Company(
						rs.getString("Id"),
						rs.getString("Name"),
						rs.getString("Type"),
						new Address(
							rs.getString("City"),
							rs.getString("Country"),
							rs.getString("PostalCode"),
							rs.getString("Street"),
							rs.getString("StreetNumber")
						));
				
				rs.close();
				stmt.close();
				
				return company;
			}
		} catch (SQLException e) { System.out.println("Failed to retrieve company: " + e); }
		
		return null;
	}
	
	public void create(Company company) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO Companies (Id, Name, Type, City, Country, PostalCode, Street, StreetNumber) VALUES ('" + 
				company.getId() + "', '" + 
				company.getName() + "', '" + 
				company.getType() + "', '" +
				company.getAddress().getCity() + "', '" +
				company.getAddress().getCountry() + "', '" + 
				company.getAddress().getPostalCode() + "', '" + 
				company.getAddress().getStreet() + "', '" + 
				company.getAddress().getStreetNumber() + "')");
			
			stmt.close();
		} catch (SQLException e) { System.out.println("Failed to create company: " + e); }
	}
	
	public void update(Company company, Company oldCompany) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE Companies SET Name='" + company.getName() + 
					"', Id='" + company.getId() + 
					"', Type='" + company.getType() +
					"', City='" + company.getAddress().getCity() + 
					"', Country='" + company.getAddress().getCountry() + 
					"', PostalCode='" + company.getAddress().getPostalCode() + 
					"', Street='" + company.getAddress().getStreet() + 
					"', StreetNumber='" + company.getAddress().getStreetNumber() + 
					"' WHERE Id='" + oldCompany.getId() + "'");
			
			stmt.close();
		} catch (SQLException e) { System.out.println("Failed to update company: " + e); }
	}
	
	public void delete(String name) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM Companies WHERE Name='" + name + "'");
			
			stmt.close();
		} catch(SQLException e) { System.out.println("Failed to delete company: " + e); }
	}
}
