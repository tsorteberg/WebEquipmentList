package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ListDetails;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Mar 4, 2021
 */

/**
 * Servlet implementation class ListNavigationServlet
 */
@WebServlet("/ListNavigationServlet")
public class ListNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListNavigationServlet() {
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
		String act = request.getParameter("doThisToList");
		
		// If no button is selected, redirect to view all lists.
		if (act == null) {
			getServletContext().getRequestDispatcher("/ViewAllListsServlet").forward(request, response);
		} 
		// Delete button.
		else if (act.equals("delete")) 
		{
			// Input validation for no selection.
			try 
			{
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListDetails listToDelete = dao.searchForListDetailsById(tempId);
				dao.deleteList(listToDelete);
			} 
			catch (NumberFormatException e) 
			{
				// Log output to console.
				System.out.println("Forgot to click a button");
			} 
			finally 
			{
				// Redirect to to view all lists.
				getServletContext().getRequestDispatcher("/ViewAllListsServlet").forward(request, response);
			}
		} 
		// Edit button.
		else if (act.equals("edit")) 
		{
			// Try catch block for input validation and passing parameters to jsp.
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListDetails listToEdit = dao.searchForListDetailsById(tempId);
				request.setAttribute("listToEdit", listToEdit);
				request.setAttribute("month",
				listToEdit.getcreateDate().getMonthValue());
				request.setAttribute("date",
				listToEdit.getcreateDate().getDayOfMonth());
				request.setAttribute("year",
				listToEdit.getcreateDate().getYear());
				ListItemHelper daoForItems = new ListItemHelper();
				request.setAttribute("allItems", daoForItems.showAllItems());
				
				// If model object is empty, then make all attributes blank.
				if(daoForItems.showAllItems().isEmpty()){
					request.setAttribute("allItems", " ");
				}
				
				getServletContext().getRequestDispatcher("/edit-list.jsp").forward(request, response);
			} 
			catch (NumberFormatException e) 
			{
				// Forward http request/response to jsp page.
				getServletContext().getRequestDispatcher("/ViewAllListsServlet").forward(request, response);
			}
		// Add button.
		} else if (act.equals("add")) {
			// Redirect to new-list.asp.
			getServletContext().getRequestDispatcher("/AddItemsForListServlet").forward(request, response);
		}
	}
}
