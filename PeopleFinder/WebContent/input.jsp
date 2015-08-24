<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>People Finder</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>



	<div class="container">
		<br /> <br />

		<div class="jumbotron">
			<center>
				<h1>People Finder</h1>
			</center>
			<form class="form-horizontal" action="Finder" method="post">
				<div class="form-group">
					<input type="text" class="form-control" id="lastName"
						name="lastName" placeholder="Last Name" required>
				</div>
				
				<div class="form-group">
					<input type="submit" class="btn btn-primary btn-lg btn-block"
						value="Find">
				</div>
			</form>
		</div>
	</div>
</body>
</html>