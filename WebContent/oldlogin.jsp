<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Welcome To CSGradTracker</title>
<link href="CSS/login.css" type="text/css" rel="stylesheet"></link>

</head>
<body>
	<div class='page-wrapper'>
		<div class='logo'>The Computer Science Graduate Student Progress
			Tracking System.</div>

		<div class='nav-bar'></div>

		<div class='content'>
			<div class='login-box'>
				<div class='login-area'>
					<div class="login-caption">Login Information</div>

					<form method="post" action="j_security_check">
						<div class="login-user">
							<div class="login-input-userinfo">UserName:</div>
							<input type="text" id="id-login-user" class="login-input"
								name="j_username" />
						</div>
						<div class="user-info">
							<div class="login-input-userinfo">Password:</div>
							<input type="password" id="id-login-password" class="login-input"
								name="j_password" />
						</div>
						<div class="submit">
							<input type="submit" value="Login" class="login-btn" /><br></br>
						</div>

					</form>

				</div>

				<div class='forgot-password'>
					<a href="reset.jsp">Forgot Password?</a>
				</div>
			</div>

			<div class='login-extra'>The Users can now access
				shrikeGraduateStudentTracker.</div>
		</div>

		<div class=''></div>

	</div>
</body>
</html>