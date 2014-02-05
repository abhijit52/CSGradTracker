package com.shrike.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shrike.model.FacultyInfo;
import com.shrike.model.StudentInfo;


// TODO: Auto-generated Javadoc
/**
 * Add new user - stuent, faculty.
 *  
 *
 */

public class UserAddDAO {
	
	/** The connection. @uml.property  name="connection" @uml.associationEnd */
	private  ConnectionDAO connection = null;
	
	/** The conn. */
	private static Connection conn = null;
	
	/** The role list. @uml.property  name="roleList" */
	private ArrayList<String> roleList = null;
	
	/**
	 * Check whether user is in the database.
	 *
	 * @param userName the user name
	 * @return true, if successful
	 * @throws DataAccessException the data access exception
	 */
	public  boolean checkUser(String userName) throws DataAccessException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Users where user_name = '"+ userName+ "'";
		conn = this.getConnection();
		
		try {
			 stmt = conn.prepareStatement(sql);
			 rs = stmt.executeQuery();
			 if(rs.next())
			 {
				 return true;
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException (Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException (dce.getMessage());
		}finally{
			DbUtil.close(rs,stmt,conn);
		}
		return false;
	}

	/**
	 * get the connection.
	 *
	 * @return the connection
	 */
	public  Connection getConnection() {
		connection = new ConnectionDAO();
		return (connection.getConnectionObject());
	}
	
	/**
	 * Set connection.
	 *
	 * @param connection the new connection
	 * @uml.property  name="connection"
	 */

	public void setConnection(ConnectionDAO connection) {
		this.connection = connection;
	}
	
	/**
	 * Get the list of Roles.
	 *
	 * @return the roles
	 * @throws DataAccessException the data access exception
	 */
	public ArrayList<String> getRoles() throws DataAccessException
	{
		roleList = new ArrayList<String>();
		String sql = "SELECT DISTINCT role_name from User_roles";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = this.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				roleList.add(rs.getString("role_name"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException (Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException (dce.getMessage());
		}
		finally{
			DbUtil.close(rs, stmt, conn);
		}
				
		return roleList;
	}
	
	/**
	 * Add new student - Add it in three tables: users, roles, and Student record table.
	 * Make a transaction to either add it in all of them or don't do anything.
	 *
	 * @param info the info
	 * @param password the password
	 * @param role the role
	 * @throws DataAccessException the data access exception
	 */

	public void addStudent(StudentInfo info, String password, String role) throws DataAccessException {
		ConnectionDAO connDao = new ConnectionDAO();
		PreparedStatement pSU = null; //user table.
		PreparedStatement pSR = null; //role table.
		PreparedStatement pStudent = null; // for student.
		PreparedStatement pDoc = null; // for DocType
		Connection conn = null;

		try {
			if (conn == null)
				conn = connDao.getConnectionObject();
			conn.setAutoCommit(false);      //transaction block start
			pSU = conn.prepareStatement("INSERT INTO Users (user_name, user_pass) VALUES (?, md5(?))");
			if (pSU != null) {
				pSU.setString(1, info.getUserName());
				pSU.setString(2, password);
				pSU.executeUpdate();
			}
			pSR = conn.prepareStatement("INSERT INTO User_roles (user_name, role_name) VALUES (?, ?)");
			if (pSR != null) {
				pSR.setString(1, info.getUserName());
				pSR.setString(2, role);
				pSR.executeUpdate();
			}
			// Profile temp
			pDoc = conn.prepareStatement("INSERT INTO UserDoc (DocType, Description, DataStore, username) " +
					"SELECT 'jpg', 'Profile picture', DataStore, ? FROM UserDoc WHERE Description='No Image' ");
			if (pDoc != null) {
				pDoc.setString(1, info.getUserName());
				pDoc.executeUpdate();
			}
			pStudent = conn
					.prepareStatement("INSERT INTO StudentRecord (firstname, middlename, lastname, Email, AcademicLevel, EnrollmentDate, Username, FacultyAdvisor, Birthday, Birthmonth)" +
							" VALUES (?,?,?,?,?,?,?,?,?,?)");
			if (pStudent != null) {
				pStudent.setString(1, info.getFirstName());
				pStudent.setString(2, info.getMiddleName());
				pStudent.setString(3, info.getLastName());
				pStudent.setString(4, info.getEmail());
				pStudent.setString(5, info.getAcademicLevel());
				pStudent.setDate(6, new java.sql.Date(info.getEnrollmentDate().getTime()));
				pStudent.setString(7, info.getUserName());
				pStudent.setString(8, info.getAdvisor());
				pStudent.setInt(9, info.getBirthDay());
				pStudent.setInt(10, info.getBirthMonth());
				pStudent.executeUpdate();	
			}	
    		conn.commit(); //transaction block end
 		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException (Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException (dce.getMessage());
		}
		finally {
			DbUtil.close(pSU);
			DbUtil.close(pSR);
			DbUtil.close(pStudent);
			DbUtil.close(conn);
		}
	}	
	
	/**
	 * Add new faculty - Add it in three tables: users, roles, and Student record table.
	 * Make a transaction to either add it in all of them or don't do anything.
	 *
	 * @param info the info
	 * @param password the password
	 * @param role the role
	 * @throws DataAccessException the data access exception
	 */

	public void addFaculty(FacultyInfo info, String password, String role) throws DataAccessException {
		ConnectionDAO connDao = new ConnectionDAO();
		PreparedStatement pStmtUser = null; //user table.
		PreparedStatement pStmtRole = null; //role table.
		PreparedStatement pStmtFaculty = null; // for faculty table.
		PreparedStatement pDoc = null; // for DocType
		Connection conn = null;

		try {
			if (conn == null)
				conn = connDao.getConnectionObject();
			
			//if(1==1) return;
			
			conn.setAutoCommit(false);      //transaction block start
			pStmtUser = conn.prepareStatement("INSERT INTO Users (user_name, user_pass) VALUES (?, md5(?))");
			if (pStmtUser != null) {
				pStmtUser.setString(1, info.getUserName());
				pStmtUser.setString(2, password);
				pStmtUser.executeUpdate();
			}
			pStmtRole = conn.prepareStatement("INSERT INTO User_roles (user_name, role_name) VALUES (?, ?)");
			if (pStmtRole != null) {
				pStmtRole.setString(1, info.getUserName());
				pStmtRole.setString(2, role);
				pStmtRole.executeUpdate();
			}
			// Profile temp
			pDoc = conn.prepareStatement("INSERT INTO UserDoc (DocType, Description, DataStore, username) " +
					"SELECT 'jpg', 'Profile picture', DataStore, ? FROM UserDoc WHERE Description='No Image' ");
			if (pDoc != null) {
				pDoc.setString(1, info.getUserName());
				pDoc.executeUpdate();
			}
			pStmtFaculty = conn
					.prepareStatement("INSERT INTO FacultySecretaryRecord (firstname, middlename, lastname, Email, JoinedDate, Username, Affiliation, Department, Birthday, BirthMonth)" +
							" VALUES (?,?,?,?,?,?,?,?,?,?)");
			if (pStmtFaculty != null) {
				pStmtFaculty.setString(1, info.getFirstName());
				pStmtFaculty.setString(2, info.getMiddleName());
				pStmtFaculty.setString(3, info.getLastName());
				pStmtFaculty.setString(4, info.getEmail());
				pStmtFaculty.setDate(5, new java.sql.Date(info.getJoinedDate().getTime()));
				pStmtFaculty.setString(6, info.getUserName());
				pStmtFaculty.setString(7, info.getAffiliation());
				pStmtFaculty.setString(8, info.getDepartment());
				pStmtFaculty.setInt(9, info.getBirthDay());
				pStmtFaculty.setInt(10, info.getBirthMonth());
				pStmtFaculty.executeUpdate();	
			}	
    		conn.commit(); //transaction block ends
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException (Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException (dce.getMessage());
		} finally {
				DbUtil.close(pStmtUser);
				DbUtil.close(pStmtRole);
				DbUtil.close(pStmtFaculty);
				DbUtil.close(conn);
		}
	}	
	
	/**
	 * Update the student information.
	 *
	 * @param info the info
	 * @throws DataAccessException the data access exception
	 */
	
	public void updateStudentInfo(StudentInfo info) throws DataAccessException {
		ConnectionDAO connDao = new ConnectionDAO();
		PreparedStatement pSU = null; //user table.
		PreparedStatement pSR = null; //role table.
		PreparedStatement pStudent = null; // for student.
		Connection conn = null;

		try {
			if (conn == null)
				conn = connDao.getConnectionObject();
			conn.setAutoCommit(false);      //transaction block start
			
			pStudent = conn
					.prepareStatement("UPDATE StudentRecord SET firstname=?, middlename=?, lastname=?, Email=?, " +
							"HouseNum=?, Street=?, City=?, State=?, Zipcode=?, Phone=?" +
							"WHERE username=?");
			if (pStudent != null) {
				pStudent.setString(1, info.getFirstName());
				pStudent.setString(2, info.getMiddleName());
				pStudent.setString(3, info.getLastName());
				pStudent.setString(4, info.getEmail());
				pStudent.setInt(5, info.getHousenum());
				pStudent.setString(6, info.getStreet());
				pStudent.setString(7, info.getCity());
				pStudent.setString(8, info.getState());
				pStudent.setInt(9, info.getZipcode());
				pStudent.setString(10, info.getPhone());
				//pStudent.setString(11, info.getAcademicLevel());
				//pStudent.setString(12, info.getAdvisor());
				pStudent.setString(11, info.getUserName());
				pStudent.executeUpdate();	
			}	
    		conn.commit(); //transaction block end
 		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException (Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException (dce.getMessage());
		}
		finally {
			DbUtil.close(pSU);
			DbUtil.close(pSR);
			DbUtil.close(pStudent);
			DbUtil.close(conn);
		}
	}
	
	/**
	 * Update faculty info.
	 *
	 * @param info the info
	 * @throws DataAccessException the data access exception
	 */
	public void updateFacultyInfo(FacultyInfo info) throws DataAccessException {
		ConnectionDAO connDao = new ConnectionDAO();
		PreparedStatement pSU = null; //user table.
		PreparedStatement pSR = null; //role table.
		PreparedStatement pStudent = null; // for student.
		Connection conn = null;

		try {
			if (conn == null)
				conn = connDao.getConnectionObject();
			conn.setAutoCommit(false);      //transaction block start
			
			pStudent = conn
					.prepareStatement("UPDATE FacultySecretaryRecord SET firstname=?, middlename=?, lastname=?, Email=?, " +
							"HouseNum=?, Street=?, City=?, State=?, Zipcode=?, Phone=?" +
							"WHERE username=?");
			if (pStudent != null) {
				pStudent.setString(1, info.getFirstName());
				pStudent.setString(2, info.getMiddleName());
				pStudent.setString(3, info.getLastName());
				pStudent.setString(4, info.getEmail());
				pStudent.setInt(5, info.getHousenum());
				pStudent.setString(6, info.getStreet());
				pStudent.setString(7, info.getCity());
				pStudent.setString(8, info.getState());
				pStudent.setInt(9, info.getZipcode());
				pStudent.setString(10, info.getPhone());
				pStudent.setString(11, info.getUserName());
				pStudent.executeUpdate();	
			}	
    		conn.commit(); //transaction block end
 		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException (Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException (dce.getMessage());
		}
		finally {
			DbUtil.close(pSU);
			DbUtil.close(pSR);
			DbUtil.close(pStudent);
			DbUtil.close(conn);
		}
	}
	
}
