package ch.unine.ILCF.SERMO

//import groovy.sql.GroovyRowResult
//import groovy.sql.Sql

class GetPeopleController {
	
//	def dataSource // injected data source from application
	//def scaffold = true
	def getPeopleService
	
    def index() {
	//	def db = new Sql(dataSource)
	//	def results = db.rows("SELECT sp_first_name,sp_last_name,sp_mail  FROM sermo_people")
	//	render view: 'index', model: results
		//def sermo_people = People.list()
		
		def sermo_people = getPeopleService.getPeople()
		[sermo_people:sermo_people]
	}
	
	def showPersonInfo(){
		//def person = People.get("LjD")
		def person = People.get(params.id)
		if(person){
			//def person = params.person
		
		render(template : "people/people-info", model:[person:person])
		}else{
		 render "parameter not passed"
		}
	}
	
	def showTime() {
		render "The time is ${new Date()}"
		}
}
