<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="com.shrike.model.services.StudentInfoDAO"%>
<%@page import="com.shrike.model.StudentInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
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

<title>Faculty::List of Advisee</title>
</head>

<body>
	<%
		if (request.isUserInRole("student")) {
			response.sendRedirect("../student/student_home.jsp");
			return;
		}
		if (request.isUserInRole("secretary")) {
			response.sendRedirect("../secretary/secretary_home.jsp");
			return;
		}
	%>

		<div class="size">

			<%@include file="../MenuBlocks/facultyheader.jsp"%>

			<div class="content">
			<div class="mcleft">
				<div id="LeftmenuPlaceHolder">
					<div class="elementslabel">
						<%-- <%@include file="../MenuBlocks/facultyLeftMenu.jsp"%> --%>
					</div>
				</div>
			</div>
		    <div class="mcright">
				<div id="MainPlaceHolder">
					<div class="mc0201">
			    		<div class="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span>Advisees</span>
								</div>
				<%
				String errorMsg = (String)request.getAttribute("error");
				if (errorMsg != null) {
					out.print("<label class=\"elementslabel\"><font color = \"red\">" + errorMsg + "</font></label><br>");
				} else {
				      ArrayList<StudentInfo> students = (ArrayList<StudentInfo>)request.getAttribute("adviseeList");
					  if (students != null) {
					  	if (students.size() > 0) {
						  int counter = 1;
						  out.print("<table class=\"elementstable\">");
						  for (StudentInfo student : students) {
							  out.print("<tr><td>"+ counter + ":</td><td><a href=\"./studentDetails.do?studentId="+student.getUserName() +"\">"+ student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName() + "</a></td></tr>");
							  counter++;
						  }
						  out.print("</table>");
					  } else {
						  out.print("<label class=\"elementslabel\"><font color = \"black\">No advisee!</font></label><br>");
					  }
					}
				}	  
				 %>
			</div>
			
			</div>
			</div>
			</div>
			</div>
			</div> <!--  Content div -->
			<%@include file="../MenuBlocks/facultyFooter.jsp"%>
		</div>
</body>
</html>

