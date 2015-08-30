/**
 * 
 */
package yardmanager;


import yardmanager.dao.*;
import yardmanager.gui.Login;
import yardmanager.gui.MainFrame;

/**
 * @author Home
 *
 */
public class Main {

	/**
	 * @param args Blue
	 */
	public static void main(String[] args) {
		Database database = new Database("jdbc:ucanaccess://C:/Users/Home/My Documents/java programs/containers.mdb");
		
		UserDAO userDAO =  new UserDAO(database.getConnection());

		Login login = new Login(userDAO);
	}

}
