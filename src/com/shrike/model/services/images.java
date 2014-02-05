package com.shrike.model.services;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.sql.*;

// TODO: Auto-generated Javadoc
/**
 * The Class images.
 */
public class images
{
  /*-------------------------
   *   Get the Blob image
   *------------------------*/
  /**
   * Gets the photo.
   *
   * @param conn the conn
   * @param iNumPhoto the i num photo
   * @return the photo
   */
  public static byte[] getPhoto (Connection conn, int iNumPhoto)
      
  {
	  byte[] imgData = null ;
	  try{
	String req = "" ;
    Blob img ;
    
    Statement stmt = conn.createStatement ();
   
    // Query
    req = "SELECT DataStore FROM UserDoc WHERE idUserDoc = \"" + iNumPhoto+"\"" ;
   
    ResultSet rset  = stmt.executeQuery ( req );
  
    while (rset.next ())
    {   
      img = rset.getBlob(1);
      imgData = img.getBytes(1,(int)img.length());
      break;
    }   
   
    rset.close();
    stmt.close();
	  }catch(Exception e){
		  e.printStackTrace();
	  }
    return imgData ;

  }
 
}  