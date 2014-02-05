<%@page import="java.util.ArrayList"%>
<%@page import="com.shrike.model.services.*"%>
<%@page  import="com.shrike.model.*" %>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html >

<html>

<head>
<link href="CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/menu.js"></script>
<script type="text/javascript" src="JS/togglemenu.js"></script>
<script>
	function validate()
	{
		var len;
		if(document.getElementById("psize").value=="")
		{
			len = 1;
		}
		else
		{
			len  = parseInt(document.getElementById("psize").value);
		}
		
		for(var i=1; i<=len; i++) {
			var gradeVar = "grade" + i;
			grade = document.getElementById(gradeVar).value;
			grade = grade.toLowerCase();
			
			if(grade==""){
				alert("Please enter grade");
				document.getElementById(gradeVar).focus();
				return false;
			}			
			else if (grade == "a" || grade == "a-" || grade == "a+"){
				return true;
			}
			else if (grade == "b" || grade == "b-" || grade == "b+"){
				return true;
			}
			else if (grade == "c" || grade == "c-" || grade == "c+"){
				return true;
			}
			else if (grade == "d" || grade == "d-" || grade == "d+"){
				return true;
			}
			else if (grade == "f"){
				return true;
			}
			else{
				alert("Please enter a valid grade");
				document.getElementById(gradeVar).focus();
				return false;
			}
		}
		
		return true;
	  }
	
	function listChanged(){
		document.getElementById("listItem").value = document.getElementById("list").value;
		
		if(document.getElementById("list").value == "course"){
			document.getElementById("gradeheader").style.display = "none";
		}
		else{
			document.getElementById("gradeheader").style.display = "block";
		}
	}
	
	function showResult(){
		if(document.getElementById("list").value == "course"){
			document.getElementById("gradeheader").style.display = "none";
		}
		else{
			document.getElementById("gradeheader").style.display = "block";
		}
		
		var msg = document.getElementById("message").value;
		
		if(msg != ""){
			alert(msg);
		}
	}
</script>

<title>
	Faculty - Approve Requests
</title>

</head>

<body onload="showResult();">
	<form id="fromCourseGradeApproval" method="post" action="approveCourseGrade.do">
		<div class="size">
			
			<%@include file ="../MenuBlocks/facultyheader.jsp" %>

			<div class="content">
				<div class="mcontent">
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">
								<%@include file="../MenuBlocks/facultyLeftMenu.jsp"%>

								<input type="hidden" name="message" id="message"
									value="<%=session.getAttribute("message") == null ? "" : session.getAttribute("message")%>">
							</div>
						</div>
					</div>

					<div class="mcright">
						<div id="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span>Course/Grade: </span>
								</div>
								<table id="selectTable">
									<tr>
										<td class="elementslabel">Session:</td>
										<td class="elementsdropdownlist">
											<select id="list" name="list" onchange="listChanged();">
												<option value="course">Course</option>
												<option value="grade">Grade</option>
											</select>
										</td>
									</tr>

									<tr>
										<td><input type="Submit" value="Show" id="btnshow" name="btnshow" /></td>
									</tr>
								</table>
								
								<input type="hidden" id="listItem"
									value="<%= session.getAttribute("list") == null? "null": session.getAttribute("list")%>">
								<script type="text/javascript">
										document.getElementById("list").value = document.getElementById("listItem").value;
								</script>
								<br>


								<!-- course choice table -->
								<span class="elementslabel" style="font-size: large;">Waiting for Approval: </span> 
								<input type="hidden" name="psize" id="psize"
									value="<%=session.getAttribute("number") == null ? "0" : session.getAttribute("number")%>">

								<table id="tableCourse" class="elementstable">
									<tr>
										<th align="left" class="elementslabel" style="max-width: 60px;"
											style="font-size: large;">Hold</th>
										<th align="left" class="elementslabel" style="max-width: 60px;"
											style="font-size: large;">Approve</th>
										<th align="left" class="elementslabel" style="max-width: 60px;"
											style="font-size: large;">Reject</th>	
										<th align="left" class="elementslabel"
											style="font-size: large;">Student Name</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Code</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Title</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Credit</th>
										<th id="gradeheader" align="left" class="elementslabel"
											style="font-size: large;">Grade</th>
									</tr>


									<%
										ArrayList<CourseApprove> cPlan = (ArrayList<CourseApprove>) session.getAttribute("coursePlan");
										int i = 0;

										if (cPlan != null) {
											for (CourseApprove cp : cPlan) {
												i++;
									%>
									<tr>
										<td class="elementslabelinfo" style="max-width: 60px;">
											<input type="radio" name="<%out.print("" + i);%>" checked="yes" value="<%out.print(i + "Hold");%>">
										</td>
										<td class="elementslabelinfo" style="max-width: 60px;">
											<input type="radio" name="<%out.print("" + i);%>" value="<%out.print(i + "Approve");%>">
										</td>
										<td class="elementslabelinfo" style="max-width: 60px;">
											<input type="radio" name="<%out.print("" + i);%>" value="<%out.print(i + "Reject");%>">
										</td>
										
										<td class="elementslabelinfo"> <% out.print(cp.getUserName()); %> 
											<input type="hidden" name="<%out.print("name" + i);%>" id="<%out.print("name" + i);%>"
												value="<%out.print(cp.getUserName());%>">
										</td>
										<td class="elementslabelinfo"> <%out.print(cp.getCourseCode());%>
											<input type="hidden" name="<%out.print("code" + i);%>" id="<%out.print("code" + i);%>"
												value="<%out.print(cp.getCourseCode());%>">
										</td>
										<td class="elementslabelinfo"> <%out.print(cp.getCourseTitle());%> 
											<input type="hidden" name="<%out.print("title" + i);%>" id="<%out.print("title" + i);%>"
												value="<%out.print(cp.getCourseTitle());%>">
										</td>
										<td class="elementslabelinfo"> <%out.print(cp.getCourseCredit());%> 
											<input type="hidden" name="<%out.print("credit" + i);%>" id="<%out.print("credit" + i);%>"
												value="<%out.print(cp.getCourseCredit());%>">
										</td>
										<td class="elementslabelinfo"> <%out.print(cp.getGrade());%> 
											<input type="hidden" name="<%out.print("grade" + i);%>" id="<%out.print("grade" + i);%>"
												value="<%out.print(cp.getGrade());%>">
										</td>
										<td>
											<input type="hidden" name="<%out.print("id" + i);%>"
											id="<%out.print("id" + i);%>" value="<%out.print(cp.getId());%>">
										</td>
										
									</tr>
									<%
											}
										}
									%>
								</table>

								<table>
									<tr>
										<td><input type="Submit" value="Submit" id="btnsubmit" name="btnsubmit" /></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%@include file="../MenuBlocks/facultyFooter.jsp"%>
		</div>
	</form>
	
</body>
</html>

