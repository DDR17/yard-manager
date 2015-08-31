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
	private String type;
	private Address address;
	
	/**
	 * @param id
	 * @param name
	 * @param address
	 */
	public Company(String id, String name, String type, Address address) {
		this.id = id;
		this.name = name;
		this.type = type;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
