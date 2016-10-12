package ch.unine.ILCF.SERMO.statistics

//import grails.transaction.Transactional
import groovy.sql.Sql;

class CollectionStatsService {
	boolean transactional = false
	
	javax.sql.DataSource dataSource;
	
    Map getDocBaseStats(docId) {
		
		String query ="""select distinct(sub_id),section_id from token_body where  doc_id =?;""";
		
		def db = new Sql(dataSource);
		
		return db.rows(query, [docId]).collectEntries{ row -> [row[0],[row[1],row[2]]]}
    }
	
	Map getCollBaseStats() {
		
		def query ="""select count(token),section_id  from token_body group by section_id;""";
		
		def db = new Sql(dataSource);
		def totalCount = 0;
		Map noTokens=[:]
		db.eachRow(query) { row ->
			def tCount=row[0];
			noTokens[row[1]]= tCount;
			totalCount+=tCount;
			
		}
		
		[totalCount : totalCount,
			noTokens : noTokens]
    }
}
