/**
 * 
 */
package yardmanager;

/**
 * @author maxetron
 *
 */
public class Company {
	private String name;
	private String code;
	private Address address;
	
	/**
	 * @param name
	 * @param code
	 * @param address
	 */
	public Company(String name, String code, Address address) {
		this.name = name;
		this.code = code;
		this.address = address;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
