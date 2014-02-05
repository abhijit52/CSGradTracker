<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../CSS/styles.css" rel="stylesheet" type="text/css" />
<link href="../CSS/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../JS/jquery.js"></script>
<script type="text/javascript" src="../JS/menu.js"></script>
<script type="text/javascript" src="../JS/togglemenu.js"></script>
<title>Insert User Information</title>
<script>
function validateUserForm(form) {
	
	<%-- checking the userID field--%>
	var nameString=document.forms["PasswordForm"]["UserId"].value;
	if (nameString==null || nameString=="")
	  {
	  alert("Please insert a valid user id");
	  document.getElementById('userIDfield').style.color = "#FF0000";
	  return false;
	  }
	if(nameString.length > 10)
	  {
		alert("User id can not be more than 10 characters!!");
		document.getElementById('userIDfield').style.color = "#FF0000";
		return false;
	  }
	<%-- checking the email field--%>
	var emailString = document.forms["PasswordForm"]["Email"].value;
	if(emailString!= null)
		{
	var atpos=emailString.indexOf("@");
	var dotpos=emailString.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=emailString.length)
	  {
	  alert("Not a valid e-mail address!");
	  document.getElementById('emailField').style.color = "#FF0000";
	  return false;
	  }
	
	if(emailString.length >45) 
	  {
		alert("Email address should be less than 45 characters!");
		document.getElementById('emailField').style.color = "#FF0000";
		return false;
	  }
	}
	<%-- checking Birthday --%>
	var birthDayString=Number(document.forms["PasswordForm"]["day"].value);
	if (birthDayString==null || birthDayString=="")
	  {
	  alert("Please insert a valid Birth day [1-31]");
	  document.getElementById('birthDayField').style.color = "#FF0000";
	  return false;
	  }
	 if(Math.floor(birthDayString) != birthDayString ) {
		alert("Please insert valid input");
		document.getElementById('birthDayField').style.color = "#FF0000";
		return false;
	}
	var validDay = Integer.parseInt(birthDayString); 
	if(validDay <1 || validDay > 31) 
		{
		alert("Day should be between 1 and 31");
		document.getElementById('birthDayField').style.color = "#FF0000";
		return false;
		}
	
	if(nameString.length > 11)
	  {
		alert("User id can not be more than 10 characters!!");
		document.getElementById('birthDayField').style.color = "#FF0000";
		return false;
	  }
	
	<%-- Checking Birth Month --%>
	var birthMonthString=Number(document.forms["PasswordForm"]["month"].value);
	if (birthMonthString==null || birthMonthString=="")
	  {
	  alert("Please insert a valid Birth Month [1-12]");
	  document.getElementById('birthMonthField').style.color = "#FF0000";
	  return false;
	  }
	
	var validMonth = Integer.parseInt(birthMonthString); 
	 if(validMonth != birthMonthString ) {
			alert("Please insert valid input");
			document.getElementById('birthMonthField').style.color = "#FF0000";
			return false;
		}
	if(validMonth <1 || validMonth > 12) 
		{
		alert("Month should be between 1 and 12");
		document.getElementById('birthMonthField').style.color = "#FF0000";
		return false;
		}
	if(validMonth == 2 || validMonth == 4 || validMonth == 6 || validMonth == 9 || validMonth == 11 )
		{
		  if(validDay > 30)
			  {
			  	alert("Birthday can not be more than 30 for your birth Month");
				document.getElementById('birthMonthField').style.color = "#FF0000";
				return false;
			  }
		}
	if(validMonth == 2) {
		 if(validDay > 29) {
			 alert("Birthday can not be more than 29 for your birth Month: February");
				document.getElementById('birthMonthField').style.color = "#FF0000";
				return false;
		 }
	}
	
	
	if(nameString.length > 11)
	  {
		alert("User id can not be more than 10 characters!!");
		document.getElementById('userIDfield').style.color = "#FF0000";
		return false;
	  }
	return confirm('Do you really want to submit the change?');
}
</script>
</head>
<body>
	<form action="../ForgetPasswordServlet.do" name="PasswordForm"
		onsubmit="return validateUserForm(this)" method="post">
		<div class="size">

			<%@include file="../MenuBlocks/header_logalt.jsp"%>
			<div class="content">
				<div class="mcleft">
					<div id="LeftmenuPlaceHolder">
						<div class="login-caption">
							<a href="../">Return to Login</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="content">
				<div class="mcontent">
					<div class="mcright">
						<div id="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span><%=session.getAttribute("pass") == null ? "" : session
					.getAttribute("pass")%></span>
									<br> <span>Please provide the following
										information:</span>
								</div>
								<div class="elementslabelinfo" style="width: auto;">
									* indicates mandatory field to be filled out <br> <br>
								</div>
								<div id="userIDfield" class="elementslabel">
									UserID:* <input type="text" name="UserId" id="nameField1"
										value="">
								</div>
								<br>
								<div id="emailField" class="elementslabel">
									Email:* <input type="text" name="Email" id="emailField1"
										value="">
								</div>
								<br>
								<div id="birthDayField" class="elementslabel">
									Birth Day[1-31]:* <input type="text" name="day" id="bField1"
										value="">
								</div>
								<br>
								<div id="birthMonthField" class="elementslabel">
									Birth Month [1-12]:* <input type="text" name="month"
										id="bField2" value="">
								</div>
								<br> <br>

								<div>
									<input type="submit" value="Submit"> <br> <input
										type="reset" value="Clear"> <br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%@include file="../MenuBlocks/footer_log.jsp"%>
		</div>
	</form>
</body>
</html>