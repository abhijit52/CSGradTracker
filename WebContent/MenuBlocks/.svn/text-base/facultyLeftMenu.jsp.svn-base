<%@page import="java.util.ArrayList"%>
<%@page import="com.shrike.model.services.*"%>
<%@page  import="com.shrike.model.*" %>
<%@page import="java.sql.*" %>

<%
	FacultyInfo faculty = new FacultyInfo(request.getRemoteUser());
	out.print("<br><img src=\"./displayImage.do?user="
			+ request.getRemoteUser()
			+ "\" height=\"200\" width=\"150\"><br>");
	String name = faculty.getFirstName() + " "
			+ faculty.getMiddleName() + " " + faculty.getLastName();
	out.print(name + ",<br>" + faculty.getAffiliation() + "<br>"
			+ faculty.getDepartment());
%>
