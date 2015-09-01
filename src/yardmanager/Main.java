/**
 * 
 */
package yardmanager;


import yardmanager.dao.*;
import yardmanager.gui.Login;

/**
 * @author Home
 *
 */
public class Main {

	/**
	 * @param args Blue
	 */
	public static void main(String[] args) {
		Database database = new Database("jdbc:ucanaccess://containers.mdb");
		
		UserDAO userDAO =  new UserDAO(database.getConnection());

		new Login(userDAO, database.getConnection());
	}
}
