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
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Mar 4, 2021
 */

/**
 * Servlet implementation class CreateNewListServlet
 */
@WebServlet("/CreateNewListServlet")
public class CreateNewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Local variable declaration and initialization.
		ListItemHelper lih = new ListItemHelper();
		LocalDate ld;
		
		// Get parameters from jsp page.
		String listName = request.getParameter("listName");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String studioName = request.getParameter("studioName");
		
		
		// Log output to console.
		System.out.println("List Name: "+ listName);
		
		// Try catch block for date format input validation.
		try {
			// Attempt to parse to correct format.
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		}
		catch(NumberFormatException ex) {
			// If parse fails, set to current date.
			ld = LocalDate.now();
		}
		
		// Create array of items to add from parameters.
		String[] selectedItems = request.getParameterValues("allItemsToAdd");
		
		// Instantiate a list of ListItem objects.
		List<ListItem> selectedItemsInList = new ArrayList<ListItem>();
		
		//make sure something was selected – otherwise we get a null pointer exception.
		
		if (selectedItems != null && selectedItems.length > 0) {
			for(int i = 0; i < selectedItems.length; i++) {
				// Log output to console.
				System.out.println(selectedItems[i]);
				
				// Search for items in array and add to list.
				ListItem c = lih.searchForItemById(Integer.parseInt(selectedItems[i]));
				selectedItemsInList.add(c);
			}
		}
		
		// Instantiate ListDetails object.
		Studio studio = new Studio(studioName);
		ListDetails sld = new ListDetails(listName, ld, studio);
		sld.setlistOfDevices(selectedItemsInList);
		ListDetailsHelper slh = new ListDetailsHelper();
		slh.insertNewListDetails(sld);
		
		// Log output to console.
		System.out.println("Success!");
		System.out.println(sld.toString());
		
		// Forward http request/response to jsp page.
		getServletContext().getRequestDispatcher("/ViewAllListsServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
