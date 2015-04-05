<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta name="layout" content="test"/>
	<title>Hi</title>
</head>

<body>
  
<g:form controller="Hello" action="save" >	
	
	<!-- The '?' after person stops it from throwing an error if there's a null point exception -->
	
    <label for="lastName">Last Name</label>
    <g:textField name="lastName" value="${person?.lastName}"/>
    <br/>
    <label for="firstName">First Name</label>
    <g:textField name="firstName" value="${person?.firstName}"/>
    <br/>
    <label for="age">Age</label>
    <g:textField name="age" value="${person.age}"/>
    <br/>
    <g:actionSubmit name="create" value="Save" />
</g:form>

<br/>

<g:each in="${persons}" var="p">
	Last Name: ${p?.lastName}, First Name: ${p?.firstName}, Age: ${p?.age} <br/>
</g:each>

<br/>

<div id="jsTest"></div>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$('#jsTest').text("${person.age}");
		
	});
</script>

</body>
</html>