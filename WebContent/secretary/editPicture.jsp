<%@page import="com.shrike.model.services.ConnectionDAO"%>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html >

<html>
<head>
<link href="./CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="./CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./JS/jquery.js"></script>
<script type="text/javascript" src="./JS/menu.js"></script>
<script type="text/javascript" src="./JS/togglemenu.js"></script>

<script type="text/javascript">
function validateForm(f){
 f = f.elements;
 if(/.*\.(gif)|(jpeg)|(jpg)$/.test(f['file'].value.toLowerCase()))
  return true;
 alert('Please Upload Gif or Jpg Images Only.');
 f['file'].focus();
 return false;
};
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>SHRIKE : Secretary</title>
</head>
<body>
<%-- <%if(request.isUserInRole("student"))  --%>
// {
// 	response.sendRedirect("../student/student_home.jsp");
// 	return;
// }
// if(request.isUserInRole("faculty"))
// {
// 	response.sendRedirect("../faculty/faculty_home.jsp");
// 	return;
// }

<%-- %> --%>
	<form id="form1" name="myForm" enctype="multipart/form-data" action="./UploadServlet" method ="post" onsubmit="return validateForm(this)">
	
		<div class="size">
			
			<%@include file ="../MenuBlocks/secretaryheader.jsp" %>
					
			<div class="content">
				<div class="mcontent">
<%-- 					<%@include file="../MenuBlocks/leftmenu.jsp"%> --%>
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">
							<%
									ConnectionDAO cn = new ConnectionDAO();
									Connection c = cn.getConnectionObject();
									String username = request.getRemoteUser();
									Statement stmt = c.createStatement();
									String name="";
									ResultSet rs = stmt
											.executeQuery("SELECT * FROM FacultySecretaryRecord WHERE Username = \""
													+ username.trim() + "\"");

									if (!rs.next()) {
										out.print("Invalid username: " + username);
									}

									else {
										name = rs.getNString("firstname");
										String level = rs.getNString("Affiliation");
										String mn;
										if(rs.getNString("middlename")==null){
											mn="";
										}else{
											mn = rs.getNString("middlename");
										}
										String fullname = name + " " + mn
												+ " " + rs.getNString("lastname");
										out.print("<br><img src=\"./tools/image.jsp?user="+username.trim()+"\" height=\"200\" width=\"150\"><br>" + fullname
												+ "," + level);
									}
									c.close();
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
														<span> Upload an image file </span>
														 <br>
														<INPUT NAME="file" TYPE="file">
														<input type="submit" value="Save Picture"> 													
													</div>
												</div>
											</div>
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