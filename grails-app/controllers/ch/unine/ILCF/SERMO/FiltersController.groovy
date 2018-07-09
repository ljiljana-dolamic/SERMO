package ch.unine.ILCF.SERMO

import ch.unine.ILCF.SERMO.statistics.CollectionStatsService;
import ch.unine.ILCF.SERMO.filter.FilterService;


class FiltersController  {
	CollectionStatsService collectionStatsService
	FilterService filterService
    def index() { }
	
	def countByAuthor = {
		def result = collectionStatsService.getCountbyAuthor();
		def filter = params.filter;
//		params.remove('filter')
		[
			count:result.size() ,countByAuthor:result, filter: filter
		]
	}
	
	def countByPubPlace = {
		def result = collectionStatsService.getCountbyPubPlace();
		[
			count:result.size() ,countByPubPlace:result
		]
	}
	
	def countByDecade = {
		def result = collectionStatsService.getCountbyDecade();
		
		[
			count:result.size() ,countByDecade:result
		]
	}
	
	def countByGenre = {
		def result = collectionStatsService.getCountbyGenre();
		[
			count:result.size() ,countByGenre:result
		]
	}
	
	def addAuthorToFilter = {
		def first_name= params.first_name;
		def last_name= params.last_name;
		
		
		redirect(controller:'collection', params:[first_name:first_name,last_name:last_name])
	
	}
	def addEditionPlaceFilter = {
		def ep= params.place_edition;
		
		
		redirect(controller:'collection', params:[place_edition:ep])
	
	}
	def addDecadeToFilter = {
		def dc= params.start;
		def de= params.end;
		
		
		
		redirect(controller:'collection', params:[decade_start:dc,decade_end:de ])
	
	}
	def addGenreToFilter = {
		def genre= params.genre;
		
		
		
		redirect(controller:'collection', params:[genre:genre ])
	
	}
}
