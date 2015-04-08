<!DOCTYPE html>
<%@page import="java.lang.BootstrapMethodError"%>
<html>

<style type="text/css">
	.back {
		font-size: large;
	}
	.back:hover {
		color: red;
	}
</style>

<h1>File Uploaded!</h1>
<br/>
<a href="${resource(dir: 'main')}" class="back">Go Back</a>

<%--<p style="color: black">${myFile}</p>--%>

<%--<input id="in" type="text" value='${resource(dir: "files", file: "${myFile}")}'>--%>
<%----%>
<%----%>
<%--<script src="${resource(dir: 'assets', file: 'jquery.min.js')}"></script>--%>
<%----%>
<%--<script>--%>
<%--	var contPath = $('#in').val();--%>
<%----%>
<%--	/** Read Text File **/--%>
<%--	--%>
<%--	$.get(contPath, function(data) {--%>
<%--		   console.log(data);--%>
<%--		}, 'text');--%>
<%----%>
<%--</script>--%>

</html>