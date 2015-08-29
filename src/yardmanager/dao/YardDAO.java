///**
// * 
// */
//package yardmanager.dao;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * @author Home
// *
// */
//public class YardDAO {
//	private Connection conn;
//	
//	public YardDAO(Connection conn){
//		this.conn = conn;
//	}
//
//	public String[][] companyData() {
//		
//		size = 0;
//		try {
//			rs = con.createStatement().executeQuery("SELECT * FROM Companies");
//			while(rs.next()) size++;
//		} catch(SQLException e) {}
//	
//		String[][] data = new String[size][4];
//	
//		try {
//			int i = 0;
//			rs = con.createStatement().executeQuery("SELECT * FROM Companies");
//			while(rs.next()) {
//				data[i][0] = rs.getString("Company");
//				data[i][1] = rs.getString("Code");
//				data[i][2] = rs.getString("City");
//				data[i][3] = rs.getString("DailyCost");
//				i+=1;
//			}
//		} catch (SQLException e) {e.printStackTrace();}finally {
//		    try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//
//		return data;
//	}
//
//	public void Ingate(String info[]) {
//		
//		try {
//			con.createStatement().executeUpdate("INSERT INTO Interchanges "
//					+ "(Container, ISOcode, Acceptance, Mass, Seal, DOM, CustomerCode, CustomerName,"
//					+ " TruckCompanyCode, TruckCompanyName, TruckLiscence, InspectorName, DateIn, Comments, Status)"
//					+ " VALUES ('"+info[0]+"', '"+info[1]+"', '"+info[2]+"', '"+info[3]+"', '"+info[4]+"',"
//					+ " '"+info[5]+"', '"+info[6]+"', '"+info[7]+"', '"+info[8]+"', '"+info[9]+"', "
//					+ "'"+info[10]+"', '"+info[11]+"', '"+info[12]+"', '"+info[13]+"', 'still in')");
//			
//			con.createStatement().executeUpdate("INSERT INTO Inventory (Container) VALUES ('"+info[0]+"')");
//		} catch (SQLException e) {}finally {
//		    try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		
//	}
//	
//	public String[] interchangeData(String container) {
//		String[] data = new String[8];
//		data[0] = container;
//		data[1] = "bad";
//		try {
//			rs = con.createStatement().executeQuery("SELECT ISOcode, Mass, Seal, DOM, Acceptance, Release, CustomerCode, CustomerName, TruckCompanyName, TruckCompamyCode, TruckLiscence, InspectorName, DateIn, DateOut, Comments FROM Interchanges WHERE Container='"+container+ "' AND Status='still in'");
//			if(!rs.next()) return data;
//			data[1] = rs.getString("ISOcode");
//			data[2] = rs.getString("Mass");
//			data[3] = rs.getString("Seal");
//			data[4] = rs.getString("DOM");
//			data[5] = rs.getString("Acceptance");
//			data[6] = rs.getString("Release");
//			data[7] = rs.getString("CustomerCode");
//			data[8]	= rs.getString("CustomerName");
//			data[9] = rs.getString("TruckCompanyName");
//			data[10] = rs.getString("TruckCompanyCode");
//			data[11] = rs.getString("TruckLiscense");
//			data[12] = rs.getString("InspectorName");
//			data[13] = rs.getString("DateIn");
//			data[14] = rs.getString("DateOut");
//			data[15] = rs.getString("Comments");
//		} catch (SQLException e) {}finally {
//		    try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		return data;
//	}
//	
//	public String[] getColour(int lvl) {
//		size = 0;
//		int i = 0;
//		
//		try{
//			rs = con.createStatement().executeQuery("SELECT Colour FROM Inventory WHERE Levels <= "+lvl);
//			while(rs.next()) size++;
//		} catch(SQLException e) {}
//		
//			String[] colours = new String[size];
//		try{
//			rs = con.createStatement().executeQuery("SELECT Colour FROM Inventory WHERE Levels <= "+lvl);
//			while(rs.next()) {
//				colours[i] = rs.getString("Colour");
//				i+=1;
//			}
//		} catch(SQLException e) {} finally{
//			try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		
//		return colours;	
//	}
//	
//	public void setLocation(String container, int x, int y, int length, int width, String colour, int level, int oldX, int oldY, int oldLevel) 
//	{
//		
//		try
//		{
//			con.createStatement().executeUpdate("UPDATE Inventory "
//					+ "SET X='"+x+"', Y='"+y+"', Length='"+length+"', Width='"+width+"', Colour='"+colour+"', Levels='"+level+"' "
//					+ "WHERE Container='"+container+"'");
//			if(level>1) con.createStatement().executeUpdate("UPDATE Inventory SET Coverage=1 WHERE X="+x+" AND Y="+y+" AND Levels="+(level-1)+"");
//			if(level==3) con.createStatement().executeUpdate("UPDATE Inventory SET Coverage=2 WHERE X="+x+" AND Y="+y+" AND Levels=1");
//			if(oldLevel>1) con.createStatement().executeUpdate("UPDATE Inventory SET Coverage=0 WHERE X="+oldX+" AND Y="+oldY+" AND Levels="+(oldLevel-1)+"");
//			if(oldLevel==3) con.createStatement().executeUpdate("UPDATE Inventory SET Coverage=1 WHERE X="+oldX+" AND Y="+oldY+" AND Levels=1");
//		} catch(SQLException e) {} finally 
//		{
//			try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		
//	}
//	
//	public String[] getNames(int lvl) {
//		int i = 0;
//		size = 0;
//		try{
//			rs = con.createStatement().executeQuery("SELECT Container FROM Inventory WHERE Levels <= "+lvl);
//			while(rs.next()) size++;
//		} catch(SQLException e) {}
//		
//			String[] names = new String[size];
//		try{
//			rs = con.createStatement().executeQuery("SELECT Container FROM Inventory WHERE Levels <= "+lvl);
//			while(rs.next()) {
//				names[i] = rs.getString("Container");
//				i+=1;
//			}
//		} catch(SQLException e) {} finally{
//			try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		
//		return names;
//	}
//	
//	public void outgate(String info[]) {
//		
//		try {
//			rs = con.createStatement().executeQuery(" SELECT DateIn FROM Interchanges WHERE Container='"+info[0]+"' AND Status='still in'");
//			rs.next();
//			String dateIn = new String(rs.getString("DateIn"));
//
//			con.createStatement().executeUpdate("INSERT INTO Interchanges "
//					+ "(Container, ISOcode, Release, Mass, Seal, DOM, CustomerCode, CustomerName,"
//					+ " TruckCompanyCode, TruckCompanyName, TruckLiscence, InspectorName, DateOut, Comments)"
//					+ " VALUES ('"+info[0]+"', '"+info[1]+"', '"+info[2]+"', '"+info[3]+"', '"+info[4]+"',"
//					+ " '"+info[5]+"', '"+info[6]+"', '"+info[7]+"', '"+info[8]+"', '"+info[9]+"', "
//					+ "'"+info[10]+"', '"+info[11]+"', '"+info[12]+"', '"+info[13]+"')");
//			
//			con.createStatement().executeUpdate("INSERT INTO History "
//					+ "(Container, ISOcode, Mass, Seal, Dom, CustomerCode, CustomerName, DateOut, Comments, DateIn)"
//					+ " VALUES ('"+info[0]+"', '"+info[1]+"', '"+info[3]+"', '"+info[4]+"', '"+info[5]+"', '"+info[6]+"', '"+info[7]+"', '"+info[12]+"', '"+info[13]+"', '"+dateIn+"')");
//			
//			con.createStatement().executeUpdate("DELETE FROM Inventory WHERE Container='"+info[0]+"'");
//			con.createStatement().executeUpdate("UPDATE Interchanges SET Status='out' WHERE Container='"+info[0]+"' AND Status='still in'");
//		} catch (SQLException e) {}finally {
//		    try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		
//	}
//
//	public int[] findContainer(String container) {
//		int dim[] = new int[2];
//		dim[0] = -1;
//		try{
//			rs = con.createStatement().executeQuery("SELECT X, Y FROM Inventory WHERE Container='"+container+"'");
//			rs.next(); 
//			dim[0] = rs.getInt("X");
//			dim[1] = rs.getInt("Y");
//			
//		} catch(SQLException e) {} finally{
//			try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		return dim;
//	}
//
//	public String[][] getHistory(String date) {
//		int i = 0;
//		int size = 0;
//		String dateMorning = new String();
//		String dateNight = new String();
//		dateMorning = date + " 11:59:00 PM";
//		dateNight = date + " 01:00:00 AM";
//		
//		System.out.println(date);
//		try{
//			rs = con.createStatement().executeQuery("SELECT Container FROM History WHERE DateIn <= #"+dateMorning+"# AND DateOut >= #"+dateNight+"#");
//			while(rs.next()) size++;
//		} catch(SQLException e) {}
//		
//			String[][] snapShot = new String[size][3];
//		try{
//			rs = con.createStatement().executeQuery("SELECT Container, Size, CustomerName FROM History WHERE DateIn <= #"+dateMorning+"# AND DateOut >= #"+dateNight+"#");
//			while(rs.next()) {
//				snapShot[i][0] = rs.getString("Container");
//				snapShot[i][1] = rs.getString("Size");
//				snapShot[i][2] = rs.getString("CustomerName");
//				i+=1;
//			}
//		} catch(SQLException e) {} finally{
//			try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		
//		
//		return snapShot;
//	}
//	
//	public String[][] getInventory() {
//		int i = 0;
//		int size = 0;
//		
//		try{
//			rs = con.createStatement().executeQuery("SELECT Container FROM Inventory");
//			while(rs.next()) size++;
//		} catch(SQLException e) {}
//		
//			String[][] inventory = new String[size][3];
//		try{
//			rs = con.createStatement().executeQuery("SELECT Container, Size, CustomerName FROM Inventory");
//			while(rs.next()) {
//				inventory[i][0] = rs.getString("Container");
//				inventory[i][1] = rs.getString("Size");
//				inventory[i][2] = rs.getString("CustomerName");
//				i+=1;
//			}
//		} catch(SQLException e) {} finally{
//			try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		
//		
//		return inventory;
//	}
//	
//	public String[][] searchCon(int target, String searchString){
//		int i = 0;
//		int size = 0;
//		String sqlCount = null;
//		String sqlData = null;
//		
//		if(target == 1) {
//			sqlCount = "SELECT Container FROM Inventory WHERE Container LIKE '%"+searchString+"%'";
//			sqlData = "SELECT Container, Size, CustomerName FROM Inventory WHERE Container LIKE '%"+searchString+"%'";
//		}else if(target == 2){
//			sqlCount = "SELECT Container FROM History WHERE Container LIKE '%"+searchString+"%'";
//			sqlData = "SELECT Container, Size, CustomerName FROM History WHERE Container LIKE '%"+searchString+"%'";
//		}else{
//			sqlCount = "SELECT Container FROM Inventory WHERE Container LIKE '%"+searchString+"%' UNION SELECT Container FROM History WHERE Container LIKE '%"+searchString+"%'";
//			sqlData = "SELECT Container, Size, CustomerName FROM Inventory WHERE Container LIKE '%"+searchString+"%' UNION SELECT Container, Size, CustomerName FROM History WHERE Container LIKE '%"+searchString+"%'";
//		}
//		
//		
//		try{
//			rs = con.createStatement().executeQuery(sqlCount);
//			while(rs.next()) size++;
//		} catch(SQLException e) {}
//		
//			String[][] result = new String[size][3];
//		try{
//			rs = con.createStatement().executeQuery(sqlData);
//			while(rs.next()) {
//				result[i][0] = rs.getString("Container");
//				result[i][1] = rs.getString("Size");
//				result[i][2] = rs.getString("CustomerName");
//				i+=1;
//			}
//		} catch(SQLException e) {} finally{
//			try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		return result;
//	}
//	
//	public String[][] searchInter(String searchString){
//		int i = 0;
//		int size = 0;
//				
//		try{
//			rs = con.createStatement().executeQuery("SELECT Container FROM Interchanges WHERE EISNumber LIKE '%"+searchString+"%'");
//			while(rs.next()) size++;
//		} catch(SQLException e) {}
//		
//			String[][] result = new String[size][5];
//		try{
//			rs = con.createStatement().executeQuery("SELECT EISNumber, Container, Size, DateIn, DateOut  FROM Interchanges WHERE EISNumber LIKE '%"+searchString+"%'");
//			while(rs.next()) {
//				result[i][0] = rs.getString("EISNumber");
//				result[i][1] = rs.getString("Container");
//				result[i][2] = rs.getString("Size");
//				result[i][3] = rs.getString("DateIn");
//				result[i][4] = rs.getString("DateOut");
//				i+=1;
//			}
//		} catch(SQLException e) {} finally{
//			try { rs.close(); } catch (Exception e) {}
//		    try { con.close(); } catch (Exception e) {}
//		}
//		return result;
//	}
//	
//	
//}
