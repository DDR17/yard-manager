/**
 * 
 */
package yardmanager;

import java.util.Date;

/**
 * @author maxetron
 *
 */
public class Interchange {
	private String id;
	private Container container;
	private Company company;
	private String CSCExpiry;
	private String inspectorName;
	private String location;
	private String releaseAcceptance;
	private String truckLicense;
	private String comments;
	private Date date;
	private boolean onHireSurvey;
	private boolean in;
	
	/**
	 * @param id
	 * @param container
	 * @param company
	 * @param cSCExpiry
	 * @param inspectorName
	 * @param location
	 * @param releaseAcceptance
	 * @param truckLicense
	 * @param comments
	 * @param date
	 * @param onHireSurvey
	 * @param in
	 */
	public Interchange(String id, Container container, Company company,
			String cSCExpiry, String inspectorName, String location,
			String releaseAcceptance, String truckLicense, String comments,
			Date date, boolean onHireSurvey, boolean in) {
		this.id = id;
		this.container = container;
		this.company = company;
		CSCExpiry = cSCExpiry;
		this.inspectorName = inspectorName;
		this.location = location;
		this.releaseAcceptance = releaseAcceptance;
		this.truckLicense = truckLicense;
		this.comments = comments;
		this.date = date;
		this.onHireSurvey = onHireSurvey;
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
	 * @return the container
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @return the cSCExpiry
	 */
	public String getCSCExpiry() {
		return CSCExpiry;
	}

	/**
	 * @param cSCExpiry the cSCExpiry to set
	 */
	public void setCSCExpiry(String cSCExpiry) {
		CSCExpiry = cSCExpiry;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the releaseAcceptance
	 */
	public String getReleaseAcceptance() {
		return releaseAcceptance;
	}

	/**
	 * @param releaseAcceptance the releaseAcceptance to set
	 */
	public void setReleaseAcceptance(String releaseAcceptance) {
		this.releaseAcceptance = releaseAcceptance;
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the onHireSurvey
	 */
	public boolean isOnHireSurvey() {
		return onHireSurvey;
	}

	/**
	 * @param onHireSurvey the onHireSurvey to set
	 */
	public void setOnHireSurvey(boolean onHireSurvey) {
		this.onHireSurvey = onHireSurvey;
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
