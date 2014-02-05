package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shrike.model.services.CourseInfoDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ViewRequiredCoursesServlet.
 */
@WebServlet("/ViewRequiredCourses.do")
public class ViewRequiredCoursesServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new view required courses servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ViewRequiredCoursesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method  view all the required courses
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CourseInfoDAO courseDAO = new CourseInfoDAO();
		
		courseDAO.retrieveRequired();
		
		String major = (String)request.getParameter("majorSelect");
		request.setAttribute("majorSelect", major);
		request.setAttribute("arrayRequired", courseDAO);
		String address = "/student/student_ViewRequiredCourses.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
