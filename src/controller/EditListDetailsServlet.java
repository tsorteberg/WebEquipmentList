package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ListDetails;
import model.ListItem;
import model.Studio;

/**
 * Servlet implementation class editListDetailsServlet
 */
@WebServlet("/EditListDetailsServlet")
public class EditListDetailsServlet extends HttpServlet {
	
	// Class constants declaration and initialization.
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditListDetailsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Local variable declaration and initialization.
		ListDetailsHelper dao = new ListDetailsHelper();
		ListItemHelper lih = new ListItemHelper();
		StudioHelper sh = new StudioHelper();
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		ListDetails listToUpdate = dao.searchForListDetailsById(tempId);
		
		// Get parameters from jsp page.
		String newListName = request.getParameter("listName");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String studioName = request.getParameter("studioName");
		
		// Find Studio object by name attribute.
		Studio newStudio = sh.findStudio(studioName);
		LocalDate ld;
		try 
		{
			// Attempt to parse date parameters.
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} 
		catch (NumberFormatException ex) 
		{
			// If parse fails, set to current date.
			ld = LocalDate.now();
		}
		try 
		{
			// Create array of items to add from parameters.
			String[] selectedItems = request.getParameterValues("allItemsToAdd");
			
			// Instantiate a list of ListItem objects.
			List<ListItem> selectedItemsInList = new ArrayList<ListItem>();
			
			// Populate list.
			for (int i = 0; i < selectedItems.length; i++) {
				// Log output to console.
				System.out.println(selectedItems[i]);
				ListItem c = lih.searchForItemById(Integer.parseInt(selectedItems[i]));
				selectedItemsInList.add(c);
			}
			listToUpdate.setlistOfDevices(selectedItemsInList);
		} 
		catch (NullPointerException ex) 
		{
			// If there are no items in the list, then create empty list.
			List<ListItem> selectedItemsInList = new ArrayList<ListItem>();
			listToUpdate.setlistOfDevices(selectedItemsInList);
		}
		
		// Populate list and update database table.
		listToUpdate.setListName(newListName);
		listToUpdate.setcreateDate(ld);
		listToUpdate.setstudio(newStudio);
		dao.updateList(listToUpdate);
		
		// Redirect to viewing all lists.
		getServletContext().getRequestDispatcher("/ViewAllListsServlet").forward(request, response);
	}
}
