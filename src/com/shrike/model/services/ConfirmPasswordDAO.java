package com.shrike.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shrike.model.ForgotUserInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfirmPasswordDAO.
 */
public class ConfirmPasswordDAO {
	
	/** The connectiondao. @uml.property  name="connectiondao" @uml.associationEnd  multiplicity="(1 1)" */
	private ConnectionDAO connectiondao;
	
	/** The con. @uml.property  name="con" */
	private Connection con = null;
	
	/** The statement. @uml.property  name="statement" */
	private Statement statement = null;
	
	/** The prepared statement. @uml.property  name="preparedStatement" */
	private PreparedStatement preparedStatement = null;
	
	/** The result set. @uml.property  name="resultSet" */
	private ResultSet resultSet = null;
	
	/** The user info. @uml.property  name="userInfo" @uml.associationEnd */
	private ForgotUserInfo userInfo = null;

	/** The given password. @uml.property  name="givenPassword" */
	private String givenPassword = null;

	/**
	 * Instantiates a new confirm password dao.
	 */
	public ConfirmPasswordDAO() {
		connectiondao = new ConnectionDAO();
	}

	/**
	 * Insert password.
	 *
	 * @param fUserInfo the f user info
	 * @return the string
	 */
	public String insertPassword(ForgotUserInfo fUserInfo) {
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
					.prepareStatement("update Users set user_pass = MD5(?) where user_name = ?");

			if (preparedStatement != null) {
				preparedStatement.setString(1, fUserInfo.getPasswordEntered());
				preparedStatement.setString(2, fUserInfo.getUserID());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.close();
				return "ERROR";
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		return "OK";
	}
}
