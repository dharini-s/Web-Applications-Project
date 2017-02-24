<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.battleborn.webapps.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
	<meta charset="UTF-8">
    <title>Fabflix Dashboard</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	

    <!-- Custom CSS -->
    <link href="/Fabflix/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/Fabflix/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/Fabflix/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
	<style type="text/css">
		th, td {
    		padding: 2px;
    		font-size: 100%;
		}
		hr {
    		border: 0;
    		height: 1px;
/*     		background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0)); */
/* 			Comment this background-image out to make hr hidden. */
		}
		body {
/*         	background-image: url("https://www.planwallpaper.com/static/images/desktop-background14.jpg");
 */         background-color: #eeeeee;
        	background-size: cover;
        	background-repeat:no-repeat;
        	padding:0;
  			margin:0;
  			
  			-moz-background-size: cover;
			-webkit-background-size: cover;
			background-size: cover;
			background-position: top center !important;
			background-repeat: no-repeat !important;
			background-attachment: fixed;
		}
		
 		div.transbox {
  			margin: 50px 0px 0px 220px;
  			width:1000px;
 			/* background-color: #eeeeee; */
  			/* border: 1px solid white; */
  			opacity: 0.75;
  			filter: alpha(opacity=60); /* For IE8 and earlier */
		}
		 
 		.form-control {
    		width:1000px;
		}
  			 
	</style>
</head>
<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/Fabflix/Dashboard.jsp">Dashboard</a>
            </div>
            
            <!-- Top Menu Items -->
           <ul class="nav navbar-right top-nav">
                 <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Admin<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/Fabflix/EmployeeLogin.jsp"><i class="fa fa-fw fa-user"></i> Sign in</a>
                        </li>
                        <li>
                            <a href="/Fabflix/servlet/EmployeeLogout"><i class="fa fa-fw fa-power-off"></i> Sign Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="/Fabflix/InsertStar.jsp"><i class="fa fa-fw fa-dashboard"></i> Insert Star</a>
                    </li>
                    <li class="active">
                        <a href="/Fabflix/InsertMovie.jsp"><i class="fa fa-fw fa-dashboard"></i> Insert Movie</a>
                    </li>
                    <li>
                        <a href="/Fabflix/Metadata.jsp"><i class="fa fa-fw fa-bar-chart-o"></i> Metadata</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

    </div>
    <!-- /#wrapper -->
    
	
	
<div class="transbox"> 
<div class="container">
<hr>
<h4>Please enter star information:</h4>
<h4><font color="green">${message}</font></h4>
  <hr>
  <form action="/Fabflix/servlet/InsertStar" method="post">
    <div class="form-group">
      <label for="usr">Star Name:</label>
      <input type="text" name="starName" maxlength=50 class="form-control" id="usr">
    </div>
    <p>Please divide the star first name and last name with white space!</p>
    <hr>
    <div class="form-group">
      <label for="usr">Date of Birth:</label>
      <input type="text" name="starDoB" maxlength=50 class="form-control" id="usr">
    </div>
    <p>Please input the date with format yyyy/mm/dd !</p>
    <hr>
    <div class="form-group">
      <label for="usr">Photo Url:</label>
      <input type="text" name="starPhotoUrl" maxlength=50 class="form-control" id="usr">
    </div>
    <input type="submit" class="btn btn-success" style="padding:6px;"/>
	<hr>
  </form>
</div>
</div>
</body>
</html>

