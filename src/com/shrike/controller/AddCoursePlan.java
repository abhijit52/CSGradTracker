package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shrike.model.services.CoursePlanDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class AddCoursePlan.
 */
@WebServlet("/AddCoursePlan.do")
public class AddCoursePlan extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
    
    /**
     * Instantiates a new adds the course plan.
     *
     * @see HttpServlet#HttpServlet()
     */
    public AddCoursePlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method to load the Add Course Plan page
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("message", "");
		RequestDispatcher view = request.getRequestDispatcher("/student/addcourseplan.jsp");
		view.forward(request, response);
	}

	/**
	 * Do post method to save the course plan in database
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(insertCoursePlan(request) == 1){
			session.setAttribute("message","Course plan has been created successfully!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/student/addcourseplan.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * Insert course plan.
	 *
	 * @param request the request
	 * @return the int
	 */
	private int insertCoursePlan(HttpServletRequest request){
		int success = 0;
		
		CoursePlanDAO cPlan = new CoursePlanDAO();
		
		String courseCode = "";
		String courseCredit = "";
		String comment = "";
		String loginName = request.getRemoteUser();
		String semester = request.getParameter("session") + request.getParameter("year");
		int row = Integer.parseInt(request.getParameter("psize"));

		for(int i = 1; i <= row; i++){
			courseCode = request.getParameter("code" + i);
			courseCredit = request.getParameter("credit" + i);
			comment = request.getParameter("cmnt"+i);

			if(courseCredit == null || courseCredit.equals("")){
				courseCredit = "3";
			}
			if(comment == null || comment.equals("")){
				comment ="NA";
			}
			
			success = cPlan.addCoursePlanEntry(loginName, courseCode, courseCredit, comment, semester);
			
			if(success == 0){
				return 0;
			}
		}
		
		return 1;
	}

}
