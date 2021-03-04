package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.ListDetails;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Mar 3, 2021
 */
public class ListDetailsHelper {
	
	// Instantiate EntityManagerFactory object.
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudioEquipmentList");
	
	/**
	 * Context method to insert ListDetails object into database table.
	 * @param s: Required ListDetails object type.
	 */
	public void insertNewListDetails(ListDetails s) {
		
		// Instantiate EntityManager object and open connection to database.
		EntityManager em = emfactory.createEntityManager();
		
		// Persist Studio object to database table.
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Context method to retrieve a list of all ListDetails objects from database table.
	 * @return: Returns a list of ListDetails objects.
	 */
	@SuppressWarnings("unchecked")
	public List<ListDetails> getLists() {
		
		// Instantiate EntityManager object and open connection to database.
		EntityManager em = emfactory.createEntityManager();
		
		// Instantiate and initialize query object.
		List<ListDetails> allDetails = em.createQuery("SELECT d FROM ListDetails d").getResultList();
		
		// Return statement.
		return allDetails;
	}
	
	/**
	 * Context method to delete a ListDetails object from database table.
	 * @param toDelete: Required ListDetails object.
	 */
	public void deleteList(ListDetails toDelete) {
		
		// Instantiate EntityManager object and open connection to database.
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		// Instantiate and initialize query object.
		TypedQuery<ListDetails> typedQuery = em.createQuery("select detail from ListDetails detail where detail.id = :selectedId", ListDetails.class);
		typedQuery.setParameter("selectedId", toDelete.getId());
		typedQuery.setMaxResults(1);
		
		// Local variable definition and initialization to query object data.
		ListDetails result = typedQuery.getSingleResult();
		
		// Remove object persistence from database table.
		em.remove(result);
		em.getTransaction().commit();
		
		// Close database connection.
		em.close();
		}
	
	/**
	 * Context method to search for ListDetails object.
	 * @param tempId: Required integer.
	 * @return: Returns ListDetails object.
	 */
	public ListDetails searchForListDetailsById(Integer tempId) {
		
		// Instantiate EntityManager object and open connection to database.
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		// Local variable definition and initialization.
		ListDetails found = em.find(ListDetails.class, tempId);
		
		// Close database connection.
		em.close();
		
		// Return statement.
		return found;
	}
	
	/**
	 * Context method to update ListDetails object.
	 * @param toEdit: Required ListDetails object.
	 */
	public void updateList(ListDetails toEdit) {
		// Instantiate EntityManager object and open connection to database.
		EntityManager em = emfactory.createEntityManager();
		
		// Update object persistence in database table.
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		
		// Close database connection.
		em.close();
	}
}
