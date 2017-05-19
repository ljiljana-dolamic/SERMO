package ch.unine.ILCF.SERMO.statistics

//import grails.transaction.Transactional
import groovy.sql.Sql;

class CollectionStatsService {
	boolean transactional = false
	
	javax.sql.DataSource dataSource;
	
    Map docTokenCount(docId) {
		
		String query ="""select section_id, count(*)  from token_body  where doc_id =? group by section_id;""";
		
		def db = new Sql(dataSource);
		
		return db.rows(query, [docId]).collectEntries{ row -> [row[0],row[1]]}
    }
	
	Map docTagCount(docId) {
		
		String query ="""select tag_name, count(*) from tag_body where doc_id =? group by  tag_name; """;
		
		def db = new Sql(dataSource);
		
		return db.rows(query, [docId]).collectEntries{ row -> [row[0],row[1]]}
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
		db.eachRow(query) { row ->
			def tCount=row[0];
			noTokens[row[1]]= tCount;
			totalCount+=tCount;
			
		}
		[totalCount : totalCount,
			noTokens : noTokens]
    }
	
	def getCountbyAuthor() {
		def query =""" select author_first_name,author_last_name,count(author_first_name) from documents_info group by author_first_name,author_last_name;""";
		def db = new Sql(dataSource);
		
		return db.rows(query)
    }
}
