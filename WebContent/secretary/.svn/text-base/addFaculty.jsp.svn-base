<%@page import ="com.shrike.model.FacultyInfo" %>
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
<title>SHRIKE::Secretary::Add new Faculty</title>
<script>
function validateAddUserForm()
{
	var userName = document.forms["adduser"]["loginName"].value;
	var password = document.forms["adduser"]["password"].value;
	var retypePassword = document.forms["adduser"]["retypePassword"].value;
	var firstName = document.forms["adduser"]["firstName"].value;
	var lastName = document.forms["adduser"]["lastName"].value;
	var email = document.forms["adduser"]["email"].value;
	var joinDate = document.forms["adduser"]["joinDate"].value;

	if(userName == "" || password == "" 
			|| retypePassword == "" || firstName == ""
			|| lastName == "" || email == "" 
			|| joinDate == ""){ 
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
				
				<form name="adduser" action="addFaculty.do"
					onsubmit="return validateAddUserForm()" method="post">
					<input type="hidden" id="userType" name ="userType" value="<% out.print(userType); %>">
					<% 
					boolean error = false;
					FacultyInfo facultyInfo = null;
					Map<String, String> validationErrors = null;
					String status = (String)request.getAttribute("error");
					if (status != null && status != "") {
						error = true;
						out.print("<label class=\"elementslabel\"><font color = \"red\">" + status + "</font></label><br>");
						facultyInfo = (FacultyInfo)request.getAttribute("facultyInfo");
						validationErrors = (Map<String, String>)request.getAttribute("validationErrors");
						if (validationErrors != null) {
						for (String key : validationErrors.keySet()){
							out.print("<label class=\"elementslabel\"><font color = \"red\">" + validationErrors.get(key) + "</font></label><br>");
							}
						}
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
							<td><input type="text" name="loginName" class="elementstextbox" size="30" maxlength = "10" value = "<% out.print(error ? facultyInfo.getUserName() : ""); %>"></input></td>
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
							<td><input type="text" name="firstName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(error ? facultyInfo.getFirstName() : ""); %>"></input></td>
						</tr>
						<tr>
							<td>Middle Name</td>
							<td><input type="text" name="middleName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(error ? facultyInfo.getMiddleName() : ""); %>" ></input></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><input type="text" name="lastName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(error ? facultyInfo.getLastName() : ""); %>" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><input type="text" name="email" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(error ? facultyInfo.getEmail() : ""); %>" /></td>
						</tr>
						<tr>
						<td>Join Date</td>
						<td>
						<%
						SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");

						String dateStr = "xxxx";
						if (error) {
							dateStr = formatter.format(facultyInfo.getJoinedDate());
						}
						%>
						
						<input type="text" id = "joinDate" name="joinDate" class="elementstextbox" size="30" maxlength = "10"  value = "<% out.print(error ? dateStr : ""); %>" readonly>
						<a href="javascript:NewCal('joinDate','mmddyyyy')"><img src="images/calendar.gif" width="16" height="16" border="0" alt="Pick a date"></a>
						</td>
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