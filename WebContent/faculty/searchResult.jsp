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

<title>Faculty::Search Student</title>
<script type="text/javascript">
function validateSearchForm()
{
	var query = document.forms["searchForm"]["searchQuery"].value;
    query = query.trim();
	if (query == "") {
		return false;
	}
	return true;
}
</script>

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
									<span>Student Search</span>
								</div>
			
			
			
			<form id ="searchForm" action="./search.do" method="post" onsubmit="return validateSearchForm()">
				<input type="text" name = "searchQuery" class="elementstextbox" size="30" maxlength = "50" value="<% String queryText = (String)request.getAttribute("searchQuery"); out.print(queryText != null ? queryText : ""); %>">	
				<input type ="submit" value="Search">
			</form>	
				<br>
				<%
				      ArrayList<StudentInfo> students = (ArrayList<StudentInfo>)request.getAttribute("searchResult");
					  if (students != null) {
				      	out.print("<label class=\"elementslabel\">" + students.size() + "  <i>result for "+  (String)request.getAttribute("searchQuery") + "</i></label><br>"); 
					  	if (students.size() > 0) {
						  int counter = 1;
						  out.print("<table class=\"elementstable\">");
						  for (StudentInfo student : students) {
							  out.print("<tr><td class=\"elementslabel\"> Name: </td><td><a href=\"./studentDetails.do?studentId="+student.getUserName() +"\">"+ student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName() + "</a></td></tr>");
							  out.print("<tr><td> Academic Level: </td><td>"+ student.getAcademicLevel() + "</td></tr>");
							  out.print("<tr><td> Email: </td><td>"+ student.getEmail() + "</td></tr>");
							  out.print("<tr><td> Username: </td><td>"+ student.getUserName() + "</td></tr>");
							  //out.print("<tr><td> Academic Advisor: </td><td>"+ student.getAdvisor() + "</td></tr>");
							  out.print("<tr><td>------------</td><td>---------------</td></tr>");
						  }
						  out.print("</table>");
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

