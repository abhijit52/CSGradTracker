<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>

<link href="CSS/menu.css" rel="stylesheet" type="text/css" /> 
<link href="CSS/login.css?v=1.0.1" type="text/css" rel="stylesheet" />
<script src="JS/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="JS/jquery.exposure.js?v=1.0.1" type="text/javascript"></script>

<title>Problem with Login</title>

<script>
	function showResult() {
		var msg = document.getElementById("message").value;
		if (msg != "") {
			alert(msg);
		}
	}
	
	
	$(function(){
		var gallery = $('#images');
		gallery.exposure({controlsTarget : '#controls',
			controls : { prevNext : true, pageNumbers : true, firstLast : false },
			visiblePages : 2,
			slideshowControlsTarget : '#slideshow',
			onThumb : function(thumb) {
				var li = thumb.parents('li');				
				var fadeTo = li.hasClass($.exposure.activeThumbClass) ? 1 : 0.3;
				
				thumb.css({display : 'none', opacity : fadeTo}).stop().fadeIn(200);
				
				thumb.hover(function() { 
					thumb.fadeTo('fast',1); 
				}, function() { 
					li.not('.' + $.exposure.activeThumbClass).children('img').fadeTo('fast', 0.3); 
				});
			},
			onImage : function(image, imageData, thumb) {
				// Fade out the previous image.
				image.siblings('.' + $.exposure.lastImageClass).stop().fadeOut(500, function() {
					$(this).remove();
				});
				
				// Fade in the current image.
				image.hide().stop().fadeIn(1000);

				// Fade in selected thumbnail (and fade out others).
				if (gallery.showThumbs && thumb && thumb.length) {
					thumb.parents('li').siblings().children('img.' + $.exposure.selectedImageClass).stop().fadeTo(200, 0.3, function() { $(this).removeClass($.exposure.selectedImageClass); });			
					thumb.fadeTo('fast', 1).addClass($.exposure.selectedImageClass);
				}
			},
			onPageChanged : function() {
				// Fade in thumbnails on current page.
				gallery.find('li.' + $.exposure.currentThumbClass).hide().stop().fadeIn('fast');
			}
		});
	});
</script>
</head>

<body>
	<div class="size">

		<%@include file="MenuBlocks/header_log.jsp"%>
		
		<div class="content">
			<div class="mcleft">
				<div id="LeftmenuPlaceHolder">
					<div class='login-box'>
						<div class='login-area'>
						
							<span class="elementslabel" style="font-weight: bold; color: red;">
									User name or Password is invalid</span>
							
							<div class="login-caption">Login Information</div>

							<form method="post" action="j_security_check">
								<div class="Leftmenu">
									<div class="login-info">UserName:</div>
									<input type="text" id="id-login-user" class="login-input"
										name="j_username" />
								</div>
								<div class="Leftmenu">
									<div class="login-info">Password:</div>
									<input type="password" id="id-login-password"
										class="login-input" name="j_password" />
								</div>

								<div class="loginbutton">
									<input type="submit" value="Login" class="login-btn" /><br></br>
								</div>
							</form>
						</div>

						<div class='forgot-password'>
							<a href="ForgotPassword/forgetPassword.jsp">Forgot Password?</a>
						</div>
					</div>
				</div>
			</div>

						<input type="hidden" name="message" id="message"
				value="<%=session.getAttribute("confirm") == null ? "" : session.getAttribute("confirm")%>">

			<div class="mcright">
				<div id="MainPlaceHolder">
									<div class="mc0201">
				
					<div id="main">
						<div class="panel">
							<ul id="images">
								<li><a href="images/Grad1.jpg"><img
										src="images/Grad1_thumb.jpg" title="Exubernce" /></a></li>
								<li><a href="images/Grad2.jpg"><img
										src="images/Grad2_thumb.jpg" title="Joy" /></a></li>
								<li><a href="images/Grad3.jpg"><img
										src="images/Grad3_thumb.jpg" title="Graduation" /></a></li>
								<li><a href="images/Grad4.jpg"><img
										src="images/Grad4_thumb.jpg" title="" /></a></li>
							</ul>
						
							<div id="controls"></div>
						
							<div class="clear"></div>
						</div>
						
						<div id="exposure"></div>
						
						<div class="clear"></div>
						
						<div id="slideshow"></div>
					</div>

				</div>
				</div>
			</div>
		</div>
		<div id="FooterPlaceHolder">
			<div class="footer">
				<a href="">HOME</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="">Menu 1</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
				<a href="">Menu 2</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="">Menu 3</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
				<a href="">Menu 4</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="contactus.jsp">CONTACT</a> 
				
				<br /> <font color="#8D8D8D">Copyright
					© 2013 Team Shirke (The University of Memphis)| Developed and
					Maintained by <a style="color: #8D8D8D"
					href="http://www.cs.memphis.edu/" target="_blank"> Team Shrike</a>
				</font>
			</div>
		</div>
	</div>

</body>
</html>
