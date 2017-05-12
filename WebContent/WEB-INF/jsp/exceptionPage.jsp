<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
	<head>
		<title>Flight Search Application</title>
		<link rel="stylesheet" type="text/css" href="global.css">
	</head>

<body>
	<div id="divHeader"><h1>Flight Search</h1></div>
	<div>Hello <span class="divUsername">${cookie.username.value}!</span> [<span><a class="authorisation" href="./logout">LogOut</a></span>]</div>
	<h2>Some Unknown Error Occured!</h2>
	<h2>Error Msg= ${errorMsg}</h2> </body>
</html>