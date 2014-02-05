<%@page import="java.util.ArrayList"%>
<%@page import="com.shrike.model.services.*"%>
<%@page  import="com.shrike.model.*" %>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html >

<html>

<head>
<link href="./CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="./CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./JS/jquery.js"></script>
<script type="text/javascript" src="./JS/togglemenu.js"></script>

<script type="text/javascript">

function btnUpateDeleteSelectedClick(action) {
	var selectedRows = "";
	var courseCount =  document.forms["formCoursePlanEdit"]["courseCount"].value;
	for (var rowNum = 1; rowNum <= courseCount; rowNum++) {
		var checked = document.forms["formCoursePlanEdit"]["chk"+rowNum].checked;
		if (checked) {
			selectedRows += " " + rowNum;
		}
	}
	
	if (selectedRows == "") {
		alert("Please select at least one course to submit the request!");
		return false;
	}
	document.forms["formCoursePlanEdit"]["selectedRows"].value = selectedRows;	
	document.forms["formCoursePlanEdit"]["formAction"].value = action;
	document.forms["formCoursePlanEdit"].submit();
   return true;	
}

</script>




<title>
	Student - View Course Plan
</title>

</head>

<body>
		<div class="size">
			
			<%@include file ="../MenuBlocks/studentheader.jsp" %>

			<div class="content">
				<div class="mcontent">
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">
								<%@include file="../MenuBlocks/studentLeftMenu.jsp"%>
							</div>
						</div>
					</div>

					<div class="mcright">
						<div id="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span>View Course Plan</span>
								</div>
								<form id="fromAddCoursePlan" method="post" action="viewCoursePlan.do">
								<input type="hidden" id="formAction" name="formAction" value="viewPlan">
								<table id="selectTable">
									<tr>
										<td class="elementslabel">Session:</td>
										<td class="elementsdropdownlist">
											<select id="session" name="session">
												<option value="spring">Spring</option>
												<option value="fall">Fall</option>
											</select>
										</td>
									</tr>

									<tr>
										<td align="left" class="elementslabel"
											style="vertical-align: top">Year:</td>
										<td class="elementsdropdownlist">
											<select id="year" name="year">
												<option value="2013">2013</option>
												<option value="2014">2014</option>
												<option value="2015">2015</option>
												<option value="2016">2016</option>
												<option value="2017">2017</option>
											</select>
										</td>
									</tr>

									<tr>
										<td class="elementsbutton" id="submit"><input
											type="submit" value="View" /></td>
									</tr>
								</table>
								</form> <!-- View form submit ends here -->
								
								<!-- Show Status block -->
								<br>
								<%
									String error = (String)request.getAttribute("error");
								    if (error != null && error != "") {
										out.print("<label class=\"elementslabel\"><font color = \"red\">" + error + "</font></label><br>");
								    }
									String message = (String)request.getAttribute("message");
								    if (message != null && message != "") {
										out.print("<label class=\"elementslabel\"><font color = \"green\">" + message + "</font></label><br>");
								    }
								%>
									<%
										ArrayList<CoursePlan> cPlan = (ArrayList<CoursePlan>) request.getAttribute("coursePlan");
										int i = 0;

										if (cPlan != null) {
									%>		
								<!-- course choice table -->
								<form id="formCoursePlanEdit" method="post" action="viewCoursePlan.do">
								<input type="hidden" id="formAction" name="formAction" value="">								
								<input type="hidden" id="selectedRows" name="selectedRows" value="">
								<input type="hidden" id="session" name="session" value="<% out.print(request.getAttribute("session"));%>">
								<input type="hidden" id="year" name="year" value="<% out.print(request.getAttribute("year")); %>">
								
								<span style="font-size: large;">Course Plan (<% out.print((String)request.getAttribute("session") + " " +(String)request.getAttribute("year")); %>): </span> 
								<table id="tableCourse" class="elementstable">
									<tr>
										<th align="left" class="elementslabel" width="4px"
											style="font-size: large;"></th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Code</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Title</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Credit</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Comment</th>
									</tr>
									<%
									   for (CoursePlan cp : cPlan) {
												i++;
									%>
									<tr>
										<td class="elementslabelinfo">
											<input type = "checkbox" name = "<% out.print("chk"+i); %>" id = "<% out.print("chk"+i); %>" value="<% out.print(cp.getCourseCode()); %>">
										</td>
										<td class="elementslabelinfo">
											<%
												out.print(cp.getCourseCode());
											%>
										</td>
										<td class="elementslabelinfo">
											<%
												out.print(cp.getCourseTitle());
											%>
										</td>
										<td class="elementslabelinfo">
											<%
												out.print(cp.getCourseCredit());
											%>
										</td>
										<td class="elementslabelinfo">
											<input type="text" name="comment<% out.print(i); %>" id="comment<% out.print(i); %>" value="<% out.print(cp.getComment()); %>" size="20" maxlength="50">
												
										</td>
									</tr>
									<%
										}
									%>
									</table>
									<input type="hidden" name="courseCount" id="courseCount" value="<% out.print(cPlan.size()); %>">
									<% if (i > 0) { %>
										<input type="button" value = "Delete Selected" onClick="btnUpateDeleteSelectedClick('delete');">
										<input type="button" value = "Update comments" onClick="btnUpateDeleteSelectedClick('update');">
									
									<% } %>
									</form>
									<%
									}
									%>
									
								<br><br>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%@include file="../MenuBlocks/studentfooter.jsp"%>
		</div>

	
</body>
</html>

