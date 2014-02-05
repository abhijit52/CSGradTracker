<%@page import="java.util.ArrayList"%>
<%@page import="com.shrike.model.services.*"%>
<%@page import="com.shrike.model.*" %>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html >

<html>

<head>
<link href="./CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="./CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./JS/jquery.js"></script>
<script type="text/javascript" src="./JS/togglemenu.js"></script>

<title>
	Student - View Course Status
</title>

</head>

<body>
	<form id="fromCourseStatus" method="post" action="viewCourseStatus.do">
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
									<span>View Course Status</span>
								</div>
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
										<td align="left" class="elementslabel" style="vertical-align: top">
											Year:</td>
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
											type="submit" value="submit" /></td>
									</tr>
								</table>

								<br>

								<!-- course choice table -->
								<span style="font-size: large;">Course Plan: </span> <input
									type="hidden" name="psize" id="psize">
								<table id="tableCourse" class="elementstable">
									<tr>
										<th align="left" class="elementslabel" width="4px"
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


									<%
										ArrayList<CoursePlan> cPlan = (ArrayList<CoursePlan>) session.getAttribute("coursePlan");
										int i = 0;

										if (cPlan != null) {
											for (CoursePlan cp : cPlan) {
												i++;
									%>
									<tr>
										<td class="elementslabelinfo">
											<%
												out.print(i + "");
											%>
										</td>
										<td class="elementslabelinfo">
											<%
												out.print(cp.getCourseCode()== null ? "": cp.getCourseCode());
											%>
										</td>
										<td class="elementslabelinfo">
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
									<tr>
										<td> <br>
										</td>
									</tr>
									<tr>
										<td class="elementslabel">
											G.P.A.:
										</td>
										<td class="elementslabel">
											<% out.print(session.getAttribute("gpa")== null ? "":""+session.getAttribute("gpa"));%>
										</td>
									</tr>
									<tr>
										<td> <br>
										</td>
									</tr>
									<tr>
										<td class="elementslabel">
											C.G.P.A.:
										</td>
										<td class="elementslabel">
											<% out.print(session.getAttribute("cgpa")== null ? "":""+session.getAttribute("cgpa"));%>
										</td>
									</tr>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>

			<%@include file="../MenuBlocks/studentfooter.jsp"%>
		</div>
	</form>
	
</body>
</html>