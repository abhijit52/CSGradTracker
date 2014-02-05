package com.shrike.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.shrike.model.CourseApprove;
import com.shrike.model.CoursePlan;

// TODO: Auto-generated Javadoc
/**
 * The Class CourseApproveDAO.
 */
public class CourseApproveDAO {
	
	/** The connectiondao. */
	private ConnectionDAO connectiondao = new ConnectionDAO();
	
	/** The con. */
	private Connection con = null;
	
	/**
	 * Gets the approved course list.
	 *
	 * @param userName the user name
	 * @return the approved course list
	 */
	public ArrayList<CourseApprove> getApprovedCourseList(String userName){
		ResultSet rs = null;
		PreparedStatement pStmnt = null;
		ArrayList<CourseApprove> courseApprove = new ArrayList<CourseApprove>();
		CoursePlanDAO cp = new CoursePlanDAO();
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			pStmnt = con.prepareStatement("select * from shrike.StudentCourseTaken where username = ? " +
								"and CourseApproved = 1 and GradeApproved = 0");
			if (pStmnt != null) {
				pStmnt.setString(1, userName.trim());
			}
			
			rs = pStmnt.executeQuery();

		    while(rs.next()){
		    	CourseApprove cPlan = new CourseApprove();
		    	cPlan.setCourseCode(rs.getString("CourseCode"));
		    	cPlan.setCourseCredit(rs.getString("CreditHours"));
		    	cPlan.setCourseTitle(cp.getCourseTitleByCourseCode(rs.getString("CourseCode")));
		    	cPlan.setGrade((rs.getString("Grade")==null) ? "" : rs.getString("Grade"));
		    			    	
		    	
		    	courseApprove.add(cPlan);
		    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();
				if (pStmnt != null)
					pStmnt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courseApprove;
	}

	
	/**
	 * Gets the course listto approve.
	 *
	 * @return the course listto approve
	 */
	public ArrayList<CourseApprove> getCourseListtoApprove(){
		ResultSet rs = null;
		PreparedStatement pStmnt = null;
		ArrayList<CourseApprove> courseApprove = new ArrayList<CourseApprove>();
		CoursePlanDAO cp = new CoursePlanDAO();
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			pStmnt = con.prepareStatement("select * from shrike.StudentCourseTaken where CourseApproved = 0");
			
			rs = pStmnt.executeQuery();

		    while(rs.next()){
		    	CourseApprove cPlan = new CourseApprove();
		    	cPlan.setUserName(rs.getString("username"));
		    	cPlan.setCourseCode(rs.getString("CourseCode"));
		    	cPlan.setCourseCredit(rs.getString("CreditHours"));
		    	cPlan.setCourseTitle(cp.getCourseTitleByCourseCode(rs.getString("CourseCode")));
		    	cPlan.setGrade((rs.getString("Grade")==null) ? "" : rs.getString("Grade"));
		    	cPlan.setId(rs.getString("idStudentCourseTaken"));		    	
		    	courseApprove.add(cPlan);
		    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();
				if (pStmnt != null)
					pStmnt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courseApprove;
	}

	
	/**
	 * Gets the numberof course listto approve.
	 *
	 * @return the numberof course listto approve
	 */
	public int getNumberofCourseListtoApprove(){
		ResultSet rs = null;
		PreparedStatement pStmnt = null;
		int number = 0;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			pStmnt = con.prepareStatement("select count(*) as 'no' from shrike.StudentCourseTaken where " +
					" CourseApproved = 0");
			
			rs = pStmnt.executeQuery();

		    while(rs.next()){
		    	number = Integer.parseInt(rs.getString("no"));
		    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();
				if (pStmnt != null)
					pStmnt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return number;
	}


	/**
	 * Gets the numberof course list approved.
	 *
	 * @param userName the user name
	 * @return the numberof course list approved
	 */
	public int getNumberofCourseListApproved(String userName){
		ResultSet rs = null;
		PreparedStatement pStmnt = null;
		int number = 0;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			pStmnt = con.prepareStatement("select count(*) as 'no' from shrike.StudentCourseTaken where " +
					"username = ? and CourseApproved = 1 and GradeApproved = 0");
			if (pStmnt != null) {
				pStmnt.setString(1, userName.trim());
			}
			
			rs = pStmnt.executeQuery();

		    while(rs.next()){
		    	number = Integer.parseInt(rs.getString("no"));
		    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();
				if (pStmnt != null)
					pStmnt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return number;
	}

	/**
	 * Gets the grade listto approve.
	 *
	 * @return the grade listto approve
	 */
	public ArrayList<CourseApprove> getGradeListtoApprove(){
		ResultSet rs = null;
		PreparedStatement pStmnt = null;
		ArrayList<CourseApprove> courseApprove = new ArrayList<CourseApprove>();
		CoursePlanDAO cp = new CoursePlanDAO();
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			pStmnt = con.prepareStatement("select * from shrike.StudentCourseTaken where " +
									"CourseApproved = 1 and GradeApproved = 0");
			
			rs = pStmnt.executeQuery();

		    while(rs.next()){
		    	CourseApprove cPlan = new CourseApprove();
		    	cPlan.setUserName(rs.getString("username"));
		    	cPlan.setCourseCode(rs.getString("CourseCode"));
		    	cPlan.setCourseCredit(rs.getString("CreditHours"));
		    	cPlan.setCourseTitle(cp.getCourseTitleByCourseCode(rs.getString("CourseCode")));
		    	cPlan.setGrade((rs.getString("Grade")==null) ? "" : rs.getString("Grade"));
		    	cPlan.setId(rs.getString("idStudentCourseTaken"));		    	
		    	courseApprove.add(cPlan);
		    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();
				if (pStmnt != null)
					pStmnt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courseApprove;
	}

	
	/**
	 * Gets the numberof grade listto approve.
	 *
	 * @return the numberof grade listto approve
	 */
	public int getNumberofGradeListtoApprove(){
		ResultSet rs = null;
		PreparedStatement pStmnt = null;
		int number = 0;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			pStmnt = con.prepareStatement("select count(*) as 'no' from shrike.StudentCourseTaken where CourseApproved = 1" +
										" and GradeApproved = 0");
			
			rs = pStmnt.executeQuery();

		    while(rs.next()){
		    	number = Integer.parseInt(rs.getString("no"));
		    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();
				if (pStmnt != null)
					pStmnt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return number;
	}


	/**
	 * Adds the course approval.
	 *
	 * @param userName the user name
	 * @param courseCode the course code
	 * @param creditHour the credit hour
	 * @param semester the semester
	 * @return the int
	 */
	public int addCourseApproval(String userName, String courseCode, String creditHour, String semester) {
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
			pStmnt = con.prepareStatement("insert into shrike.StudentCourseTaken (CourseApproved, CourseCode, Semester,"
					+"CreditHours, username) values (0, ?, ?, ?, ?)");
			if (pStmnt != null) {
				pStmnt.setString(1, courseCode.trim());
				pStmnt.setString(2, semester.trim());
				pStmnt.setString(3, creditHour);
				pStmnt.setString(4, userName.trim());
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

	
	/**
	 * Adds the grade approval.
	 *
	 * @param userName the user name
	 * @param courseCode the course code
	 * @param Grade the grade
	 * @param semester the semester
	 * @return the int
	 */
	public int addGradeApproval(String userName, String courseCode, String Grade, String semester) {
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
			pStmnt = con.prepareStatement("update shrike.StudentCourseTaken set" +
										" Grade = ?, GradeApproved = 0 where " +
										" username = ? and CourseCode = ? and semester = ?");
			if (pStmnt != null) {
				pStmnt.setString(1, Grade.trim());
				pStmnt.setString(2, userName.trim());
				pStmnt.setString(3, courseCode.trim());
				pStmnt.setString(4, semester.trim());
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
	
	
	/**
	 * Approve course.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int approveCourse(String id){
		int success = 0;
		PreparedStatement pStmnt = null;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			con.setAutoCommit(false);   
			pStmnt = con.prepareStatement("update shrike.StudentCourseTaken set" +
										" CourseApproved = 1 where " +
										" idStudentCourseTaken = ?");
			if (pStmnt != null) {
				pStmnt.setString(1, id.trim());
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
	

/**
 * Reject course.
 *
 * @param id the id
 * @return the int
 */
public int rejectCourse(String id){
		int success = 0;
		PreparedStatement pStmnt = null;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			con.setAutoCommit(false);   
			pStmnt = con.prepareStatement("update shrike.StudentCourseTaken set" +
										" CourseApproved = -1 where " +
										" idStudentCourseTaken = ?");
			if (pStmnt != null) {
				pStmnt.setString(1, id.trim());
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
	
	/**
	 * Approve grade.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int approveGrade(String id){
		int success = 0;
		PreparedStatement pStmnt = null;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			con.setAutoCommit(false);   
			pStmnt = con.prepareStatement("update shrike.StudentCourseTaken set" +
										" GradeApproved = 1 where " +
										" idStudentCourseTaken = ?");
			if (pStmnt != null) {
				pStmnt.setString(1, id.trim());
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
	

	/**
	 * Reject grade.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int rejectGrade(String id){
		int success = 0;
		PreparedStatement pStmnt = null;
		
		try
		{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			con.setAutoCommit(false);   
			pStmnt = con.prepareStatement("update shrike.StudentCourseTaken set" +
										" GradeApproved = -1 where " +
										" idStudentCourseTaken = ?");
			if (pStmnt != null) {
				pStmnt.setString(1, id.trim());
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
	
	
	/**
	 * Gets the courses.
	 *
	 * @param userName the user name
	 * @param semester the semester
	 * @return the courses
	 */
	public ArrayList<CoursePlan> getCourses(String userName, String semester){
		ResultSet rs = null;
		Statement stmt = null;
		
		ArrayList<CoursePlan> coursePlan = new ArrayList<CoursePlan>();
		CoursePlanDAO cPlanDAO = new CoursePlanDAO();
		
		try	{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			stmt = con.createStatement();
		    String strQuery = "select * from shrike.StudentCourseTaken where username = '" + userName 
		    				+"' and Semester = '" + semester+ "'";
		    
		    rs = stmt.executeQuery(strQuery);

		    while(rs.next()){
		    	CoursePlan cPlan = new CoursePlan();
		    	
		    	cPlan.setCourseCode(rs.getString("CourseCode"));
		    	cPlan.setCourseCredit(rs.getString("CreditHours"));
		    	cPlan.setCourseApproved(rs.getString("CourseApproved"));
		    	cPlan.setGrade(rs.getString("Grade"));
		    	cPlan.setCourseTitle(cPlanDAO.getCourseTitleByCourseCode(rs.getString("CourseCode")));
		    	cPlan.setGradeApproved(rs.getString("GradeApproved"));
		     	
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
	 * Get the whole course plan.
	 *
	 * @param userName the user name
	 * @return the whole course plan
	 */
	
	public TreeMap<String, ArrayList<CoursePlan>> getWholeCoursePlan(String userName){
		ResultSet rs = null;
		Statement stmt = null;
		TreeMap<String, ArrayList<CoursePlan>> wholeCoursePlan = new TreeMap<String, ArrayList<CoursePlan>>();
		
		ArrayList<CoursePlan> semCoursePlan = null;
		CoursePlanDAO cPlanDAO = new CoursePlanDAO();
		
		try	{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			stmt = con.createStatement();
		    String strQuery = "SELECT * FROM StudentCourseTaken WHERE username = '" + userName +"'"; 
		    
		    rs = stmt.executeQuery(strQuery);

		    while(rs.next()){
		    	CoursePlan cPlan = new CoursePlan();
		    	
		    	cPlan.setCourseCode(rs.getString("CourseCode"));
		    	cPlan.setCourseCredit(rs.getString("CreditHours"));
		    	cPlan.setCourseApproved(rs.getString("CourseApproved"));
		    	cPlan.setGrade(rs.getString("Grade"));
		    	cPlan.setCourseTitle(cPlanDAO.getCourseTitleByCourseCode(rs.getString("CourseCode")));
		    	cPlan.setGradeApproved(rs.getString("GradeApproved"));
		    	cPlan.setSemester(rs.getString("Semester"));
		    	
		    	semCoursePlan = wholeCoursePlan.get(cPlan.getSemester());
		    	if (semCoursePlan == null) {
		    		semCoursePlan = new ArrayList<CoursePlan>();
		    	}
		    	semCoursePlan.add(cPlan);
		    	wholeCoursePlan.put(cPlan.getSemester(), semCoursePlan);
		    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DbUtil.close(rs,stmt,con);
		}
		
		return wholeCoursePlan;
	}
	
	
	
	
	
	/**
	 * Gets the gpa.
	 *
	 * @param userName the user name
	 * @param semester the semester
	 * @return the gpa
	 */
	public double getGPA(String userName, String semester){
		ResultSet rs = null;
		Statement stmt = null;
		
		ArrayList<CoursePlan> coursePlan = new ArrayList<CoursePlan>();
		CoursePlanDAO cPlanDAO = new CoursePlanDAO();
		
		try	{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			stmt = con.createStatement();
		    String strQuery = "select * from shrike.StudentCourseTaken where username = '" + userName 
		    				+"' and Semester = '" + semester+ "' and CourseApproved = 1 and Grade != ''";
		    
		    rs = stmt.executeQuery(strQuery);

		    while(rs.next()){
		    	CoursePlan cPlan = new CoursePlan();
		    	
		    	cPlan.setCourseCode(rs.getString("CourseCode"));
		    	cPlan.setCourseCredit(rs.getString("CreditHours"));
		    	cPlan.setCourseApproved(rs.getString("CourseApproved"));
		    	cPlan.setGrade(rs.getString("Grade"));
		    	cPlan.setCourseTitle(cPlanDAO.getCourseTitleByCourseCode(rs.getString("CourseCode")));
		    	cPlan.setGradeApproved(rs.getString("GradeApproved"));
		     	
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

//		ArrayList<CoursePlan> course = this.getCourses(userName, semester);
		
		double gradePoint = 0;
		double credit = 0;
		double totalCredit = 0;
		
		for(CoursePlan c: coursePlan){
			credit = Double.parseDouble(c.getCourseCredit());
						
			if(c.getGrade() == null)
				continue;
			else if(c.getGrade().equals("A+") || c.getGrade().equals("A"))
				gradePoint += 4*credit;
			else if(c.getGrade().equals("A-"))
				gradePoint += 3.84*credit;
			else if(c.getGrade().equals("B+"))
				gradePoint += 3.33*credit;
			else if(c.getGrade().equals("B"))
				gradePoint += 3.00*credit;
			else if(c.getGrade().equals("B-"))
				gradePoint += 2.67*credit;
			else if(c.getGrade().equals("C+"))
				gradePoint += 2.33*credit;
			else if(c.getGrade().equals("C"))
				gradePoint += 2.00*credit;
			else if(c.getGrade().equals("C-"))
				gradePoint += 1.67*credit;
			else if(c.getGrade().equals("D+"))
				gradePoint += 1.33*credit;
			else if(c.getGrade().equals("D"))
				gradePoint += 1.00*credit;
			else
				gradePoint += 0;
			
			totalCredit += credit;
		}

		return gradePoint/totalCredit;
	}
	
	
	/**
	 * Gets the cgpa.
	 *
	 * @param userName the user name
	 * @return the cgpa
	 */
	public double getCGPA(String userName){
		ArrayList<CoursePlan> course = this.getAllCourses(userName);
		
		double gradePoint = 0;
		double credit = 0;
		double totalCredit = 0;
		
		for(CoursePlan c: course){
			credit = Double.parseDouble(c.getCourseCredit());
						
			if(c.getGrade() == null)
				continue;
			else if(c.getGrade().equals("A+") || c.getGrade().equals("A"))
				gradePoint += 4*credit;
			else if(c.getGrade().equals("A-"))
				gradePoint += 3.84*credit;
			else if(c.getGrade().equals("B+"))
				gradePoint += 3.33*credit;
			else if(c.getGrade().equals("B"))
				gradePoint += 3.00*credit;
			else if(c.getGrade().equals("B-"))
				gradePoint += 2.67*credit;
			else if(c.getGrade().equals("C+"))
				gradePoint += 2.33*credit;
			else if(c.getGrade().equals("C"))
				gradePoint += 2.00*credit;
			else if(c.getGrade().equals("C-"))
				gradePoint += 1.67*credit;
			else if(c.getGrade().equals("D+"))
				gradePoint += 1.33*credit;
			else if(c.getGrade().equals("D"))
				gradePoint += 1.00*credit;
			else
				gradePoint += 0;
			
			totalCredit += credit;
		}

		return gradePoint/totalCredit;
	}

	/**
	 * Gets the all courses.
	 *
	 * @param userName the user name
	 * @return the all courses
	 */
	public ArrayList<CoursePlan> getAllCourses(String userName){
		ResultSet rs = null;
		Statement stmt = null;
		
		ArrayList<CoursePlan> coursePlan = new ArrayList<CoursePlan>();
		CoursePlanDAO cPlanDAO = new CoursePlanDAO();
		
		try	{
			if (con == null )
				con = connectiondao.getConnectionObject();
			else
				if(con.isClosed())
				  con = connectiondao.getConnectionObject();					
			
			stmt = con.createStatement();
		    String strQuery = "select * from shrike.StudentCourseTaken where username = '" + userName 
		    				+"' and GradeApproved = 1";
		    
		    rs = stmt.executeQuery(strQuery);

		    while(rs.next()){
		    	CoursePlan cPlan = new CoursePlan();
		    	
		    	cPlan.setCourseCode(rs.getString("CourseCode"));
		    	cPlan.setCourseCredit(rs.getString("CreditHours"));
		    	cPlan.setCourseApproved(rs.getString("CourseApproved"));
		    	cPlan.setGrade(rs.getString("Grade"));
		    	cPlan.setCourseTitle(cPlanDAO.getCourseTitleByCourseCode(rs.getString("CourseCode")));
		    	cPlan.setGradeApproved(rs.getString("GradeApproved"));
		     	
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
}
