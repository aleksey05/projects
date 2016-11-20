<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/java_script/car.js" />" /></script>
<link type="text/css" rel="stylesheet"	href="<c:url value="/resources/css/car.css" />" />
</head>
<body>

	<div class="form-group">
		<div class="row">
			<div class="col-sm-4">
				<table>
				        <tr><td class="left"><label>Brand:</label></td><td id="carId"><c:out value="${car.id}" /></td></tr>
						<tr><td class="left"><label>Brand:</label></td><td><c:out value="${car.brand}" /></td></tr>
						<tr><td class="left"><label>Gear Type:</label></td><td><c:out value="${car.gear}" /></tr>
						<tr><td class="left"><label>Production year :</label></td><td><c:out value=" ${car.year}" /></tr>
						<tr><td class="left"><label>Colour:</label></td><td><c:out value="${car.colour}" /></tr>
						<tr><td class="left"><label>Fuel Type :</label></td><td><c:out value="${car.fuel}" /></tr>
						<tr><td class="left"><label>Addition Date :</label></td><td><c:out value="${car.date}" /></tr>
						<tr><td class="left"><label>Mileage :</label></td><td><c:out value="${car.mileage}" /></tr>
					</table>
			</div>
			<div class="col-sm-4">
				<a href="./"><img src="${car.image_url}" class="img-thumbnail"
					alt="Yellow Flower" width="250" height="250" /></a>
			</div>

			<div class="col-sm-4">
				<ul>
					<li>Owner info :</li>
					<li><c:out value="${car.user.name}" /></li>
					<li><c:out value="${car.user.email}" /></li>
					<li><c:out value="${car.getUser().getPhone_umber()}" /></li>
				</ul>
			</div>
		</div>
	</div>

       <div class="comments">
       <ul class="comment_list">
       </ul>
       <div class="form-group">

			<input type=text id="comment_text" class="form-control" placeholder="add your comment" /> <br /> 
			<input class="btn btn-danger btn-lg" type=submit id="button" />

		</div>
       </div>

	<!-- <div class="hiden" id="coment_section">
		<c:if test="${!comments.isEmpty()}">
		 <table>
			<c:forEach items="${comments}" var="comment">
			 <tr> 
			  <td>
				<c:out value="${comment.text}" />
			  </td>
			 </tr> 
			</c:forEach>
		 </table>
		</c:if>

		<div class="form-group">

			<input type=text id="comment" class="form-control" placeholder="add your comment" /> <br /> 
			<input class="btn btn-danger btn-lg" type=submit id="button" />

		</div>
	</div> -->

</body>
</html>