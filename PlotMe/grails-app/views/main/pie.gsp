<!DOCTYPE html>
<html>

<head>

<link rel="stylesheet" href="${resource(dir: 'assets', file: 'bootstrap.min.css')}">
<link rel="stylesheet" href="${resource(dir: 'assets', file: 'bootstrap-theme.min.css')}">

<style type="text/css">
	.back {
		font-size: large;
	}
	.back:hover {
		color: red;
	}
</style>

</head>

<body>

<h1>Pie Chart</h1>
<div>
	<a href="${resource(dir: 'main')}" class="back">Go Back</a>
</div>
<hr>

<div id="jsonObject" data-name="${objJSON}"></div>

<div class="row">

	<div class="col-md-8">
		<div id="main">
		    <div id="sequence"></div>
		    <div id="chart">
		        <div id="explanation" style="visibility: hidden;">
		            <span id="percentage" style="display:none"></span><br/>
		<%--            of people had this happen to them--%>
		        </div>
		    </div>
		</div>
	</div>
		
	<div class="col-md-4">
		<div id="sidebar">
		    <input type="checkbox" id="togglelegend" checked> Legend<br/>
		    <div id="legend" style="visibility: visible;"></div>
		</div>
	</div>

</div>


<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="${resource(dir: 'assets', file: 'jquery.min.js')}"></script>

<script type="text/javascript">
	var dataJSON = $('#jsonObject').data().name;
	console.log("before: " + dataJSON);
</script>

<script src="${resource(dir: 'assets', file: 'pieChart.js')}"></script>

</body>

</html>

