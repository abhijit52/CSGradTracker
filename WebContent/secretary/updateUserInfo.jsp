<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.shrike.model.StudentInfo"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/menu.js"></script>
<script type="text/javascript" src="JS/togglemenu.js"></script>
<title>Edit User Infromation</title>
</head>
<body>
<div class="size">
<%@ include file = "../MenuBlocks/secretaryheader.jsp" %>
<div class="content">
	<div class="mcright">
		<div id="MainPlaceHolder">
			<div class="mc0201">
				<div class="MainPlaceHolder">
						<div class="mc0201">
						
							<div class="sectionheader">
							Find For Edit
							</div>
							
							<form method="post" action="./edit.do">
									<input type = "text" name="searchString"></input>
									<input type="submit" value="Search"></input>
									</form>
									
									Student List
									<%
									ArrayList<StudentInfo> sList = (ArrayList<StudentInfo>)request.getAttribute("studentList"); 
									if(sList!=null)
									{
										if(sList.size()==0)
										{
											out.print("No Recod Foud");
										}else
										{
											out.print("<table class=\"elmentstable\">");
											for(StudentInfo s: sList)
											{
												out.print("<tr><td>"+ "<input type=\"checkbox\"></input>" +"</td><td>+ "+ s.getFirstName()+"</td><td>" + "<input type=\"button\">" + "</td></tr>");
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

</div>
<%@ include file="../MenuBlocks/studentfooter.jsp" %>
</div>
</body>
</html>