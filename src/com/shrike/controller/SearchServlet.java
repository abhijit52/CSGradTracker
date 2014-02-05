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

import com.shrike.model.services.StudentInfoDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet for search feature - from faculty dashboard.
 */

@ServletSecurity(value=@HttpConstraint (rolesAllowed = {"faculty"}))
public class SearchServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new search servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        
    }

	/**
	 * Do get method to load search students page
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
			RequestDispatcher view = request.getRequestDispatcher("/faculty/searchResult.jsp");
			view.forward(request, response);
		}
		else if(request.isUserInRole("student")){
			
			response.sendRedirect(request.getContextPath()+"/auth");
		}
		else{
			response.sendRedirect(request.getContextPath()+"/auth");
		}
	}

	/**
	 * Find students based on search query
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("searchQuery");
		StudentInfoDAO userInfoDao = new StudentInfoDAO();
		//find student and set them to the request. If no student record matched - the result size will be zero.
		request.setAttribute("searchResult", userInfoDao.findStudents(query));
		request.setAttribute("searchQuery", query);
		RequestDispatcher view = request.getRequestDispatcher("/faculty/searchResult.jsp");
		view.forward(request, response);
	}

}
