package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Studio;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Mar 4, 2021
 */
public class StudioHelper {

	// Instantiate EntityManagerFactory object.
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StudioEquipmentList");
	
	/**
	 * Context method to insert Studio object into database table.
	 * @param s: Required Studio object type.
	 */
	public void insertStudio(Studio s) {
		
		// Instantiate EntityManager object and open connection to database.
		EntityManager em = emfactory.createEntityManager();
		
		// Persist Studio object to database table.
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Context method to retrieve a list of all Studio objects from database table.
	 * @return: Returns a list of Studio objects.
	 */
	@SuppressWarnings("unchecked")
	public List<Studio> showAllStudios() {
		
		// Instantiate EntityManager object and open connection to database.
		EntityManager em = emfactory.createEntityManager();
		
		// Instantiate and initialize query object.
		List<Studio> allStudios = em.createQuery("SELECT s FROM studio s").getResultList();
		
		// Return statement.
		return allStudios;
	}
	
	/**
	 * Context method to search for Studio object by name.
	 * @param nameToLookUp: Required string.
	 * @return: Returns: Returns a Studio object.
	 */
	public Studio findStudio(String nameToLookUp) {
		
		// Instantiate EntityManager object and open connection to database.
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		// Instantiate and initialize query object.
		TypedQuery<Studio> typedQuery = em.createQuery("SELECT st FROM Studio st where st.studioName = :selectedName", Studio.class);
		typedQuery.setParameter("selectedName", nameToLookUp);
		
		// Local variable definition.
		Studio foundStudio;
		
		// Try catch block to determine outcome if object is found.
		try 
		{
			// Set return to object found.
			foundStudio = typedQuery.getSingleResult();
		} 
		catch (NoResultException ex) 
		{
			// Set return to new object.
			foundStudio = new Studio(nameToLookUp);
		}
		
		// Close database connection.
		em.close();
		
		// Return statement.
		return foundStudio;
	}
}