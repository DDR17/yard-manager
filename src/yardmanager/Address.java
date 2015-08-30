/**
 * 
 */
package yardmanager;

/**
 * @author maxetron
 *
 */
public class Address {
	private String city;
	private String country;
	private String postalCode;
	private String street;
	private String streetNumber;
	
	/**
	 * @param city
	 * @param country
	 * @param postalCode
	 * @param street
	 * @param streetNumber
	 */
	public Address(String city, String country, String postalCode,
			String street, String streetNumber) {
		this.city = city;
		this.country = country;
		this.postalCode = postalCode;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
}
