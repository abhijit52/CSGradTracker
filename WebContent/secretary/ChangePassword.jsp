<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/menu.js"></script>
<script type="text/javascript" src="JS/togglemenu.js"></script>
<script type="text/javascript" src="JS/datetimepicker.js"></script>
<title>Secretary Home-Change Password</title>
<script>
function validateForm2(form) {
	
	var passString = document.forms["NewPasswordForm"]["newPasswordId"].value;
	document.getElementById('passwordIDField').style.color = "#FFFFFF";
	var confirmPassString = document.forms["NewPasswordForm"]["confirmPasswordID"].value;
	document.getElementById('confirmPasswordlField').style.color = "#FFFFFF";
	
		if (passString == null || passString == "") {
			alert("Please insert your new password");
			document.getElementById('passwordIDField').style.color = "#FF0000";
			return false;
		}
		if (passString.length < 5) {
			alert("Please provide password more than 5 characters to make it strong");
			document.getElementById('passwordIDField').style.color = "#FF0000";
			return false;
		}
<%--  checking confirm password field--%>
	    if (confirmPassString == null || confirmPassString == "") {
			alert("Please enter your password again to confirm");
			document.getElementById('confirmPasswordlField').style.color = "#FF0000";
			return false;
		}
		if (confirmPassString.length != passString.length) {
			alert("Please enter your password again to confirm");
			document.getElementById('confirmPasswordlField').style.color = "#FF0000";
			document.getElementById('passwordIDField').style.color = "#FF0000";
			return false;
		}
		if(confirmPassString != passString )
			{
			alert("The fields do not match. Please insert the new password correctly");
			document.getElementById('passwordIDField').style.color = "#FF0000";
			document.getElementById('confirmPasswordlField').style.color = "#FF0000";
			return false;
			}
		return confirm('Do you really want to submit the change?');
}
</script>
</head>
<body>
	<form action="ChangePwd.do" name="NewPasswordForm"
		onsubmit="return validateForm2(this)" method="post">
		<div class="size">

			<%@include file="../MenuBlocks/secretaryheader.jsp"%>

			<div class="content">
				<div class="mcontent">
					<%--<%@include file="../MenuBlocks/leftmenu.jsp"%> --%>
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">
								<%
									ConnectionDAO cn = new ConnectionDAO();
									Connection c = cn.getConnectionObject();
									String username = request.getRemoteUser();
									Statement stmt = c.createStatement();
									String name="";
									ResultSet rs = stmt
											.executeQuery("SELECT * FROM FacultySecretaryRecord WHERE Username = \""
													+ username.trim() + "\"");

									if (!rs.next()) {
										out.print("Invalid username: " + username);
									}

									else {
										name = rs.getNString("firstname");
										String level = rs.getNString("Affiliation");
										String mn;
										if(rs.getNString("middlename")==null){
											mn="";
										}else{
											mn = rs.getNString("middlename");
										}
										String fullname = name + " " + mn
												+ " " + rs.getNString("lastname");
										out.print("<br><img src=\"./tools/image.jsp?user="+username.trim()+"\" height=\"200\" width=\"150\"><br>" + fullname
												+ "," + level);
									}
									rs.close();
									stmt.close();
									c.close();
								%>
							</div>
						</div>
					</div>
					<div class="mcright">
						<div id="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span> <%=session.getAttribute("confirm") == null ? "" : session
					.getAttribute("confirm")%><br> </span>
								</div>
								<div class="elementslabelinfo" style="width: auto;">
									* indicates mandatory field to be filled <br>
								</div>
								<div id="userIDField" class="elementslabel">
									UserId: <input type="text" name="newUserID" id="pass0"
										value="<%=request.getRemoteUser() == null ? "" : request.getRemoteUser()%>"
										disabled>
								</div>
								<br>

								<div id="passwordIDField" class="elementslabel">
									Password:* <input type="password" name="newPasswordId"
										id="pass1" value="">
								</div>
								<br>

								<div id="confirmPasswordlField" class="elementslabel">
									Confirm Password:* <input type="password"
										name="confirmPasswordID" id="pass2" value="">
								</div>
								<br>
								<div>
									<input type="hidden" name="message" id="message"
										value="<%=session.getAttribute("pass") == null ? "" : session
					.getAttribute("pass")%>">
								</div>

								<br>
								<div>
									<input type="submit" value="Submit"> <br> <input
										type="reset" value="Clear">
								</div>
								<br>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="../MenuBlocks/studentfooter.jsp"%>
		</div>
	</form>
</body>
</html>