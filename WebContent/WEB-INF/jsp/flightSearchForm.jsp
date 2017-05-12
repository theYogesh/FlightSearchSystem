<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/css/main.css" var="mainCss" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Search</title>
<link href="${mainCss}" rel="stylesheet" />

</head>
<body>
	<div id="divHeader">
		<h1>Flight Search</h1>
	</div>
	<div class="divUsername">
		Hello <span class="spanUsername">${cookie.username.value}!</span> [<span><a
			class="authorisation" href="./logout">LogOut</a></span>]
	</div>
	<fieldset class="formField">
		<legend>FLIGHT SEARCH FORM</legend>
		<springForm:form action="flightSearch" method="POST"
			commandName="flightSearchForm">
			<springForm:label path="depLoc">Dep Loc: </springForm:label>
			<springForm:input path="depLoc" />
			<springForm:errors path="depLoc" cssClass="error" />
			<br>
			<br>
			<springForm:label path="arrLoc">Arr Loc: </springForm:label>
			<springForm:input path="arrLoc" />
			<springForm:errors path="arrLoc" cssClass="error" />
			<br>
			<br>
			<springForm:label path="flightDate">Flight Date: </springForm:label>
			<springForm:input path="flightDate" />
			<springForm:errors path="flightDate" cssClass="error" />
			<br>
			<br>
			<springForm:label path="flightClass">Flight Class:</springForm:label>
			<springForm:select path="flightClass">
				<springForm:option value="E" label="Economy Class" />
				<springForm:option value="B" label="Business Class" />
			</springForm:select>
			<br>
			<br>
			Order By: <br><label for="idFare">Fare: </label>
			<input type="radio" name="orderBy" id="idFare" value="fare">
			<label for="idDuration">Duration: </label>
			<input type="radio" name="orderBy" id="idDuration" value="duration">
			<springForm:errors path="orderBy" cssClass="error" />
			<br>
			<br>
			<input type="submit" value="Submit">
		</springForm:form>
	</fieldset>
</body>
</html>