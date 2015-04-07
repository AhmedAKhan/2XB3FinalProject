<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Sub sequences</title>
    
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600">
    
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'assets', file: 'sequences.css')}"/>
    
</head>

<body>
<h1>Titanic Statistical Data</h1>

<input id="path" type="hidden" value="${resource(dir: 'assets', file: 'Titanic.csv')}">

<div id="abc" data-name="${myJSON}"></div>

<div id="main">
    <div id="sequence"></div>
    <div id="chart">
        <div id="explanation" style="visibility: hidden;">
            <span id="percentage"></span><br/>
            of people had this happen to them
        </div>
    </div>
</div>
<div id="sidebar">
    <input type="checkbox" id="togglelegend" checked> Legend<br/>
    <div id="legend" style="visibility: visible;"></div>
</div>

<script src="http://d3js.org/d3.v3.min.js"></script>

<script src="${resource(dir: 'assets', file: 'jquery.min.js')}"></script>

<script type="text/javascript">
	var data = $('#abc').data();
	var path = $('#path').val();
</script>

<script type="text/javascript" src="${resource(dir: 'assets', file: 'sequences.js')}"></script>

<script type="text/javascript">
    // Hack to make this example display correctly in an iframe on bl.ocks.org
    d3.select(self.frameElement).style("height", "700px");
</script>

</body>
</html>