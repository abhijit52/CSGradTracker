<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="com.shrike.model.services.StudentInfoDAO"%>
<%@page import="com.shrike.model.StudentInfo"%>
<%@page import = "com.shrike.model.CoursePlan" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.TreeMap"%>
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

<script>
function goBack()
  {
  window.history.back()
  }
</script>


<script type="text/javascript">
//http://tuljo.com/web-development/expanding-collapsing-sliding-div-jquery
$(document).ready(function(){
	$("#expanderHeadG").click(function(){
		$("#expanderContentG").slideToggle();
		if ($("#expanderSignG").text() == "+"){
			$("#expanderSignG").html("-");
		}
		else {
			$("#expanderSignG").text("+");
		}
	});
});

$(document).ready(function(){
	$("#expanderHeadA").click(function(){
		$("#expanderContentA").slideToggle();
		if ($("#expanderSignA").text() == "+"){
			$("#expanderSignA").html("-");
		}
		else {
			$("#expanderSignA").text("+");
		}
	});
});

</script>

<title>Faculty::Student details</title>
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
									<span>Student Profile</span>
								</div>
				
				<% String errorMsg = (String)request.getAttribute("error"); 
					if (errorMsg != null && errorMsg != "") {
						out.print("<label class=\"elementslabel\"><font color = \"red\">" + errorMsg + "</font></label><br>");
					} else {
				%>
				
				<label class="sectionheader" id="expanderHeadG" ><span id="expanderSignG">+</span> General </label>
				<div class ="mc0201Collapse" id = "expanderContentG">
				<%
				        StudentInfo studentInfo = (StudentInfo)request.getAttribute("student");
					  	if (studentInfo != null) {
						      out.print("<table class=\"elementstable\">");
							  out.print("<tr><td class=\"elementslabel\"> Name: </td><td><a href=\"./studentDetails.do?studentId="+studentInfo.getUserName() +"\">"+ studentInfo.getFirstName() + " " + studentInfo.getMiddleName() + " " + studentInfo.getLastName() + "</a></td></tr>");
							  out.print("<tr><td> Academic Level: </td><td>"+ studentInfo.getAcademicLevel() + "</td></tr>");
							  out.print("<tr><td> Email: </td><td>"+ studentInfo.getEmail() + "</td></tr>");
							  out.print("<tr><td> Username: </td><td>"+ studentInfo.getUserName() + "</td></tr>");
							  //out.print("<tr><td> Academic Advisor: </td><td>"+ student.getAdvisor() + "</td></tr>");
							  //out.print("<tr><td>------------</td><td>---------------</td></tr>");
							  out.print("</table>");
						}
				 %>
				 </div>
				 
			<br>
 			<label class="sectionheader" id="expanderHeadA" ><span id="expanderSignA">+</span> Academic plan </label>
 				<div class ="mc0201Collapse" id = "expanderContentA">
				<table id="tableCourse" class="elementstable">
				<tr>
					<th align="left" class="elementslabel" 
						style="font-size: large;">Sl.</th>
					<th align="left" class="elementslabel"
						style="font-size: large;">Course Code</th>
					<th align="left" class="elementslabel"
						style="font-size: large;">Course Title</th>
					<th align="left" class="elementslabel"
						style="font-size: large;">Course Credit</th>
					<th align="left" class="elementslabel"
						style="font-size: large;">Grade</th>
					<th align="left" class="elementslabel"
						style="font-size: large;">Status</th>	
				</tr>
				<!-- </table> -->

				<%
					TreeMap<String, ArrayList<CoursePlan>> wholeCoursePlan = (TreeMap<String, ArrayList<CoursePlan>>)request.getAttribute("wholeCoursePlan");
					for (String semester : wholeCoursePlan.keySet()) { 
						ArrayList<CoursePlan> cPlan = wholeCoursePlan.get(semester);
						
						
						String firstChar = semester.substring(0,1).toUpperCase();
						String rest = semester.substring(1, semester.length());
						String updated = firstChar.concat(rest);
						
						
						
						out.print("<tr colspan=\"6\"><td><strong>"+ updated+"</strong></td></tr>"); 
						%>
						<!-- <table class="elementstable"> -->
						<%
						int i = 0;
						for (CoursePlan cp : cPlan) {
								i++;
				%>
				   <tr>
					<td width="">
						<%
							out.print(i + "");
						%>
					</td>
					<td>
						<%
							out.print(cp.getCourseCode()== null ? "": cp.getCourseCode());
						%>
					</td>
					<td>
						<%
							out.print(cp.getCourseTitle()== null ? "": cp.getCourseTitle());
						%>
					</td>
					<td class="elementslabelinfo">
						<%
							out.print(cp.getCourseCredit()== null ? "": cp.getCourseCredit());
						%>
					</td>
					<td class="elementslabelinfo">
						<%
							out.print(cp.getGrade()== null ? "": cp.getGrade());
						%>
					</td>
					<td class="elementslabelinfo">
						<%
							if(cp.getCourseApproved().equals("0")){
								out.print("Course submitted for approval");
							}
							else if(cp.getCourseApproved().equals("-1")){
							out.print("Course has been disapproved");
							}
							else if(cp.getGrade() == null){
								out.print("Grade not submitted");
							}
							else if(cp.getGradeApproved().equals("0")){
								out.print("Grade submitted for approval");
							}
							else{
								out.print("Approved by faculty");
							}
							
						%>
					</td>
				</tr>
				<%
					} 
				}
				%>
				</table>
				<table>
				<tr>
					<td class="elementslabel">
						C.G.P.A.:
					</td>
					<td class="elementslabel">
						<% out.print(session.getAttribute("cgpa")== null ? "-":""+session.getAttribute("cgpa"));%>
					</td>
				</tr>
				</table>				
 				
				</div>
				
				<% } //expand/collapse area finished. %>
			<div class="mc012">
			<br>
			<br>
			<input type ="button" onClick="goBack()" value="  Back ">
			</div>
			
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

