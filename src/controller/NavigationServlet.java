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
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Local variable declaration an initialization.
		String act = request.getParameter("doThisToItem");
		String path = "/viewAllItemsServlet";
		
		// Local object declaration and instantiation.
		ListItemHelper dao = new ListItemHelper();
		
		// Selection logic to determine selected action with exception handling.
		// If delete action is selected, then call delete method from context object.
		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListItem itemToDelete = dao.searchForItemById(tempId);
				dao.deleteItem(itemToDelete);
			}
			catch (NumberFormatException e) {
				System.out.println("An item must be selected for deletion.");
			}
		} 
		// If edit action is selected, then call edit method from context object.
		else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListItem itemToEdit = dao.searchForItemById(tempId);
				request.setAttribute("itemToEdit", itemToEdit);
				path = "/edit-item.jsp";
			}
			catch (NumberFormatException e) {
				System.out.println("An item must be selected for editing.");
			}
		}
		// If add action is selected, then redirect to index.html.
		else if (act.equals("add")) {
			path = "/index.html";
		}
		
		// Method call to redirect to index.html using request dispatcher.
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
