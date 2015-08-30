/**
 * 
 */
package yardmanager;

/**
 * @author Home
 *
 */
public class Container {
	
	private String containerID;
	private String companyID;
	private int size;
	private int mass;
	private int xPos;
	private int yPos;
	private int level;
	private int coverage;
	private String type;
	private String isoCode ;
	private String acceptance;
	private String seal;
	private String truckCode;
	private String truckName;
	private String dateOfManufacture;
	private String truckLicense;
	private String inspectorName;
	private String dateIn;
	private String dateOut;
	private String comments;
	private String colour;
	private boolean full;
	
	public Container(String containerID, String companyID, int size, int mass, int xPos, int yPos,
			int level, int coverage, String type, String isoCode,
			String acceptance, String seal, String truckCode, String truckName,
			String dateOfManufacture, String truckLicense,
			String inspectorName, String dateIn, String dateOut,
			String comments, String colour, boolean full) {
		this.containerID = containerID;
		this.companyID = companyID;
		this.size = size;
		this.mass = mass;
		this.xPos = xPos;
		this.yPos = yPos;
		this.level = level;
		this.coverage = coverage;
		this.type = type;
		this.isoCode = isoCode;
		this.acceptance = acceptance;
		this.seal = seal;
		this.truckCode = truckCode;
		this.truckName = truckName;
		this.dateOfManufacture = dateOfManufacture;
		this.truckLicense = truckLicense;
		this.inspectorName = inspectorName;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.comments = comments;
		this.colour = colour;
		this.full = full;
	}

	/**
	 * @return the containerID
	 */
	public String getContainerID() {
		return containerID;
	}

	/**
	 * @param containerID the containerID to set
	 */
	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}
	
	/**
	 * @return the companyID
	 */
	public String getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
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
	 * @return the isoCode
	 */
	public String getIsoCode() {
		return isoCode;
	}

	/**
	 * @param isoCode the isoCode to set
	 */
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	/**
	 * @return the acceptance
	 */
	public String getAcceptance() {
		return acceptance;
	}

	/**
	 * @param acceptance the acceptance to set
	 */
	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}

	/**
	 * @return the seal
	 */
	public String getSeal() {
		return seal;
	}

	/**
	 * @param seal the seal to set
	 */
	public void setSeal(String seal) {
		this.seal = seal;
	}

	/**
	 * @return the truckCode
	 */
	public String getTruckCode() {
		return truckCode;
	}

	/**
	 * @param truckCode the truckCode to set
	 */
	public void setTruckCode(String truckCode) {
		this.truckCode = truckCode;
	}

	/**
	 * @return the truckName
	 */
	public String getTruckName() {
		return truckName;
	}

	/**
	 * @param truckName the truckName to set
	 */
	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}

	/**
	 * @return the dateOfManufacture
	 */
	public String getDateOfManufacture() {
		return dateOfManufacture;
	}

	/**
	 * @param dateOfManufacture the dateOfManufacture to set
	 */
	public void setDateOfManufacture(String dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	/**
	 * @return the truckLicense
	 */
	public String getTruckLicense() {
		return truckLicense;
	}

	/**
	 * @param truckLicense the truckLicense to set
	 */
	public void setTruckLicense(String truckLicense) {
		this.truckLicense = truckLicense;
	}

	/**
	 * @return the inspectorName
	 */
	public String getInspectorName() {
		return inspectorName;
	}

	/**
	 * @param inspectorName the inspectorName to set
	 */
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}

	/**
	 * @return the dateIn
	 */
	public String getDateIn() {
		return dateIn;
	}

	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	/**
	 * @return the dateOut
	 */
	public String getDateOut() {
		return dateOut;
	}

	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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

}