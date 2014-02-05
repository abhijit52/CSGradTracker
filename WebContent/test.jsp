<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>

<link href="CSS/menu.css" rel="stylesheet" type="text/css" />
<link href="demo1.css?v=1.0.1" type="text/css" rel="stylesheet" />
<script src="res/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="jquery.exposure.js?v=1.0.1" type="text/javascript"></script>

<title>Welcome To CSGradTracker</title>

<script>
	function showResult() {
		var msg = document.getElementById("message").value;
		if (msg != "") {
			alert(msg);
		}
	}
</script>
</head>

<body onload="showResult();">
	<div class="size">

		<%@include file="MenuBlocks/header_log.jsp"%>
		
		<div class="content">
			<div class="mcleft">
				<div id="LeftmenuPlaceHolder">
					<div class='login-box'>
						<div class='login-area'>
							<div class="login-caption">Login Information</div>

							<form method="post" action="j_security_check">
								<div class="Leftmenu">
									<div class="login-info">UserName:</div>
									<input type="text" id="id-login-user" class="login-input"
										name="j_username" />
								</div>
								<div class="Leftmenu">
									<div class="login-info">Password:</div>
									<input type="password" id="id-login-password"
										class="login-input" name="j_password" />
								</div>

								<div class="loginbutton">
									<input type="submit" value="Login" class="login-btn" /><br></br>
								</div>
							</form>
						</div>

						<div class='forgot-password'>
							<a href="ForgotPassword/forgetPassword.jsp">Forgot Password?</a>
						</div>
					</div>
				</div>
			</div>

			<input type="hidden" name="message" id="message"
				value="<%=session.getAttribute("confirm") == null ? "" : session.getAttribute("confirm")%>">

			<div class="mcright">
				<div id="MainPlaceHolder">
					<div class="mc0201">
						<div class="sectionheader" style="padding-left: 150px;">
						<!-- 	<span>Graduate Student Progress	Tracking System for Computer Science Department</span> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="./MenuBlocks/footer.jsp"%>
	</div>

</body>
</html>
