<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main"/>
	<title>Index</title>
</head>

<body>

<p style="padding-bottom: 10px">Welcome to index page</p>

Last Name: <g:fieldValue bean="${person}" field="lastName"/><br/>
First Name: <g:fieldValue bean="${person}" field="firstName"/><br/>
Age: <g:fieldValue bean="${person}" field="age"/><br/><br/>

<!-- OR -->

<%--Last Name: ${person.lastName} <br/>--%>
<%--First Name: ${person.firstName} <br/>--%>
<%--Age: ${person.age} <br/>--%>

<!-- PRINT NUMBERS FROM 1 TO 10 -->
<g:each in="${(1..10)}" var="number">
	${number}<br/>
</g:each>

<br/>

<!-- PRINT ALL ITEMS FROM LIST list -->
<g:each in="${list}" var="person">
	Last Name: ${person.lastName}, First Name: ${person.firstName}, Age: ${person.age}<br/>
</g:each>

</body>

</html>