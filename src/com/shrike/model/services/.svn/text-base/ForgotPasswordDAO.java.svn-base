package com.shrike.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shrike.model.ForgotUserInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class ForgotPasswordDAO.
 */
public class ForgotPasswordDAO {
	
	/** The connectiondao. @uml.property  name="connectiondao" @uml.associationEnd  multiplicity="(1 1)" */
	private ConnectionDAO connectiondao;
	
	/** The con. @uml.property  name="con" */
	private Connection con = null;
	
	/** The prepared statement. @uml.property  name="preparedStatement" */
	private PreparedStatement preparedStatement = null;
	
	/** The result set. @uml.property  name="resultSet" */
	private ResultSet resultSet = null;
	
	/** The user verified. @uml.property  name="userVerified" */
	private boolean userVerified = false;

	/**
	 * Instantiates a new forgot password dao.
	 */
	public ForgotPasswordDAO() {
		connectiondao = new ConnectionDAO();
	}

	/**
	 * Verify fields.
	 *
	 * @param fUserInfo the f user info
	 * @return true, if successful
	 */
	public boolean VerifyFields(ForgotUserInfo fUserInfo) {

		String roleUser = "";
		int birthDay = 0;
		int birthMonth = 0;
		String emailAddress = "";
		try {
			if (con == null)
				con = connectiondao.getConnectionObject();
			else if (con.isClosed())
				con = connectiondao.getConnectionObject();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedStatement = con
					.prepareStatement("SELECT role_name  from User_roles where user_name = ? ");
			preparedStatement.setString(1, fUserInfo.getUserID());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			roleUser = resultSet.getString(1);
			if (roleUser == null || roleUser == "") {
				userVerified = false;
			} else {
				if (roleUser == "student") {
					// read the Student Record Table to verify the provided
					// information
					preparedStatement = con
							.prepareStatement("SELECT Birthday, Birthmonth, Email from StudentRecord where username = ? ");
					preparedStatement.setString(1, fUserInfo.getUserID());
					resultSet = preparedStatement.executeQuery();
					resultSet.next();
				}

				else {
					// read the Faculty Secretary Record to verify the provided
					// information
					preparedStatement = con
							.prepareStatement("SELECT BirthDay, BirthMonth, Email from FacultySecretaryRecord where username = ? ");
					preparedStatement.setString(1, fUserInfo.getUserID());
					resultSet = preparedStatement.executeQuery();
					resultSet.next();
				}
				birthDay = Integer.parseInt(resultSet.getString(1));
				birthMonth = Integer.parseInt(resultSet.getString(2));
				emailAddress = resultSet.getString(3);

				if (birthDay == fUserInfo.getBirthDay()
						&& birthMonth == fUserInfo.getBirthMonth()
						&& emailAddress.equals(fUserInfo.getEmailAddress())) {
					userVerified = true;
				} else
					userVerified = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userVerified;
	}

}
