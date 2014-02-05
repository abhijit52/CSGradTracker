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

import com.shrike.model.CourseApprove;
import com.shrike.model.services.CourseApproveDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class addGradeApproval.
 */
@WebServlet("/AddGradeApproval.do")
public class AddGradeApproval extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new adds the grade approval.
     *
     * @see HttpServlet#HttpServlet()
     */
    public AddGradeApproval() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method to view grade approval page for students
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
		request.setAttribute("number", 0);
		RequestDispatcher view = request.getRequestDispatcher("/student/addGradeForApproval.jsp");
		view.forward(request, response);
	}

	/**
	 * Do post method to initiate the grade submission process to the faculty
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
		String btnShow = null;
		String btnSubmit = null;
		
		String loginName = request.getRemoteUser();
		String semester = request.getParameter("session") + request.getParameter("year");
		
		btnShow = request.getParameter("btnshow");
		btnSubmit = request.getParameter("btnsubmit");
		
		
		if (btnSubmit != null){
			String courseCode = null;
			String courseGrade = null;
			
			int number = Integer.parseInt(request.getParameter("psize"));
			
			for(int i=1; i<=number; i++){
				courseCode = request.getParameter("code"+i);
				courseGrade = request.getParameter("grade"+i);
				
				
				CourseApproveDAO cApproveDAO = new CourseApproveDAO();
				cApproveDAO.addGradeApproval(loginName, courseCode, courseGrade, semester);
				
				request.setAttribute("message", "Grade successfully submitted for approval");
				ArrayList<CourseApprove> cPlan = cApproveDAO.getApprovedCourseList(loginName);

				request.setAttribute("number", number);
				request.setAttribute("year", request.getParameter("year"));
				request.setAttribute("semester", request.getParameter("semester"));
				request.setAttribute("coursePlan", cPlan);	

				btnShow = null;
			}
		}

		else if(btnShow != null){
			CourseApproveDAO cApproveDAO = new CourseApproveDAO();
			ArrayList<CourseApprove> cPlan = cApproveDAO.getApprovedCourseList(loginName);
			int number = cApproveDAO.getNumberofCourseListApproved(loginName);

			request.setAttribute("message", "");
			request.setAttribute("number", number);
			request.setAttribute("year", request.getParameter("year"));
			request.setAttribute("semester", request.getParameter("semester"));
			request.setAttribute("coursePlan", cPlan);	
			
			btnSubmit = null;
		}
				
		RequestDispatcher view = request.getRequestDispatcher("/student/addGradeForApproval.jsp");
		view.forward(request, response);
	}
}
