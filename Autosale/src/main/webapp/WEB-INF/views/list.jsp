<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Cars List</title>
<style>
.row
{
border:1px solid lightgrey;
margin-top:auto;
margin-right:auto;
margin-bottom:10px;
margin-left:auto;
}
.col-sm-4
{
padding:5px;

}
</style>
</head>
<body>


	<c:forEach items="${carList}" var="car">

	</c:forEach>
	<div class="form-group">
		<c:forEach items="${carList}" var="car">
			<div class="row">
				<div class="col-sm-4">
					<ul>
						<li>Brand:<c:out value="${car.brand}" /></li>
						<li>Gear Type:<c:out value="${car.gear}" /></li>
						<li>Production year:<c:out value="${car.year}" /></li>
						<li>Colour:<c:out value="${car.colour}" /></li>
						<li>Fuel Type :<c:out value="${car.fuel}" /></li>
						<li>Addition Date :<c:out value="${car.date}" /></li>
						<li>Mileage :<c:out value="${car.mileage}" /></li>
					</ul>
				</div>
				<div class="col-sm-4">
					<a href="./car?carId=${car.id}"><img src="${car.image_url}" class="img-thumbnail"
						alt="Yellow Flower" width="250" height="250" /></a>
				</div>

				<div class="col-sm-4">
					<span>Owner info :</span>
					<ul>
						<li><c:out value="${car.user.name}" /></li>
						<li><c:out value="${car.user.email}" /></li>
						<li><c:out value="${car.getUser().getPhone_umber()}" /></li>
					</ul>
				</div>
			</div>
		</c:forEach>
	</div>

</body>
</html>