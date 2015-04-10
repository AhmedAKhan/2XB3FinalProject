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
    
<%--    <r:require module="fileuploader" />--%>
<%--  	<r:layoutResources />--%>

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
                <div style="margin-left: 20px">
                    <p class="menuTitle">Upload the data file</p>

					<div style="padding-bottom: 10px">
						<g:uploadForm action="fileUpload" method="post">
							<input type="file" name="inputFile" style="color: white; line-height: 0px">
							<input type="submit" name="upload" id="upload" value="Upload" class="btn btn-warning">
						</g:uploadForm>
					</div>


					<div>
						<g:uploadForm action="drawGraph" method="post" class="settingsForm" style="display:none">
					
							<p class="menuTitle">Pick a graph</p>
												
					        <div class="radio lineText">
					            <label>
					                <input type="radio" name="graphOptions" id="optionsRadios1" value="bar" checked>
					                Bar Graph
					            </label>
					        </div>
					        <div class="radio lineText">
					            <label>
					                <input type="radio" name="graphOptions" id="optionsRadios2" value="whiskers">
					                Box & Whiskers Graph
					            </label>
					        </div>
					        <div class="radio lineText">
					            <label>
					                <input type="radio" name="graphOptions" id="optionsRadios2" value="pie">
					                Pie Chart
					            </label>
					        </div>
					                            
					                                
					                                
							<label for="independent">Independent Row (x-axis)</label><br/>
							<input id="indepInput" class="axisInput" type="text" name="independent" value="0"><br/>
							<label for="dependent">Dependent Row (y-axis)</label><br/>
							<input id="depInput" class="axisInput" type="text" name="dependent" value="1">
							<br/>
						
							<div class="checkbox lineText">
						   		<label style="padding-left: 0px">
						        	<input name="sorted" type="checkbox"> Sorted
						    	</label>
							</div>
							
							<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-success">
						
						</g:uploadForm>
					</div>
                    
                    <div id="activeFile">File Active: ${activeFile}</div>
                            
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
				<a href="${resource(dir: 'main', file: 'titanic.jsp')}" 
					class="thumbnail" style="width: 350px; height: 350px">
			      <img src="${resource(dir: 'assets/graph-pics', file: 'sequences_pic.png')}">
			    </a>
        	</div>
        	
        	<div class="col-md-6">
				<a href="${resource(dir: 'main', file: 'histogram.jsp')}" 
					class="thumbnail" style="width: 350px; height: 350px">
			      <img style="margin-top: 25%" src="${resource(dir: 'assets/graph-pics', file: 'histogram_pic.png')}">
			    </a>
        	</div>
        </div>
        
        <div class="row" style="padding-left: 20px">
        	
        	<div class="col-md-6">
				<a href="${resource(dir: 'main', file: 'pie_chart.jsp')}" 
					class="thumbnail" style="width: 350px; height: 350px">
			      <img src="${resource(dir: 'assets/graph-pics', file: 'zoomable_pie_pic.png')}">
			    </a>
        	</div>
        	
        	<div class="col-md-6">
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

<script type="text/javascript">
	$(document).ready(function() {
		var activeFile = $('#activeFile');
		var fileStatus = activeFile.text();

		var red = "File Active: No file activated"

		if (fileStatus === red) activeFile.css({'color':'red','text-indent':'0px'});
		else {
			activeFile.css({'color':'green','text-indent':'0px'});
			$('.settingsForm').css('display','initial');
		}

		var submit = $('#submit')
		
		var checkEmptyInput = function() {
				if($('#indepInput').val() != '' && $('#depInput').val() != '') {
		        	submit.removeAttr('disabled');
		        } else {
		        	submit.attr('disabled','disabled'); 
			    }
			}
		
		checkEmptyInput();
		
<%--		submit.attr('disabled','disabled');--%>
		$('.axisInput').keyup(function() {
	        checkEmptyInput();
		});
	     
	});
</script>

<%--<script src="${resource(dir: 'assets', file: 'uploadFile.js')}"></script>--%>

</body>
</html>