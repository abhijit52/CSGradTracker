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
import com.shrike.model.services.ForgotPasswordDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ForgetPasswordServlet.
 */
@WebServlet("/ForgetPasswordServlet.do")
public class ForgetPasswordServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new forget password servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgetPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get loads forget Password page
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
		session.setAttribute("pass", "");
		String address = "/ForgotPassword/forgetPassword.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * Do post verifies the inputed information and loads the confirmation password page
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
		String address = "";
		HttpSession session = request.getSession();

		ForgotUserInfo	fUserInfo = new ForgotUserInfo();

		fUserInfo.setUserID(request.getParameter("UserId") == null ? "" : ""
				+ request.getParameter("UserId"));
		fUserInfo.setEmailAddress(request.getParameter("Email") == null ? ""
				: "" + request.getParameter("Email"));
		fUserInfo.setEmailAddress(request.getParameter("Email") == null ? ""
				: "" + request.getParameter("Email"));
		fUserInfo.setBirthDay((request.getParameter("day") == null || request
				.getParameter("day") == "") ? 0 : Integer.parseInt(request
				.getParameter("day")));
		fUserInfo
				.setBirthMonth((request.getParameter("month") == null || request
						.getParameter("month") == "") ? 0 : Integer
						.parseInt(request.getParameter("month")));

		ForgotPasswordDAO	fPasswordDAO = new ForgotPasswordDAO();
		
		boolean result = fPasswordDAO.VerifyFields(fUserInfo);
		if (result) {
			address = "/ForgotPassword/NewPassword.jsp";
			session.setAttribute("pass",fUserInfo.getUserID());
		} else {
			address = "/ForgotPassword/forgetPassword.jsp";
			session.setAttribute("pass",
					"Does not match with Database record. Please enter the correct values");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
