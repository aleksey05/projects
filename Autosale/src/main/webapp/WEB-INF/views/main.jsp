<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<title>Main</title>
<style>
#form
{
    margin:0 auto;
	width:50%;
}
#tr
{
padding:10px;
}
body {
	background-color:grey ;
	width: 85%;
	margin: 0 auto;
	
}
html {
	background-color:white;
	
}
.li
{
margin-top:5%;
color:white;
}
</style>

<body>
	<nav class="navbar navbar-inverse">
	 <div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/">Autosale</a>
		</div>
		  <ul class="nav navbar-nav">
			 <li class="active"><a href="/autosale">Home</a></li>
		  </ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="li">Logged in as: <sec:authentication property="name" /></li>
			<li class="li"><sec:authentication property="authorities" /></li>
			<li><a href="logout"><span
			class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>
	</div>
	</nav>


<a href=./add><input type="button" class="btn btn-danger btn-lg" value="AddNew" /></a>
<div id="form">
<form method="get" action="cars">
            <table id="table" border="0">
                 <tr>
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="maximum price" name="priceTo" size="50"/></td>
                 </tr>
                 <tr>
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="minimum price" name="priceFrom" size="50"/></td>
                 </tr>
                 <tr>
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="Brand" name="brand" size="50"/></td>
                </tr>
                <tr >
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="Model" name="model" size="50"/></td>
                </tr>
                 <tr >
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="Year From" name="yearFrom" size="50"/></td>
                </tr>
                 <tr>
                    <td  id="tr"><input class="form-control input-lg" type="text" placeholder="Year To" name="yearTo" size="50"/></td>
                </tr>
                <tr>
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="Mileage From" name="mileageFrom" size="50"/></td>
                </tr>
                 <tr>
                    <td  id="tr"><input class="form-control input-lg" type="text" placeholder="Mileage To" name="mileageTo" size="50"/></td>
                </tr>
                <tr>
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="Gear type" name="gearType" size="50"/></td>
                </tr>
                <tr>
                    <td  id="tr"><input class="form-control input-lg" type="text" placeholder="Fuel Type"  name="fuelType" size="50"/></td>
                </tr>
                <tr>
                    <td  id="tr"><input class="form-control input-lg" type="text" placeholder="Engine Volume From"  name="engineVolumeFrom" size="50"/></td>
                </tr>
                <tr>
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="Engine Volume To" name="engineVolumeTo" size="50"/></td>
                </tr>
                <tr>
                    <td id="tr"><input class="form-control input-lg" type="text" placeholder="Additional Info" name="additionalInfo" size="50"/></td>
                </tr>
                <tr>
                    <td  id="tr"><input class="form-control input-lg" type="text" placeholder="Colour" name="colour" size="50"/></td>
                </tr>
                <tr>
                    <td id="tr" colspan="2">
                        <input type="submit" class="btn btn-danger btn-lg" value="Search">
                    </td>
                </tr>
            </table>
        </form>
</div>




</body>
</html>