package com.shrike.model;

import java.util.Date;

import java.sql.*;
import com.shrike.model.services.ConnectionDAO;

// TODO: Auto-generated Javadoc
/**
 * Class for student information.
 * 
 *
 */
public class StudentInfo {
	
	/** The first name. @uml.property  name="firstName" */
	String firstName;
	
	/** The middle name. @uml.property  name="middleName" */
	String middleName;
	
	/** The last name. @uml.property  name="lastName" */
	String lastName;
	
	/** The email. @uml.property  name="email" */
	String email;
	
	/** The enrollment date. @uml.property  name="enrollmentDate" */
	Date enrollmentDate;
	
	/** The user name. @uml.property  name="userName" */
	String userName;
	
	/** The academic level. @uml.property  name="academicLevel" */
	String academicLevel;
	
	/** The advisor. @uml.property  name="advisor" */
	String advisor;
	
	/** The birth day. @uml.property  name="birthDay" */
	int birthDay;
	
	/** The birth month. @uml.property  name="birthMonth" */
	int birthMonth;
	
	/** The valid. @uml.property  name="valid" */
	public boolean valid;
	
	/** The housenum. @uml.property  name="housenum" */
	int housenum;
	
	/** The street. @uml.property  name="street" */
	String street;
	
	/** The city. @uml.property  name="city" */
	String city;
	
	/** The state. @uml.property  name="state" */
	String state;
	
	/** The zipcode. @uml.property  name="zipcode" */
	int zipcode;
	
	/** The phone. @uml.property  name="phone" */
	String phone;
	
	//TODO: please move this block to DAO class and fix the other references as well. - rajendra.
	/**
	 * Instantiates a new student info.
	 *
	 * @param user the user
	 */
	public StudentInfo(String user){
		try{
		ConnectionDAO cn = new ConnectionDAO();
		Connection c = cn.getConnectionObject();
		userName = user;
		Statement stmt = c.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM StudentRecord WHERE Username = \""
						+ user + "\"");

		valid = true;
		if (!rs.next()) {
			valid = false;
		}

		else {
			firstName = rs.getNString("firstname");
			academicLevel = rs.getNString("AcademicLevel");
			middleName = rs.getNString("middlename");
			if(middleName==null)
				middleName="";
			lastName =  rs.getNString("lastname");
		    email = rs.getNString("Email");
		    enrollmentDate = new java.sql.Date(rs.getDate("EnrollmentDate").getTime());		    
		    birthDay = rs.getInt("Birthday");
		    birthMonth = rs.getInt("Birthmonth");
		    advisor = rs.getNString("FacultyAdvisor");
		    housenum = rs.getInt("HouseNum");
			street = rs.getNString("Street");
			city = rs.getNString("City");
			state = rs.getNString("State");
			zipcode = rs.getInt("Zipcode");
			phone = rs.getNString("Phone");					
		}
		
		c.close();
		}catch(Exception e){
			valid = false;
		}
	}
	
	/**
	 * Instantiates a new student info.
	 */
	public StudentInfo(){
		
	}
		
	/**
	 * Gets the birth day.
	 *
	 * @return the birth day
	 * @uml.property  name="birthDay"
	 */
	public int getBirthDay() {
		return birthDay;
	}
	
	/**
	 * Sets the birth day.
	 *
	 * @param birthDay the new birth day
	 * @uml.property  name="birthDay"
	 */
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}
	
	/**
	 * Gets the birth month.
	 *
	 * @return the birth month
	 * @uml.property  name="birthMonth"
	 */
	public int getBirthMonth() {
		return birthMonth;
	}
	
	/**
	 * Sets the birth month.
	 *
	 * @param birthMonth the new birth month
	 * @uml.property  name="birthMonth"
	 */
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 * @uml.property  name="firstName"
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 * @uml.property  name="email"
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 * @uml.property  name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the enrollment date.
	 *
	 * @return the enrollment date
	 * @uml.property  name="enrollmentDate"
	 */
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	
	/**
	 * Sets the enrollment date.
	 *
	 * @param enrollmentDate the new enrollment date
	 * @uml.property  name="enrollmentDate"
	 */
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 * @uml.property  name="userName"
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 * @uml.property  name="userName"
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 * @uml.property  name="firstName"
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 * @uml.property  name="middleName"
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	/**
	 * Sets the middle name.
	 *
	 * @param middleName the new middle name
	 * @uml.property  name="middleName"
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 * @uml.property  name="lastName"
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 * @uml.property  name="lastName"
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the academic level.
	 *
	 * @return the academic level
	 * @uml.property  name="academicLevel"
	 */
	public String getAcademicLevel() {
		return academicLevel;
	}
	
	/**
	 * Sets the academic level.
	 *
	 * @param academicLevel the new academic level
	 * @uml.property  name="academicLevel"
	 */
	public void setAcademicLevel(String academicLevel) {
		this.academicLevel = academicLevel;
	}
	
	/**
	 * Gets the advisor.
	 *
	 * @return the advisor
	 * @uml.property  name="advisor"
	 */
	public String getAdvisor() {
		return advisor;
	}
	
	/**
	 * Sets the advisor.
	 *
	 * @param advisor the new advisor
	 * @uml.property  name="advisor"
	 */
	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}
	
	/**
	 * Gets the housenum.
	 *
	 * @return the housenum
	 * @uml.property  name="housenum"
	 */
	public int getHousenum() {
		return housenum;
	}

	/**
	 * Sets the housenum.
	 *
	 * @param housenum the new housenum
	 * @uml.property  name="housenum"
	 */
	public void setHousenum(int housenum) {
		this.housenum = housenum;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 * @uml.property  name="street"
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 * @uml.property  name="street"
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 * @uml.property  name="city"
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 * @uml.property  name="city"
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 * @uml.property  name="state"
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 * @uml.property  name="state"
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the zipcode.
	 *
	 * @return the zipcode
	 * @uml.property  name="zipcode"
	 */
	public int getZipcode() {
		return zipcode;
	}

	/**
	 * Sets the zipcode.
	 *
	 * @param zipcode the new zipcode
	 * @uml.property  name="zipcode"
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 * @uml.property  name="phone"
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 * @uml.property  name="phone"
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
