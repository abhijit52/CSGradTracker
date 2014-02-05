<%@page import="com.shrike.model.services.CourseInfoDAO"%>
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
<script>
function showCourse(courseCode)
{ 
	if(document.getElementById("course_id").value!="-1")
    {
 		xmlHttp=GetXmlHttpObject();
		
 		if (xmlHttp==null)
 		{
			alert ("Browser does not support HTTP Request");
 			return;
 		}
 		
		var url="student/showCourse.jsp";
		url=url+"?courseCode="+courseCode;
		
		xmlHttp.onreadystatechange=stateChanged; 
		xmlHttp.open("GET",url,true);
		xmlHttp.send(null);
	}
    
	else{
        alert("Please Select a Course");
	}
}
</script>

<title>
	Student - Home
</title>

</head>

<body>
<%if(request.isUserInRole("faculty")) 
{
	response.sendRedirect("../faculty/student_home.jsp");
	return;
}
if(request.isUserInRole("secretary"))
{
	response.sendRedirect("../secretary/secretary_home.jsp");
	return;
}

%>

	<form id="form1">
	
		<div class="size">
			
			<%@include file ="../MenuBlocks/studentheader.jsp" %>
					
			<div class="content">
				<div class="mcontent">
<%-- 					<%@include file="../MenuBlocks/leftmenu.jsp"%> --%>
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">
								<%
									String name="";
									ConnectionDAO cn = new ConnectionDAO();
									Connection c = cn.getConnectionObject();
									String username = request.getRemoteUser();
									Statement stmt = c.createStatement();
									ResultSet rs = stmt
											.executeQuery("SELECT * FROM StudentRecord WHERE Username = \""
													+ username.trim() + "\"");

									if (!rs.next()) {
										out.print("Invalid username: " + username);
									}

									else {
										name = rs.getNString("firstname");
										String level = rs.getNString("AcademicLevel");
										String fullname = name + " " + rs.getNString("middlename")
												+ " " + rs.getNString("lastname");
									
										out.print("<br><img src=\"./tools/image.jsp?user="+username.trim()+"\" height=\"200\" width=\"150\"><br>" + fullname
												+ "," + level + " Student");
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
														<span>Required Courses</span>	
														<div class="elementsdropdownlist">
												<select name="majorSelect" onchange="showCourse(this.value);">
														<option value="MSCS">M.S. Computer Science</option>
														<option value="MSACS">M.S. Applied Computer Science</option>
														<option value="PHD">Ph.D. Computer Science</option>
												</select>	</div> 
												<input type=submit value="View" /> 																									
<% 
String major = (String)request.getParameter("majorSelect");
System.out.print(major);
CourseInfoDAO info = (CourseInfoDAO)request.getAttribute("arrayRequired"); 
if (major!= null && major.compareToIgnoreCase("MSCS")==0)
{
%>
<div class="elementslabel">M.S. Computer Science</div>
<% for (int i=0; i<info.getMasterRequired().size(); i++) {%>
<ul>
<% out.println("<li><div class=elementslabelinfo>" + info.getMasterRequired().get(i).getCourseCode() + ": " + info.getMasterRequired().get(i).getCourseTitle() + "</div></li>"); %>
</ul>
<% } 
}
if (major!= null && major.compareToIgnoreCase("MSACS")==0)
{
%>
<div class="elementslabel">M.S. Applied Computer Science </div>
<% for (int i=0; i<info.getMasterRequired().size(); i++) {%>
<ul>
<% out.println("<li><div class=elementslabelinfo>" + info.getAppliedMasterRequired().get(i).getCourseCode() + ": " + info.getAppliedMasterRequired().get(i).getCourseTitle() + "</div></li>"); %>
</ul>
<% } 
}
if (major!= null && major.compareToIgnoreCase("PHD")==0)
{
%>
<div class="elementslabel">Ph.D. Computer Science </div>
<% for (int i=0; i<info.getMasterRequired().size(); i++) {%>
<ul>
<% out.println("<li><div class=elementslabelinfo>" + info.getPhdRequired().get(i).getCourseCode() + ": " + info.getPhdRequired().get(i).getCourseTitle() + "</div></li>"); %>
</ul>
<% } 
}%>
														
														
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