

var margin = {top: 20, right: 40, bottom: 30, left: 20},
    width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom,
    barWidth = Math.floor(width / 19) - 1;

var x = d3.scale.linear()
    .range([barWidth / 2, width - barWidth / 2]);

var y = d3.scale.linear()
    .range([height, 0]);

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("right")
    .tickSize(-width)
    .tickFormat(function(d) { return Math.round(d / 1e6) + "M"; });

var chartSVG = d3.select("#chart")
        .select("svg")
        .attr("width",  width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
    .append("g")
        .attr("transform", "translate("+margin.left+","+margin.top+")");

// A label for the current year.
//var title = chartSVG.append("text")
//    .attr("class", "title")
//    .attr("dy", ".71em")
//    .text(2000);


d3.csv("population.csv", function(error, data) {
    // Convert strings to numbers.
    data.forEach(function(d) {
        d.people = +d.people;
        d.year = +d.year;
        d.age = +d.age;
    });

    // Compute the extent of the data set in age and years.
    var ageMax = d3.max(data, function(d) { return d.age; }),
        yearMin = d3.min(data, function(d) { return d.year; }),
        yearMax = d3.max(data, function(d) { return d.year; }),
        year = yearMin;

    // Update the scale domains.
    x.domain([yearMax - ageMax, yearMax]);
    y.domain([0, d3.max(data, function(d) { return d.people; })]);


    console.log(data);

    // Update the scale domains.
    //x.domain([year1 - age1, year1]);
    //y.domain([0, d3.max(data, function(d) { return d.people; })]);


});



