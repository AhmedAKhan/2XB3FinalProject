/**
 * Created by ahmed on 4/1/15.
 */

// Dimensions of sunburst.
var width = 750;
var height = 600;
var radius = Math.min(width, height) / 2;

//creates a linear map between 0 to 2*Pi. meaning all the x values from one end to the other
var x = d3.scale.linear()
    .domain([0, 2*Math.PI])
    .range([0, 2 * Math.PI]);

//does a mapping for the y axis as well
var y = d3.scale.sqrt()//d3.scale.sqrt()
    .domain([0, radius*radius])
    .range([0, radius]);

// Breadcrumb dimensions: width, height, spacing, width of tip/tail.
var b = {w: 75, h: 30, s: 3, t: 10 };

// Mapping of step names to colors.
var colors = {
    "Survived": "#007E00",
    "Deceased":"#D30000",
    "Men": "#0D0893",
    "Women": "#862D63",
    "Children": "#226666",

    "FirstClass":"#aa6c39",
    "SecondClass":"#aa8e39",
    "ThirdClass":"#AA8739",
    "Crew":"#91a437",
    "root":"#ffffff"
};

// Total size of all segments; we set this later, after loading the data.
var totalSize = 0;

var vis = d3.select("#chart").append("svg:svg")
    .attr("width", width)
    .attr("height", height)
    .append("svg:g")
    .attr("id", "container")
    .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

var partition = d3.layout.partition()
    .size([2 * Math.PI, radius * radius])
    .value(function(d) { return d.size; });

var arc = d3.svg.arc()
    .startAngle(function(d) {   return Math.max(0, Math.min(2 * Math.PI, x(d.x))); })
    .endAngle(function(d) {     return Math.max(0, Math.min(2 * Math.PI, x(d.x + d.dx))); })
    .innerRadius(function(d) {  return Math.max(0, y(d.y)); })
    .outerRadius(function(d) {  return Math.max(0, y(d.y + d.dy)); });

var node;

//var data = document.getElementById('saim').value;
//var data = ${myJSON};

/** data is the variable with the JSON object **/

console.log(data);
console.log(typeof(data));
// Use d3.text and d3.csv.parseRows so that we do not need to have a header
// row, and can receive the csv as an array of arrays.
//d3.text("visit-sequences.csv", function(text) {

/** path is the variable with path to Titanic.csv file **/

d3.text(path, function(text){
    var csv = d3.csv.parseRows(text);
    var json = buildHierarchy(csv);
    createVisualization(json);
});

// Main function to draw and set up the visualization, once we have the data.
function createVisualization(json) {
    node = json;
    console.log("node");
    console.log(node);
    // Basic setup of page elements.
    initializeBreadcrumbTrail();
    drawLegend();
    d3.select("#togglelegend").on("click", toggleLegend);

    // Bounding circle underneath the sunburst, to make it easier to detect
    // when the mouse leaves the parent g.
    vis.append("svg:circle")
        .attr("r", radius)
        .style("opacity", 0);


    // For efficiency, filter nodes to keep only those large enough to see.
    var nodes = partition.nodes(json)
        .filter(function(d) {
            return (d.dx > 0.005); // 0.005 radians = 0.29 degrees
        });

    console.log("nodes");
    console.log(nodes);
    var root = json;
    console.log("2 printing root properties name: " + root.name + " x: "+root.x + " y: " + root.y + " depth: " + root.depth);

    //console.log("printing nodes");
    //console.log(nodes);
    //console.log("printing root node");
    //console.log(nodes[0]);
    //console.log("printing paths");

    var path = vis.data([json]).selectAll("path")
        .data(nodes)
        .enter().append("svg:path")
        .attr("display", function(d) { return null;})//return d.depth ? null : "none"; })
        .attr("d", arc)
        .attr("fill-rule", "evenodd")
        .style("fill", function(d) { return colors[d.name]; })
        .style("opacity", 1)
        .style("z-index", 1)
        .on("mouseover", mouseover)
        .on("click", click)             //just added
        .each(stash);                   //just added

    console.log(path);
    console.log("printing out path[0][0]");
    console.log(path[0][0]);

    console.log("printing out path[0][1]");
    console.log(path[0][1]);

    d3.select("#container").on("mouseleave", mouseleave);

    // Get total size of the tree = value of root node from partition.
    totalSize = path.node().__data__.value;

    function click(clickedNode) {
        path.transition()
            .duration(1000)
            .attrTween("d", arcTweenZoom(clickedNode));

        node = clickedNode;
    }
};


