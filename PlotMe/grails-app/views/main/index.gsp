<!DOCTYPE html>
<%@page import="java.lang.BootstrapMethodError"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>PlotMe</title>

    <link rel="stylesheet" href="${resource(dir: 'assets', file: 'bootstrap.min.css')}">
    <link rel="stylesheet" href="${resource(dir: 'assets', file: 'bootstrap-theme.min.css')}">

    <link rel="stylesheet" href="${resource(dir: 'assets', file: 'sidebar.css')}">

    <link rel="stylesheet" href="${resource(dir: 'assets', file: 'custom.css')}">   
    

</head>
<body>

<div id="wrapper" class="toggled">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <p>Menu</p>
            </li>
            <li>
                <div>
                    <table>
                        <tr>
                            <td>
                                <label style="display: inline">Upload the data file</label>
                                <div class="form-group lineText">
                                    <input type="file" id="inputFile" name="inputFile" style="display: inline; color: white">
                                    <!--<progress id="prog" value="0" min="0" max="100" style="margin-left: 20px; margin-top: 10px"></progress>-->
                                    <div class="progressBar progress">
                                        <div id="prog" class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50"
                                             aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                                        </div>
                                    </div>
                                    <label id="uploadLabel"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="radio lineText">
                                    <label>
                                        <input type="radio" name="graphOptions" id="optionsRadios1" value="option1" checked>
                                        Graph 1
                                    </label>
                                </div>
                                <div class="radio lineText">
                                    <label>
                                        <input type="radio" name="graphOptions" id="optionsRadios2" value="option2">
                                        Graph 2
                                    </label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="checkbox lineText">
                                    <label style="padding-left: 0px">
                                        <input type="checkbox"> Sorted
                                    </label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><input name="submit" id="submit" type="button" class="btn btn-success" value="Submit"></td>
                        </tr>
                    </table>
                </div>
            </li>
        </ul>
    </div>

    <div id="page-content-wrapper" style="padding-left: 0px; height: 100%">
        <div id="menu-toggle" class="menuToggleBtn" style="width: 15px"> < </div>
        <div style="padding-left: 20px">
            <h1>PlotMe</h1>
        </div>
        <hr style="border-color: black">
        <div class="row" style="padding-left: 20px">
        
        	<div class="col-md-6">
<%--        		<img src="${resource(dir: 'assets/graph-pics', file: 'sequences_pic.png')}" height="200px" width="200px">--%>
<%--        		<a href="${resource(dir: 'main', file: 'titanic.jsp')}">Sequences Graph</a>--%>

				<a href="${resource(dir: 'main', file: 'titanic.jsp')}" 
					class="thumbnail" style="width: 350px; height: 350px">
			      <img src="${resource(dir: 'assets/graph-pics', file: 'sequences_pic.png')}">
			    </a>
        	</div>
        	
        	<div class="col-md-6">
<%--        		<a href="${resource(dir: 'main', file: 'histogram.jsp')}">Histogram Graph</a>--%>

				<a href="${resource(dir: 'main', file: 'histogram.jsp')}" 
					class="thumbnail" style="width: 350px; height: 350px">
			      <img style="margin-top: 25%" src="${resource(dir: 'assets/graph-pics', file: 'histogram_pic.png')}">
			    </a>
        	</div>
        </div>
        
        <div class="row" style="padding-left: 20px">
        	
        	<div class="col-md-6">
<%--        		<a href="${resource(dir: 'main', file: 'pie_chart.jsp')}">Zoomable Pie Chart</a>--%>
				<a href="${resource(dir: 'main', file: 'pie_chart.jsp')}" 
					class="thumbnail" style="width: 350px; height: 350px">
			      <img src="${resource(dir: 'assets/graph-pics', file: 'zoomable_pie_pic.png')}">
			    </a>
        	</div>
        	
        	<div class="col-md-6">
<%--        		<a href="${resource(dir: 'main', file: 'pie_line.jsp')}">Pie Chart and Line Graph</a>--%>

				<a href="${resource(dir: 'main', file: 'pie_line.jsp')}" 
					class="thumbnail" style="width: 350px; height: 350px">
			      <img src="${resource(dir: 'assets/graph-pics', file: 'pie_line_pic.png')}">
			    </a>
        	</div>
        
        </div>
    </div>

</div>

<script src="${resource(dir: 'assets', file: 'jquery.min.js')}"></script>
<script src="${resource(dir: 'assets', file: 'bootstrap.min.js')}"></script>

<script src="${resource(dir: 'assets', file: 'custom.js')}"></script>
<%--<script src="js/uploadFile.js"></script>--%>

</body>
</html>