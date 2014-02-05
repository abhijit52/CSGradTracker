<%@page import="com.shrike.model.StudentInfo"%>
<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
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

<title>Student - Home</title>

</head>

<body>
<%-- 	<%if(request.isUserInRole("faculty"))  --%>
// {
// 	response.sendRedirect("../faculty/faculty_home.jsp");
// 	return;
// }
// if(request.isUserInRole("secretary"))
// {
// 	response.sendRedirect("../secretary/secretary_home.jsp");
// 	return;
// }

<%-- %> --%>

	<form id="form1">

		<div class="size">

<%-- 			<%@include file="../MenuBlocks/studentheader.jsp"%> --%>

			<div class="content">
				<div class="mcontent">
					<%-- 					<%@include file="../MenuBlocks/leftmenu.jsp"%> --%>
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">
<%-- 								<%@include file ="../MenuBlocks/studentLeftMenu.jsp" %> --%>

 								<% 
 									 
								
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
											String user = request.getParameter("student");
											StudentInfo student = new StudentInfo(user); 
			 								out.print("<br><img src=\"./tools/image.jsp?user="+user+"\" height=\"200\" width=\"150\"><br>"); 
			 								String name = student.getFirstName()+" "+student.getMiddleName()+" "+student.getLastName(); 
			 								out.print(name+",<br>"+student.getAcademicLevel()+" Student,<br>E-mail: "+student.getEmail()+"<br>Advisor: "+student.getAdvisor());
											%>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			
		</div>
	</form>
</body>
</html>

