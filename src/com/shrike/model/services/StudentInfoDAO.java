package com.shrike.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shrike.model.services.ConnectionDAO;
import com.shrike.model.StudentInfo;

// TODO: Auto-generated Javadoc
/**
 * Class for CRUD operations on StudentRecord table.
 * 
 */
public class StudentInfoDAO {

	/**
	 * Get student details from the student table.
	 *
	 * @param studentId the student id
	 * @return the info
	 */

	public StudentInfo getInfo(String studentId) {
		StudentInfo student = null;
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			student = new StudentInfo();
			ConnectionDAO connDao = new ConnectionDAO();
			conn = connDao.getConnectionObject();
			student.setUserName(studentId);
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("SELECT * FROM StudentRecord WHERE Username = \""
							+ studentId + "\"");

			student.valid = true;
			if (!rs.next()) {
				student.valid = false;
			} else {
				student.setFirstName(rs.getNString("firstname"));
				student.setAcademicLevel(rs.getNString("AcademicLevel"));
				student.setMiddleName(rs.getNString("middlename"));
				if (student.getMiddleName() == null)
					student.setMiddleName("");
				student.setLastName(rs.getNString("lastname"));
				student.setEmail(rs.getNString("Email"));
				student.setEnrollmentDate(rs.getDate("EnrollmentDate"));
				student.setBirthDay(rs.getInt("Birthday"));
				student.setBirthMonth(rs.getInt("Birthmonth"));
				student.setHousenum(rs.getInt("HouseNum"));
				student.setStreet(rs.getNString("Street"));
				student.setCity(rs.getNString("City"));
				student.setState(rs.getNString("State"));
				student.setZipcode(rs.getInt("Zipcode"));
				student.setPhone(rs.getNString("Phone"));
			}
		} catch (Exception e) {
			student.valid = false;
		} finally {
			DbUtil.close(rs,stmt,conn);
		}
		return student;
	}

	/**
	 * Adds the student.
	 *
	 * @param info the info
	 * @return true, if successful
	 */
	public boolean addStudent(StudentInfo info) {
		ConnectionDAO connDao = new ConnectionDAO();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			if (conn == null)
				conn = connDao.getConnectionObject();
			preparedStatement = conn
					.prepareStatement("INSERT INTO StudentRecord (firstname, middlename, lastname, Email, AcademicLevel, EnrollmentDate, Username, FacultyAdvisor, Birthday, Birthmonth)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)");
			if (preparedStatement != null) {
				preparedStatement.setString(1, info.getFirstName());
				preparedStatement.setString(2, info.getMiddleName());
				preparedStatement.setString(3, info.getLastName());
				preparedStatement.setString(4, info.getEmail());
				preparedStatement.setString(5, info.getAcademicLevel());
				preparedStatement.setDate(6, new java.sql.Date(info
						.getEnrollmentDate().getTime()));
				preparedStatement.setString(7, info.getUserName());
				preparedStatement.setString(8, info.getAdvisor());
				preparedStatement.setInt(9, info.getBirthDay());
				preparedStatement.setInt(10, info.getBirthMonth());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * Find the students - matching the given query words pattern. For any one
	 * of the given words (i.e. Doing ORING).
	 *
	 * @param pattern the pattern
	 * @return the array list
	 */
	public ArrayList<StudentInfo> findStudents(String pattern) {
		String[] words = pattern.trim().split("\\s+");
		ArrayList<StudentInfo> students = new ArrayList<StudentInfo>();
		ConnectionDAO connDao = new ConnectionDAO();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		StudentInfo studentInfo = null;

		// TODO: what is the limit? show all with pagination or ?? now limiting
		// to 20.
		String query = "SELECT firstname, middlename, lastname, Username, Email, AcademicLevel,FacultyAdvisor from StudentRecord"
				+ " WHERE Concat(firstname, ' ',middlename,'', lastname, '',Email,' ', Username) like \"%"
				+ words[0] + "%\"";
		// for each remaining keywords, add the ORing condition.
		for (int i = 1; i < words.length; i++) {
			query += " OR Concat(firstname, ' ',middlename,'', lastname, '',Email,' ', Username) like \"%"
					+ words[i] + "%\"";
		}
		query += " LIMIT 20";

		try {
			conn = connDao.getConnectionObject();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				studentInfo = new StudentInfo();
				studentInfo.setFirstName(rs.getString("firstname"));
				studentInfo.setMiddleName(rs.getString("middlename"));
				studentInfo.setLastName(rs.getString("lastname"));
				studentInfo.setUserName(rs.getString("Username"));
				studentInfo.setAdvisor(rs.getString("FacultyAdvisor"));
				studentInfo.setEmail(rs.getString("Email"));
				studentInfo.setAcademicLevel(rs.getString("AcademicLevel"));
				students.add(studentInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return students;
	}

	/**
	 * Given the faculty username, get the list of Advisees.
	 *
	 * @param facultyUserName the faculty user name
	 * @return the advisee list
	 * @throws DataAccessException the data access exception
	 */
	public ArrayList<StudentInfo> getAdviseeList(String facultyUserName)
			throws DataAccessException {
		ArrayList<StudentInfo> students = new ArrayList<StudentInfo>();
		ConnectionDAO connDao = new ConnectionDAO();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StudentInfo studentInfo = null;

		String query = "SELECT firstname, middlename, lastname, Username, Email, AcademicLevel,FacultyAdvisor from StudentRecord"
				+ " WHERE FacultyAdvisor = ?";

		try {
			conn = connDao.getConnectionObject();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, facultyUserName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				studentInfo = new StudentInfo();
				studentInfo.setFirstName(rs.getString("firstname"));
				studentInfo.setMiddleName(rs.getString("middlename"));
				studentInfo.setLastName(rs.getString("lastname"));
				studentInfo.setUserName(rs.getString("Username"));
				studentInfo.setAdvisor(rs.getString("FacultyAdvisor"));
				studentInfo.setEmail(rs.getString("Email"));
				studentInfo.setAcademicLevel(rs.getString("AcademicLevel"));
				students.add(studentInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(Messages.DB_OPERATION_ERR_MSG);
		} catch (DbConnectionException dce) {
			throw new DataAccessException(dce.getMessage());
		} finally {
			DbUtil.close(rs, stmt, conn);
		}
		return students;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws DataAccessException the data access exception
	 */
	public static void main(String[] args) throws DataAccessException {
		StudentInfoDAO daoObj = new StudentInfoDAO();
		ArrayList<StudentInfo> students = daoObj.getAdviseeList("sdf");
	}

}
