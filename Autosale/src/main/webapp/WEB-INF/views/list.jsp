<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet"	href="<c:url value="/resources/css/list.css" />" />
<script src="<c:url value="/resources/java_script/list.js" />" /></script>
<title>Cars List</title>

</head>
 <body>
	<div class="form-group">
		<c:forEach items="${carList}" var="car">
			<div class="row">
				 <p class="top_label"> <c:out value="${car.brand}" />
					   <c:out value="${car.model}"/></p>
					  
					 
				<div class="col-sm-4 border">
					<table>
					    <tr><td class="left"><label>Price:</label></td><td><c:out value="${car.price}$"/></td></tr>
						<tr><td class="left"><label>Gear Type:</label></td><td><c:out value="${car.gear}" /></tr>
						<tr><td class="left"><label>Production year :</label></td><td><c:out value=" ${car.year}" /></tr>
						<tr><td class="left"><label>Color:</label></td><td><c:out value="${car.colour}" /></tr>
						<tr><td class="left"><label>Fuel Type :</label></td><td><c:out value="${car.fuel}" /></tr>
						<tr><td class="left"><label>Addition Date :</label></td><td><c:out value="${car.date}" /></tr>
						<tr><td class="left"><label>Mileage :</label></td><td><c:out value="${car.mileage}" /></tr>
					</table>
				</div>
				<div   class="col-sm-4 border">
					<a href="./car?carId=${car.id}"><img id="image" src="${car.image_url}" class="img-thumbnail"
						 width="250" height="250" /></a>
				</div>

				<div class="col-sm-4">
					<span>Owner info :</span>
					<table>
						<tr><td class="left"><label>Name:</label></td><td><c:out value="${car.user.name}" /></td></tr>
						<tr><td class="left"><label>Email:</label></td><td><c:out value="${car.user.email}" /></td></tr>
						<tr><td class="left"><label>Phone Number:</label></td><td><c:out value="${car.getUser().getPhone_umber()}" /></td></tr>
					</table>
				</div>
	
			</div>
						
		</c:forEach>
	</div>
 </body>
</html>