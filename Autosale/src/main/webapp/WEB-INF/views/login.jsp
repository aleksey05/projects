<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Login page</title>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
</head>
<body>
<h2>Please enter your name and password</h2>
<div>
	<form id="form" action="j_spring_security_check" method="post">
		<table id="table">
			<tr>
				<td>Name:<input class="form-control"  placeholder="Enter name" name="j_username" /></td>
			</tr>
			<tr>
				<td>Password:<input type="password" class="form-control" placeholder="Enter password" name="j_password" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /></td>
			</tr>
		</table>
		<br>
	       <div class="button">
		     <input id="sub" type="submit" class="btn btn-info btn-md" value="Login" />
          </div>
	</form>
</div>
<br>
    <a href=./user/provide><input id="reg" type="button" class="btn btn-danger btn-md" value="Register" /></a>
	<form>
		<span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
	</form>

</body>
</html>
