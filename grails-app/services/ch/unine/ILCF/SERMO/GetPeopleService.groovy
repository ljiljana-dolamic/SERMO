package ch.unine.ILCF.SERMO
import grails.transaction.Transactional

@Transactional
class GetPeopleService {
	boolean transactional = false
	
    def getPeople() {
		
		def sermo_people = People.list()
		
		return sermo_people

    }
}
