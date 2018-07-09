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
		
		[totalCount : totalCount,
			noTokens : noTokens]
    }
	
	def getCountbyAuthor() {
		def query =""" select author_first_name,author_last_name,count(author_first_name) from documents_info group by author_first_name,author_last_name order by author_last_name;""";
		def db = new Sql(dataSource);
		
		return db.rows(query)
    }
	
	def getCountbyPubPlace() {
		def query =""" select place,count(place) from documents_info group by place order by place;""";
		def db = new Sql(dataSource);
		
		return db.rows(query)
    }
	
	def getCountbyDecade() {
		def query =""" select edition_year,count(edition_year) from documents_info group by edition_year;""";
		
		Map noDocs=[:]
		Map tmp=[:]
		
		def db = new Sql(dataSource);
		
		db.eachRow(query) { row ->
			def ed_y = row[0];
			def cnt = row[1];
			def dec= (ed_y - 1550).intdiv(10);
			dec= (dec == 20) ? 19:dec;
//			def start= 1550+dec*10;
//			def end= (start==1740)?1750:start+9;
//			String decName=start+" : "+end;
			if(noDocs[dec]){
			 tmp= noDocs[dec]
			}else{
			def start= 1550+dec*10;
			def end= (start==1740)?1750:start+9;
			String decName=start+" : "+end;
			 tmp=[:]
			 tmp['name']=decName;
			 tmp['start']=start;
			 tmp['end']=end
			 tmp['cnt']=0
			}
			
			
			tmp['cnt']+= cnt;
			noDocs[dec]	=tmp;		
		}
		
		def list_results = []
		noDocs.each() { k, v -> list_results << v }
		//System.out.println(list_results.toString());
		
		//return noDocs
		return list_results
    }
	def getCountbyGenre() {
		def query =""" select genre,count(genre) from documents_info group by genre order by genre;""";
		def db = new Sql(dataSource);
		
		return db.rows(query)
    }
}
