package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListItem;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Feb 25, 2021
 */

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/addItemServlet")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Local variable declaration and initialization.
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		
		// Local object declaration and instantiation.
		ListItem li = new ListItem(brand, model);
		ListItemHelper dao = new ListItemHelper();
		
		// Method call to add ListItem object to database using ListItemHelper context object.
		dao.insertItem(li);
		
		// Method call to redirect to index.html using request dispatcher.
		getServletContext().getRequestDispatcher("/ViewAllItemsServlet").forward(request, response);
	}

}
