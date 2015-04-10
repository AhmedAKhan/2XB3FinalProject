<!DOCTYPE html>
<html>

<head>
	<style>
        .bar {          fill: steelblue;        }
        .bar:hover{     fill:brown;             }
        .axis text {    font: 10px sans-serif;  }
        .axis {         font: 10px sans-serif;  }
        .x.axis path {  display: none;          }
        .axis path,
        .axis line {
            fill: none;
            stroke: #000;
            shape-rendering: crispEdges;
        }
        
        .back {
		font-size: large;
		}
		.back:hover {
			color: red;
		}
        
    </style>
</head>

<body>

<h1>Bar Graph</h1>
<div>
	<a href="javascript:history.back()" class="back">Go Back</a>
</div>
<hr>

<div id="abc" data-name="${objJSON}"></div>

<section>
    <svg class="chart"></svg>
</section>

<%--<g:uploadForm action="drawGraph" method="post">--%>
<%--	<div class="checkbox lineText">--%>
<%--   		<label style="padding-left: 0px">--%>
<%--        	<input name="sorted" type="checkbox"> Sorted--%>
<%--        	<br>--%>
<%--        	<input type="submit" value="Submit" name="submit">--%>
<%--    	</label>--%>
<%--	</div>--%>
<%--</g:uploadForm>--%>

<script src="${resource(dir: 'assets', file: 'jquery.min.js')}"></script>

<script src="http://d3js.org/d3.v3.min.js"  charset="utf-8"></script>

<script>
	var data = $('#abc').data();
	console.log("before: " + data);
</script>

<script>
    //define all the variables
    var margin = {top: 20, right: 30, bottom: 30, left: 40},
            width = 960 - margin.left - margin.right,
            height = 500 - margin.top - margin.bottom;
    //define x axis for the given coorindates, with a mergin
    var x = d3.scale.ordinal()
            .rangeRoundBands([0,width], 0.1);
    //define the y axis for the given coordinates, with a margin
    var y = d3.scale.linear()
            .range([height, 0]);
    //makes an x axis variable
    var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom");
    var yAxis = d3.svg.axis()
            .scale(y)
            .orient("left")
            .ticks(10);
    //creates the chart
    var chart = d3.select(".chart")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
        .append("g")
            .attr("transform", "translate("+margin.left+","+margin.top+")");
//    d3.tsv("data.tsv", type, function(error, data) {
	
	/** data IS THE JSON OBJECT **/
	
    //d3.json(data, function(error, data){
        console.log(data);
        data = data.name;
//        gives the x and y domain.
        x.domain(data.data.map(function(data){ return data.name; }));
        y.domain([0, d3.max(data.data, function(data) { return data.value; })]);
        //add the x axis
        chart.append("g")
                .attr("class", "x axis")
                .attr("transform", "translate(0,"+height+")")
                .call(xAxis);
        //add the y axis
        chart.append("g")
                .attr("class", "y axis")
                .call(yAxis)
            .append("text")
                .attr("transform", "rotate(-90)")
                .attr("y", 6)
                .attr("dy", ".71em")
                .style("text-anchor", "end")
                .text("Frequency");
        console.log(data);
        console.log("width: " + width);
        console.log("x.rangeBand(): " + x.rangeBand());
        //creates the individual bars
        chart.selectAll(".bar")
                .data(data.data)
                .enter().append("rect")
                .attr("class", "bar")
                .attr("x", function(d){ return x(d.name) })
                .attr("y", function(d){ return y(d.value) })
                .attr("height", function(d){ return height - y(d.value) })
                .attr("width", 100);//x.rangeBand());
    //converts a given string to a value
    function type(d) {
        d.value = +d.value; // coerce to number
        return d;
    }
</script>

</body>

</html>