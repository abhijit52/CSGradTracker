package com.shrike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.HttpConstraint;

import com.shrike.model.StudentInfo;
import com.shrike.model.services.DataAccessException;
import com.shrike.model.services.FacultyInfoDAO;
import com.shrike.model.services.StudentInfoDAO;
import com.shrike.model.services.UserAddDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class for adding user - student. Please see web.xml
 * for the servlet mapping (i.e. to find the servlet name/alias etc).
 * 
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = { "secretary" }))
public class UserAddServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new user add servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddServlet() {
		super();
	}

	/**
	 * When user clicks the add student link.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.isUserInRole("secretary")) {
			String userType = request.getParameter("userType");
			request.setAttribute("userType", userType);
			String nextPage = "";
			if (userType.equalsIgnoreCase("student")) {
				nextPage = "/secretary/addStudent.jsp";
				FacultyInfoDAO facultyInfo = new FacultyInfoDAO();
				// list of faculty <id, name> to select from drop down.
				Map<String, String> facultyMap = facultyInfo.getFacultyList();
				request.setAttribute("facultyMap", facultyMap);
				RequestDispatcher dispatch = request
						.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			}
		} else if (request.isUserInRole("student")) {
			response.sendRedirect(request.getContextPath() + "/auth");
		} else {
			response.sendRedirect(request.getContextPath() + "/auth");
		}
	}

	/**
	 * When user submits the add user (student/faculty) form.
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
		String userType = request.getParameter("userType");
		String userName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String enrollmentDateStr = request.getParameter("enrollmentDate");
		DateFormat df = new SimpleDateFormat("mm-dd-yyyy");
		Date enrollmentDate = null;
		String message = null;

		// all form validation error messages
		Map<String, String> validationErrors = new HashMap<String, String>();

		FacultyInfoDAO facultyInfo = new FacultyInfoDAO();
		// list of faculties - <faculty id, faculty name> pairs.
		Map<String, String> facultyMap = facultyInfo.getFacultyList();

		StudentInfo studentInfo = null;
		String academicLevel = request.getParameter("academicLevel");
		String advisor = request.getParameter("advisor");
		studentInfo = new StudentInfo();
		studentInfo.setUserName(userName);
		studentInfo.setFirstName(firstName);
		studentInfo.setLastName(lastName);
		studentInfo.setEmail(email);
		studentInfo.setMiddleName(middleName);
		studentInfo.setAcademicLevel(academicLevel);
		studentInfo.setAdvisor(advisor);
		try {
			if (enrollmentDateStr != null && enrollmentDateStr != "") {
				enrollmentDate = df.parse(enrollmentDateStr);
				studentInfo.setEnrollmentDate(enrollmentDate);
			}
		} catch (Exception exe) {
			message = "Malformed date in the Enrollment date field";
			validationErrors.put("enrollmentDate", message);
		}

		// check password field value.
		if ((password == null || password.trim() == "" || password.length() < 5)) {
			validationErrors.put("password","Password is too short!. Password should be at least 5 characters long.");

		}
		// check if retyped password match.. case sensitive match.
		if (!password.equals(retypePassword)) {
			validationErrors.put("retypePassword", "Password didn't match!");
		}

		// validate the user info,
		validationErrors.putAll(validateStudent(studentInfo));
		if (validationErrors.size() == 0) {
			try {
				UserAddDAO userAddDao = new UserAddDAO();
				// check if user exists.
				if (!userAddDao.checkUser(studentInfo.getUserName())) {
					// try to add student in the database.
					userAddDao.addStudent(studentInfo, password, "student");
					message = "User " + userName + " has been added successfully!";
					request.setAttribute("message", message);
				} else {
					// user already exists.
					request.setAttribute("error", "Failed to add user "	+ studentInfo.getUserName()	+ ". User already exists!");
				}
			} catch (DataAccessException dae) {
				// failed to add in the database - for some reason.
				request.setAttribute("error", "Failed to add user "	+ studentInfo.getUserName()	+ dae.getMessage());
			}

		} else {
			// else there are some validation errors.
			request.setAttribute("error","Failed to add user " + studentInfo.getUserName()	+ ". Invalid input!");
			request.setAttribute("validationErrors", validationErrors);
		}

		// faculty list to select from drop down list.
		facultyMap = facultyInfo.getFacultyList();
		request.setAttribute("facultyMap", facultyMap);

		request.setAttribute("userType", userType);
		// use this attribute from JSP code to re-populate the data again, if there was something wrong.
		request.setAttribute("studentInfo", studentInfo); 
		RequestDispatcher view = request.getRequestDispatcher("/secretary/addStudent.jsp");
		view.forward(request, response);
	}

	/**
	 * Validate the student add form data. There is no straightforward way to
	 * come to this place as javascript validation is also there to reduce the
	 * round trip. But this is must because, javascript is not reliable- what if
	 * some home made tool tries to add user bypassing the javascript? We have
	 * to have server side validations too. Now, the validation is not bullet
	 * proof but the practice is to have it, and make it strong.
	 *
	 * @param studentInfo the student info
	 * @return - Map containing list of fields and corresponding validation
	 * error message.
	 */

	private Map<String, String> validateStudent(StudentInfo studentInfo) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		Pattern pattern = null;
		Matcher matcher = null;
		boolean matched = false;
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		// validate the username - basic validation includes checking if there
		// is some special characters.
		pattern = Pattern.compile("[a-z0-9]", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(studentInfo.getUserName());
		matched = matcher.find();
		String errorText = null;
		if (!matched) {
			errorText = "Special characters are not allowed in user name";
			validationErrors.put("loginName", errorText);
		}

		// validate email ..etc.
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(studentInfo.getEmail());
		if (!matcher.find()) {
			errorText = "Email address is invalid!";
			validationErrors.put("email", errorText);
		}
		return validationErrors;
	}

}
