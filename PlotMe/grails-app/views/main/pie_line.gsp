<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Skills sunburst</title>
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'assets', file: 'skill.css')}"/>
</head>
<body>
<div id="skills">
    <div id="skillmap">
        <div class="skills-wrapper">
            <div class="skills-sunburst"></div>
            <div class="skills-chart">
                <div id="skills-chart-breadcrumb"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${resource(dir: 'assets', file: 'jquery-2.1.3.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'assets', file: 'skillsdata.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'assets', file: 'skill.js')}"></script>
<script type="text/javascript">
    // Hack to make this example display correctly in an iframe on bl.ocks.org
    d3.select(self.frameElement).style("height", "700px");
</script>
</body>
</html>