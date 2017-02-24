<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.battleborn.webapps.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>Fabflix</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style type="text/css">
		th, td {
    		padding: 2px;
    		font-size: 110%;
		}
		hr {
    		border: 0;
    		height: 1px;
    		background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
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
  			margin: 260px 180px 180px 180px;
 			background-color: #eeeeee;
  			border: 1px solid white;
  			opacity: 0.75;
  			filter: alpha(opacity=60); /* For IE8 and earlier */
		}
  			
	</style>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
  		<div class="container-fluid">
    		<div class="navbar-header">
      			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        			<span class="icon-bar"></span>
        			<span class="icon-bar"></span>
        			<span class="icon-bar"></span> 
      			</button>
      			<a class="navbar-brand" href="#"><b>Fabflix</b></a>
   			 </div>
    		<div class="collapse navbar-collapse" id="myNavbar">
      			<ul class="nav navbar-nav">
				<form action="/Fabflix/servlet/MainPage" method="get" class="navbar-form navbar-left">
      				<div class="input-group">
        				<input type="text" name="search" class="form-control" id="search" placeholder="Search">
        				<input type="hidden" name="sortOrder" value=""/>
				 		<input type="hidden" name="page" value="1"/> 
        				<div class="input-group-btn">
          					<button class="btn btn-default" type="submit">
            					<i class="glyphicon glyphicon-search"></i>
          					</button>
        				</div>
      				</div>
    			</form>
      			</ul>
      			
       			<ul id="multicol-menu" class="nav navbar-nav navbar-right">
      				<form action="/Fabflix/servlet/Home" method="get"></form>
      				<li class="active"><a href="/Fabflix/servlet/Home">Home</a></li>			
			 		<li><a href="/Fabflix/advancedSearch.jsp">Advanced Search</a></li>
        			<li><a href="/Fabflix/servlet/ShoppingCart"><span></span>My Cart</a></li>
        			<li><a href="/Fabflix/servlet/CheckOut"><span></span>Checkout</a></li>
        			
        			<li class="dropdown">
        				<a class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>Sign in<span class="caret"></span></a>
        				<ul class="dropdown-menu">
        					<li><a href="/Fabflix/Login.jsp">Sign in</a></li>
          					<li><a href="/Fabflix/servlet/Logout">Sign out</a></li>
        				</ul>
      				</li>
      			</ul>
      		
    		</div>
 	 	</div>
	</nav>
	
	
	<div class="container-fluid">
  	<div class="transbox">
		<form action="/Fabflix/servlet/SingleStar" method="post"></form>
	
		<BODY BGCOLOR="#FDF5E6">
		<H3><b>Star Details:</b></H3>
		<hr>
		<TABLE ALIGN="CENTER">
			<col width="140" align="right">
			<col width="130">
			<col width="200">
			<TR BGCOLOR="#FFAD00">
			<TR>
				<% ArrayList<Movie> movie = (ArrayList<Movie>)request.getAttribute("movies");
				   int s = 5;
				   if (!movie.isEmpty()) {
					   s = movie.size() + s - 1;
				   }
				%>
				<TD rowspan = "<%=s%>"><img src="<%=request.getAttribute("photourl")%>" alt="<%=request.getAttribute("starname")%>" height="140" width="95"></TD>
				<TD><b>Star Name:</b></TD>
				<TD><b><%=request.getAttribute("starname")%></b></TD>
			</TR>

			<TR>
				<TD><b>Date of Birth:</b></TD>
				<TD><b><%=request.getAttribute("dob")%></b></TD>
			</TR>

			<TR>
				<TD><b>Star ID:</b></TD>
				<TD><b><%=request.getAttribute("starid")%></b></TD>
			</TR>

			<TR>
				<% 
				   int size = 0;
				   if (!movie.isEmpty()) {
					   size = movie.size();
				%>	   
					   <TD rowspan = "<%=size%>" valign="top"><b>Starred In:</b></TD>
					   <TD style="padding-bottom:0px;"><a href="/Fabflix/servlet/SingleMovie?movieid=<%=movie.get(0).getID()%>"><b><%=movie.get(0).getTitle() + "(" + movie.get(0).getYear() + ")"%></b></a></TD>
				<%   							
    				} else {
    			%>
    				   <TD><b>Stars:</b></TD>
					   <TD></TD>
    			<%		
    				}
	    		%>
			</TR>

				<% if (!movie.isEmpty()) {
						if (movie.size() > 1) {
					   		for(int j = 1; j < movie.size(); j++) {
				%>	   
								<TR>
					   			<TD style="padding-top:0px; padding-bottom:0px;"><a href="/Fabflix/servlet/SingleMovie?movieid=<%=movie.get(j).getID()%>"><b><%=movie.get(j).getTitle() + "(" + movie.get(j).getYear() + ")"%></b></a></TD>
					   			</TR>
				<%   							
    						}
						}
					}
	    		%>
										
		</TABLE>
		<hr>
	</div>
	</div>
</body>
</html>


