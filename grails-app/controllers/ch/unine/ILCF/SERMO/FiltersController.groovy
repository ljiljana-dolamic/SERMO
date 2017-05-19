package ch.unine.ILCF.SERMO

import ch.unine.ILCF.SERMO.statistics.CollectionStatsService;

class FiltersController {
	CollectionStatsService collectionStatsService
    def index() { }
	
	def countByAuthor = {
		def result = collectionStatsService.getCountbyAuthor();
		[
			countByAuthor:result
		]
	}
}
