package com.shrike.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shrike.model.services.ConnectionDAO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class UploadServlet.
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The conection dao. @uml.property  name="conectionDAO" @uml.associationEnd */
	private ConnectionDAO conectionDAO = null;
	
	/** The con. @uml.property  name="con" */
	private Connection con = null;
	
	/** The prepare statement. @uml.property  name="prepareStatement" */
	private PreparedStatement prepareStatement = null;
	
	/** The file name. @uml.property  name="fileName" */
	private File fileName = null;
	
	/** The fis. @uml.property  name="fis" */
	private FileInputStream fis = null;
	
	/** The boundary. @uml.property  name="boundary" */
	private String boundary = null;
	
	/** The extension. @uml.property  name="extension" */
	private String extension = null;
	
	/** The result. @uml.property  name="result" */
	private int result = 0;
	
	/** The username. @uml.property  name="username" */
	private String username = null;
	
	/** The save file. @uml.property  name="saveFile" */
	private String saveFile = null;
	
	/** The file. @uml.property  name="file" */
	private String file = null;
	
	/** The content type. @uml.property  name="contentType" */
	private String contentType = null;
	
	/** The last index. @uml.property  name="lastIndex" */
	private int lastIndex;
	
	/** The pos. @uml.property  name="pos" */
	private int pos;
	
	/** The boundary location. @uml.property  name="boundaryLocation" */
	private int boundaryLocation;
	
	/** The start pos. @uml.property  name="startPos" */
	private int startPos;
	
	/** The end pos. @uml.property  name="endPos" */
	private int endPos;
	
	/** The file out. @uml.property  name="fileOut" */
	private FileOutputStream fileOut = null;
	
	/** The file instance. @uml.property  name="fileInstance" */
	private File fileInstance = null;
	
	/** The data bytes. @uml.property  name="dataBytes" multiplicity="(0 -1)" dimension="1" */
	private byte dataBytes[];
	
	/** The form data length. @uml.property  name="formDataLength" */
	private int formDataLength;
	
	/** The byte read. @uml.property  name="byteRead" */
	private int byteRead;
	
	/** The total bytes read. @uml.property  name="totalBytesRead" */
	private int totalBytesRead;
	
	/** The in. @uml.property  name="in" */
	private DataInputStream in = null;

	/**
	 * Instantiates a new upload servlet.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get
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
		// TODO Auto-generated method stub
	}

	/**
	 * Do post method updates the reference and images of any particular user
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
		
		int maxCount = Integer.parseInt(session.getAttribute("maxcount").toString());
		
		username = request.getRemoteUser();
		response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		saveFile = "";
		contentType = request.getContentType();
		if ((contentType != null)
				&& (contentType.indexOf("multipart/form-data") >= 0)) {
			in = new DataInputStream(request.getInputStream());
			formDataLength = request.getContentLength();
			dataBytes = new byte[formDataLength];
			byteRead = 0;
			totalBytesRead = 0;
			while (totalBytesRead < formDataLength) {
				byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += byteRead;
			}
			file = new String(dataBytes);
			saveFile = file.substring(file.indexOf("filename=\"") + 10);
			saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
			saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
					saveFile.indexOf("\""));

			lastIndex = contentType.lastIndexOf("=");
			boundary = contentType.substring(lastIndex + 1,
					contentType.length());

			pos = file.indexOf("filename=\"");
			pos = file.indexOf("\n", pos) + 1;
			pos = file.indexOf("\n", pos) + 1;
			pos = file.indexOf("\n", pos) + 1;
			boundaryLocation = file.indexOf(boundary, pos) - 4;
			startPos = ((file.substring(0, pos)).getBytes()).length;
			endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;

			extension = saveFile.substring(saveFile.lastIndexOf(".") + 1);
			fileInstance = new File(saveFile);
			fileOut = new FileOutputStream(fileInstance);
			fileOut.write(dataBytes, startPos, (endPos - startPos));
			fileOut.flush();
			fileOut.close();
			// out.println("You have successfully upload the file by the name of: "+saveFile);
			try {
				if (conectionDAO == null)
					conectionDAO = new ConnectionDAO();
				if (con == null)
					con = conectionDAO.getConnectionObject();
				else if (con.isClosed())
					con = conectionDAO.getConnectionObject();

				fileName = new File(saveFile);
				fis = new FileInputStream(fileName);
				if(extension.equalsIgnoreCase("pdf")) {
					prepareStatement = con
							.prepareStatement("UPDATE UserDoc SET DataStore=? , DocType=? WHERE username ='"
							+ username + "'" + " and idUserDoc = '"+maxCount +"'");
				}
				else
				{
				prepareStatement = con
						.prepareStatement("UPDATE UserDoc SET DataStore=? , DocType=? WHERE username ='"
								+ username + "'");
				
				}
				
				prepareStatement.setBinaryStream(1, (InputStream) fis,
						(int) (fileName.length()));
				prepareStatement.setString(2, extension);
				result = prepareStatement.executeUpdate();
				if (result > 0) {
					System.out.println("Uploaded successfully !");
				} else {
					System.out.println("unsucessfull to upload file.");
				}
				prepareStatement.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataBytes = null;
		}
		//RequestDispatcher view = request.getRequestDispatcher("editPicture.do");
		//view.forward(request, response);
		String studentPage = "/student/student_home.jsp";
		//String myPage="/test.do";
		String facultyPage = "/faculty/faculty_home.jsp";
		String secretaryPage = "/secretary/secretary_home.jsp";
		
		if(request.isUserInRole("student"))
		{
			//response.sendRedirect(studentPage);
			//response.sendRedirect(myPage);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(studentPage);  
            rd.forward(request, response);  
		}
		else if(request.isUserInRole("faculty"))
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(facultyPage);  
            rd.forward(request, response);  
			//response.sendRedirect(facultyPage);
		}
		else if(request.isUserInRole("secretary")){
			//response.sendRedirect(secretaryPage);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(secretaryPage);  
			rd.forward(request, response);  
		}
	}

}
