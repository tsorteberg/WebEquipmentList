package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Feb 25, 2021
 */

import model.ListItem;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Feb 25, 2021
 */

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/editItemServlet")
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItemServlet() {
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
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		// Local object declaration and instantiation.
		ListItemHelper dao = new ListItemHelper();
		ListItem itemToUpdate = dao.searchForItemById(tempId);
		
		// Set ListItem object parameters.
		itemToUpdate.setBrand(brand);
		itemToUpdate.setModel(model);
		
		// Method call to update ListItem object to database using ListItemHelper context object.
		dao.updateItem(itemToUpdate);
		
		// Method call to redirect to viewAllItemsServlet using request dispatcher.
		getServletContext().getRequestDispatcher("/viewAllItemsServlet").forward(request, response);
	}

}
