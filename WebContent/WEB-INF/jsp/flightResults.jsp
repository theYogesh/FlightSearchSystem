<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div id="divResult">
	<h2>${errorMsg}</h2>
	<table id="resultTable" width="70%">
		<tr>
			<th>Flight No.</th>
			<th>Airline</th>
			<th>Dep LOC</th>
			<th>Arr LOC</th>
			<th>Duration</th>
			<th>Time</th>
			<th>Fare</th>
		</tr>
		<c:forEach items="${resultList}" var="flight" varStatus="status">
			<tr>
				<td>${flight.flightNo}</td>
				<td>${flight.airlineName}</td>
				<td>${flight.depLOC}</td>
				<td>${flight.arrLOC}</td>
				<td>${flight.flightDuration}</td>
				<td>${flight.flightTime}</td>
				<td>${flight.fare}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	</div>
</body>
</html>