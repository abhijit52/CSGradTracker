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

import com.shrike.model.FacultyInfo;
import com.shrike.model.StudentInfo;
import com.shrike.model.services.DataAccessException;
import com.shrike.model.services.FacultyInfoDAO;
import com.shrike.model.services.StudentInfoDAO;
import com.shrike.model.services.UserAddDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class for adding faculty. Please see web.xml for the
 * servlet mapping (i.e. to find the servlet name/alias etc).
 * 
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = { "secretary" }))
public class AddFacultyServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new adds the faculty servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFacultyServlet() {
		super();
	}

	/**
	 * When secreatary clicks the add faculty link.
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
			if (userType.equalsIgnoreCase("faculty")) {
				nextPage = "/secretary/addFaculty.jsp";
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
		String joinDateStr = request.getParameter("joinDate");
		DateFormat df = new SimpleDateFormat("mm-dd-yyyy");
		Date joinDate = null;
		String message = null;

		// all form validation error messages
		Map<String, String> validationErrors = new HashMap<String, String>();

		FacultyInfo facultyInfo = null;
		facultyInfo = new FacultyInfo();
		facultyInfo.setUserName(userName);
		facultyInfo.setFirstName(firstName);
		facultyInfo.setLastName(lastName);
		facultyInfo.setEmail(email);
		facultyInfo.setMiddleName(middleName);
		facultyInfo.setAffiliation("Dr."); /*
											 * faculty can later change those
											 * values if he/she wants to
											 */
		facultyInfo.setDepartment("Computer science");

		try {
			if (joinDateStr != null && joinDateStr != "") {
				joinDate = df.parse(joinDateStr);
				facultyInfo.setJoinedDate(joinDate);
			}
		} catch (Exception exe) {
			message = "Malformed date in the Join date field";
			validationErrors.put("joinDate", message);
		}

		// check password field value.
		if ((password == null || password.trim() == "" || password.length() < 5)) {
			validationErrors
					.put("password",
							"Password is too short! Password should be at least 5 characters long.");

		}
		// check if retyped password match.. case sensitive match.
		if (!password.equals(retypePassword)) {
			validationErrors.put("retypePassword", "Password didn't match!");
		}

		// validate the user info,
		validationErrors.putAll(validateFaculty(facultyInfo));
		if (validationErrors.size() == 0) {
			try {
				UserAddDAO userAddDao = new UserAddDAO();
				// check if user exists.
				if (!userAddDao.checkUser(facultyInfo.getUserName())) {
					// try to add student in the database.
					userAddDao.addFaculty(facultyInfo, password, "faculty");
					message = "User " + userName
							+ " has been added successfully!";
					request.setAttribute("message", message);
				} else {
					// user already exists.
					request.setAttribute("error", "Failed to add user "
							+ facultyInfo.getUserName()
							+ ". User already exists!");
				}
			} catch (DataAccessException dae) {
				// failed to add in the database - for some reason.
				request.setAttribute("error", "Failed to add user "
						+ facultyInfo.getUserName()
						+ dae.getMessage());
			}

		} else {
			// else there are some validation errors.
			request.setAttribute("error",
					"Failed to add user " + facultyInfo.getUserName()
							+ ". Invalid input!");
			request.setAttribute("validationErrors", validationErrors);
		}

		request.setAttribute("userType", userType);
		// use this attribute from JSP code to re-populate the data again, if
		// there was something wrong.
		request.setAttribute("facultyInfo", facultyInfo);
		RequestDispatcher view = request
				.getRequestDispatcher("/secretary/addFaculty.jsp");
		view.forward(request, response);
	}

	/**
	 * Validate the faculty add form data. There is no straightforward way to
	 * come to this place as javascript validation is also there to reduce the
	 * round trip. But this is must because, javascript is not reliable- what if
	 * some home made tool tries to add user bypassing the javascript? We have
	 * to have server side validations too. Now, the validation is not bullet
	 * proof but the practice is to have it, and make it strong.
	 *
	 * @param facultyInfo the faculty info
	 * @return - Map containing list of fields and corresponding validation
	 * error message.
	 */

	private Map<String, String> validateFaculty(FacultyInfo facultyInfo) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		Pattern pattern = null;
		Matcher matcher = null;
		boolean matched = false;

		/*
		 * email validation pattern, ref:
		 * stackoverflow.com/questions/46155/validate
		 * -email-address-in-javascript
		 */
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		// validate the username - basic validation includes checking if there
		// is some special characters.
		pattern = Pattern.compile("[a-z0-9]", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(facultyInfo.getUserName());
		matched = matcher.find();
		String errorText = null;
		if (!matched) {
			errorText = "Special characters are not allowed in user name";
			validationErrors.put("loginName", errorText);
		}

		// validate email ..etc.
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(facultyInfo.getEmail());
		if (!matcher.find()) {
			errorText = "Email address is invalid!";
			validationErrors.put("email", errorText);
		}
		return validationErrors;
	}
}
