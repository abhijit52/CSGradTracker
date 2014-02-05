<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head >
    <link rel="shortcut icon" href="favicon.ico" />
    <link href="./CSS/styles.css" rel="stylesheet" type="text/css" />
    <link href="./CSS/menu.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./JS/jquery.js"></script>
    <script type="text/javascript" src="./JS/menu.js"></script>
    <script type="text/javascript" src="./JS/togglemenu.js"></script>
</head>

<body>
    <form id="form1"  >
    <div class="size">
        <div id="HeaderPlaceHolder"  >
            <div class="header">
                <div class="header01">
                    <div class="logo">
                      <img src="./images/shrike.jpg" alt="" />
                    </div>
                </div>
                <div id="menu">
                    <ul class="menu">
                        <li><a href="" class="parent"><span>Home</span></a>                 
                        </li>
                        <li><a href="#" class="parent"><span>Entry</span></a>
                            <ul>
                                <li><a href=""><span>Entry 1</span></a></li>
								<li><a href=""><span>Entry 2</span></a></li>
                            </ul>
                        </li>
                        <li><a href="#" class="parent"><span>Views</span></a>
                            <ul>
                                <li><a href=""><span>Views 1</span></a></li>
                                <li><a href=""><span>Views 2</span></a></li>
                            </ul>
                        </li>
                        <li><a href="#" class="parent"><span>User Management</span></a>
                            <ul>
                                <li><a href=""><span>Create Role</span></a></li> 
                                <li><a href=""><span>Edit Role</span></a></li>
                                <li><a href=""><span>Create User</span></a></li> 
                                <li><a href=""><span>Edit User</span></a></li> 
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="content">
            <div class="mcontent">
                <div class="mcleft">
                    <div id="LeftmenuPlaceHolder"  >
						<ul class="leftmenu">
							<li><a href=".">Menu 1</a>
								<ul>
									<li><a href=""><span>Sub-Menu 1</span></a></li>
									<li><a href=""><span>Sub-Menu 2</span></a></li>
								</ul>
							</li>
							<li><a href=".">Menu 2</a>
								<ul>
									<li><a href=""><span>Sub-Menu 1</span></a></li>
									<li><a href=""><span>Sub-Menu 2</span></a></li>
								</ul>
							</li>
							<li><a href=".">Menu 3</a>
								<ul>
									<li><a href=""><span>Sub-Menu 1</span></a></li>
									<li><a href=""><span>Sub-Menu 2</span></a></li>
								</ul>
							</li>
							<li><a href=".">Menu 4</a>
								<ul>
									<li><a href=""><span>Sub-Menu 1</span></a></li>
									<li><a href=""><span>Sub-Menu 2</span></a></li>
								</ul>
							</li>
						</ul>
					</div>
                </div>
                <div class="mcright">
                    <div id="MainPlaceHolder"  >
                        <div class="mc0201">
                            Main content will be here
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="FooterPlaceHolder"  >
            <div class="footer">
                <img class="mnimgl" src="image/img_275.jpg" alt="" />
                <img class="mnimgr" src="image/img_291.jpg" alt="" />
                <a href="../Web/Home.aspx">HOME</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="../Web/ViewSales.aspx">SALES</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
                    href="#">REMITTANCE</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="3">DESPATCH</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
                        href="../Web/Reception.aspx">RECEPTION</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">CONTACT</a>
                <br />
                <font color="#333333">Copyright © 2013 Team Shirke (The University of Memphis)| Developed
                    and Maintained by <a style="color: #333333" href="http://www.eclipse-systems.com"> Team Shrike</a>
                </font>
            </div>
        </div>
    </div>
    </form>
</body>
</html>
