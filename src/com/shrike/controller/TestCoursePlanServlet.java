package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.shrike.model.services.CourseInfoDAO;
import com.shrike.model.services.CoursePlanDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class TestCoursePlanServlet.
 */
//@WebServlet("/TestCoursePlanServlet")
public class TestCoursePlanServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new test course plan servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public TestCoursePlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method loads creates studetns course plan page
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.isUserInRole("student"))
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("/student/testcreatecourse.jsp");
			dispatch.forward(request, response);
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/auth");
		}
	}

	/**
	 * Do post method saves the course plan page
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
		
		if(createCoursePlan(request)==1){
			RequestDispatcher dispatch = request.getRequestDispatcher("/student/testcreatecourse.jsp");
			dispatch.forward(request, response);
		}
			
	}
	
	/**
	 * Creates the course plan.
	 *
	 * @param request the request
	 * @return the int
	 */
	private int createCoursePlan(HttpServletRequest request)
	{
		int success = 0;
		
		String courseCode = "";
		String courseCredit = "";
		String comment = "";
		String loginName = request.getRemoteUser();
		String semester = request.getParameter("session")+ request.getParameter("year");
		String courseList = request.getParameter("courseList");
		String [] courses = courseList.split(",");
		System.out.println("Login as:"+loginName);
		System.out.println("Semester:"+semester);
		System.out.println("Course List from Browser:"+courseList);
		CourseInfoDAO courseInfo = new CourseInfoDAO();
		CoursePlanDAO coursePlan = new CoursePlanDAO();
		
		
		for(int i = 0; i < courses.length; i++)
		{
			courseCode = courses[i];
			courseCredit = String.valueOf(courseInfo.getCredit(courses[i]));
			comment = request.getParameter("comment"+courses[i]);
			//System.out.println(courses[i]);
			//request.getParameter("comment"+courses[i]);
			//System.out.println(courseInfo.getCredit(courses[i]));
			//get course credit from database
			
			if((courseCredit == null)||(courseCredit==""))
			{
				courseCredit = "3";
			}
			if((comment == null)||(comment == ""))
			{
				comment = "NA";
			}
			success = coursePlan.addCoursePlanEntry(loginName,courseCode , courseCredit, comment, semester);
			
			if(success == 0)
			{
				return 0;
			}
			
			//System.out.println(request.getParameter("comment"+courses[i]));
		}		
		return 1;
	}

}
