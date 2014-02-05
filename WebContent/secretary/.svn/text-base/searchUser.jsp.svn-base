<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="com.shrike.model.FacultyInfo"%>
<%@page import="com.shrike.model.StudentInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/menu.js"></script>
<script type="text/javascript" src="JS/togglemenu.js"></script>
<script type="text/javascript" src="JS/datetimepicker.js"></script>
<title>Search User</title>

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
	<div class="size">

			<%@include file="../MenuBlocks/secretaryheader.jsp"%>

			<div class="content">
			
			
		    <div class="mcright">
				<div id="MainPlaceHolder">
					<div class="mc0201">
			    		<div class="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span>Search User</span>
								</div>
			
			
			
			<form id ="searchForm" action="./find.do" method="post" onsubmit="return validateSearchForm()">
				<input type="text" name = "searchQuery" class="elementstextbox" size="50" maxlength = "50" value="<% String queryText = (String)request.getAttribute("searchQuery"); out.print(queryText != null ? queryText : ""); %>"></input>	
				<input type ="submit" value="Search"></input>
			</form>	
				<br></br>
				<%
				ArrayList<StudentInfo> studentList = (ArrayList<StudentInfo>)request.getAttribute("searchStudentList");
				if(studentList!=null)
				{
					out.print("Student Record<br><br>");
					out.print("<label class=\"elementslabel\">" + studentList.size() + "  <i>result for "+  (String)request.getAttribute("inputQuery") + "</i></label><br>"); 
				  	if (studentList.size() == 0) {
				  		//out.print("<label class=\"elementslabel\">" + studentList.size() + "  <i>result for "+  (String)request.getAttribute("inputQuery") + "</i></label><br>"); 
					  out.println("No results found for your query!<br><br><br>");
				  	} else {
					  int counter = 1;
					  out.print("<table class=\"elementstable\">");
					  for (StudentInfo student : studentList) {
						  out.print("<tr><td class=\"elementslabel\"> Name: </td><td>"+ student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName() + "</td></tr>");
						  out.print("<tr><td> Academic Level: </td><td>"+ student.getAcademicLevel() + "</td></tr>");
						  out.print("<tr><td> Email: </td><td>"+ student.getEmail() + "</td></tr>");
						  out.print("<tr><td> Academic Advisor: </td><td>"+ student.getAdvisor() + "</td></tr>");
						  out.print("<tr><td>------------</td><td>---------------</td></tr>");
					  }
					  out.print("</table>");
				  }
				}
				
				ArrayList<FacultyInfo> facultyList = (ArrayList<FacultyInfo>)request.getAttribute("searchFacultyList");
				if(facultyList!=null)
				{
					out.print("Faculty Record<br><br>");
					out.print("<label class=\"elementstable\">" + facultyList.size() + "  <i>result for "+  (String)request.getAttribute("inputQuery") + "</i></label><br>"); 
					if(facultyList.size() == 0)
					{
						//out.print("<label class=\"elementslabel\">" + facultyList.size() + "  <i>result for "+  (String)request.getAttribute("inputQuery") + "</i></label><br>"); 
						out.print("No results found for your query!<br><br>");
					}
					else
					{
						int count = 1;
						out.print("<table class=\"elementstable\">");
						for(FacultyInfo faculty: facultyList)
						{
							out.print("<tr><td class=\"elementslabel \">Name</td><td>"+ faculty.getAffiliation() + "  " +faculty.getFirstName()+ " " + faculty.getLastName() +"</td></tr>");
							out.print("<tr> <td>Email Address: </td><td><i>"+ faculty.getEmail()+"</i></td></tr>");
							out.print("<tr><td>Department:</td><td>"+ faculty.getDepartment()+"</td></tr>");
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
			<%@include file="../MenuBlocks/studentfooter.jsp"%>
		</div>
</body>
</html>