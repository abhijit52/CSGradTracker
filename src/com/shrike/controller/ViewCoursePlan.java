package com.shrike.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shrike.model.CoursePlan;
import com.shrike.model.services.CoursePlanDAO;
import com.shrike.model.services.DataAccessException;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ViewCoursePlan.
 */
@WebServlet("/ViewCoursePlan.do")
public class ViewCoursePlan extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new view course plan.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ViewCoursePlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get method loads the view course plan page
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("year", request.getAttribute("year"));
		request.setAttribute("semester", request.getAttribute("semester"));
		request.setAttribute("coursePlan", null);	
	
		RequestDispatcher view = request.getRequestDispatcher("/student/viewCoursePlan.jsp");
		view.forward(request, response);
	}

	/**
	 * Do post method saves the view course plan according to user's operation
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String semester = request.getParameter("session") + request.getParameter("year");
		//get the form action - delete selected courses, update comments.
		String formAction = request.getParameter("formAction");	
		
		ArrayList<CoursePlan> coursePlan = null;
		
		try {
			if (formAction.equals("delete")) {
				handleDeleteAction(request);
				request.setAttribute("message", "Course deleted successfully!");
			} else if (formAction.equals("update")) {
				handleUpdateAction(request);
				request.setAttribute("message", "Course updated successfully!");
			}		
		coursePlan = getCoursePlans(request,semester);
		}catch (DataAccessException dae) {
			request.setAttribute("error", dae.getMessage());
		}
		request.setAttribute("year", request.getParameter("year"));
		request.setAttribute("session", request.getParameter("session"));
		request.setAttribute("coursePlan", coursePlan);
		RequestDispatcher view = request.getRequestDispatcher("/student/viewCoursePlan.jsp");
		view.forward(request, response);
		return;
	}
	

	/**
	 * Get the list of course plans for the selected semester (when user presses view button or something).
	 *
	 * @param request the request
	 * @param semester the semester
	 * @return the course plans
	 */
	private ArrayList<CoursePlan> getCoursePlans(HttpServletRequest request, String semester) {
		CoursePlanDAO cPlanDAO = new CoursePlanDAO();
		ArrayList<CoursePlan> coursePlans = cPlanDAO.getCoursePlan(request.getRemoteUser(), semester);
		return coursePlans;
	}
	
	/**
	 * Delete the selected courses from the plan.
	 *
	 * @param request the request
	 * @throws DataAccessException the data access exception
	 */
	private void handleDeleteAction(HttpServletRequest request) throws DataAccessException {
		String selectedRowIds = request.getParameter("selectedRows");
		String [] rowIds = selectedRowIds.trim().split("\\s+");
		CoursePlanDAO cPlanDAO = new CoursePlanDAO();
		String semester = request.getParameter("session") + request.getParameter("year");

		for (String rowId : rowIds) {
			String courseId = request.getParameter("chk"+rowId);
			cPlanDAO.deleteCourseFromPlan(request.getRemoteUser(), courseId, semester);
		}
	}
	
	/**
	 * Handle the update course plan action.
	 *
	 * @param request the request
	 * @throws DataAccessException the data access exception
	 */
	private void handleUpdateAction(HttpServletRequest request) throws DataAccessException {
		String selectedRowIds = request.getParameter("selectedRows");
		String [] rowIds = selectedRowIds.trim().split("\\s+");
		String semester = request.getParameter("session") + request.getParameter("year");

		CoursePlanDAO cPlanDAO = new CoursePlanDAO();
		
		for (String rowId : rowIds) {
			String courseId = request.getParameter("chk"+rowId);
			String comments = request.getParameter("comment"+rowId);
			if (comments == null) { 
				comments = "";
			}	
			cPlanDAO.updateCoursePlanComments(request.getRemoteUser(), courseId,comments, semester);
		}
	}
	
}
