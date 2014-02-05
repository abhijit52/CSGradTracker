<%@page import="com.shrike.model.StudentInfo"%>
<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
	<form id="form1">
		<div class="size">
			<%@include file="../MenuBlocks/studentheader.jsp"%>

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
									<span>Welcome <%
										out.print("" + student.getFirstName() + "!");
										int maxCount = 0;
										session.setAttribute("maxcount", maxCount);%>
									</span>
								</div>

								<table class="elementstable">
									<tr>

										<td>First Name</td>
										<td>
											<%
												out.print(student.getFirstName() != null ? student.getFirstName()
														: "");
											%>
										</td>
									</tr>
									<tr>
										<td>Middle Name</td>
										<td>
											<%
												out.print(student.getMiddleName() != null ? student.getMiddleName()
														: "");
											%>
										</td>
									</tr>
									<tr>
										<td>Last Name</td>
										<td>
											<%
												out.print(student.getLastName() != null ? student.getLastName()
														: "");
											%>
										</td>
									</tr>
									<tr>
										<td>Address</td>
										<td>
											<%
												out.print(student.getHousenum() != 0 ? student.getHousenum() : "");
											%> <%
 	out.print(student.getStreet() != null ? " " + student.getStreet()
 			: "");
 %>
										</td>
									</tr>
									<tr>
										<td>City</td>
										<td>
											<%
												out.print(student.getCity() != null ? student.getCity() : "");
											%>
										</td>
									</tr>
									<tr>
										<td>State</td>
										<td>
											<%
												out.print(student.getState() != null ? student.getState() : "");
											%>
										</td>
									</tr>
									<tr>
										<td>Zipcode</td>
										<td>
											<%
												out.print(student.getZipcode() != 0 ? student.getZipcode() : "");
											%>
										</td>
									</tr>
									<tr>
										<td>Phone</td>
										<td>
											<%
												out.print(student.getPhone() != null ? student.getPhone() : "");
											%>
										</td>
									</tr>
									<tr>
										<td>E-mail</td>
										<td>
											<%
												out.print(student.getEmail() != null ? student.getEmail() : "");
											%>
										</td>
									</tr>
									<tr>
										<td>Enrollment Date</td>
										<td>
											<%
												SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy");

												String dateStr = formatter.format(student.getEnrollmentDate());
											%> <%
 	out.print(dateStr != null ? dateStr : "");
 %>
										</td>
									</tr>
									<tr>
										<td>Academic Level</td>
										<td>
											<%
												out.print(student.getAcademicLevel() != null ? student
														.getAcademicLevel() : "");
											%>
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

