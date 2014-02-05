package com.shrike.controller;

import java.io.*;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shrike.model.services.ConnectionDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class DownloadFileServlet.
 */
@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The conection dao. @uml.property  name="conectionDAO" @uml.associationEnd */
	private ConnectionDAO conectionDAO = null;
	
	/** The con. @uml.property  name="con" */
	private Connection con = null;
	
	/** The st1. @uml.property  name="st1" */
	private Statement st1 = null;
	
	/** The str query. @uml.property  name="strQuery" */
	private String strQuery = null;
	
	/** The rs1. @uml.property  name="rs1" */
	private ResultSet rs1 = null;
	
	/** The img len. @uml.property  name="imgLen" */
	private String imgLen = null;
	
	/** The id. @uml.property  name="id" */
	private int id = 0;
	
	/** The filename. @uml.property  name="filename" */
	private String filename = null;

	/**
	 * Instantiates a new download file servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get method to load the downloading image or reference
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();

		id = Integer.parseInt(request.getParameter("id"));
		filename = "file" + id;
		try {
			if (conectionDAO == null)
				conectionDAO = new ConnectionDAO();
			if (con == null)
				con = conectionDAO.getConnectionObject();
			else if (con.isClosed())
				con = conectionDAO.getConnectionObject();

			st1 = con.createStatement();
			strQuery = "select DataStore, DocType from UserDoc where idUserDoc="
					+ id;
			rs1 = st1.executeQuery(strQuery);
			imgLen = "";
			if (rs1.next()) {
				imgLen = rs1.getString(1);
				filename += "." + rs1.getString("docType");
				System.out.print(filename);

			}
			rs1 = st1.executeQuery(strQuery);
			if (rs1.next()) {
				int len = imgLen.length();
				byte[] rb = new byte[len];
				InputStream readImg = rs1.getBinaryStream(1);
				int index = readImg.read(rb, 0, len);

				if (rs1.getString("docType").compareToIgnoreCase("pdf") == 0) {
					response.reset();
					response.setContentType("application/pdf");
					response.setHeader("Content-disposition",
							"attachment; filename=" + filename);
					response.getOutputStream().write(rb, 0, len);
					response.getOutputStream().flush();
				} else if (rs1.getString("docType").compareToIgnoreCase("doc") == 0
						|| rs1.getString(2).compareToIgnoreCase("docx") == 0) {
					response.reset();
					response.setContentType("application/word");
					response.setHeader("Content-disposition",
							"attachment; filename=" + filename);
					response.getOutputStream().write(rb, 0, len);
					response.getOutputStream().flush();
				} else {
					response.reset();
					response.setContentType("image/jpg");
					response.setHeader("Content-disposition",
							"attachment; filename=" + filename);
					response.getOutputStream().write(rb, 0, len);
					response.getOutputStream().flush();
				}
				rs1.close();
				st1.close();
				con.close();
				rb = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Do post.
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
	}

}
