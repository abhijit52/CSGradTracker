package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shrike.model.FacultyInfo;
import com.shrike.model.services.DataAccessException;
import com.shrike.model.services.UserAddDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class EditFacultyInfoServlet.
 */
@WebServlet("/EditFacultyInfoServlet")
public class EditFacultyInfoServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new edits the faculty info servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public EditFacultyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method to edit the faculty information in dashboard
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.isUserInRole("faculty"))
		{
			String userType = request.getParameter("userType");
			request.setAttribute("userType", userType);
			String nextPage = "";
			if (userType.equalsIgnoreCase("faculty")) {
				nextPage = "/faculty/updateInfoFaculty.jsp";
				FacultyInfo facultyInfo = new FacultyInfo(request.getRemoteUser());
				request.setAttribute("facultyInfo", facultyInfo);
				request.setAttribute("error", "");			
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			} 	
		}
		else {
			response.sendRedirect(request.getContextPath()+"/auth");
		}
	}

	/**
	 * Do post method to save the edited information in Dashboard
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
		
		FacultyInfo facultyInfo = null;
		if (userType.equalsIgnoreCase("faculty")) {
			facultyInfo = new FacultyInfo();
			facultyInfo.setUserName(request.getRemoteUser()); //--------
			facultyInfo.setFirstName(firstName);
			facultyInfo.setLastName(lastName);
			facultyInfo.setEmail(email);
			facultyInfo.setMiddleName(middleName);
			facultyInfo.setHousenum(housenum);
			facultyInfo.setStreet(street);
			facultyInfo.setCity(city);
			facultyInfo.setState(state);
			facultyInfo.setPhone(phone);
			facultyInfo.setZipcode(zipcode);			
		}
		
		UserAddDAO userAddDao = new UserAddDAO();
		try {
			userAddDao.updateFacultyInfo(facultyInfo);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
   		
		FacultyInfo facultyInfo1 = new FacultyInfo(request.getRemoteUser());
		request.setAttribute("userType", userType);
		request.setAttribute("facultyInfo", facultyInfo1); //use this attribute from JSP code to re-populate the data again, if there was something wrong.
		RequestDispatcher view = request.getRequestDispatcher("/faculty/updateInfoFaculty.jsp");
		view.forward(request, response);
	}

}
