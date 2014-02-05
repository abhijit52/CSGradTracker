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
 * Servlet implementation class ApproveCourseGrade.
 */
@WebServlet("/OldApproveCourseGrade.do")
public class Old_ApproveCourseGrade extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new old_ approve course grade.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Old_ApproveCourseGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.isUserInRole("faculty")) {
			HttpSession session = request.getSession();
			session.setAttribute("list", "course");
			RequestDispatcher view = request.getRequestDispatcher("/faculty/approveCourseGrade.jsp");
			view.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath()+"/auth");
		}
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
		HttpSession session = request.getSession();
		String btnShow = null;
		String btnSubmit = null;
		
		btnShow = request.getParameter("btnshow");
		btnSubmit = request.getParameter("btnsubmit");
		
		
		if (btnSubmit != null){
			int number = Integer.parseInt(request.getParameter("psize"));
			CourseApproveDAO cApprove = new CourseApproveDAO();
			ArrayList<CourseApprove> cPlan = null;
			
			if(request.getParameter("list").equals("course")){
				for(int i = 1; i <= number; i++){
					if(request.getParameter("check"+i) != null){
						cApprove.approveCourse(request.getParameter("id"+i));
					}
				}
				
				cPlan = cApprove.getCourseListtoApprove();
				number = cApprove.getNumberofCourseListtoApprove();

				session.setAttribute("message", "Courses successfully approved");
				session.setAttribute("coursePlan", cPlan);	
			}
			else if(request.getParameter("list").equals("grade")){
				for(int i = 1; i <= number; i++){
					if(request.getParameter("check"+i) != null){
						cApprove.approveGrade(request.getParameter("id"+i));
					}
				}
				
				cPlan = cApprove.getGradeListtoApprove();
				number = cApprove.getNumberofGradeListtoApprove();

				session.setAttribute("message", "Grades successfully approved");
				session.setAttribute("coursePlan", cPlan);	

			}
			
			session.setAttribute("number", number);
			session.setAttribute("coursePlan", cPlan);
			session.setAttribute("list", request.getParameter("list"));
			
			btnShow = null;
		}

		if(btnShow != null){
			CourseApproveDAO cApproveDAO = new CourseApproveDAO();
			ArrayList<CourseApprove> cPlan = null;
			int number = 0;
			
			if(request.getParameter("list").equals("course")){
				cPlan = cApproveDAO.getCourseListtoApprove();
				number = cApproveDAO.getNumberofCourseListtoApprove();

				session.setAttribute("message", "");
				session.setAttribute("coursePlan", cPlan);	
			}
			else if(request.getParameter("list").equals("grade")){
				cPlan = cApproveDAO.getGradeListtoApprove();
				number = cApproveDAO.getNumberofGradeListtoApprove();
				
				request.setAttribute("list", "Grade");
				session.setAttribute("coursePlan", cPlan);	
			}
			
			session.setAttribute("message", "");
			session.setAttribute("number", number);
			session.setAttribute("coursePlan", cPlan);
			session.setAttribute("list", request.getParameter("list"));
			btnSubmit = null;
		}
				
		RequestDispatcher view = request.getRequestDispatcher("/faculty/approveCourseGrade.jsp");
		view.forward(request, response);
	}
}
