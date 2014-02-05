package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shrike.model.services.DataAccessException;
import com.shrike.model.services.StudentInfoDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet for search feature - from faculty dashboard.
 */

@ServletSecurity(value=@HttpConstraint (rolesAllowed = {"faculty"}))
public class ListAdviseeServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new list advisee servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ListAdviseeServlet() {
        super();
        
    }

	/**
	 * Do get method views the list of students advised
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.isUserInRole("faculty"))
		{
			try {
			StudentInfoDAO studentInfoDao = new StudentInfoDAO();
			//given the faculty user name, get the list of advisees.
			request.setAttribute("adviseeList", studentInfoDao.getAdviseeList(request.getRemoteUser()));
			} catch (DataAccessException dae) {
				request.setAttribute("error",dae.getMessage());
			}
			RequestDispatcher view = request.getRequestDispatcher("/faculty/adviseeList.jsp");
			view.forward(request, response);
			return;
		}
		else if(request.isUserInRole("student")){
			response.sendRedirect(request.getContextPath()+"/auth");
		}
		else{
			response.sendRedirect(request.getContextPath()+"/auth");
		}
	}

}
