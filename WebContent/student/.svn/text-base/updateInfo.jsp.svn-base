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
<title>Student - Update Information</title>
<script>
function validateAddUserForm(form)
{
	var userType = document.getElementById('userType').value;
	var advisor = "";
	if (userType == "Student"){
		advisor = document.forms["adduser"]["advisor"].value;
	}
	var firstName = document.forms["adduser"]["firstName"].value;
	var lastName = document.forms["adduser"]["lastName"].value;
	var email = document.forms["adduser"]["email"].value;
	var enrollDate = document.forms["adduser"]["enrollmentDate"].value;
	var old_val = document.forms["adduser"]["housenum"].value;
	var old_val1 = document.forms["adduser"]["zipcode"].value;
	
	if(firstName == ""
			|| lastName == "" || email == "" 
			|| enrollDate == "" ||old_val == "" || old_val1==""
			|| (userType == "Student" && advisor == "")){ 
		alert("One or more field (s) is not filled up. Please check!");
		return false;
	}
	
	if (!validateEmail(email)) {
		alert("Please enter valid email address!");
		return false;
	}
	if(!validateNumeric(old_val)) return false;
	if(!validateNumeric(old_val1)) return false;
	return confirm('Do you really want to submit the change?');
}

//Quang numberic checking!!!
function validateNumeric(old_val) {
  var new_val = old_val.replace(/^\s+|\s+$/g,"");
  var validChars = '0123456789.'; 

  for(var i = 0; i < new_val.length; i++){ 
    if(validChars.indexOf(new_val.charAt(i)) == -1){
      alert('Please enter valid number');
      return false; 
    } 
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

			<%@include file="../MenuBlocks/studentheader.jsp"%>
			
			<div class="content">
			<div class="mcleft">
				<div id="LeftmenuPlaceHolder">
					<div class="elementslabel">
						<%@include file="../MenuBlocks/studentLeftMenu.jsp"%>
					</div>
				</div>
			</div>
			<div class="mcright">
				<div id="MainPlaceHolder">
					<div class="mc0201">
			    		<div class="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<% String userType = (String)request.getAttribute("userType"); %>
									<span>Update Information</span>
								</div>
				<!-- <div class="input-form">-->
				
				<form name="adduser" action="edit.do"
					onsubmit="return validateAddUserForm(this)" method="post">
					<input type="hidden" id="userType" name ="userType" value="<% out.print(userType); %>">
					<% 
					boolean error = false;
					StudentInfo studentInfo = (StudentInfo)request.getAttribute("studentInfo");					
					%>
					<table class="elementstable">
						<tr>
							<td>Login Name</td>
							<td><input type="text" name="loginName" class="elementstextbox" size="30" maxlength = "10" value = "<%=request.getRemoteUser() == null ? "" : request.getRemoteUser()%>"disabled></input></td>

						</tr>
						<tr>
							<td>First Name</td>
							<td><input type="text" name="firstName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getFirstName()!=null? studentInfo.getFirstName() : ""); %>"></input></td>
						</tr>
						<tr>
							<td>Middle Name</td>
							<td><input type="text" name="middleName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getMiddleName()!=null ? studentInfo.getMiddleName() : ""); %>" ></input></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><input type="text" name="lastName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getLastName()!=null ? studentInfo.getLastName() : ""); %>" /></td>
						</tr>
						<tr>
							<td>Street#</td>
							<td><input type="text" name="housenum" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getHousenum()!=0 ? studentInfo.getHousenum() : ""); %>"/> 							
							</td>							
						</tr>
						<tr>
							<td>Street Name</td>
							<td><input type="text" name="street" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getStreet()!=null ? studentInfo.getStreet() : ""); %>"/>							
							</td>							
						</tr>
						<tr>
							<td>City</td>
							<td><input type="text" name="city" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getCity()!=null ? studentInfo.getCity() : ""); %>"/></td>
						</tr>
						<tr>
							<td>State</td>
							<td><input type="text" name="state" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getState()!=null ? studentInfo.getState() : ""); %>"/></td>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td><input type="text" name="zipcode" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getZipcode()!=0 ? studentInfo.getZipcode() : ""); %>"/></td>
						</tr>
						<tr>
							<td>Phone</td>
							<td><input type="text" name="phone" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getPhone()!=null ? studentInfo.getPhone() : ""); %>" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><input type="text" name="email" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(studentInfo.getEmail()!=null ? studentInfo.getEmail() : ""); %>" /></td>
						</tr>
						<tr>
						<td>Enrollment Date</td>
						<td>
						<%
						DateFormat  formatter = new SimpleDateFormat("MMM dd,yyyy");

						String dateStr = formatter.format(studentInfo.getEnrollmentDate());
						
						%>
						
						<input type="text" id = "enrollmentDate" name="enrollmentDate" class="elementstextbox" size="30" maxlength = "10"  value = "<% out.print(dateStr!=null ? dateStr : ""); %>" disabled><img src="images/calendar.gif" width="16" height="16" border="0" alt="Pick a date">
						<!-- <a href="javascript:NewCal('enrollmentDate','mmddyyyy')"><img src="images/calendar.gif" width="16" height="16" border="0" alt="Pick a date"></a> -->
						</td>
						</tr>
						<tr>
						<td>Advisor</td>
						<td>
					   <select name="advisor" disabled>
						<% 
							// facultyMap - list of <faculty id, name> pairs
							Map<String, String> facultyMap = (Map<String, String>)request.getAttribute("facultyMap");
							String perviouslySelected = null;
							perviouslySelected = studentInfo.getAdvisor();
							
							for (String facultyId : facultyMap.keySet()) {
								// if error, select the previously selected item.
								{
									String selected = "";
									if (facultyId.equalsIgnoreCase(perviouslySelected)) {
										selected = "selected"; 
									}
									out.print("<option value=\"" +facultyId + "\" " + selected + ">" + facultyMap.get(facultyId) + "</option>");	
								} 
							}	
						%>
						</select>
						</td>
						</tr>	
						<tr>
						<td>Academic Level </td>
						<td><select name="academicLevel" disabled>
								<% 
									// if error, select the previously selected item.
									
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
								%>	
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