// Setup for switching data: stash the old values for transition.
function stash(d) {
    d.x0 = d.x;
    d.dx0 = d.dx;
}

// When zooming: interpolate the scales.
function arcTweenZoom(d) {
    var xd = d3.interpolate(x.domain(), [d.x, d.x + d.dx]),
        yd = d3.interpolate(y.domain(), [d.y, radius*radius]), //1]),
        yr = d3.interpolate(y.range(), [d.y ? 20:0, radius]);//20 : 0, radius]);

    //this function takes in the data and that data's position and returns the
    return function(d, i) {
        return i
            ? function(t) {return arc(d);}
            : function(t) { x.domain(xd(t)); y.domain(yd(t)).range(yr(t)); return arc(d); };
    };
}
/*function arcTweenZoom(clickedNode){
    //return : (d,i)->(t)->(svg path)

    return function(d,i){
        if(arrayContains(getAncestors(d), clickedNode)) return function(t) { return arc(d); };
        else return function(t){ return d3.svg.arc(); };
    };

    function arrayContains(arr, obj) {
        for (var i = 0; i < arr.length; i++)
            if (arr[i] === obj) return true;
        return false;
    }
};*/


// Fade all but the current sequence, and show it in the breadcrumb trail.
function mouseover(d) {
    var percentage = (100 * d.value / totalSize).toPrecision(3);
    var percentageString = percentage + "%";
    if (percentage < 0.1) {
        percentageString = "< 0.1%";
    }

    d3.select("#percentage")
        .text(percentageString)
        .style("z-index", "10");

    d3.select("#explanation")
        .style("visibility", "visible");

    var sequenceArray = getAncestors(d);
    updateBreadcrumbs(sequenceArray, percentageString);

    // Fade all the segments.
    d3.selectAll("path")
        .style("opacity", 0.3);

    // Then highlight only those that are an ancestor of the current segment.
    vis.selectAll("path")
        .filter(function(node) {
            return (sequenceArray.indexOf(node) >= 0);
        })
        .style("opacity", 1);
}

// Restore everything to full opacity when moving off the visualization.
function mouseleave(d) {

    // Hide the breadcrumb trail
    d3.select("#trail")
        .style("visibility", "");

    // Deactivate all segments during transition.
    d3.selectAll("path").on("mouseover", null);

    // Transition each segment to full opacity and then reactivate it.
    d3.selectAll("path")
        .transition()
        .duration(1000)
        .style("opacity", 1)
        .each("end", function() {
            d3.select(this).on("mouseover", mouseover);
        });

    d3.select("#explanation")
        .style("visibility", "hidden");
}

// Given a node in a partition layout, return an array of all of its ancestor
// nodes, highest first, but excluding the root.
function getAncestors(node) {
    var path = [];
    var current = node;
    while (current.parent) {
        path.unshift(current);  // adds the new parent element in the path array
        current = current.parent;// makes the current element the parent
    }
    return path;
}

function initializeBreadcrumbTrail() {
    // Add the svg area.
    var trail = d3.select("#sequence").append("svg:svg")
        .attr("width", width)
        .attr("height", 50)
        .attr("id", "trail");

    // Add the label at the end, for the percentage.
    trail.append("svg:text")
        .attr("id", "endlabel")
        .style("fill", "#000");
}

// Generate a string that describes the points of a breadcrumb polygon.
function breadcrumbPoints(d, i) {
    var points = [];
    points.push("0,0");
    points.push(b.w + ",0");
    points.push(b.w + b.t + "," + (b.h / 2));
    points.push(b.w + "," + b.h);
    points.push("0," + b.h);
    if (i > 0) { // Leftmost breadcrumb; don't include 6th vertex.
        points.push(b.t + "," + (b.h / 2));
    }
    return points.join(" ");
}

