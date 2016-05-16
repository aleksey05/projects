<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>User Form</title>
<%@ include file="/WEB-INF/views/includes/user_form_header.jsp" %>
</head>
<body>

<h2>Please enter your name and password</h2>
<div>
	<form id="form" action="./accept" method="post">
		<table id="table">
			<tr>
				<td>Name:<input id="name" class="form-control"  placeholder="Enter name" name="name" /></td><td><span id="name_err"> "Please input at least 4 symbols"</span></td>
			</tr>
			<tr>
				<td>Phone Number:<input id="phone" type="text" class="form-control" placeholder="Enter Phone Number" name="phone" /></td><td><span id="phone_err">"Phone must contain only digits and be at least 5 symbols"</span></td>
			</tr>
			<tr>
				<td>Email:<input id="email" type="text" class="form-control" placeholder="Enter Email" name="email" /></td><td><span id="email_err"> Check Email</span></td>
			</tr>
			<tr>
				<td>Password:<input id="password" type="password" class="form-control" placeholder="Enter password" name="password" /></td><td><span id="password_err">Password should contain at least one digit and have min length of 5 symbols</span></td>
			</tr>
		</table>
		<br>
	       <div class="button">
		     <input id="sub" type="submit" class="btn btn-info btn-md" value="Submit" />
          </div>
	</form>
</div>

</body>
</html>