<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

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
         	background-image: url("https://www.planwallpaper.com/static/images/desktop-background14.jpg");
        	background-color: #eeeeee;
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
  			margin: 180px 180px 180px 580px;
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
<h3>Welcome to the dashboard!</h3>
<hr>
<h4>Please choose options from sidebar!</h4>
<hr>
</div>
</div>

</body>
</html>
                    			
