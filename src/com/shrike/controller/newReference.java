package com.shrike.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import com.shrike.model.services.ConnectionDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class newReference.
 */
@WebServlet("/addRef.do")
public class newReference extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new new reference.
     *
     * @see HttpServlet#HttpServlet()
     */
    public newReference() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get methods loads the  reference for the user
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Do post methods saves the reference to the database
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String desc = (String) request.getParameter("desc");
		String username = request.getRemoteUser();
		ConnectionDAO cn = new ConnectionDAO();
	 	Connection con = cn.getConnectionObject();
	 	Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	 	String pid = request.getParameter("id");
	 	desc = desc.replace("\"", "\\\"");
	 	
	 	String s2q = "INSERT INTO UserDoc (DocType, Description, username) VALUES (\"pdf\" ,\""+desc+"\",\""+username+"\")";
	 		
		try {
			int rs0 = stmt.executeUpdate(s2q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (con == null)
				con = cn.getConnectionObject();
			else if (con.isClosed())
				con = cn.getConnectionObject();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int maxCount = 0;
		try {
			preparedStatement = con
					.prepareStatement("SELECT max(idUserDoc)  from UserDoc ");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			maxCount = resultSet.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("maxcount", maxCount);
		
		RequestDispatcher view = request.getRequestDispatcher("upload.do");
		view.forward(request, response);
	}

}
