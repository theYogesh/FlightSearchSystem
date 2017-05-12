<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Search</title>
</head>
<body>
	<a id='logoutBtn' href="./logout">LogOut</a>
	<h1>Flight Search</h1>

	<form action="flightSearch" method="POST">
		Dep LOC: <form:input path="depLoc" maxlength="3"
			pattern="[a-zA-Z0-9]{3}" title="Alphanumeric 3 letter Code" required/>
		<springForm:errors path="flightSearchForm.depLoc" cssClass="error" />
		<br>
		<br> Arr LOC: <form:input path="arrLoc" maxlength="3"
			pattern="[a-zA-Z0-9]{3}" title="Alphanumeric 3 letter Code" required/>
		<springForm:errors path="flightSearchForm.arrLoc" cssClass="error" />
		<br>
		<br> Flight Date: <input type="date" name="flightDate"
			min="${maxDate}" required>
		<springForm:errors path="flightSearchForm.flightDate" cssClass="error" />
		<br>
		<br> Flight Class: <select name="flightClass" required>
			<option value="E">Economy Class</option>
			<option value="B">Business Class</option>
		</select>
		<springForm:errors path="flightSearchForm.flightClass"
			cssClass="error" />
		<br>
		<br> <input type="submit" value="Submit">
	</form>
</body>
</html>