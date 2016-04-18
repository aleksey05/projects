<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Main</title>
</head>
<body>
<a href=./add><input type="button" class="btn btn-danger btn-lg" value="AddNew" /></a>

<form method="get" action="cars">
            <table border="0">
                 <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Brand" name="brand" size="50"/></td>
                </tr>
                <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Model" name="model" size="50"/></td>
                </tr>
                 <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Year From" name="yearFrom" size="50"/></td>
                </tr>
                 <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Year To" name="yearTo" size="50"/></td>
                </tr>
                <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Mileage From" name="mileageFrom" size="50"/></td>
                </tr>
                 <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Mileage To" name="mileageTo" size="50"/></td>
                </tr>
                <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Gear type" name="gearType" size="50"/></td>
                </tr>
                <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Fuel Type"  name="fuelType" size="50"/></td>
                </tr>
                <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Engine Volume From"  name="engineVolumeFrom" size="50"/></td>
                </tr>
                <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Engine Volume To" name="engineVolumeTo" size="50"/></td>
                </tr>
                <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Additional Info" name="additionalInfo" size="50"/></td>
                </tr>
                <tr>
                    <td><input class="form-control input-lg" type="text" placeholder="Colour" name="colour" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Search">
                    </td>
                </tr>
            </table>
        </form>





</body>
</html>