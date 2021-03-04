package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Mar 4, 2021
 */
@Entity
@Table(name="studio")
public class Studio {
	
	// Instance variable declaration and initialization.
	// id instance.
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STUDIO_ID")
	private int id;
	
	// studioName instance.
	@Column(name="STUDIO_NAME")
	private String studioName;
	
	/**
	 * Default constructor.
	 */
	public Studio() {
		super();
	}
	
	/**
	 * Primary constructor.
	 * @param id: Required integer.
	 * @param studioName: Required string.
	 */
	public Studio(int id, String studioName) {
		super();
		this.id = id;
		this.studioName = studioName;
	}
	
	/**
	 * Overload constructor.
	 * @param studioName: Required string.
	 */
	public Studio(String studioName) {
		super();
		this.studioName = studioName;
	}
	
	/**
	 * Get method for id instance.
	 * @return: Returns an integer.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set method for id instance.
	 * @param id: Required integer.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get method for studioName instance.
	 * @return: Returns a string.
	 */
	public String getStudioName() {
		return studioName;
	}
	
	/**
	 * Set method for 
	 * @param studioName: Required string.
	 */
	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}
	
	/**
	 * Default toString() method.
	 */
	@Override
	public String toString() {
		return "Studio [id=" + id + ", studioName=" +
				studioName + "]";
	}
}