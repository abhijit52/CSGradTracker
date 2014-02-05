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
		
		for(var i=1; i<=len; i++) {
			var gradeVar = "grade" + i;
			grade = document.getElementById(gradeVar).value;
			grade = grade.toLowerCase();
			
			if(grade==""){
				alert("Please enter grade");
				document.getElementById(gradeVar).focus();
				return false;
			}			
			else if (grade == "a" || grade == "a-" || grade == "a+"){
				return true;
			}
			else if (grade == "b" || grade == "b-" || grade == "b+"){
				return true;
			}
			else if (grade == "c" || grade == "c-" || grade == "c+"){
				return true;
			}
			else if (grade == "d" || grade == "d-" || grade == "d+"){
				return true;
			}
			else if (grade == "f"){
				return true;
			}
			else{
				alert("Please enter a valid grade");
				document.getElementById(gradeVar).focus();
				return false;
			}
		}
		
		return true;
	  }
	
	
	function showResult(){
		var msg = document.getElementById("message").value;
		
		if(msg != ""){
			alert(msg);
		}
	}
	
	
	function comboInit(thelist)
	{
	  theinput = document.getElementById(theinput);  
	  var idx = thelist.selectedIndex;
	  var content = thelist.options[idx].innerHTML;
	  if(theinput.value == "")
	    theinput.value = content;
	}
	function combo(thelist, theinput)
	{
	  theinput = document.getElementById(theinput);  
	  var idx = thelist.selectedIndex;
	  var content = thelist.options[idx].innerHTML;
	  theinput.value = content;
	}

</script>

<title>
	Student - Add Grades for approval
</title>

</head>

<body onload="showResult();">
	<form id="fromAddCourseApproval" method="post" action="addGradeApproval.do">
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
									<span>Add Grade Approval</span>
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
										<td>
											<input type="Submit" value="Submit"
											onclick="validate();" id="btnshow" name="btnshow" />
										</td>
									</tr>
								</table>

								<br>
								
								<!-- course choice table -->
								<span style="font-size: large;">Course Plan: </span> 
								
								<input type="hidden" name="psize" id="psize"
									value="<%=request.getAttribute("number") == null ? "0" : request.getAttribute("number") %>">

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
										<th align="left" class="elementslabel"
											style="font-size: large;">Grade</th>
									</tr>


									<% ArrayList<CourseApprove> cPlan = (ArrayList<CourseApprove>)request.getAttribute("coursePlan"); 
 										int i = 0; 
												
 										if(cPlan != null){
 											for(CourseApprove cp: cPlan){ 
 												i++; 
  									%>
									<tr>
										<td class="elementslabelinfo"> <% out.print(i+""); %> 
											<input type="hidden" name="<% out.print("no"+i);%>" id="<% out.print("no"+i);%>"
												value="<%out.print(i+"");%>">
										</td>
										<td class="elementslabelinfo"> <% out.print(cp.getCourseCode()); %> 
											<input type="hidden" name="<% out.print("code"+i);%>" id="<% out.print("code"+i);%>"
												value="<%out.print(cp.getCourseCode());%>">
										</td>
										<td class="elementslabelinfo"> <% out.print(cp.getCourseTitle()); %> 
											<input type="hidden" name="<% out.print("title"+i);%>" id="<% out.print("title"+i);%>"
												value="<%out.print(cp.getCourseTitle());%>">
										</td>
										<td class="elementslabelinfo"> <% out.print(cp.getCourseCredit()); %> 
											<input type="hidden" name="<% out.print("credit"+i);%>" id="<% out.print("credit"+i);%>"
												value="<%out.print(cp.getCourseCredit());%>">
										</td>

										<td>										
											<input type="hidden" name="<% out.print("grade"+i);%>" id="<% out.print("grade"+i);%>"
												value="<%out.print(cp.getGrade());%>" size="3" >
											
											<select name="thelist" onChange="combo(this, '<% out.print("grade"+i);%>')" onMouseOut="comboInit(this, '<% out.print("grade"+i);%>')">
  											<option <% if (cp.getGrade().compareToIgnoreCase("a+")==0) out.print("selected"); %>>A+</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("a")==0) out.print("selected"); %>>A</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("a-")==0) out.print("selected"); %>>A-</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("b+")==0) out.print("selected"); %>>B+</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("b")==0) out.print("selected"); %>>B</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("b-")==0) out.print("selected"); %>>B-</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("c+")==0) out.print("selected"); %>>C+</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("c")==0) out.print("selected"); %>>C</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("c-")==0) out.print("selected"); %>>C-</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("d")==0) out.print("selected"); %>>D</option>
  											<option <% if (cp.getGrade().compareToIgnoreCase("f")==0) out.print("selected"); %>>F</option>
											</select> 
										</td>
									</tr>
									<%		}
										}
									%>
								</table>

								<table>
									<tr>
										<td><input type="Submit" value="Submit"
											onclick="return validate();" id="btnsubmit" name="btnsubmit" />
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

