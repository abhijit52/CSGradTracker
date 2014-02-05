<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="com.shrike.model.*"%>
<%@page import="java.sql.*"%>
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
	<form id="form1" enctype="multipart/form-data" action="./UploadServlet" method ="post">

		<div class="size">

			<%@include file="../MenuBlocks/facultyheader.jsp"%>

			<div class="content">
				<div class="mcontent">
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">

								<%
								FacultyInfo faculty = new FacultyInfo(request.getRemoteUser());
								out.print("<br><img src=\"./tools/image.jsp?user="+request.getRemoteUser()+"\" height=\"200\" width=\"150\"><br>");
								String name = faculty.getFirstName()+" "+faculty.getMiddleName()+" "+faculty.getLastName();
								out.print(name+",<br>"+faculty.getAffiliation()+"<br>"+faculty.getDepartment());
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
											<span> Upload file </span>
					

 <br>
<INPUT NAME="file" TYPE="file">
<input type="submit" value="Upload"> 
						
																				
										</div>
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