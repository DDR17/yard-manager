/**
 * 
 */
package yardmanager;

import java.util.ArrayList;

/**
 * @author Home
 *
 */
public class Yard {

	private String name;
	private ArrayList<Container> containers;
	private String lastEdited;
	
	public Yard(String name, ArrayList<Container> containers, String lastEdited) {
		this.name = name;
		this.containers = containers;
		this.lastEdited = lastEdited;
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
	 * @return the containers
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}

	/**
	 * @param containers the containers to set
	 */
	public void setContainers(ArrayList<Container> containers) {
		this.containers = containers;
	}

	/**
	 * @return the lastEdited
	 */
	public String getLastEdited() {
		return lastEdited;
	}

	/**
	 * @param lastEdited the lastEdited to set
	 */
	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}
	
	
	
}
