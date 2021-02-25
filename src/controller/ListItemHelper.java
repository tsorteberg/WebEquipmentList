package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Feb 11, 2021
 */
public class ListItemHelper {
	
	// Instance variable declaration and initialization.
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudioEquipmentList");

	/**
	 * Default constructor.
	 */
	public ListItemHelper() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Method that accepts a list item to add.
	 * @param li: Required listItem object type.
	 */
	public void insertItem(ListItem li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Method that returns ListItem object of all objects in the database.
	 * @return: ListItem object.
	 */
	@SuppressWarnings("unchecked")
	public List<ListItem> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<ListItem> allItems = em.createQuery("SELECT i FROM ListItem i").getResultList();
		return allItems;
	}
	
	/**
	 * Method that deletes the first ListItem object from database.
	 * @param toDelete: Required ListItem object.
	 */
	public void deleteItem(ListItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.brand = :selectedBrand and li.model = :selectedModel", ListItem.class);
		typedQuery.setParameter("selectedBrand", toDelete.getBrand());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		typedQuery.setMaxResults(1);
		ListItem result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Method that searches for database object via ID instance.
	 * @param idToEdit: Required ListItem object.
	 * @return: ListItem object.
	 */
	public ListItem searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListItem found = em.find(ListItem.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * Method that updates database objects.
	 * @param toEdit: Required ListItem object.
	 */
	public void updateItem(ListItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Method that searches for database object via BRAND instance.
	 * @param brandName: Required ListItem object.
	 * @return: ListItem object.
	 */
	public List<ListItem> searchForItemByBrand(String brandName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.brand = :selectedBrand", ListItem.class);
		typedQuery.setParameter("selectedBrand", brandName);
		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	/**
	 * Method that searches for database object via MODEL instance.
	 * @param brandName: Required ListItem object.
	 * @return: ListItem object.
	 */
	public List<ListItem> searchForItemByModel(String modelName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.model = :selectedModel", ListItem.class);
		typedQuery.setParameter("selectedModel", modelName);
		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	/**
	 * Default method to close database connection.
	 */
	public void cleanUp(){
		emfactory.close();
		}
}
