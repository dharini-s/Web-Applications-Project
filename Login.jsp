<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.battleborn.webapps.QueryInformation"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.battleborn.webapps.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<script src='https://www.google.com/recaptcha/api.js'></script>

<head>
	<meta charset="UTF-8">
	<title>Fabflix</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
        	background-image: url("https://www.planwallpaper.com/static/images/desktop-background14.jpg");
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
  			margin: 180px 180px 180px 450px;
  			width:400px;
 			background-color: #eeeeee;
  			border: 1px solid white;
  			opacity: 0.75;
  			filter: alpha(opacity=60); /* For IE8 and earlier */
		}
		
		.form-control {
    		width:350px;
		}
  			
	</style>
</head>
<body>


<div class="transbox">
<div class="container">
<p>${message}</>
  <h3>Log in:</h3>
  <hr>
  <form action="/Fabflix/servlet/Login" method="post">
    <div class="form-group">
      <label for="usr">E-mail ID:</label>
      <input type="text" name="id" maxlength=50 class="form-control" id="usr">
    </div>
    <div class="form-group">
      <label for="usr">Password:</label>
      <input type="password" name="password" maxlength=50 class="form-control" id="usr">
    </div>
    <input type="submit" class="btn btn-success" style="padding:6px;"/>
	<hr>
	<div class="g-recaptcha" data-sitekey="6LdsPRUUAAAAAIJER_GLSMHjb-gaTx-sgefAjv8E"></div>
	<hr>
  </form>
</div>
</div>
</body>
</html>


