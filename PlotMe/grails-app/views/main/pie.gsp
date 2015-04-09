<!DOCTYPE html>
<html>

<head>



</head>

<body>

<div>pie chart</div>

<div id="jsonObject" data-name="${objJSON}"></div>

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
var data = $('#jsonObject').data().name;
</script>

<script src="${resource(dir: 'assets', file: 'pieChart.js')}"></script>

</body>

</html>

