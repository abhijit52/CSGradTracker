package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shrike.model.ForgotUserInfo;
import com.shrike.model.services.ConfirmPasswordDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ConfirmPasswordServlet.
 */
@WebServlet("/ConfirmPasswordServlet.do")
public class ConfirmPasswordServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new confirm password servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method loads confirmation password page
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String address = "/ForgotPassword/NewPassword.jsp";
		session.setAttribute("confirm", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * Do post method saves the changed password and redirects the user to the login page
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String address ="";
		ForgotUserInfo	fUserInfo = new ForgotUserInfo();
		fUserInfo.setUserID(request.getParameter("message"));
		fUserInfo.setPasswordEntered(request.getParameter("newPasswordId") == null ? "" : ""
				+ request.getParameter("newPasswordId"));
		ConfirmPasswordDAO cPasswordDAO = new ConfirmPasswordDAO();
		String result = cPasswordDAO.insertPassword(fUserInfo);
		
		if(result.equalsIgnoreCase("OK")) {
			session.setAttribute("confirm", "Password updated successfully. Please Login");
			 address = "/login.jsp";
		}
		else 
		{
			session.setAttribute("confirm", "Password can not be changed. Please Retry");
			address = "/ForgotPassword/NewPassword.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);

	}

}
