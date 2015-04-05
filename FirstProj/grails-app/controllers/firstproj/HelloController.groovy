package firstproj

class HelloController {

    def index() { 
//		render "Welcome to index page"
		
//		Person p = new Person(lastName:'Malik', firstName:'Saim', age:20)
		Person p = new Person('Malik','Saim',20)
//		[person:p]
		
		def list = []
		list << new Person(firstName: 'John', lastName:'Doe', age:50)
		list << new Person(firstName: 'Jane', lastName:'Smith', age:45)
		list << new Person(firstName: 'Sam', lastName:'Robinson', age:47)
		
		[person:p, list:list]
	}
	
	def hi() {
//		render "This is hi page"
		
		/** Display index.gsp when hi is accessed **/
//		render (view:'index.gsp')
		
		Person p = new Person(age:20)
		
		def personList = []
		
		for (int i = 1; i < Person.count(); i++) {
			personList << Person.get(i)
		}
		
//		Person p2 = Person.get(1)
		
		[person:p, persons:personList]
	}
	
	def save() {
		Person p = new Person(params).save()
		render "Last Name: ${p.lastName}, First Name: ${p.firstName}, Age: ${p.age}"
	}
}
