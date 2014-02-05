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
public class FacultyInfo {
	
	/** The first name. @uml.property  name="firstName" */
	String firstName;
	
	/** The middle name. @uml.property  name="middleName" */
	String middleName;
	
	/** The last name. @uml.property  name="lastName" */
	String lastName;
	
	/** The email. @uml.property  name="email" */
	String email;
	
	/** The Affiliation. @uml.property  name="affiliation" */
	String Affiliation;
	
	/** The join date. @uml.property  name="joinDate" */
	Date joinDate;
	
	/** The user name. @uml.property  name="userName" */
	String userName;
	
	/** The Department. @uml.property  name="department" */
	String Department;
	
	/** The birth day. @uml.property  name="birthDay" */
	int birthDay;
	
	/** The birth month. @uml.property  name="birthMonth" */
	int birthMonth;
	
	/** The valid. @uml.property  name="valid" */
	public boolean valid;
	
	//additional info
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
	
	/**
	 * Instantiates a new faculty info.
	 *
	 * @param user the user
	 */
	public FacultyInfo(String user){
		try{
		ConnectionDAO cn = new ConnectionDAO();
		Connection c = cn.getConnectionObject();
		userName = user;
		Statement stmt = c.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM FacultySecretaryRecord WHERE Username = \""
						+ user + "\"");

		valid = true;
		if (!rs.next()) {
			valid = false;
		}

		else {
			firstName = rs.getNString("firstname");
			Affiliation = rs.getNString("Affiliation");
			Department = rs.getNString("Department");
			middleName = rs.getNString("middlename");
			if(middleName==null)
				middleName="";
			lastName =  rs.getNString("lastname");
		    email = rs.getNString("Email");
		    joinDate = rs.getDate("JoinedDate");
		    birthDay = rs.getInt("BirthDay");
		    birthMonth = rs.getInt("BirthMonth");
		    //additional info
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
	 * Instantiates a new faculty info.
	 */
	public FacultyInfo(){
		
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
	 * Gets the joined date.
	 *
	 * @return the joined date
	 */
	public Date getJoinedDate() {
		return joinDate;
	}
	
	/**
	 * Sets the joined date.
	 *
	 * @param jDate the new joined date
	 */
	public void setJoinedDate(Date jDate) {
		this.joinDate = jDate;
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
	 * Gets the affiliation.
	 *
	 * @return the affiliation
	 * @uml.property  name="affiliation"
	 */
	public String getAffiliation() {
		return Affiliation;
	}
	
	/**
	 * Sets the affiliation.
	 *
	 * @param aff the new affiliation
	 * @uml.property  name="affiliation"
	 */
	public void setAffiliation(String aff) {
		this.Affiliation = aff;
	}
	
	/**
	 * Gets the department.
	 *
	 * @return the department
	 * @uml.property  name="department"
	 */
	public String getDepartment() {
		return Department;
	}
	
	/**
	 * Sets the department.
	 *
	 * @param dep the new department
	 * @uml.property  name="department"
	 */
	public void setDepartment(String dep) {
		this.Department = dep;
	}
	
	/**
	 * Gets the join date.
	 *
	 * @return the join date
	 * @uml.property  name="joinDate"
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * Sets the join date.
	 *
	 * @param joinDate the new join date
	 * @uml.property  name="joinDate"
	 */
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 * @uml.property  name="valid"
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Sets the valid.
	 *
	 * @param valid the new valid
	 * @uml.property  name="valid"
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
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
