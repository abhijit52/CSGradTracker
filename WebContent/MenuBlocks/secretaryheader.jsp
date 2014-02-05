<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<body>
	<div id="HeaderPlaceHolder">
		<div class="header">

			<div class="header01">
				<div class="logo">
					<img src="./image/CSLogo2.jpg" alt="Logo" align = "left"/>
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
					<li><a href="./" class="parent"><span>Home</span>
					</a>
					</li>
					<li><a href="#" class="parent"><span>Profile</span>
					</a>
						<ul>
							<li><a href="./editSecretaryPicture.do"><span>Edit picture</span>
							</a>
							</li>
							<li><a href="./editInfoSecretary.do?userType=Secretary"><span>Edit Basic-info</span>
							</a>
							</li>
							<li><a href="./ChangePwd.do"><span>Change password</span>
							</a>
							</li>
						</ul></li>
					<li><a href="#" class="parent"><span>Users</span>
					</a>
						<ul>
							<li><a href="./addUser.do?userType=Student"><span>Add
										Student</span>
							</a>
							</li>
							<li><a href="./addFaculty.do?userType=Faculty"><span>Add Faculty</span>
							</a>
							</li>
							<li><a href="./edit.do"><span>Edit Information</span>
							</a>
							</li>
							<!-- 							<li><a href=""><span>Search</span></a></li> -->
							<li><a href="./deactivateUser.do"><span>DeactivateUser</span>
							</a>
							</li>
							<li><a href="./find.do?k=v"><span>Search</span>
							</a>
							</li>
						</ul>
					<li>
					<li><a href="logout.do" class="parent"><span>Logout</span>
					</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
