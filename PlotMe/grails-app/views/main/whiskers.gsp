<!DOCTYPE html>
<html>

<head>
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <style>
    body {  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;  }
    .box {  font: 10px sans-serif;  }
    .box .center {  stroke-dasharray: 3,3;  }
    .box line,
    .box rect,
    .box circle {
        fill: #fff;
        stroke: #000;
        stroke-width: 1.5px;
    }
    .box .outlier {
        fill: none;
        stroke: #ccc;
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
<h1>Box and Whisker Graph</h1>
<div>
	<a href="${resource(dir: 'main')}" class="back">Go Back</a>
</div>
<hr>

<div id="chart">

</div>

<ul>
    <li>middle line of box = median</li>
    <li>edges of box represents the quartile value</li>
    <li>the line after the dashed line represents the minimum and maximum value (excluding outliers)</li>
    <li>the circles represents the outliers</li>
</ul>
<%--%{--<div>${objJSON}</div>--}%--%>

<div id="jsonObject" data-name="${objJSON}"></div>

<script src="${resource(dir: 'assets', file: 'jquery.min.js')}"></script>
<script src="${resource(dir: 'assets', file: 'box.js')}"></script>
%{--<script src="box.js"></script>--}%

<script>
    var data = $('#jsonObject').data().name;

    var margin = {top: 10, right: 50, bottom: 20, left: 50},
            width = 120 - margin.left - margin.right,
            height = 500 - margin.top - margin.bottom;

    var min = Infinity,
            max = -Infinity;

    var chart = d3.box()
            .whiskers(iqr(1.5))
            .width(width)
            .height(height);

    // takes in a json object in the format where
    /*
     the data variable contains the following.
     [
     [(first data set )],
     [(second data set)],
     ...]
     All the different data sets will be displayed as different box and whisker graphs
     */
//    d3.json("input.json", function(error, data){
    data = formatData(data);
    findMinAndMax(data);
    chart.domain([min, max]);

    var svg = d3.select("#chart").selectAll("svg")
            .data(data)
            .enter().append("svg")
            .attr("class", "box")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.bottom + margin.top)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
            .call(chart);

//    });

    function formatData(data){
        data = data.data;
        var newData = [];
        for(var i = 0; i < data.length; i++){
            newData.push(data[i].value);
        }
        console.log(newData);
        newData = [newData];
        return newData;
    }
    //this function just simply gets the minimum and maximum value
    function findMinAndMax(data){
        for(var c1 = 0; c1 < data.length; c1++){
            for(var c2 = 0; c2<data[c1].length; c2++){
                if(min > data[c1][c2]) min = data[c1][c2];
                else if(max < data[c1][c2]) max = data[c1][c2];
            }
        }
    }

    // Returns a function to compute the interquartile range.
    function iqr(k) {
        return function(d, i) {
            var q1 = d.quartiles[0],
                    q3 = d.quartiles[2],
                    iqr = (q3 - q1) * k,
                    i = -1,
                    j = d.length;
            while (d[++i] < q1 - iqr);
            while (d[--j] > q3 + iqr);
            return [i, j];
        };
    }
</script>

</body>

</html>