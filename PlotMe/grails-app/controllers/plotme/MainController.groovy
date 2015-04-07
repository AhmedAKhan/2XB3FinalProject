package plotme

import grails.converters.JSON

class MainController {

    def index() {
		
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
}
