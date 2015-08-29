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
	
	public User(String username, String password, String clearance,
			String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.clearance = clearance;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClearance() {
		return clearance;
	}

	public void setClearance(String clearance) {
		this.clearance = clearance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
