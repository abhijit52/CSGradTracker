<%@page import="java.util.ArrayList"%>
<%@page import="com.shrike.model.services.*"%>
<%@page  import="com.shrike.model.*" %>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html >

<html>

<head>
<link href="CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/menu.js"></script>
<script type="text/javascript" src="JS/togglemenu.js"></script>
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
	
	function stateChanged() 
	{ 
	    document.getElementById("courseTitle").innerHTML ="";
	    document.getElementById("course_id").value  ="";
	    document.getElementById("courseCredit").innerHTML = " ";
	    
		if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
	 	{ 
	  		var showdata = xmlHttp.responseText; 
	    	var strar = showdata.split(":");
	        
			if(strar.length==1)
	        {
	        	document.getElementById("course_id").focus();
	            alert("Please Select a Course");
	            document.getElementById("courseTitle").innerHTML =" ";
				document.getElementById("course_id").value =" ";
				document.getElementById("courseCredit").innerHTML = " ";
			}
	        else if(strar.length>1)
	        {
	        //	document.getElementById("course_id").value= strar[3];
	        	document.getElementById("courseTitle").innerHTML = strar[1];
	        	document.getElementById("courseCredit").innerHTML = strar[2];
	        	
	        }
		} 
	}
	
	function GetXmlHttpObject()
	{
		var xmlHttp=null;
		try
		{
			// Firefox, Opera 8.0+, Safari
			xmlHttp=new XMLHttpRequest();
		}
		catch (e)
		{
			//Internet Explorer
			try
			{
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e)
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
	}


	function addRow() {
		if(document.getElementById('scourseCode').value == "-1"){
			alert('Please Select a Course');
			return;
		}
		
		var ptable = document.getElementById('tableCourse');
		var lastElement = ptable.rows.length;
		var index = lastElement;
		var row = ptable.insertRow(lastElement);

		var cellLeft = row.insertCell(0);
		var textNode = document.createTextNode(index);
		cellLeft.appendChild(textNode);

		//Course Code
		var cellCode = row.insertCell(1);  
		var element = document.createElement('input');  
		element.type = 'hidden';  
		element.name = 'code' + index;  
		element.id = 'code' + index;  
		element.value = document.getElementById('scourseCode').value;  
		    
		var el1 = document.createTextNode(element.value );  
		    
		cellCode.appendChild(el1);  
		cellCode.appendChild(element); 
		  
		//Course Title
		var cellTitle = row.insertCell(2);
		var element = document.createElement('input');  
		element.type = 'hidden';  
		element.name = 'code' + index;  
		element.id = 'code' + index;  
		element.value = document.getElementById('courseTitle').innerHTML;  
		    
		var el1 = document.createTextNode(element.value );  
		    
		cellTitle.appendChild(el1);  
		cellTitle.appendChild(element); 

		//Course Credit
		var cellCredit = row.insertCell(3);
		var element = document.createElement('input');  
		element.type = 'hidden';  
		element.name = 'credit' + index;  
		element.id = 'credit' + index;  
		element.value = document.getElementById('courseCredit').innerHTML;  
		    
		var el1 = document.createTextNode(element.value );  
		    
		cellCredit.appendChild(el1);  
		cellCredit.appendChild(element); 

		document.getElementById("psize").value = index;
	}

	function removeRow() {
		var ptable = document.getElementById('tableCourse');
		var lastElement = ptable.rows.length;
		if (lastElement > 2)
			ptable.deleteRow(lastElement - 1);
		if (document.getElementById("psize").value > 0) {
			document.getElementById("psize").value = document
					.getElementById("psize").value - 1;
		}
	}
	
	function validate()
	{
		var len;
		if(document.getElementById("psize").value=="")
		{
			len = 1;
		}
		else
		{
			len  = parseInt(document.getElementById("psize").value);
		}
		
		
// 		for(var i=1; i<=len; i++) {
// 			var person = "cmnt" + i;
// 			if(document.getElementById(person).value=="")
// 			{
// 				alert("Please enter person");
// 				document.getElementById(person).focus();
// 				return false;
// 			}
// 		}
		return true;
	  }
	
	
	function showResult(){
		var msg = document.getElementById("message").value;
		
		if(msg != ""){
			alert(msg);
		}
	}
</script>

<title>
	Student - Add Courses for approval
</title>

</head>

<body onload="showResult();">
	<form id="fromAddCourseApproval" method="post" action="addCourseApproval.do">
		<div class="size">
			
			<%@include file ="../MenuBlocks/studentheader.jsp" %>
					
			<div class="content">
				<div class="mcontent">
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">
								<%@include file="../MenuBlocks/studentLeftMenu.jsp"%>

								<input type="hidden" name="message" id="message"
									value="<%=request.getAttribute("message") == null ? "" : request.getAttribute("message")%>">

							</div>
						</div>
					</div>

					<div class="mcright">
						<div id="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span>Add Courses </span>
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
										<td align="left" class="elementslabel"
											style="vertical-align: top">Year:</td>
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
										<td align="left" class="elementslabel"
											style="vertical-align: top">Select Courses:</td>
									</tr>
									<tr>
										<td class="elementslabel">Course Code</td>
										<td class="elementslabel">Course Title</td>
										<td class="elementslabel">Course Credit</td>
									</tr>
									<tr>
										<td class="elementsdropdownlist">
											<%
												CoursePlanDAO coursePlanDAO = new CoursePlanDAO();
												ArrayList<CourseInfo> cInfo = coursePlanDAO.getAllcourses();
											%> 
											<select id="scourseCode" onchange="showCourse(this.value);">
												<option value="-1">Select</option>

												<%for(CourseInfo ci: cInfo){%>
												<option value="<%out.println(ci.getCourseCode());%>">
													<%out.println(ci.getCourseCode());%>
												</option>
												<% } %>

											</select>
										</td>
										
										<td class="elementslabelinfo">
											<label id="courseTitle"></label>
										</td>

										<td class="elementslabelinfo">
											<label id="courseCredit"></label>
										</td>

										<td hidden="true">
											<input type="text" id="course_id" value="">
										</td>
									</tr>

									<tr>
										<td><input type="button"
											value="Add this course to register" onclick="addRow();" /></td>
										<td><input type="button" value="Remove the Last Course"
											onclick="removeRow();" /></td>
									</tr>
								</table>


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
									</tr>
								</table>

								<table>
									<tr>
										<td>
											<input type="Submit" value="Submit" onclick="validate();" />
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

