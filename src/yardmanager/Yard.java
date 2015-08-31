/**
 * 
 */
package yardmanager;

import java.awt.Polygon;
import java.util.Date;
import java.util.List;

/**
 * @author Home
 *
 */
public class Yard {
	private String id;
	private List<Container> containers;
	private Polygon boundaries;
	private Date lastEdited;
	
	/**
	 * @param id
	 * @param containers
	 * @param boundaries
	 * @param lastEdited
	 */
	public Yard(String id, List<Container> containers, Polygon boundaries,
			Date lastEdited) {
		super();
		this.id = id;
		this.containers = containers;
		this.boundaries = boundaries;
		this.lastEdited = lastEdited;
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
	 * @return the containers
	 */
	public List<Container> getContainers() {
		return containers;
	}

	/**
	 * @param containers the containers to set
	 */
	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	/**
	 * @return the lastEdited
	 */
	public Date getLastEdited() {
		return lastEdited;
	}

	/**
	 * @param lastEdited the lastEdited to set
	 */
	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}

	/**
	 * @return the boundaries
	 */
	public Polygon getBoundaries() {
		return boundaries;
	}

	/**
	 * @param boundaries the boundaries to set
	 */
	public void setBoundaries(Polygon boundaries) {
		this.boundaries = boundaries;
	}
}
