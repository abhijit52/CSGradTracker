package com.shrike.controller.faculty;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shrike.model.CoursePlan;
import com.shrike.model.StudentInfo;
import com.shrike.model.services.CourseApproveDAO;
import com.shrike.model.services.DataAccessException;
import com.shrike.model.services.StudentInfoDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet for search feature - from faculty dashboard.
 */

@ServletSecurity(value=@HttpConstraint (rolesAllowed = {"faculty"}))
public class StudentDetailsServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new student details servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public StudentDetailsServlet() {
        super();
        
    }

	/**
	 * Do get method to load student details information
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.isUserInRole("faculty"))
		{
			String studentId =  request.getParameter("studentId"); 
			StudentInfoDAO studentInfoDao = new StudentInfoDAO();
			//get the details of the student for the given id
			StudentInfo studentInfo = studentInfoDao.getInfo(studentId);
			request.setAttribute("student", studentInfo);
			
			// get the course plan... in summary.
			
			String loginName = request.getRemoteUser();
			
			CourseApproveDAO cApproveDAO = new CourseApproveDAO();
			request.setAttribute("wholeCoursePlan",cApproveDAO.getWholeCoursePlan(studentId));
			//request.setAttribute("gpa", cApproveDAO.getGPA(loginName, semester));
			request.setAttribute("cgpa", cApproveDAO.getCGPA(studentId));
			//request.setAttribute("coursePlan", course);	
			
			RequestDispatcher view = request.getRequestDispatcher("/faculty/studentDetails.jsp");
			view.forward(request, response);
			return;
		}
		else if(request.isUserInRole("student")){
			response.sendRedirect(request.getContextPath()+"/auth");
		}
		else{
			response.sendRedirect(request.getContextPath()+"/auth");
		}
	}

}
