package com.shrike.model.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shrike.model.CourseInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class CourseInfoDAO.
 */
public class CourseInfoDAO {
	
	/** The connectiondao. @uml.property  name="connectiondao" @uml.associationEnd  multiplicity="(1 1)" */
	private ConnectionDAO connectiondao;
	
	/** The con. @uml.property  name="con" */
	private Connection con = null;
	
	/** The Master required. @uml.property  name="masterRequired" @uml.associationEnd  multiplicity="(0 -1)" elementType="com.shrike.model.CourseInfo" */
	private ArrayList<CourseInfo> MasterRequired;
	
	/**
	 * Gets the master required.
	 *
	 * @return the master required
	 */
	public ArrayList<CourseInfo> getMasterRequired() {
		return MasterRequired;
	}

	/**
	 * Sets the master required.
	 *
	 * @param masterRequired the new master required
	 */
	public void setMasterRequired(ArrayList<CourseInfo> masterRequired) {
		MasterRequired = masterRequired;
	}

	/**
	 * Gets the applied master required.
	 *
	 * @return the applied master required
	 */
	public ArrayList<CourseInfo> getAppliedMasterRequired() {
		return AppliedMasterRequired;
	}

	/**
	 * Sets the applied master required.
	 *
	 * @param appliedMasterRequired the new applied master required
	 */
	public void setAppliedMasterRequired(ArrayList<CourseInfo> appliedMasterRequired) {
		AppliedMasterRequired = appliedMasterRequired;
	}

	/**
	 * Gets the phd required.
	 *
	 * @return the phd required
	 */
	public ArrayList<CourseInfo> getPhdRequired() {
		return PhdRequired;
	}

	/**
	 * Sets the phd required.
	 *
	 * @param phdRequired the new phd required
	 */
	public void setPhdRequired(ArrayList<CourseInfo> phdRequired) {
		PhdRequired = phdRequired;
	}

	/** The Applied master required. @uml.property  name="appliedMasterRequired" @uml.associationEnd  multiplicity="(0 -1)" elementType="com.shrike.model.CourseInfo" */
	private ArrayList<CourseInfo> AppliedMasterRequired;
	
	/** The Phd required. @uml.property  name="phdRequired" @uml.associationEnd  multiplicity="(0 -1)" elementType="com.shrike.model.CourseInfo" */
	private ArrayList<CourseInfo> PhdRequired;
	
	
	/**
	 * Instantiates a new course info dao.
	 */
	public CourseInfoDAO()
	{
		connectiondao = new ConnectionDAO();		
	}
	
	/**
	 * Retrieve required.
	 */
	public void retrieveRequired()
	{
		if (MasterRequired == null)
			MasterRequired = new ArrayList<CourseInfo>();
			else
				if (!MasterRequired.isEmpty())
					MasterRequired.clear();
		if (AppliedMasterRequired == null)
			AppliedMasterRequired = new ArrayList<CourseInfo>();
			else
				if (!AppliedMasterRequired.isEmpty())
					AppliedMasterRequired.clear();
		if (PhdRequired == null)
			PhdRequired = new ArrayList<CourseInfo>();
			else
				if (!PhdRequired.isEmpty())
					PhdRequired.clear();		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			Statement stmt = con.createStatement();
		    String strQuery = "select * from CourseInfo";
		    ResultSet rs = stmt.executeQuery(strQuery);
		    
		    while(rs.next())
		    {
		    	CourseInfo course = new CourseInfo(rs.getString("CourseCode"), rs.getString("CourseTitle"), rs.getInt("Masters"), rs.getInt("AppliedMasters"), rs.getInt("Phd"), rs.getInt("CoreReq"));
		    	if (course.getCoreReq() == 1)
		    	{
		    		if (course.getMaster() == 1)
		    		{
		    			MasterRequired.add(course);
		    		}
		    		if (course.getAplliedmaster() == 1)
		    		{
		    			AppliedMasterRequired.add(course);
		    		}
		    		if (course.getPhd() == 1)
		    		{
		    			PhdRequired.add(course);
		    		}
		    	}
		    }
		    rs.close();
		    con.close();
		    stmt.close();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
	
	/**
	 * Gets the credit.
	 *
	 * @param courseCode the course code
	 * @return the credit
	 */
	public int getCredit(String courseCode)
	{
		int credit = 0;
		ConnectionDAO connectionDAO = new ConnectionDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "SELECT CourseCredit FROM CourseInfo where CourseCode='"+ courseCode +"'";
		
		try {
			conn = connectionDAO.getConnectionObject();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				credit = rs.getInt("CourseCredit");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return credit;
	}
	
	
}
