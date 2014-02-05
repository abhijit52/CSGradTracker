package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class DeleteUserController.
 */
@WebServlet("/deleteUser")
public class DeactivateUserServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new deactivate user servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public DeactivateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method to load the de-activate the user
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.isUserInRole("secretary")){
			RequestDispatcher dispatch = request.getRequestDispatcher("/secretary/deactivateUser.jsp");
			dispatch.forward(request, response);
			
		}
		else{
			response.sendRedirect(request.getContextPath()+"/auth");
			
		}
				
	}

	/**
	 * Do post method to allow the deactivation
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//processDeleteUserAction(request,response);
		
	}
	
}
