package com.shrike.model.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionDAO.
 */
public class ConnectionDAO {
	
	/** The Constant jdbcDriver. */
	private final static String jdbcDriver = "com.mysql.jdbc.Driver";
	
	/** The driver object. */
	private static Object driverObject = null;
	
	/** The Constant url. */
	private final static String url = "jdbc:mysql://141.225.11.129:3306/";
	
	/** The Constant db. */
	private final static String db = "shrike";
	
	/** The Constant user. */
	private final static String user = "u_shrike";
	
	/** The Constant pass. */
	private final static String pass = "bea2TRIX";

	/**
	 * constructor of ConnectionDAO.
	 */
	public ConnectionDAO() {
		// Load the driver class.
		if (driverObject == null) {
			try {
				// The newInstance() call is a work around for some broken Java
				// implementations.
				driverObject = Class.forName(jdbcDriver).newInstance();
			} catch (ClassNotFoundException e) {
				// Couldn't find the driver class.
				// TODO Handle the error.
			} catch (Exception e) {
				// Other problems with loading the driver class.
				// TODO Handle the error.
			}
		}
	}

	/**
	 * Gets the connection object.
	 *
	 * @return Connection Object
	 */
	public  Connection getConnectionObject() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url + db, user, pass);
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("SQL EXCEPTION:");
				System.err.println("Error msg: " + e.getMessage());
				System.err.println("SQLSTATE: " + e.getSQLState());
				System.err.println("Error code: " + e.getErrorCode());
				e.printStackTrace();
				e = e.getNextException(); // For drivers that support chained
											// exceptions
			}
			throw new DbConnectionException(Messages.DB_CONNECTION_ERR_MSG);	
		}
		return con;
	}
}