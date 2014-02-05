<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<body>
	<div id="HeaderPlaceHolder">
		<div class="header">

			<div class="header01">
				<div class="logo">
					<img src="./image/CSLogo2.jpg" alt="Logo"  align = "left"/>
					<div class="mcright">
						<div class="mc0202">
							<span><br>Graduate Student Progress	Tracking System for Computer Science Department</span>
						</div>
					</div>
				</div>
				<div class="hright">
					<div class="hr01">
						<div class="hr0101">
							<a href="../Web/ChangePassword.aspx">Change Password</a>
						</div>
					</div>
					<div class="hr02">&nbsp;</div>
				</div>
			</div>
			<div id="menu">
				<ul class="menu">
					<li><a href="./" class="parent"><span>Home</span></a></li>
					<li><a href="#" class="parent"><span>Profile</span></a>
						<ul>
							<li><a href="./editStudentPicture.do"><span>Edit picture</span></a></li>
							<li><a href="./edit.do?userType=Student"><span>Edit Basic-info</span></a></li>
							<li><a href="./ChangePwd.do"><span>Change password</span></a></li>
						</ul></li>
					<li><a href="#" class="parent"><span>Academic plan</span></a>
						<ul>
							<li><a href="./testCoursePlan.do"><span>Create Plan</span></a></li>
							<li><a href="./viewCoursePlan.do"><span>View Plan</span></a></li>
						<!-- 	<li><a href="./testCoursePlan.do"><span>Test Course Plan</span></a></li> -->
						</ul>
					<li>
					<li> <a href="" class="parent"><span>Courses</span></a>
						<ul>
							<li><a href="./addCourseApproval.do"><span>Add Courses</span></a></li>
							<li><a href="./addGradeApproval.do"><span>Add Grades</span></a></li>
							<li><a href="./viewCourseStatus.do"><span>View Course Status</span></a></li>
							<li><a href="./editCourses.do"><span>Edit Courses</span></a></li>
						</ul>
					</li>
						<li><a href="logout.do" class="parent"><span>Logout</span></a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
