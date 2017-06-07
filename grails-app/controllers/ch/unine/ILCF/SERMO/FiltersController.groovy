package ch.unine.ILCF.SERMO

import ch.unine.ILCF.SERMO.statistics.CollectionStatsService;
import ch.unine.ILCF.SERMO.filter.FilterService;


class FiltersController extends SermoAbstractController {
	CollectionStatsService collectionStatsService
	FilterService filterService
    def index() { }
	
	def countByAuthor = {
		def result = collectionStatsService.getCountbyAuthor();
		[
			count:result.size() ,countByAuthor:result
		]
	}
	
	def countByPubPlace = {
		def result = collectionStatsService.getCountbyPubPlace();
		[
			count:result.size() ,countByPubPlace:result
		]
	}
	
	def addAuthorToFilter = {
		def fd = filterService.getFilterDocsList(params.first_name,params.last_name) ;
	//	def fd;
//		if(null == session.getAttribute(filteredDocs)){
		if(session["filteredDocs"].equals(null)){
			System.out.println("session fd is null");}
		//	def fd = ['_1561_Jean_Calvin_CiM'].toSet();
//		 
//		} else {
//		fd = session.getAttribute(filteredDocs);
		//fd.add('_1561_Jean_Calvin_CiM');
	//	}
		session["filteredDocs"] = fd
		Map author=[:];
	    author= params;
		System.out.println(session);
	 //   System.out.println(session.filteredDocs)
//		[ aC:author.first_name
//			]
	}
}
