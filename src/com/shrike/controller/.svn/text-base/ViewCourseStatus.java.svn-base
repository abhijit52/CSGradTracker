package com.shrike.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shrike.model.CoursePlan;
import com.shrike.model.services.CourseApproveDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ViewCourseStatus.
 */
@WebServlet("/ViewCourseStatus.do")
public class ViewCourseStatus extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new view course status.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ViewCourseStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method loads the view course status page
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view = request.getRequestDispatcher("/student/viewCourseStatus.jsp");
		view.forward(request, response);
	}

	/**
	 * Do post method show the total GPA, CCGPA and do the calculation for a given semester.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String loginName = request.getRemoteUser();
		String semester = request.getParameter("session") + request.getParameter("year");
		
		CourseApproveDAO cApproveDAO = new CourseApproveDAO();
		ArrayList<CoursePlan> course = cApproveDAO.getCourses(loginName, semester);
		
//		session.setAttribute("year", request.getAttribute("year"));
//		session.setAttribute("semester", request.getAttribute("semester"));
		session.setAttribute("gpa", cApproveDAO.getGPA(loginName, semester));
		session.setAttribute("cgpa", cApproveDAO.getCGPA(loginName));
		session.setAttribute("coursePlan", course);	
		
		RequestDispatcher view = request.getRequestDispatcher("/student/viewCourseStatus.jsp");
		view.forward(request, response);
	}
}
