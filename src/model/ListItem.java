package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Feb 25, 2021
 */
@Entity
@Table(name="devices")
public class ListItem {
	
	// Instance variable declaration and initialization.
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="BRAND")
	private String brand;
	
	@Column(name="MODEL")
	private String model;

	/**
	 * Default constructor.
	 */
	public ListItem() {
		super();
	}
	
	/**
	 * Primary constructor.
	 * @param brand; Required string.
	 * @param model; Required string.
	 */
	public ListItem(String brand, String model) {
		super();
		this.brand = brand;
		this.model = model;
	}

	/**
	 * Get method for id instance.
	 * @return: Returns an integer.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Get method for brand instance.
	 * @return: Returns a string.
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * Get method for model instance.
	 * @return: Returns a string.
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * Set method for id instance.
	 * @param id: Required integer.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Set method for brand instance.
	 * @param id: Required string.
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * Set method for model instance.
	 * @param id: Required string.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * Default print method.
	 * @return: Returns a string.
	 */
	public String returnItemDetails() {
		return "| ID: " + this.id + " * Brand: " + brand + " * Model: " + model + " |";
	}
}