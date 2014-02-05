package com.shrike.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shrike.model.StudentInfo;
import com.shrike.model.services.DataAccessException;
import com.shrike.model.services.FacultyInfoDAO;
import com.shrike.model.services.UserAddDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class EditStudentInfoServlet.
 */
@WebServlet("/EditStudentInfoServlet")
public class EditStudentInfoServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new edits the student info servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method view student's dashboard information
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
			String userType = request.getParameter("userType");
			request.setAttribute("userType", userType);
			String nextPage = "";
			if (userType.equalsIgnoreCase("student")) {
				nextPage = "/student/updateInfo.jsp";
				FacultyInfoDAO facultyInfo = new FacultyInfoDAO();
				StudentInfo studentInfo = new StudentInfo(request.getRemoteUser());
				request.setAttribute("studentInfo", studentInfo);
				request.setAttribute("error", "");				
				// list of faculty <id, name> to select from drop down.
				Map<String, String> facultyMap = facultyInfo.getFacultyList();
				request.setAttribute("facultyMap", facultyMap);
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			} 	
		}
		else if(request.isUserInRole("faculty")) {
			response.sendRedirect(request.getContextPath()+"/auth");
		}
		else {
			response.sendRedirect(request.getContextPath()+"/auth");
		}
	}

	/**
	 * Do post method saves in the databse the edited information
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userType = request.getParameter("userType");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int housenum = Integer.parseInt(request.getParameter("housenum"));
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String phone = request.getParameter("phone");
		
		//all form validation error messages
		//Map<String, String> validationErrors = new HashMap<String, String>();
		
		FacultyInfoDAO facultyInfo = new FacultyInfoDAO();
		// list of faculties - <faculty id, faculty name> pairs.
		Map<String, String> facultyMap = facultyInfo.getFacultyList();   

		
		StudentInfo studentInfo = null;
		if (userType.equalsIgnoreCase("student")) {
			String academicLevel = request.getParameter("academicLevel");
			String advisor = request.getParameter("advisor");
			studentInfo = new StudentInfo();
			studentInfo.setUserName(request.getRemoteUser()); //--------
			studentInfo.setFirstName(firstName);
			studentInfo.setLastName(lastName);
			studentInfo.setEmail(email);
			studentInfo.setMiddleName(middleName);
			studentInfo.setAcademicLevel(academicLevel);
			studentInfo.setAdvisor(advisor);
			studentInfo.setHousenum(housenum);
			studentInfo.setStreet(street);
			studentInfo.setCity(city);
			studentInfo.setState(state);
			studentInfo.setPhone(phone);
			studentInfo.setZipcode(zipcode);
		}
		
		UserAddDAO userAddDao = new UserAddDAO();
		try {
			userAddDao.updateStudentInfo(studentInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
   		
		
		// faculty list to select from drop down list.
		facultyMap = facultyInfo.getFacultyList();
		request.setAttribute("facultyMap", facultyMap);
	
		StudentInfo studentInfo1 = new StudentInfo(request.getRemoteUser());
		request.setAttribute("userType", userType);
		request.setAttribute("studentInfo", studentInfo1); //use this attribute from JSP code to re-populate the data again, if there was something wrong.
		RequestDispatcher view = request.getRequestDispatcher("/student/updateInfo.jsp");
		view.forward(request, response);
	}
	

	/**
	 *  Validate the student add form data. There is no straightforward way to come to this place as javascript validation
	 *  is also there to reduce the round trip. But this is must because, javascript is not reliable- what if some home made tool
	 *  tries to add user bypassing the javascript? We have to have server side validations too.
	 *  Now, the validation is not bullet proof but the practice is to have it, and make it strong.
	 * 
	 * @param studentInfo
	 * @return - Map containing list of fields and corresponding validation error message.
	 */
}
