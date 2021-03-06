<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="${resource(dir: 'assets', file: 'jquery-1.8.2.min.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'assets', file: 'crossfilter.v1.js')}"></script>
    
    <script type="text/javascript" src="http://d3js.org/d3.v3.min.js"></script>
    
    <script type="text/javascript" src="${resource(dir: 'assets', file: 'd3.crossfilter.ay-histogram.js')}"></script>

    <script type="text/javascript">
        var get_random_data	= function()
        {
            var data	= [];
            var getRandomInt	= function(min, max)
            { return Math.floor(Math.random() * (max - min + 1)) + min;};

            for(var i = 0; i < 10000; i++)
            {
                data.push({
                    id: i,
                    date_wide: new Date(2012, getRandomInt(5,9), getRandomInt(1,30), getRandomInt(0,23)),
                    date_narrow: new Date(2012, getRandomInt(8,9), getRandomInt(1,30), getRandomInt(0,23)),
                    a: getRandomInt(0,4999),
                    b: getRandomInt(0,49999)
                });
            }
            return data;
        };

        $(function(){
            var filter	= crossfilter(get_random_data());
            var data	= {
                date_wide:
                {dimension: filter.dimension(function(d){ return d.date_wide; }) },
                date_narrow: {dimension: filter.dimension(function(d){ return d.date_narrow; })},
                a: {dimension: filter.dimension(function(d){ return d.a; })},
                b: {dimension: filter.dimension(function(d){ return d.b; })}
            };

            data.date_wide.group	= data.date_wide.dimension.group(function(d){ return d3.time.day(d); });
            data.date_narrow.group	= data.date_narrow.dimension.group(function(d){ return d3.time.hour(d); });
            data.a.group			= data.a.dimension.group(function(d){ return Math.floor(d / 100)*100; });
            data.b.group			= data.b.dimension.group(function(d){ return Math.floor(d / 1000)*1000; });

            var date_wide	= ay_histogram('histogram-date-wide', data.date_wide, {margin: [10, 10]});
            var date_narrow	= ay_histogram('histogram-date-narrow', data.date_narrow, {margin: [10, 10], bin_width: 3600*1000});
            var a			= ay_histogram('histogram-a', data.a, {margin: [10, 10], bin_width: 100});
            var b			= ay_histogram('histogram-b', data.b, {margin: [10, 10], bin_width: 1000});

            date_wide.setRelations([date_narrow,a,b]);
            date_narrow.setRelations([date_wide,a,b]);
            a.setRelations([date_wide,date_narrow,b]);
            b.setRelations([date_wide,date_narrow,a]);
        });
    </script>

    <style>
        html, body { margin: 0; padding: 0; font: 12px/20px monospace; }
        .authorship { margin: 20px; }
        .graph-wrapper { overflow: hidden; margin: 20px; }

        svg { float: left; width: 100%; height: 200px; -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; }

        g.background rect,
        g.foreground rect { fill: #4581b3; stroke: none; shape-rendering: crispEdges; }
        g.foreground rect { fill: #ccc; }

        g.axis line,
        g.axis path { fill: none; stroke: #000; stroke-width: 1px; shape-rendering: crispEdges; stroke-linecap: butt; stroke-linejoin: miter; }
        g.brush rect.extent { fill: steelblue; fill-opacity: .125; }

        g.brush .resize path { fill: #eee; stroke: #666; }

        .delays,
        .distances { margin-left: 20px; }

        .clippath-graph-dates rect { fill: #f00; }

        rect.scrollbar { fill: #fefefe; stroke: #666; stroke-width: 1px; shape-rendering: crispEdges; }
        g.axis { pointer-events: none; }

        .graph-container { overflow: hidden; margin: 20px; }

        .column,
        .column-a,
        .column-b,
        .column-c { background: #eee; padding: 10px; box-sizing: border-box; overflow: hidden; }

        .column-a { float: left; width: 33%; margin-right: 20px; }
        .column-c { float: right; width: 33%; margin-left: 20px; }

        .column-b { margin: 0 20px; }
    </style>
</head>
<body>
<div class="graph-container">
    <div class="column">
        <svg class="histogram-date-wide"></svg>
    </div>
</div>
<div class="graph-container">
    <div class="column-a">
        <svg class="histogram-date-narrow"></svg>
    </div>
    <div class="column-c">
        <svg class="histogram-a"></svg>
    </div>
    <div class="column-b">
        <svg class="histogram-b"></svg>
    </div>
</div>

<div class="authorship">
    This is a demo of the <a href="https://github.com/gajus/interdependent-interactive-histograms" target="_blank">histogram plugin</a>.
</div>
</body>
</html>