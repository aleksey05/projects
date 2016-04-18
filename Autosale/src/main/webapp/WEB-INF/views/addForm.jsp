<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<form method="post" action="addNew" enctype="multipart/form-data">
            <table border="0">
                 <tr>
                    <td>Brand: </td>
                    <td><input type="text" name="brand" size="50"/></td>
                </tr>
                  <tr>
                    <td>Mileage: </td>
                    <td><input type="text" name="mileage" size="50"/></td>
                </tr>
                <tr>
                    <td>Model: </td>
                    <td><input type="text" name="model" size="50"/></td>
                </tr>
                 <tr>
                    <td>Year: </td>
                    <td><input type="text" name="year" size="50"/></td>
                </tr>
                <tr>
                    <td>Gear type: </td>
                    <td><input type="text" name="gearType" size="50"/></td>
                </tr>
                <tr>
                    <td>Fuel type: </td>
                    <td><input type="text" name="fuelType" size="50"/></td>
                </tr>
                <tr>
                    <td>Engine Volume: </td>
                    <td><input type="text" name="engineVolume" size="50"/></td>
                </tr>
                <tr>
                    <td>Additional info: </td>
                    <td><input type="text" name="additionalInfo" size="50"/></td>
                </tr>
                <tr>
                    <td>Colour: </td>
                    <td><input type="text" name="colour" size="50"/></td>
                </tr>
                <tr>
                    <td>Image: </td>
                    <td><input type="file" name="image" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Add">
                    </td>
                </tr>
            </table>
        </form>
</body>
</html>