/**
 * Created by ahmed on 4/3/15.
 */

var width = 300;
var height = 300;

var arc = d3.svg.arc()
    .startAngle(function(d) {  0 })//return Math.max(0, Math.min(2 * Math.PI, x(d.x))); })
    .endAngle(function(d) {    2 })//return Math.max(0, Math.min(2 * Math.PI, x(d.x + d.dx))); })
    .innerRadius(function(d) { 0 })//return Math.max(0, y(d.y)); })
    .outerRadius(function(d) { 2 })//return Math.max(0, y(d.y + d.dy)); });

var vis = d3.select("svg")
        .attr("height", 300)
        .attr("width", 300)


/*
d3.select("svg")
        .attr("height", 300)
        .attr("width", 300)
    .append("svg:rect")
        .attr("x","0")
        .attr("y","0")
        .attr("width","100")
        .attr("height","100")
        .attr("fill","#000000");
*/


/*d3.select("svg")
    .append("path")
 .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")")
        .attr("d", d3.svg.arc()
            .startAngle(Math.PI/4)
            .endAngle(Math.PI)
            .innerRadius(30)
            .outerRadius(50)
        );*/

//# svg:rect x="0" y="0" width="0" height="0" rx="0" ry="0"


console.log(vis);