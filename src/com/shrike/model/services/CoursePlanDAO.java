package com.shrike.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shrike.model.CourseInfo;
import com.shrike.model.CoursePlan;

// TODO: Auto-generated Javadoc
/**
 * The Class CoursePlanDAO.
 */
public class CoursePlanDAO {
	
	/** The connectiondao. @uml.property  name="connectiondao" @uml.associationEnd  multiplicity="(1 1)" */
	private ConnectionDAO connectiondao = new ConnectionDAO();
	
	/** The con. @uml.property  name="con" */
	private Connection con = null;
	
	/**
	 * Gets the allcourses.
	 *
	 * @return the allcourses
	 */
	public ArrayList<CourseInfo> getAllcourses()
	{
		ArrayList<CourseInfo> courseInfo = new ArrayList<CourseInfo>();
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			Statement stmt = con.createStatement();
		    String strQuery = "select * from shrike.CourseInfo";
		    ResultSet rs = stmt.executeQuery(strQuery);
		    
		    while(rs.next())
		    {
		    	CourseInfo course = new CourseInfo(rs.getString("CourseCode"), rs.getString("CourseTitle"), 
		    			rs.getInt("CourseType"), rs.getInt("CourseCredit"), rs.getInt("idCourseInfo"));
		    	courseInfo.add(course);
		    }
		    
		    rs.close();
		    stmt.close();
		    con.close();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return courseInfo;
	}

	
	/**
	 * Update course plan comment.
	 *
	 * @param userName the user name
	 * @param courseId the course id
	 * @param comments the comments
	 * @param semester the semester
	 * @throws DataAccessException the data access exception
	 */
	
	public void updateCoursePlanComments(String userName, String courseId, String comments, String semester) throws DataAccessException {
		PreparedStatement pStmnt = null;
		Connection conn = null;
		
		try
		{
			conn = connectiondao.getConnectionObject();
			pStmnt = conn.prepareStatement("UPDATE StudentCoursePlanning SET Comment = ? WHERE username=? AND CourseCode=? AND Semester=? ");
			
			if (pStmnt != null) {
				pStmnt.setString(1, comments);
				pStmnt.setString(2, userName);
				pStmnt.setString(3, courseId);
				pStmnt.setString(4, semester);
				pStmnt.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException (Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException (dce.getMessage());
		}  
		finally {
			DbUtil.close(pStmnt);
			DbUtil.close(conn);
		}
		
	}	

	
	/**
	 * Update course plan comment.
	 *
	 * @param userName the user name
	 * @param courseId the course id
	 * @param semester the semester
	 * @throws DataAccessException the data access exception
	 */
	
	public void deleteCourseFromPlan(String userName, String courseId, String semester) throws DataAccessException {
		PreparedStatement pStmnt = null;
		Connection conn = null;
		
		try
		{
			conn = connectiondao.getConnectionObject();
			pStmnt = conn.prepareStatement("DELETE FROM StudentCoursePlanning WHERE username=? AND CourseCode=? AND Semester =? ");
			
			if (pStmnt != null) {
				pStmnt.setString(1, userName);
				pStmnt.setString(2, courseId);
				pStmnt.setString(3, semester);
				pStmnt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException (Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException (dce.getMessage());
		} 
		finally {
			DbUtil.close(pStmnt);
			DbUtil.close(conn);
		}
	}	
	
	
	
	
	
	/**
	 * Gets the course title by course code.
	 *
	 * @param courseCode the course code
	 * @return the course title by course code
	 */
	public String getCourseTitleByCourseCode(String courseCode)
	{
		String courseName = "";
		Connection con = null;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			
			Statement stmt = con.createStatement();
		    String strQuery = "select CourseTitle from shrike.CourseInfo where CourseCode = \"" + courseCode + "\"";
		    ResultSet rs = stmt.executeQuery(strQuery);

		    rs.next();
		    
		    courseName = rs.getString("CourseTitle");
		    
		    rs.close();
		    stmt.close();
		    con.close();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return courseName;
	}
	
	/**
	 * Gets the course credit by course code.
	 *
	 * @param courseCode the course code
	 * @return the course credit by course code
	 */
	public String getCourseCreditByCourseCode(String courseCode)
	{
		String courseCredit = "";
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			Statement stmt = con.createStatement();
		    String strQuery = "select CourseCredit from shrike.CourseInfo where CourseCode = \"" + courseCode + "\"";
		    ResultSet rs = stmt.executeQuery(strQuery);

		    rs.next();
		    
		    courseCredit = rs.getString("CourseCredit");
		    
		    rs.close();
		    stmt.close();
		    con.close();
		}
		catch(Exception e){
			e.getMessage();
		}
		
		return courseCredit;
	}

	
	/**
	 * Gets the course plan.
	 *
	 * @param userName the user name
	 * @param semester the semester
	 * @return the course plan
	 */
	public ArrayList<CoursePlan> getCoursePlan(String userName, String semester){
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<CoursePlan> coursePlan = new ArrayList<CoursePlan>();
		
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			stmt = con.createStatement();
		    String strQuery = "select * from StudentCoursePlanning where username = '" + userName 
		    				+"' and Semester = '" + semester + "'";
		    
		    rs = stmt.executeQuery(strQuery);

		    while(rs.next()){
		    	CoursePlan cPlan = new CoursePlan();
		    	cPlan.setComment(rs.getString("Comment"));
		    	cPlan.setCourseCode(rs.getString("CourseCode"));
		    	cPlan.setCourseCredit(rs.getString("CreditHours"));
		    	cPlan.setCourseTitle(this.getCourseTitleByCourseCode(rs.getString("CourseCode")));
		     	
		    	coursePlan.add(cPlan);
		    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return coursePlan;
	}
	
	
	/**
	 * Adds the course plan entry.
	 *
	 * @param userName the user name
	 * @param courseCode the course code
	 * @param courseCredit the course credit
	 * @param commment the commment
	 * @param semester the semester
	 * @return the int
	 */
	public int addCoursePlanEntry(String userName, String courseCode, String courseCredit, String commment, String semester) {
		PreparedStatement pStmnt = null;
		int success = 0;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			con.setAutoCommit(false);   
			pStmnt = con.prepareStatement("insert into shrike.StudentCoursePlanning (CourseCode, CreditHours, " +
						"Semester, Comment, username) values (?, ?, ?, ?, ?)");
			if (pStmnt != null) {
				pStmnt.setString(1, courseCode.trim());
				pStmnt.setString(2, courseCredit.trim());
				pStmnt.setString(3, semester.trim());
				pStmnt.setString(4, commment.trim());
				pStmnt.setString(5, userName.trim());
				pStmnt.executeUpdate();
			}
			con.commit(); //transaction block end
			success = 1;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (pStmnt != null)
					pStmnt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
	}
}
