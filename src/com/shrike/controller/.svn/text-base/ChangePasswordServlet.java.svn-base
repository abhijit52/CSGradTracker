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
 * Servlet implementation class ChangePasswordServlet.
 */
@WebServlet("/ChangePasswordServlet.do")
public class ChangePasswordServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new change password servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get method to load the page "change the password" for any user
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
		HttpSession session = request.getSession();
		String address = "";
		if (request.isUserInRole("student")) {
			address = "/student/ChangePassword.jsp";

		} else if (request.isUserInRole("faculty")) {
			address = "/faculty/ChangePassword.jsp";

		} else if (request.isUserInRole("secretary")) {
			address = "/secretary/ChangePassword.jsp";
		}
		// String address = "/ForgotPassword/NewPassword.jsp";
		session.setAttribute("confirm", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * Do post submit the page for changing password
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
		HttpSession session = request.getSession();
		String address = "";
		ForgotUserInfo fUserInfo = new ForgotUserInfo();
		fUserInfo.setUserID(request.getRemoteUser());
		fUserInfo
				.setPasswordEntered(request.getParameter("newPasswordId") == null ? ""
						: "" + request.getParameter("newPasswordId"));
		ConfirmPasswordDAO cPasswordDAO = new ConfirmPasswordDAO();
		String result = cPasswordDAO.insertPassword(fUserInfo);
		if (result.equalsIgnoreCase("OK")) {
			session.setAttribute("confirm",
					"Password updated successfully. Please Login with new password");
			address = "/logout.do";
			session.invalidate();
			response.sendRedirect("./auth");
		} else {
			session.setAttribute("confirm",
					"Password can not be changed. Please Retry");
			if (request.isUserInRole("student")) {
				address = "/student/ChangePassword.jsp";

			} else if (request.isUserInRole("faculty")) {
				address = "/faculty/ChangePassword.jsp";

			} else if (request.isUserInRole("secretary")) {
				address = "/secretary/ChangePassword.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}

		
	}

}