// Update the breadcrumb trail to show the current sequence and percentage.
function updateBreadcrumbs(nodeArray, percentageString) {

    // Data join; key function combines name and depth (= position in sequence).
    var g = d3.select("#trail")
        .selectAll("g")
        .data(nodeArray, function(d) { return d.name + d.depth; });

    // Add breadcrumb and label for entering nodes.
    var entering = g.enter().append("svg:g");

    entering.append("svg:polygon")
        .attr("points", breadcrumbPoints)
        .style("fill", function(d) { return colors[d.name]; });

    entering.append("svg:text")
        .attr("x", (b.w + b.t) / 2)
        .attr("y", b.h / 2)
        .attr("dy", "0.35em")
        .attr("text-anchor", "middle")
        .text(function(d) { return d.name; });

    // Set position for entering and updating nodes.
    g.attr("transform", function(d, i) {
        return "translate(" + i * (b.w + b.s) + ", 0)";
    });

    // Remove exiting nodes.
    g.exit().remove();

    // Now move and update the percentage at the end.
    d3.select("#trail").select("#endlabel")
        .attr("x", (nodeArray.length + 0.5) * (b.w + b.s))
        .attr("y", b.h / 2)
        .attr("dy", "0.35em")
        .attr("text-anchor", "middle")
        .text(percentageString);

    // Make the breadcrumb trail visible, if it's hidden.
    d3.select("#trail")
        .style("visibility", "");

}

function drawLegend() {

    // Dimensions of legend item: width, height, spacing, radius of rounded rect.
    var li = {w: 75, h: 30, s: 3, r: 3 };

    var legend = d3.select("#legend").append("svg:svg")
        .attr("width", li.w)
        .attr("height", d3.keys(colors).length * (li.h + li.s));

    var g = legend.selectAll("g")
        .data(d3.entries(colors))
        .enter().append("svg:g")
        .attr("transform", function(d, i) {
            return "translate(0," + i * (li.h + li.s) + ")";
        });

    g.append("svg:rect")
        .attr("rx", li.r)
        .attr("ry", li.r)
        .attr("width", li.w)
        .attr("height", li.h)
        .style("fill", function(d) { return d.value; });

    g.append("svg:text")
        .attr("x", li.w / 2)
        .attr("y", li.h / 2)
        .attr("dy", "0.35em")
        .attr("text-anchor", "middle")
        .text(function(d) { return d.key; });
}

function toggleLegend() {
    var legend = d3.select("#legend");
    if (legend.style("visibility") == "hidden") {
        legend.style("visibility", "");
    } else {
        legend.style("visibility", "hidden");
    }
}

// Take a 2-column CSV and transform it into a hierarchical structure suitable
// for a partition layout. The first column is a sequence of step names, from
// root to leaf, separated by hyphens. The second column is a count of how
// often that sequence occurred.
function buildHierarchy(csv) {
    var root = {"name": "root", "children": []};
    for (var i = 0; i < csv.length; i++) {
        var sequence = csv[i][0];
        var size = +csv[i][1];
        if (isNaN(size)) { // e.g. if this is a header row
            continue;
        }
        var parts = sequence.split("-");
        var currentNode = root;
        for (var j = 0; j < parts.length; j++) {
            var children = currentNode["children"];
            var nodeName = parts[j];
            var childNode;

            if (j + 1 < parts.length) {
                // Not yet at the end of the sequence; move down the tree.
                var foundChild = false;
                for (var k = 0; k < children.length; k++) {
                    if (children[k]["name"] == nodeName) {
                        childNode = children[k];
                        foundChild = true;
                        break;
                    }
                }
                // If we don't already have a child node for this branch, create it.
                if (!foundChild) {
                    childNode = {"name": nodeName, "children": []};
                    children.push(childNode);
                }
                currentNode = childNode;
            } else {
                // Reached the end of the sequence; create a leaf node.
                childNode = {"name": nodeName, "size": size};
                children.push(childNode);
            }
        }
    }

    //define the properties of the root
    root.x = 0;
    root.dx = Math.PI*2;
    root.y = 0;
    root.dy = 10;
    root.x0 = 0;
    root.color = "#ffffff";

    console.log("printing root properties name: " + root.name + " x: "+root.x + " y: " + root.y + " depth: " + root.depth);
    return root;
};
