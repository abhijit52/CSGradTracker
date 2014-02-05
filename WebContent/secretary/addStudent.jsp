<%@page import="com.shrike.model.services.UserAddDAO"%>
<%@page import ="com.shrike.model.services.FacultyInfoDAO" %>
<%@page import ="com.shrike.model.StudentInfo" %>
<%@page import="java.util.ArrayList"%>
<%@page import ="java.text.DateFormat" %>
<%@page import = "java.text.SimpleDateFormat" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html >

<html>
<head>
<link href="CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/menu.js"></script>
<script type="text/javascript" src="JS/togglemenu.js"></script>
<script type="text/javascript" src="JS/datetimepicker.js"></script>
<!-- http://www.javascriptkit.com/script/script2/tengcalendar.shtml -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>SHRIKE::Secretary::Add new Student</title>
<script>
function validateAddUserForm()
{
	var userType = document.getElementById('userType').value;
	var userName = document.forms["adduser"]["loginName"].value;
	var password = document.forms["adduser"]["password"].value;
	var retypePassword = document.forms["adduser"]["retypePassword"].value;
	var advisor = "";
	if (userType == "Student"){
		advisor = document.forms["adduser"]["advisor"].value;
	}
	var firstName = document.forms["adduser"]["firstName"].value;
	var lastName = document.forms["adduser"]["lastName"].value;
	var email = document.forms["adduser"]["email"].value;
	var enrollDate = document.forms["adduser"]["enrollmentDate"].value;

	if(userName == "" || password == "" 
			|| retypePassword == "" || firstName == ""
			|| lastName == "" || email == "" 
			|| enrollDate == ""
			|| (userType == "Student" && advisor == "")){ 
		alert("One or more field (s) is not filled up. Please check!");
		return false;
	}
	
	var validUserName = /^[a-zA-Z0-9]*$/.test(userName);
    if (!validUserName) {
    	alert("Username can't contain space and special characters!");
    	return false;
    }
	
	if (password != retypePassword) {
		alert("Passwords didn't match!");
		return false;
	}
	
	if (!validateEmail(email)) {
		alert("Please enter valid email address!");
		return false;
	}
	return true;
}
//stackoverflow.com/questions/46155/validate-email-address-in-javascript
function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 


</script>

</head>
<body>
		<div class="size">

			<%@include file="../MenuBlocks/secretaryheader.jsp"%>
			
			<div class="content">
			
			<div class="mcright">
				<div id="MainPlaceHolder">
					<div class="mc0201">
			    		<div class="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<% String userType = (String)request.getAttribute("userType"); %>
									<span>Add new <% out.print(userType); %></span>
								</div>
				<!-- <div class="input-form">-->
				
				<form name="adduser" action="addUser.do"
					onsubmit="return validateAddUserForm()" method="post">
					<input type="hidden" id="userType" name ="userType" value="<% out.print(userType); %>">
					<% 
					boolean error = false;
					StudentInfo studentInfo = null;
					String status = (String)request.getAttribute("error");
					if (status != null && status != "") {
						error = true;
						out.print("<label class=\"elementslabel\"><font color = \"red\">" + status + "</font></label><br>");
						studentInfo = (StudentInfo)request.getAttribute("studentInfo");
					}
					else {
						status = (String)request.getAttribute("message");
						if (status != null && status != "")
						out.print("<label class=\"elementslabel\"><font color = \"green\"><b>" + status + "</b></font></label><br>");
					}
					%>
					<table class="elementstable">
						<tr>
							<td>Login Name</td>
							<td><input type="text" name="loginName" class="elementstextbox" size="30" maxlength = "10" value = "<% out.print(error ? studentInfo.getUserName() : ""); %>"></input></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" class="elementstextbox" size="30" maxlength = "8" /></td>
						</tr>
						<tr>
							<td>Re-Type Password</td>
							<td><input type="password" name="retypePassword" class="elementstextbox" size="30" maxlength = "8"/></td>
						</tr>
						<tr>
							<td>First Name</td>
							<td><input type="text" name="firstName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(error ? studentInfo.getFirstName() : ""); %>"></input></td>
						</tr>
						<tr>
							<td>Middle Name</td>
							<td><input type="text" name="middleName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(error ? studentInfo.getMiddleName() : ""); %>" ></input></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><input type="text" name="lastName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(error ? studentInfo.getLastName() : ""); %>" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><input type="text" name="email" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(error ? studentInfo.getEmail() : ""); %>" /></td>
						</tr>
						<tr>
						<td>Enrollment Date</td>
						<td>
						<%
						SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");

						String dateStr = "xxxx";
						if (error) {
							dateStr = formatter.format(studentInfo.getEnrollmentDate());
						}
						%>
						
						<input type="text" id = "enrollmentDate" name="enrollmentDate" class="elementstextbox" size="30" maxlength = "10"  value = "<% out.print(error ? dateStr : ""); %>" readonly>
						<a href="javascript:NewCal('enrollmentDate','mmddyyyy')"><img src="images/calendar.gif" width="16" height="16" border="0" alt="Pick a date"></a>
						</td>
						</tr>
						<tr>
						<td>Advisor</td>
						<td>
					   <select name="advisor">
						<% 
							// facultyMap - list of <faculty id, name> pairs
							Map<String, String> facultyMap = (Map<String, String>)request.getAttribute("facultyMap");
							String perviouslySelected = null;
							if (error) {
								perviouslySelected = studentInfo.getAdvisor();
							}
							for (String facultyId : facultyMap.keySet()) {
								// if error, select the previously selected item.
								if (error) {
									String selected = "";
									if (facultyId.equalsIgnoreCase(perviouslySelected)) {
										selected = "selected"; 
									}
									out.print("<option value=\"" +facultyId + "\" " + selected + ">" + facultyMap.get(facultyId) + "</option>");	
								} else {
									out.print("<option value=\"" +facultyId + "\">" + facultyMap.get(facultyId) + "</option>");
								}
							}	
						%>
						</select>
						</td>
						</tr>	
						<tr>
						<td>Academic Level : </td>
						<td><select name="academicLevel">
								<% 
									// if error, select the previously selected item.
									if (error) {
										perviouslySelected = studentInfo.getAcademicLevel();
										 if ("MS".equalsIgnoreCase(perviouslySelected)) {
											 out.print("<option value=\"MS\" selected> MS Computer</option>");
										 } else {
											 out.print("<option value=\"MS\"> MS Computer</option>");
										 }
										 if ("AppliedScience".equalsIgnoreCase(perviouslySelected)) {
											 out.print("<option value=\"AppliedScience\" selected> Applied Comp Science</option>");
										 } else {
											 out.print("<option value=\"AppliedScience\"> Applied Comp Science</option>");
										 }
										 
										 if ("PhD".equalsIgnoreCase(perviouslySelected)) {
											 out.print("<option value=\"PhD\" selected> CS PhD </option>");
										 } else {
											 out.print("<option value=\"PhD\"> CS PhD </option>");
										 }
									}
									else 
									 { %>
										<option value="MS"> MS Computer</option>
										<option value="AppliedScience"> Applied Comp Science</option>
										<option value="PhD">CS PhD</option>
									<%} %>	
							</select></td>
						</tr>		
					</table>

					<input type="submit" value="Submit" />
					<br>
				</form>
			</div>
			</div>
			</div>
			</div>
			</div>
			</div> <!-- content div ends -->

			<%@include file="../MenuBlocks/studentfooter.jsp"%>
		</div>
</body>
</html>