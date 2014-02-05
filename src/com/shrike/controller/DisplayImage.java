package com.shrike.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shrike.model.services.*;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class DisplayImage
 */
@WebServlet("/diplayImage.do")
public class DisplayImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 
		  int iNumPhoto ;
		  Connection conn = null;
		  if ( request.getParameter("user") != null )
		  {
		  
			  
		    //iNumPhoto = Integer.parseInt(request.getParameter("imgID")) ;  
		 
		    try
		    { 
		      ConnectionDAO cn = new ConnectionDAO();
			   conn = cn.getConnectionObject();
		       
		       conn.setAutoCommit (false); 
		       Statement stmt = conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT idUserDoc FROM UserDoc WHERE username = \""
								+ request.getParameter("user").trim() + "\" AND DocType=\"jpg\"");

				if (!rs.next()) {
					response.setContentType("image/jpg");
				       OutputStream o = response.getOutputStream();
				       o.write(null);
				       o.flush();
				       o.close();
				}else{
		       // get the image from the database
		       iNumPhoto = rs.getInt(1);
		       byte[] imgData = images.getPhoto( conn, iNumPhoto) ;  
		       // display the image
		       response.setContentType("image/jpg");
		       OutputStream o = response.getOutputStream();
		       o.write(imgData);
		       o.flush();
		       o.close();
		       imgData = null;
				}
		    }
		    catch (Exception e)
		    {
		      System.err.println("Second attempt.");
		    }
		    finally
		    {
		       try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		    } 
		  }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
