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
  			margin: 180px 180px 180px 180px;
 			background-color: #eeeeee;
  			border: 1px solid white;
  			opacity: 0.75;
  			filter: alpha(opacity=60); /* For IE8 and earlier */
		}
		
		.dropdown-submenu {
    position: relative;
}

.dropdown-submenu .dropdown-menu {
    top: 0;
    left: 100%;
    margin-top: -1px;
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

        			<li class="dropdown">
        				<a class="dropdown-toggle" data-toggle="dropdown">Browse by title<span class="caret"></span></a>
        				<ul class="dropdown-menu">

            			<li>
                 			<div class="row" style="width: 200px;">
                 			<ul class="list-unstyled col-md-4"> 					
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=0">0</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=1">1</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=2">2</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=3">3</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=4">4</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=5">5</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=6">6</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=7">7</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=8">8</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=9">9</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=A">A</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=B">B</a></li>
							</ul>
							<ul class="list-unstyled col-md-4">
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=C">C</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=D">D</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=E">E</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=F">F</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=G">G</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=H">H</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=I">I</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=J">J</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=K">K</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=L">L</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=M">M</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=N">N</a></li>
							</ul>
							<ul class="list-unstyled col-md-4">
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=O">O</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=P">P</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=Q">Q</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=R">R</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=S">S</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=T">T</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=U">U</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=V">V</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=W">W</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=X">X</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=Y">Y</a></li>
								<li><a class="text-info" href="/Fabflix/servlet/MainPage?search=Z">Z</a></li>
							</ul>
							</div>
            			</li>
            		</ul>
      				</li>

        			<li class="dropdown">
        				<a class="dropdown-toggle" data-toggle="dropdown">Browse by genre<span class="caret"></span></a>
        				<ul class="dropdown-menu">

            			<li>
                 			<div class="row" style="width: 500px;">        					
 								<% ArrayList<String> genre = (ArrayList<String>)request.getAttribute("genres");
								   if (!genre.isEmpty()) {
										int i;
								%>	
									<ul class="list-unstyled col-md-4">
								<%
    									for(i=0; i<16; i++) {
    							%>
    										<li><a href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=<%=genre.get(i)%>&page=1"><%=genre.get(i)%></a></li> 
								<% 		
										}
								%>	
									</ul>
								 	<ul class="list-unstyled col-md-4">
								<%
										for(i=16; i<genre.size(); i++) {
								%>	
    										<li><a href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=<%=genre.get(i)%>&page=1"><%=genre.get(i)%></a></li> 
								<% 		
										}
								%>	
									</ul>
								<% 
								   }
								%>

							</div>
            			</li>
            		</ul>
      				</li>
      				     				
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
  	<h3 align="center" style="font-size:30px;" >Thank you for visiting Fabflix!</h3>
		<BODY BGCOLOR="#FDF5E6">
		<hr>
<!-- 		<h4 align="center" style="font-size:25px;">Current popular titles</h4>
 -->		<TABLE ALIGN="CENTER">
			<col width="250">
			<col width="250">
			<col width="250">
			<TR BGCOLOR="#FFAD00">
			<TR>
				<TD align="center"><img src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTU4MzY4NzI0M15BMl5BanBnXkFtZTcwMjQ0MDY5Mg@@._V1_SY1000_CR0,0,697,1000_AL_.jpg" alt="All Over Me" height="140" width="95"></TD>
				<TD align="center"><img src="https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxNzYzNzUzMV5BMl5BanBnXkFtZTYwNjcwMjE3._V1_.jpg" alt="Finding Neverland" height="140" width="95"></TD>
				<TD align="center"><img src="https://images-na.ssl-images-amazon.com/images/M/MV5BZWFjYmZmZGQtYzg4YS00ZGE5LTgwYzAtZmQwZjQ2NDliMGVmXkEyXkFqcGdeQXVyNTUyMzE4Mzg@._V1_SY1000_CR0,0,696,1000_AL_.jpg" alt="The Fifth Element" height="140" width="95"></TD>
			</TR>
			<TR>
				<TD align="center"><b>All Over Me (1997)</b></TD>
				<TD align="center"><b>Finding Neverland (2004)</b></TD>
				<TD align="center"><b>The Fifth Element (1997)</b></TD>
			</TR>

			<TR>
				<TD align="center"><b>Price: $15.99</b></TD>
				<TD align="center"><b>Price: $15.99</b></TD>
				<TD align="center"><b>Price: $15.99</b></TD>
			</TR>

			<TR>
				<TD align="center"><a href="/Fabflix/servlet/SingleMovie?movieid=907009"><b>More Info</b></a></TD>
				<TD align="center"><a href="/Fabflix/servlet/SingleMovie?movieid=855007"><b>More Info</b></a></TD>
				<TD align="center"><a href="/Fabflix/servlet/SingleMovie?movieid=855002"><b>More Info</b></a></TD>
			</TR>

	    	<TR>
				<TD align="center">
					<form target="_self" action="/Fabflix/servlet/AddToCart" id = 'cartForm' method="POST">
						<input type="submit" id ="addtocart" class="btn btn-success" style="padding:4px;" value="Add to Cart" onclick="alert('Movie added to your cart successfully!')">
						<input type="hidden" name="movie_id_cart" value="907009">
					</form>
				</TD>
				
				<TD align="center">
					<form target="_self" action="/Fabflix/servlet/AddToCart" id = 'cartForm' method="POST">
						<input type="submit" id ="addtocart" class="btn btn-success" style="padding:4px;" value="Add to Cart" onclick="alert('Movie added to your cart successfully!')">
						<input type="hidden" name="movie_id_cart" value="855007">
					</form>
				</TD>
				
				<TD align="center">
					<form target="_self" action="/Fabflix/servlet/AddToCart" id = 'cartForm' method="POST">
						<input type="submit" id ="addtocart" class="btn btn-success" style="padding:4px;" value="Add to Cart" onclick="alert('Movie added to your cart successfully!')">
						<input type="hidden" name="movie_id_cart" value="855002">
					</form>
				</TD>
				
			</TR>										
		</TABLE>
		<hr>
	</div>
	</div>
</body>
</html>


