package com.shrike.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class LoginServlet.
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new login servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method loads the login page
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * Do post method loads the login and successfully login redirects to specific user's role page
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentPage = "/student/student_home.jsp";
		//String myPage="/test.do";
		String facultyPage = "/faculty/faculty_home.jsp";
		String secretaryPage = "/secretary/secretary_home.jsp";
		
		if(request.isUserInRole("student"))
		{
			//response.sendRedirect(studentPage);
			//response.sendRedirect(myPage);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(studentPage);  
            rd.forward(request, response);  
		}
		else if(request.isUserInRole("faculty"))
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(facultyPage);  
            rd.forward(request, response);  
			//response.sendRedirect(facultyPage);
		}
		else if(request.isUserInRole("secretary")){
			//response.sendRedirect(secretaryPage);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(secretaryPage);  
			rd.forward(request, response);  
		}
	}
}
