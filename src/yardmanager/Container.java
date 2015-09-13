/**
 * 
 */
package yardmanager;

import java.util.Date;

/**
 * @author Home
 *
 */
public class Container {
	private String id;
	private Yard yard;
	private Company customer;
	private String type;
	private String ISOCode;
	private String sealNumber;
	private String colour;
	private int size;
	private int mass;
	private int xPos;
	private int yPos;
	private int level;
	private int coverage;
	private Date dateManufactured;
	private boolean full;
	private boolean in;

	/**
	 * @param id
	 * @param yard
	 * @param customer
	 * @param type
	 * @param iSOCode
	 * @param sealNumber
	 * @param colour
	 * @param size
	 * @param mass
	 * @param xPos
	 * @param yPos
	 * @param level
	 * @param coverage
	 * @param dateManufactured
	 * @param full
	 * @param in
	 */
	public Container(String id, Yard yard, Company customer, String type, String iSOCode,
			String sealNumber, String colour, int size, int mass, int xPos,
			int yPos, int level, int coverage, Date dateManufactured,
			boolean full, boolean in) {
		this.id = id;
		this.yard = yard;
		this.customer = customer;
		this.type = type;
		this.ISOCode = iSOCode;
		this.sealNumber = sealNumber;
		this.colour = colour;
		this.size = size;
		this.mass = mass;
		this.xPos = xPos;
		this.yPos = yPos;
		this.level = level;
		this.coverage = coverage;
		this.dateManufactured = dateManufactured;
		this.full = full;
		this.in = in;
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
	 * @return the yard
	 */
	public Yard getYard() {
		return yard;
	}

	/**
	 * @param yard the yard to set
	 */
	public void setYard(Yard yard) {
		this.yard = yard;
	}

	/**
	 * @return the customer
	 */
	public Company getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Company customer) {
		this.customer = customer;
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
	 * @return the iSOCode
	 */
	public String getISOCode() {
		return ISOCode;
	}

	/**
	 * @param iSOCode the iSOCode to set
	 */
	public void setISOCode(String iSOCode) {
		ISOCode = iSOCode;
	}

	/**
	 * @return the sealNumber
	 */
	public String getSealNumber() {
		return sealNumber;
	}

	/**
	 * @param sealNumber the sealNumber to set
	 */
	public void setSealNumber(String sealNumber) {
		this.sealNumber = sealNumber;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the mass
	 */
	public int getMass() {
		return mass;
	}

	/**
	 * @param mass the mass to set
	 */
	public void setMass(int mass) {
		this.mass = mass;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the coverage
	 */
	public int getCoverage() {
		return coverage;
	}

	/**
	 * @param coverage the coverage to set
	 */
	public void setCoverage(int coverage) {
		this.coverage = coverage;
	}

	/**
	 * @return the dateManufactured
	 */
	public Date getDateManufactured() {
		return dateManufactured;
	}

	/**
	 * @param dateManufactured the dateManufactured to set
	 */
	public void setDateManufactured(Date dateManufactured) {
		this.dateManufactured = dateManufactured;
	}

	/**
	 * @return the full
	 */
	public boolean isFull() {
		return full;
	}

	/**
	 * @param full the full to set
	 */
	public void setFull(boolean full) {
		this.full = full;
	}
	
	/**
	 * @return the in
	 */
	public boolean isIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(boolean in) {
		this.in = in;
	}
}