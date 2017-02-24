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
    		font-size: 100%;
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
	
		<BODY BGCOLOR="#FDF5E6">
		<H3><b>Cart Details:</b></H3>
		<hr>
		<TABLE ALIGN="CENTER">
			<col width="250">
			<col width="70">
			<col width="40">
			<col width="60">
			<col width="60">
			<TR BGCOLOR="#FFAD00">
			
			<TR>
    			<TH>Title</TH>
    			<TH>Price</TH> 
    			<TH>Qty</TH>
    			<TH></TH>
    			<TH></TH>
  			</TR>
  
			<% 
			 ArrayList<CartItem> item = (ArrayList<CartItem>)request.getAttribute("cartItems");
			   if (item != null) {
				   for (int i = 0; i< item.size(); i++) {	  
			%>
			
			<TR>
				<TD><a href="/Fabflix/servlet/SingleMovie?movieid=<%=item.get(i).getID()%>"><b><%=item.get(i).getTitle()%></b></a></TD>
				<TD><b><%=item.get(i).getPrice()%></b></TD>
				<TD>
					<form target="_self" action="/Fabflix/servlet/UpdateQty" method="POST">
						<input type="text" name="new_quantity" maxlength=50 style="width: 20px;" value=<%=item.get(i).getQuantity()%>>
				</TD>
				<TD>
						<input type="submit" id ="updateQty" class="btn btn-success" style="padding:1px;" value="Update">
						<input type="hidden" name="movie_id_cart" value=<%=item.get(i).getID()%>>
					</form>
				</TD>
				<TD>
					<form target="_self" action="/Fabflix/servlet/DeleteItem" method="POST">
						<input type="submit" id ="updateQty" class="btn btn-success" style="padding:1px;" value="Delete">
						<input type="hidden" name="movie_id_cart" value=<%=item.get(i).getID()%>>
					</form>
				</TD>
			</TR>
			
			<%   }
			   }
			%>
										
		</TABLE>
		<hr>
	</div>
	</div>
</body>
</html>


