<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="com.shrike.model.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >

<html>

<head>
<link href="./CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="./CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./JS/jquery.js"></script>
<script type="text/javascript" src="./JS/menu.js"></script>
<script type="text/javascript" src="./JS/togglemenu.js"></script>

<title>Faculty - Home</title>

</head>

<body>
	<form id="form1">

		<div class="size">

			<%@include file="../MenuBlocks/facultyheader.jsp"%>

			<div class="content">
				<div class="mcontent">
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">

								<%
									FacultyInfo faculty = new FacultyInfo(request.getRemoteUser());
									out.print("<br><img src=\"./tools/image.jsp?user="
											+ request.getRemoteUser()
											+ "\" height=\"200\" width=\"150\"><br>");
									String name = faculty.getFirstName() + " "
											+ faculty.getMiddleName() + " " + faculty.getLastName();
									out.print(name + ",<br>" + faculty.getAffiliation() + "<br>"
											+ faculty.getDepartment());
								%>
							</div>
						</div>
					</div>


					<div class="mcright">
						<div id="MainPlaceHolder">
							<div class="mc0201">
								<div class="MainPlaceHolder">
									<div class="mc0201">
										<div class="sectionheader">
											<span> 
										<%
 	ConnectionDAO cn = new ConnectionDAO();
 	Connection c = cn.getConnectionObject();
 	String username = request.getRemoteUser();
 	Statement stmt = c.createStatement();
 
 	String strQuery = "SELECT * FROM UserDoc WHERE username=\""
 			+ username.trim() + "\" AND DocType <> \"jpg\"";
 	ResultSet rs2 = stmt.executeQuery(strQuery);
 	c.close();
 	
 %> 
 <br>
 </form>
 <b>Add Description for new reference</b><br>
 <form name="addref" action="addRef.do" method="post"> 
 <input name="desc" type="text" size="80"><br>
<!--  file later -->
 <input name="add" type="submit" value="Submit"><br>
 </form>
 
</span>
 
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%@include file="../MenuBlocks/facultyFooter.jsp"%>
		</div>


</body>
</html>

