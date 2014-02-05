<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="com.shrike.model.*"%>
<%@page import="java.sql.*"%>
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
								<%@include file="../MenuBlocks/facultyLeftMenu.jsp"%>
							</div>
						</div>
					</div>


					<div class="mcright">
						<div id="MainPlaceHolder">
							<div class="mc0201">
								<div class="MainPlaceHolder">
									<div class="mc0201">
										<div class="sectionheader">
											<span> <% out.print("Welcome " + name + "!"); 
											int maxCount = 0;
											session.setAttribute("maxcount", maxCount);
											%> </span>
										</div>
										<table class="elementstable">
						<tr>
							<td>Login Name</td>
							<td><%=request.getRemoteUser() == null ? "" : request.getRemoteUser()%></td>

						</tr>
						<tr>
							<td>First Name</td>
							<td><% out.print(faculty.getFirstName()!=null? faculty.getFirstName() : ""); %></td>
						</tr>
						<tr>
							<td>Middle Name</td>
							<td><% out.print(faculty.getMiddleName()!=null ? faculty.getMiddleName() : ""); %></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><% out.print(faculty.getLastName()!=null ? faculty.getLastName() : ""); %></td>
						</tr>
						<tr>
							<td>Street#</td>
							<td><% out.print(faculty.getHousenum()!=0 ? faculty.getHousenum() : ""); %> 							
							</td>							
						</tr>
						<tr>
							<td>Street Name</td>
							<td><% out.print(faculty.getStreet()!=null ? faculty.getStreet() : ""); %>							
							</td>							
						</tr>
						<tr>
							<td>City</td>
							<td><% out.print(faculty.getCity()!=null ? faculty.getCity() : ""); %>
						</tr>
						<tr>
							<td>State</td>
							<td><% out.print(faculty.getState()!=null ? faculty.getState() : ""); %>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td><% out.print(faculty.getZipcode()!=0 ? faculty.getZipcode() : ""); %>
						</tr>
						<tr>
							<td>Phone</td>
							<td><% out.print(faculty.getPhone()!=null ? faculty.getPhone() : ""); %></td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><% out.print(faculty.getEmail()!=null ? faculty.getEmail() : ""); %></td>
						</tr>
						<tr>
						<td>Joined Date</td>
						<td>
						<%
						DateFormat  formatter = new SimpleDateFormat("MMM dd,yyyy");

						String dateStr = formatter.format(faculty.getJoinDate());
						
						%>
						
						<% out.print(dateStr!=null ? dateStr : ""); %>
						</td>
						</tr>						
					</table>
									</div>
								</div>
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

