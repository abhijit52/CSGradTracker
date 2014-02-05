package com.shrike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shrike.model.services.CoursePlanDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ShowCourseServlet.
 */
@WebServlet("/ShowCourseServlet")
public class ShowCourseServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new show course servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ShowCourseServlet() {
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
		PrintWriter out = response.getWriter();
		String courseCode = request.getParameter("courseCode").toString();
	    
		CoursePlanDAO cPlan = new CoursePlanDAO();
		String courseTitle = cPlan.getCourseTitleByCourseCode(courseCode);
		String courseCredit = cPlan.getCourseCreditByCourseCode(courseCode);
		
		Statement st;
	    
	    String data = ":" + courseTitle + ":" + courseCredit + ":";
	    
	    out.println(data);
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
