package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shrike.model.services.CourseApproveDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class AddCourseApproval.
 */
@WebServlet("/AddCourseApproval.do")
public class AddCourseApproval extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new adds the course approval.
     *
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseApproval() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method for loading course approval
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		request.setAttribute("message", "");
		RequestDispatcher view = request.getRequestDispatcher("/student/addCoursesForApproval.jsp");
		view.forward(request, response);
	}

	/**
	 * Do post method to submit course approval for students
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		
		if(insertCoursePlan(request) == 1){
			request.setAttribute("message","Course successfully submitted for approval!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/student/addCoursesForApproval.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	/**
	 * Insert course plan for students
	 *
	 * @param request the request
	 * @return the int
	 */
	private int insertCoursePlan(HttpServletRequest request){
		int success = 0;
		
		CourseApproveDAO cApprove = new CourseApproveDAO();
		
		String courseCode = "";
		String courseCredit = "";
		String loginName = request.getRemoteUser();
		String semester = request.getParameter("session") + request.getParameter("year");
		int row = Integer.parseInt(request.getParameter("psize"));

		for(int i = 1; i <= row; i++){
			courseCode = request.getParameter("code" + i);
			courseCredit = request.getParameter("credit" + i);

			if(courseCredit == null || courseCredit.equals("")){
				courseCredit = "3";
			}
			
			success = cApprove.addCourseApproval(loginName, courseCode, courseCredit, semester);
			if(success == 0){
				return 0;
			}
		}
		
		return 1;
	}
}
