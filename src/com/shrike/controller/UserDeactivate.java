package com.shrike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shrike.model.FacultyInfo;
import com.shrike.model.StudentInfo;
import com.shrike.model.services.FacultyInfoDAO;
import com.shrike.model.services.StudentInfoDAO;

// TODO: Auto-generated Javadoc
//import sun.org.mozilla.javascript.internal.json.JsonParser;

/**
 * Servlet implementation class UserDeactivate.
 */
// @WebServlet("/UserDeactivate")
public class UserDeactivate extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new user deactivate.
     */
    public UserDeactivate() {
        super();
        // TODO Auto-generated constructor stub
    }




	/**
	 * <<<<<<< .mine
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 * =======
	 * Do post.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * >>>>>>> .r6856
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.isUserInRole("secretary")){
			RequestDispatcher dispatch = request.getRequestDispatcher("/secretary/deactivateUser.jsp");
			dispatch.forward(request, response);
			
		}
		else{
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userAction = request.getParameter("userAction");
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDD: " + userAction);

		if (userAction.equals("search")) {

			ArrayList<StudentInfo> studentList = new ArrayList<StudentInfo>();
			ArrayList<FacultyInfo> facultyList = new ArrayList<FacultyInfo>();
			

			PrintWriter out = response.getWriter();
			System.out.println("The User Name:" + request.getParameter("name"));

			StudentInfoDAO studentInfoDao = new StudentInfoDAO();
			studentList = studentInfoDao.findStudents(request
					.getParameter("name"));
			
			FacultyInfoDAO facultyInfoDao = new FacultyInfoDAO();
			facultyList = facultyInfoDao.findFaculties(request.getParameter("name"));
			
			StringBuilder sb = new StringBuilder();
			boolean firstTime = true;
			
			for (StudentInfo studentInfo : studentList) {
				if (firstTime) {
					sb.append(studentInfo.getUserName() + "|"
							+ studentInfo.getFirstName());
					firstTime = false;
				} else {
					sb.append("@" + studentInfo.getUserName() + "|"
							+ studentInfo.getFirstName());
				}
			}
			for (FacultyInfo facultyInfo : facultyList) {
				System.out.println(facultyInfo.getUserName());
					sb.append("@" + facultyInfo.getUserName() + "|"
							+ facultyInfo.getFirstName());
				
			}
			out.print(sb.toString());
			
		} else if (userAction.equals("deactivate")) {
			//
			String userName = request.getParameter("dUser");
			
			// update in database. - change role to ...
			
		}
	}
	
	
	/**
	 * Gets the user as string.
	 *
	 * @param query the query
	 * @return the user as string
	 */
	private String getUserAsString(String query) {
		String result = "";
		
		return result;
	}

}
