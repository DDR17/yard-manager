/**
 * 
 */
package yardmanager;

/**
 * @author maxetron
 *
 */
public class Company {
	private String id;
	private String name;
	private Address address;
	
	/**
	 * @param id
	 * @param name
	 * @param address
	 */
	public Company(String id, String name, Address address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
