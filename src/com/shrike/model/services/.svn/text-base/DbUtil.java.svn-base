package com.shrike.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * Database utility functions.
 * 
 */
public class DbUtil {
	
	/**
	 * Close.
	 *
	 * @param psmt the psmt
	 */
	public static void close(PreparedStatement psmt){
		try {
		if (psmt != null) psmt.close();
		} catch (SQLException exe) {
			exe.printStackTrace();
		}
	}
	
	/**
	 * Close.
	 *
	 * @param conn the conn
	 */
	public static void close(Connection conn){
		try {
		if (conn != null) conn.close();
		} catch (SQLException exe) {
			exe.printStackTrace();
		}
	}	
	
	/**
	 * Close.
	 *
	 * @param rs the rs
	 */
	public static void close(ResultSet rs){
		try {
		if (rs != null) rs.close();
		} catch (SQLException exe) {
			exe.printStackTrace();
		}
	}
	
	/**
	 * Close.
	 *
	 * @param rs the rs
	 * @param psmt the psmt
	 * @param conn the conn
	 */
	public static void close(ResultSet rs, PreparedStatement psmt, Connection conn){
		try {
		if (rs != null) rs.close();
		if (psmt != null) psmt.close();
		if (conn != null) conn.close();
		} catch (SQLException exe) {
			exe.printStackTrace();
		}
	}	
	
	/**
	 * Close.
	 *
	 * @param rs the rs
	 * @param stmt the stmt
	 * @param conn the conn
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn){
		try {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
		} catch (SQLException exe) {
			exe.printStackTrace();
		}
	}	
	
	
}
