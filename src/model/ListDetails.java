package model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Mar 4, 2021
 */
// Active table declaration.
@Entity
@Table(name="list_details")
public class ListDetails {
	
	// Instance object declaration and initialization.
	// id instance.
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LIST_ID")
	private int id;
	
	// listName instance.
	@Column(name="LIST_NAME")
	private String listName;
	
	// createDate instance.
	@Column(name="CREATE_DATE")
	private LocalDate createDate;
	
	// studio instance.
	@ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn(name="STUDIO_ID")
	private Studio studio;
	
	// listOfDevices instance.
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinTable (name="devices_on_list", joinColumns={ @JoinColumn(name="LIST_ID", referencedColumnName="LIST_ID") },
	inverseJoinColumns={ @JoinColumn(name="DEVICE_ID", referencedColumnName="ID", unique=true) })
	private List<ListItem> listOfDevices;

	/**
	 * Default constructor.
	 */
	public ListDetails() {
		super();
	}
	
	/**
	 * Primary constructor.
	 * @param id: Required integer.
	 * @param listName: Required string.
	 * @param createDate: Required LocalDate.
	 * @param studio: Required Studio object.
	 * @param listOfDevices: Required List<ListItem>.
	 */
	public ListDetails(int id, String listName, LocalDate createDate, Studio studio, List<ListItem> listOfDevices) {	
		super();
		this.id = id;
		this.listName = listName;
		this.createDate = createDate;
		this.studio = studio;
		this.listOfDevices = listOfDevices;
	}
	
	/**
	 * Overloaded constructor.
	 * @param listName; Required string.
	 * @param createDate: Required LocalDate.
	 * @param studio: Required Studio object.
	 * @param listOfDevices: Required List<ListItem>.
	 */
	public ListDetails(String listName, LocalDate createDate, Studio studio, List<ListItem> listOfDevices) {
		super();
		this.listName = listName;
		this.createDate = createDate;
		this.studio = studio;
		this.listOfDevices = listOfDevices;
	}
	
	
	/**
	 * Overloaded constructor.
	 * @param listName: Required string.
	 * @param createDate: Required LocalDate.
	 * @param studio: Required Studio object.
	 */
	public ListDetails(String listName, LocalDate createDate, Studio studio) {
		super();
		this.listName = listName;
		this.createDate = createDate;
		this.studio = studio;
	}

	/**
	 * Get method for id instance.
	 * @return: Returns an integer.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Get method for listName instance.
	 * @return: Returns a string.
	 */
	public String getlistName() {
		return listName;
	}
	
	/**
	 * Get method for createDate instance.
	 * @return: Returns a LocalDate object.
	 */
	public LocalDate getcreateDate() {
		return createDate;
	}
	
	/**
	 * Get method for studio instance.
	 * @return: Returns a Studio object.
	 */
	public Studio getstudio() {
		return studio;
	}
	
	/**
	 * Get method for listOfDevices instance.
	 * @return: Returns a list of ListItem objects.
	 */
	public List<ListItem> getlistOfDevices() {
		return listOfDevices;
	}
	
	/**
	 * Set method for the id instance.
	 * @param id: Required integer.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Set method for the listName instance.
	 * @param id: Required string.
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	/**
	 * Set method for the createDate instance.
	 * @param id: Required LocalDate.
	 */
	public void setcreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * Set method for the studio instance.
	 * @param id: Required Studio object type.
	 */
	public void setstudio(Studio studio) {
		this.studio = studio;
	}
	
	/**
	 * Set method for the listOfDevices instance.
	 * @param id: Required list of ListItem object type.
	 */
	public void setlistOfDevices(List<ListItem> listOfDevices) {
		this.listOfDevices = listOfDevices;
	}

	/**
	 * Default toString() method.
	 */
	@Override
	public String toString() {
		return "ListDetails [id=" + id + ", listName=" + listName + ", createDate=" + createDate + ", studio=" + studio
				+ ", listOfDevices=" + listOfDevices + "]";
	}
}