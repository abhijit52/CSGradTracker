<%@page import="com.shrike.model.services.UserAddDAO"%>
<%@page import ="com.shrike.model.services.FacultyInfoDAO" %>
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
<title>Faculty - Update Information</title>
<script>
function validateAddUserForm(form)
{
	//var userType = document.getElementById('userType').value;
	var firstName = document.forms["adduser"]["firstName"].value;
	var lastName = document.forms["adduser"]["lastName"].value;
	var email = document.forms["adduser"]["email"].value;
	var old_val = document.forms["adduser"]["housenum"].value;
	var old_val1 = document.forms["adduser"]["zipcode"].value;
	
	if(firstName == ""
			|| lastName == "" || email == "" 
			|| old_val == "" || old_val1 == ""){ 
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
// Quang numberic checking!!!
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

			<%@include file ="../MenuBlocks/secretaryheader.jsp" %>
			
			<div class="content">
			<div class="mcleft">
				<div id="LeftmenuPlaceHolder">
					<div class="elementslabel">
						<%@include file="../MenuBlocks/facultyLeftMenu.jsp"%>
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
				
				<form name="adduser" action="editInfoSecretary.do"
					onsubmit="return validateAddUserForm(this)" method="post">
					<input type="hidden" id="userType" name ="userType" value="<% out.print(userType); %>">
					<% 
					boolean error = false;
					FacultyInfo facultyInfo = (FacultyInfo)request.getAttribute("facultyInfo");					
					%>
					<table class="elementstable">
						<tr>
							<td>Login Name</td>
							<td><input type="text" name="loginName" class="elementstextbox" size="30" maxlength = "10" value = "<%=request.getRemoteUser() == null ? "" : request.getRemoteUser()%>"disabled></input></td>

						</tr>
						<tr>
							<td>First Name</td>
							<td><input type="text" name="firstName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getFirstName()!=null? facultyInfo.getFirstName() : ""); %>"></input></td>
						</tr>
						<tr>
							<td>Middle Name</td>
							<td><input type="text" name="middleName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getMiddleName()!=null ? facultyInfo.getMiddleName() : ""); %>" ></input></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><input type="text" name="lastName" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getLastName()!=null ? facultyInfo.getLastName() : ""); %>" /></td>
						</tr>
						<tr>
							<td>Street#</td>
							<td><input type="text" name="housenum" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getHousenum()!=0 ? facultyInfo.getHousenum() : ""); %>"/> 							
							</td>							
						</tr>
						<tr>
							<td>Street Name</td>
							<td><input type="text" name="street" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getStreet()!=null ? facultyInfo.getStreet() : ""); %>"/>							
							</td>							
						</tr>
						<tr>
							<td>City</td>
							<td><input type="text" name="city" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getCity()!=null ? facultyInfo.getCity() : ""); %>"/></td>
						</tr>
						<tr>
							<td>State</td>
							<td><input type="text" name="state" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getState()!=null ? facultyInfo.getState() : ""); %>"/></td>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td><input type="text" name="zipcode" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getZipcode()!=0 ? facultyInfo.getZipcode() : ""); %>"/></td>
						</tr>
						<tr>
							<td>Phone</td>
							<td><input type="text" name="phone" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getPhone()!=null ? facultyInfo.getPhone() : ""); %>" /></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><input type="text" name="email" class="elementstextbox" size="30" maxlength = "30" value = "<% out.print(facultyInfo.getEmail()!=null ? facultyInfo.getEmail() : ""); %>" /></td>
						</tr>
						<tr>
						<td>Joined Date</td>
						<td>
						<%
						DateFormat  formatter = new SimpleDateFormat("MMM dd,yyyy");

						String dateStr = formatter.format(facultyInfo.getJoinDate());
						
						%>
						
						<input type="text" id = "enrollmentDate" name="enrollmentDate" class="elementstextbox" size="30" maxlength = "10"  value = "<% out.print(dateStr!=null ? dateStr : ""); %>" disabled><img src="images/calendar.gif" width="16" height="16" border="0" alt="Pick a date">
						<!-- <a href="javascript:NewCal('enrollmentDate','mmddyyyy')"><img src="images/calendar.gif" width="16" height="16" border="0" alt="Pick a date"></a> -->
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

			<%@include file="../MenuBlocks/facultyFooter.jsp"%>
		</div>
</body>
</html>