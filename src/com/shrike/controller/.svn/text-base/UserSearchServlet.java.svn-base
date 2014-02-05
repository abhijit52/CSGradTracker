package com.shrike.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shrike.model.FacultyInfo;
import com.shrike.model.StudentInfo;
import com.shrike.model.services.FacultyInfoDAO;
import com.shrike.model.services.StudentInfoDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class UserSearchServlet.
 */
@WebServlet("/searchUser.do")
public class UserSearchServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new user search servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method to laod user search page for secretary
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.isUserInRole("secretary"))
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("/secretary/searchUser.jsp");
			dispatch.forward(request, response);
		}
		else if(request.isUserInRole("student"))
		{
			
			response.sendRedirect(request.getContextPath()+"/auth");
			
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/auth");
		}
	}

	/**
	 * Do post methods finds the user for a given user query
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.isUserInRole("secretary"))
		{
		
		String inputQuery = request.getParameter("searchQuery");
		StudentInfoDAO studentInfoDAO = new StudentInfoDAO();
		FacultyInfoDAO facultyInfoDAO = new FacultyInfoDAO();
		request.setAttribute("searchStudentList", studentInfoDAO.findStudents(inputQuery));
		request.setAttribute("searchFacultyList", facultyInfoDAO.findFaculties(inputQuery));
		request.setAttribute("inputQuery", inputQuery);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/secretary/searchUser.jsp");
		dispatch.forward(request, response);
		
		}
		
	}

}
