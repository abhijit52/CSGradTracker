<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="CSS/studentstyles.css" rel="stylesheet" type="text/css" />
<link href="CSS/studentmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/menu.js"></script>
<script type="text/javascript" src="JS/togglemenu.js"></script>
<script type="text/javascript" src="JS/datetimepicker.js"></script>
<title>User Delete</title>
</head>

<body>
		<div class="size">

			<%@include file="../MenuBlocks/secretaryheader.jsp"%>

			<div class="content"> <!-- Content -->
			 <div class="mcright">
				<div id="MainPlaceHolder">
					<div class="mc0201">
			    		<div class="MainPlaceHolder">
							<div class="mc0201">
								<div class="sectionheader">
									<span>Lookup User</span>
								</div>
		
			 <form id="deactivate-form">
				User Name:<input id="id-username" type="text" name="user-name"></input> <input type="button" id="id-search-button" value="Find User"/>
			
			<input id="userAction" type="hidden" name="userAction"/>
			
			
			
			</form>
			<br>
			</br>
			<table id="tableUser" class="elementstable" border="1">
			
			</table>
			
			<div id="userInfo">
			
			</div>
			<script type="text/javascript">
			
			$('#id-search-button').live('click',function(){
				//$("#tableCourse").html('<tr><th align="left" class="elementslabel" style="font-size:large;">'+"LoginName"+'</th><th align="left" class="elementslabel" style="font-size:large;">Role</th><th align="left" class="elementslabel" style="font-size:large;">FirstName</th><th align="left" class="elementslabel" style="font-size:large;">LastName</th></tr>');
				var userName = $('#id-username').val().trim();
				//alert(userName);
				$("#tableUser").html("");
				if(userName==""){
					alert("Please enter username to search");
					return;
				}
				var userAction = "search";
				$.ajax({
					url: 'getUserList.do',
					type: 'POST',
					data : {'name':userName, 'userAction':userAction},
					success: function(data){
						$("#tableUser").html("");
						if(data.length==0){
							alert("Record Not Found");
							return;
						}
						//alert(data);
						var user = data.split('@');
						$("#tableUser").html("");
						$("#tableUser").html('<tr><th>'+"Login Name"+'</th><th>'+"First Name"+'</th><th>'+"Edit Account"+'</th></tr>');
						for(var i = 0; i < user.length; i++){
							individualUser = user[i].split('|');
							var userName = individualUser[0];
							var firstName = individualUser[1];
							//var lastName = individualUser[2];
							if(individualUser.length==0){
								alert("Record not found");
								return;
							}
							//$("#tableUser").append('<th><td>'+"Login Name"+'</td><td>'+"First Name"+'</td><td>'+"Last Name"+'</td><td>'+"Edit Account"+'</td></th>');
							$("#tableUser").append('<tr> <td>' + userName + '</td><td>'+ firstName+'</td><td><input type="button" value="Deactivate" class="btn-deactivate-user" user-id="'+ userName +'"></input></td></tr>');
						}
					},
					error: function(data){
						alert("error");
						console.log("error:",data);
					}
				}); 
			});
			
			$('.btn-deactivate-user').live('click',function(){
				var userName = $(this).attr('user-id');
				var buttonStatus = $(this).attr("value");
				alert(buttonStatus);
				alert(userName);
				$('#userAction').val("deactivate");
				$.ajax({
					url: 'getUserList.do',
					type: 'POST',
					data: {'dUser':userName, 'userAction' : "deactivate"},
					success: function(data){
						
										
					}
												
				});
				//alert("You clicked in Deactivate");
			});
			
			</script>
			</div>
			
			</div>
			</div>
			</div>
			
				</div>	
			</div> <!-- Content -->

			<%@include file="../MenuBlocks/studentfooter.jsp"%>
		</div>
		</body>
</html>