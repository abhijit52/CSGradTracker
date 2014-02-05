package com.shrike.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.shrike.model.FacultyInfo;


// TODO: Auto-generated Javadoc
/**
 * Performs CRUD operations for faculty related information. 
 *
 */
public class FacultyInfoDAO {
	
	/**
	 * Get a map containing faculty id and name (first name + middle name + last name).
	 *
	 * @return the faculty list
	 */
	public Map<String, String> getFacultyList()
	{
		Map<String, String> facultyMap = new HashMap<String, String>();
		String sql = "SELECT username, firstname, middlename, lastname from FacultySecretaryRecord " +
				"INNER JOIN User_roles ON FacultySecretaryRecord.username=User_roles.user_name WHERE role_name=\"faculty\"";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ConnectionDAO connectiondao = new ConnectionDAO();		
		Connection conn = connectiondao.getConnectionObject();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String userName = rs.getString("username");
				facultyMap.put(userName, rs.getString("firstname") + " " + (rs.getString("middlename") == null? "" : rs.getString("middlename") + " ")  + rs.getString("lastname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
				try {
					if(rs!=null)
					rs.close();
					if(stmt!=null)
						stmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
				
		return facultyMap;
	}
	

	/**
	 * Main function.
	 *
	 * @param args the arguments
	 */
	public static void main(String []args) {
		FacultyInfoDAO dao = new FacultyInfoDAO();
		//Map<String, String> map = dao.getFacultyList();
		//System.out.println("Total faculties: " + map.size());
		//for (String key : map.keySet()) {
			//System.out.println(key + " : " + map.get(key));
		//}
		
		
		
		ArrayList<FacultyInfo> facList = dao.findFaculties("gup");
		
		for(FacultyInfo f: facList)
		{
//			System.out.println(f.getAffiliation());
//			System.out.println(f.getFirstName());
//			System.out.println(f.getLastName());
//			System.out.println(f.getEmail());
//			System.out.println(f.getDepartment());
			//System.out.println(f.getAffiliation());
		}
	}
	
	
	/**
	 * Get the list of faculties.
	 *
	 * @param inputPattern the input pattern
	 * @return the array list
	 */
	
	public ArrayList<FacultyInfo> findFaculties(String inputPattern)
	{
		ArrayList<FacultyInfo> faculties = new ArrayList<FacultyInfo>();
		String[] inputWord = inputPattern.trim().split("\\s+");
		ConnectionDAO connDAO = new ConnectionDAO();
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		FacultyInfo faculty = null;
		
		String query = "SELECT firstname, middlename, lastname, Email, Department,Affiliation from FacultySecretaryRecord";
		query += "  INNER JOIN User_roles ON FacultySecretaryRecord.username=User_roles.user_name WHERE role_name=\"faculty\"";
		
		query += "   and Concat(firstname,' ',lastname,' ', Email,' ',username) like \"%"+ inputWord[0] +"%\"";
		
		for (int i = 1; i < inputWord.length; i++){
			query += " OR Concat(firstname,' ', lastname, '',Email,' ', Username) like \"%"+ inputWord[i] +"%\"";
		}
		
		
		
		//System.out.println("Query:"+query);
		
		try {
			conn = connDAO.getConnectionObject();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				faculty = new FacultyInfo();
				faculty.setFirstName(rs.getString("firstname"));
				faculty.setMiddleName(rs.getString("middlename"));
				faculty.setLastName(rs.getString("lastname"));
				faculty.setEmail(rs.getString("Email"));
				faculty.setDepartment(rs.getString("Department"));
				faculty.setAffiliation(rs.getString("Affiliation"));
				faculties.add(faculty);
			}
		} catch (SQLException e) {
			//TODO: will come back to this with a common exception handling strategy. - raj.
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
		
		return faculties;
		
	}
	
}
