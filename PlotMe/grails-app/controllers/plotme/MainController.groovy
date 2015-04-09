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
	
	def titanic() {
		Person p = new Person(firstName: "Saim", lastName: "Malik")
		
		def json = p as JSON
		
		[saim:p, myJSON:json]
	}
	
	def histogram() {
		
	}
	
	def pie_chart() {
		
	}
	
	def pie_line() {
		
	}
	
	def fileUpload() {
		def uploadedFile = request.getFile('inputFile')
		def webRootDir = servletContext.getRealPath("/")
		
		def userDir = new File(webRootDir, "/files")
		userDir.mkdirs()
		
		def x = new File( userDir, uploadedFile.originalFilename)
		
		uploadedFile.transferTo( new File( userDir, uploadedFile.originalFilename))
		
		def name = uploadedFile.originalFilename
		
//		pathToFile = "${resource(dir: 'files', file: name)}"
		
		pathString = "${webRootDir}".replaceAll("\\\\", "/")
		
		pathString += "files/${name}"
		
//		System.out.println(pathString)
		
		UploadedFile upFile = new UploadedFile(filePath: "${pathString}", fileName:"${name}").save()
		
//		[myFile:name]
	}
	
	
	def drawGraph() {
		def graph = params.graphOptions
		
		def indepCol = params.independent
		def depCol = params.dependent
		
		def sorted = params.sorted
		
//		System.out.println("SORTED: ${sorted}")
		
		if (UploadedFile.count > 0) {
			UploadedFile abc = UploadedFile.get(UploadedFile.count)
			String pathOfFile = "${abc.filePath}"
			
			int indep = 0;
			
			if (!("${indepCol}".toString().equals(""))) indep = Integer.parseInt("${indepCol}");
			
			int dep = Integer.parseInt("${depCol}")
			
			ArrayList <String[]> myList = Parse.getArray (pathOfFile);
//			ArrayList <String[]> myListSorted = Parse.getArray (pathOfFile);
//			
//			for (String[] s : myListSorted) {
//				Arrays.sort(s);
////				System.out.println(Arrays.toString(s));
//			}
			
			Data a = Parse.getPair(myList, indep, dep);;
			
			if ("${sorted}".toString().equals("on")) {
				a = a.sort();//Parse.getPair(myListSorted, indep, dep);
//				println "ON!"
			}
//			else { 
//				a = Parse.getPair(myList, indep, dep);
//				println "OFF!"
//			}
			
//			System.out.println(a.toString())
			
//			Data p = new Data("name", "x", "y", null);
			//Person p = new Person(firstName: 'Saim', lastName: 'Malik')
			
			
			json = a as JSON
			
//			[objJSON:json]
//			bar()
//			redirect(controller: "Main", action: "bar", xyz:"post", params: [json: "${json}"])
			
			if ("${graph}".toString().equals("bar")) redirect(controller: "Main", action: "bar");
			else if ("${graph}".toString().equals("whiskers")) redirect(controller: "Main", action: "whiskers");
			else if ("${graph}".toString().equals("pie")) redirect(controller: "Main", action: "pie");
			else render "Error in redirecting part!"
			
//			render (view:'bar.gsp')
		} else {
			render "Please upload a file"
		}
		
//		String[] arrDepCols = depCols.split(",")
		
	}
	
	def bar() {
		println "INSIDE BAR"
		[objJSON:json]
	}
	
	def whiskers() {
		println "INSIDE WHISKERS"
		[objJSON:json]
	}
	
	def pie() {
		println "INSIDE PIE"
		[objJSON:json]
	}
	
	/********************************************************************************************************/
	
	// Opens the input file if it exists
	public static void openInputFile(String name) {
		try {
			input = new Scanner(new File(name));
		} catch (Exception e) {
			System.out.println("File not found!");
		}
	}

	// Closes the input file
	public static void closeInputFile() {
		input.close();
	}

	// Creates/opens the output file
	public static void openOutFile(String name) {
		try {
			output = new Formatter(name);
		} catch (Exception e) {
			System.out.println("An error occurred");
		}
	}

	// Closes the output file
	public static void closeOutputFile() {
		output.close();
	}
	
	public static void main(String[] args) {
//		openInputFile(pathString)
//		closeInputFile()
			
		String pathOfFile = "C:/Users/Saim/Documents/workspace-ggts-3.6.4.RELEASE/PlotMe/web-app/files/WDI_Data.csv";//pathString + "market-share.csv"
		
		ArrayList <String[]> myList = Parse.getArray (pathOfFile);
		Data a = Parse.getPair(myList, 0, 47);
		
		System.out.println(a.toString());
	}
}
