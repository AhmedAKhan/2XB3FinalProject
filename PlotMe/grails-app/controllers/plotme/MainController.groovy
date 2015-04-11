package plotme

import grails.converters.JSON

class MainController {

	static Scanner input;
	static Formatter output;
	static pathString = "ERROR";// = "C:/Users/Saim/Documents/workspace-ggts-3.6.4.RELEASE/PlotMe/web-app/files/"//market-share.csv"
	def json;
	
    def index() {
		def activeFile = "No file activated"
		
		if (UploadedFile.count > 0) {
			UploadedFile up = UploadedFile.get(UploadedFile.count)
			activeFile = up.fileName
		}
		
		[activeFile:activeFile]
	}
	
	def titanic() {}
	
	def histogram() {}
	def pie_chart() {}
	def pie_line() {}
	
	def fileUpload() {
		def uploadedFile = request.getFile('inputFile')
		def webRootDir = servletContext.getRealPath("/")
		
		def userDir = new File(webRootDir, "/files")
		userDir.mkdirs()
		
		def x = new File( userDir, uploadedFile.originalFilename)
		
		uploadedFile.transferTo( new File( userDir, uploadedFile.originalFilename))
		
		def name = uploadedFile.originalFilename
		
		pathString = "${webRootDir}".replaceAll("\\\\", "/")
		
		pathString += "files/${name}"
		
		UploadedFile upFile = new UploadedFile(filePath: "${pathString}", fileName:"${name}").save()
	}
	
	
	def drawGraph() {
		def graph = params.graphOptions
		
		def rowORcol = params.rowColOptions
		
		def indepCol = params.independent
		def depCol = params.dependent
		
		def sorted = params.sorted
		def search = params.search
		
		def analyze = params.analyze
		
		if (UploadedFile.count > 0) {
			UploadedFile abc = UploadedFile.get(UploadedFile.count)
			String pathOfFile = "${abc.filePath}"
			int indep = 0;
			if (!("${indepCol}".toString().equals(""))) indep = Integer.parseInt("${indepCol}");
			int dep;
			ArrayList <String[]> myList = Parse.getArray (pathOfFile);
			String rowORcolInverse;

            if ("${rowORcol}".toString().equals("row")) rowORcolInverse = "column";
			else if ("${rowORcol}".toString().equals("col")) rowORcolInverse = "row";

            String[] depArr = "${depCol}".toString().split(",");
			if ("${search}".toString().equals("on")) {
				// Get row (or column) by searching for an input string in the given column (or row)
				dep = Integer.parseInt(depArr[0]);
				
				RedBlackBST<String, Integer> st = Parse.getBST(myList, dep, "${rowORcol}".toString());
				
				String temp = st.get(depArr[1]).toString();
				
				if (temp.equals("null")) {
					render "<h1>Searched input could not be found in given ${rowORcolInverse}</h1>"
					return;
				} else {
					dep = Integer.parseInt(temp);
				}
			} else {
				if (depArr.length != 1) {
					render "<h1>Invalid input (if you want to search, please make sure to check the Search checkbox)</h1>"
					return;
				} else {
					try {
						dep = Integer.parseInt(depArr[0]);
					} catch (Exception e) {
						render "<h1>Invalid input for Dependent ${rowORcol}</h1>"
						return;
					}
				}
			}
			Data a = Parse.getPair(myList, indep, dep, "${rowORcol}".toString());
            if ("${analyze}".toString().equals("Analyze")) {
                Analayzer analayzer = new Analayzer();
                a = analayzer.analyzeGraph(myList, indep, "${rowORcol}".toString());
            }

			if ("${sorted}".toString().equals("on")) {
				a = a.sort();//Parse.getPair(myListSorted, indep, dep);
            }

            json = a as JSON

            if ("${graph}".toString().equals("bar")) redirect(controller: "Main", action: "bar");
            else if ("${graph}".toString().equals("whiskers")) redirect(controller: "Main", action: "whiskers");
            else if ("${graph}".toString().equals("pie")) redirect(controller: "Main", action: "pie");
            else render "Error in redirecting part!"
        } else render "Please upload a file"
    }

	def bar() { [objJSON:json] }
	def whiskers() { [objJSON:json] }
	def pie() { [objJSON:json] }
	
	def analyze() {
		def indep = params.indep
		render "${indep}"
	}
	
	/********************************************************************************************************/
		
	public static void main(String[] args) {
		String pathOfFile = "C:/Users/Saim/Documents/workspace-ggts-3.6.4.RELEASE/PlotMe/web-app/files/market-share.csv";//pathString + "market-share.csv"
		String[] depArr = "1,US".toString().split(",");
		
		println "${depArr}"
		ArrayList <String[]> myList = Parse.getArray (pathOfFile);
		int dep = Integer.parseInt(depArr[0]);

		RedBlackBST<String, Integer> st = Parse.getBST(myList, dep, "row");
		System.out.println(st.get(depArr[1]));
	}
}
