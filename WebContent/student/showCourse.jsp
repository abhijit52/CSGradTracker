<%@page import="java.util.ArrayList"%>
<%@page import="com.shrike.model.services.*"%>
<%@page  import="com.shrike.model.*" %>
<%@page import="java.sql.*" %>

<%
	String courseCode = request.getParameter("courseCode").toString();
    
	CoursePlanDAO cPlan = new CoursePlanDAO();
	String courseTitle = cPlan.getCourseTitleByCourseCode(courseCode);
	String courseCredit = cPlan.getCourseCreditByCourseCode(courseCode);
	
	Statement st;
    
    String data = ":" + courseTitle + ":" + courseCredit + ":";
    
    out.println(data);
 %>