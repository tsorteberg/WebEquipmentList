package controller;

import java.io.IOException;
import java.util.List;
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
 * Servlet implementation class viewAllListsServlet
 */
@WebServlet("/ViewAllListsServlet")
public class ViewAllListsServlet extends HttpServlet {
	
	// Class constants declaration and initialization.
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllListsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Local variable declaration and initialization.
		ListDetailsHelper slh = new ListDetailsHelper();
		List<ListDetails> abc = slh.getLists();
		request.setAttribute("allLists", abc);
		
		// Selection logic to determine if List<ListDetails> object is empty.
		if(abc.isEmpty()){
			request.setAttribute("allLists", " ");
		}
		
		// Forward http request/response to jsp page.
		getServletContext().getRequestDispatcher("/equipment-list-by-user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}