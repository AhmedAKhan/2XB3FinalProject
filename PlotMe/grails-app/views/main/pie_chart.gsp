<!DOCTYPE html>
<html>

<meta charset="utf-8">
<style>
    body {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        margin: auto;
        position: relative;
        width: 960px;
    }
    form {
        position: absolute;
        right: 10px;
        top: 10px;
    }
    path {
        stroke: #fff;
        fill-rule: evenodd;
    }
</style>

<body>
<form>
    <label><input type="radio" name="mode" value="size"> Size</label>
    <label><input type="radio" name="mode" value="count" checked> Count</label>
</form>

<script type="text/javascript">
	var flarePath = "${resource(dir: 'assets', file: 'flare.json')}";
</script>

<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="${resource(dir: 'assets', file: 'zoomableMaluablePieChart.js')}"></script>
</body>

</html>