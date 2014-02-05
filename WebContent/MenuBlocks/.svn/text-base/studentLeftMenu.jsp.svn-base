<%@page import="java.util.ArrayList"%>
<%@page import="com.shrike.model.services.*"%>
<%@page  import="com.shrike.model.*" %>
<%@page import="java.sql.*" %>

<%
	StudentInfoDAO sdao = new StudentInfoDAO();
	StudentInfo student = sdao.getInfo(request.getRemoteUser());
	out.print("<br><img src=\"./displayImage.do?user="
			+ request.getRemoteUser()
			+ "\" height=\"200\" width=\"150\"><br>");
	String name = student.getFirstName() + " "
			+ student.getMiddleName() + " " + student.getLastName();
	out.print(name + ",<br>" + student.getAcademicLevel() + " Student.");
%>
