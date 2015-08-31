/**
 * 
 */
package yardmanager;

/**
 * @author maxetron
 *
 */
public class User {
	private String username;
	private String password;
	private String clearance;
	private String firstName;
	private String lastName;
	
	/**
	 * @param username
	 * @param password
	 * @param clearance
	 * @param firstName
	 * @param lastName
	 */
	public User(String username, String password, String clearance,
			String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.clearance = clearance;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the clearance
	 */
	public String getClearance() {
		return clearance;
	}

	/**
	 * @param clearance the clearance to set
	 */
	public void setClearance(String clearance) {
		this.clearance = clearance;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
