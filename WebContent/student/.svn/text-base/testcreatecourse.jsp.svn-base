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
	 		
			var url="ShowCourseServlet";
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
	
	function validate()
	{
		var courseString;
		if(addedCourses.length == 0){
			alert("Select at least one course for the plan!");
			return false;
			
		}
		courseString = addedCourses.toString();
		//alert(courseString);
		document.getElementById("courseList").value=courseString;
		return true;
	  }
	
	
	function showResult(){
		var msg = document.getElementById("message").value;
// 		alert("hi");
		
		if(msg != ""){
			alert(msg);
		}
	}
	
	
</script>

<title>
	Student - Add Course Plan
</title>

</head>

<body onload="showResult();">
	<form id="fromAddCoursePlan" method="post" action="testCoursePlan.do" onSubmit=" return validate();" >
		<div class="size">
			
			<%@include file ="../MenuBlocks/studentheader.jsp" %>
					
			<div class="content">
				<div class="mcontent">
					<div class="mcleft">
						<div id="LeftmenuPlaceHolder">
							<div class="elementslabel">
								<%@include file="../MenuBlocks/studentLeftMenu.jsp"%>
								
								<!-- Set Course List -->
								<input type="hidden" name="courseList" id="courseList" value="">

								<input type="hidden" name="message" id="message"
									value="<%=session.getAttribute("message") == null ? "" : session.getAttribute("message")%>">

							</div>
						</div>
					</div>

					<div class="mcright">
						<div id="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span>Add Course Plan</span>
								</div>
								<table id="selectTable">
									<tr>
										<td class="elementslabel">Session:</td>
										<td class="elementsdropdownlist"><select id="session"
											name="session">
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
										<td class="elementslabel" align="center">Course Credit</td>
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

										<td class="elementslabelinfo" align="center">
											<label id="courseCredit"></label>
										</td>

										<td hidden="true">
											<input type="text" id="course_id" value="">
										</td>
									</tr>

									<tr>
										<td>
											<input type="button" id="id-btn-add-course" value="Add this course to my plan"  />
										</td>
										<!-- <td>
											<input type="button" value="Remove the Last Course" onclick="removeRowFromDiv();" />
										</td> -->
									</tr>
								</table>

								<br>
								
								<!-- course choice table -->
								<span style="font-size: large;">Course Plan: </span> 
								
								<input type="hidden" name="psize" id="psize" value="1">
								
								<table id="tableCourse" class="elementstable">
									<tr>
										 <!-- <th align="left" class="elementslabel" width="4px"
											style="font-size: large;">Sl.</th>  -->
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Code</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Title</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Course Credit</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Comment</th>
										<th align="left" class="elementslabel"
											style="font-size: large;">Edit</th>
									</tr>
								</table>
								
								<!-- CONTENT -->
								<table id="tableCourse" class="elementstable">
								<tr id="new-row">
								
								<!-- <div id="coursePlanDiv">
																
								</div> -->
								
								</tr>
								
								</table>
								<!-- <div id="coursePlanDiv">
																
								</div> -->
								
								<table>
									<tr>
										<td><input type="Submit" value="Submit"/></td>
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
	<script type="text/javascript">
		var addedCourses = [];
		$(function(){
			Array.prototype.remove = function() {
			    var what, a = arguments, L = a.length, ax;
			    while (L && this.length) {
			        what = a[--L];
			        while ((ax = this.indexOf(what)) !== -1) {
			            this.splice(ax, 1);
			        }
			    }
			    return this;
			};
			
			$('#id-btn-add-course').live('click', function(){
				if(document.getElementById('scourseCode').value == "-1"){
					alert('Please Select a Course');
					return;
				}
				
				var cid = $('#scourseCode').val().trim();
				$.ajax({
					url: 'ShowCourseServlet',
					type: 'GET',
					data: {'courseCode': cid},
					success: function(data){
						var courseDetails = data.split(':');
						var courseCredits = courseDetails[2];
						var courseName = courseDetails[1];
						if(jQuery.inArray(cid, addedCourses) == -1){
							addedCourses.push(cid);
							$("#tableCourse").append('<tr id="course-' + cid + '"><td><label id="new-label-cid">' + cid + '</label></td><td><label id="new-label-cname">'+courseName +'</label></td><td align="center">'+ courseCredits +'</td><td><input type="text" name="comment'+cid+'"></input></td><td><input type="button" value="Remove" class="btn-remove-course" course-id="' + cid + '"></input></td></tr>');
							/* $("#coursePlanDiv").append('<div class="new-div" id="course-' + cid + '"><label id="new-label-cid">' + cid + '</label><label id="new-label-cname">'+courseName +'</label><label id="new-label-credit">'+ courseCredits +'</label><input type="text" name="comment'+cid+'"></input><input type="button" value="Remove" class="btn-remove-course" course-id="' + cid + '"></input></div>'); */
						}else
							{
							alert(cid + "  already added to the Plan");
							}
					}
				});
			});
			
			$('.btn-remove-course').live('click', function(){
				var cid = $(this).attr('course-id');
				var divId = 'course-' + cid;
				$('#' + divId).remove();
				addedCourses.remove(cid);
			});
			
			
		});
	</script>
</body>
</html>

