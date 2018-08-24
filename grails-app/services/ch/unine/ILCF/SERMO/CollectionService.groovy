package ch.unine.ILCF.SERMO

//import grails.transaction.Transactional
import groovy.sql.Sql;

//@Transactional
class CollectionService {
	boolean transactional = false
	javax.sql.DataSource dataSource;
	
	
	
    def getDocById(docId) {
		def query =""" select * from documents_info where doc_id=?;""";
		def db = new Sql(dataSource);
		
	    
		return db.rows(query, [docId]);
    }
	
	def getDocParatextById(docId) {
		def query =""" select * from paratext where doc_id=?;""";
		def db = new Sql(dataSource);
		
	    
		return db.rows(query, [docId]);
    }
	
	def getDocTextBibliqueById(docId) {
		def query =""" select * from text_biblique where doc_id=?;""";
		def db = new Sql(dataSource);
		
	    
		return db.rows(query, [docId]);
    }
	
	def getDocs(fd){
		
	  String fds=fd.join("','");
	 
	  String query ="""select * from documents_info where doc_id in ('"""+fds +"""');""";
			//String query2 ="""select * from documents_info where author_first_name=? and author_last_name=?;""";
  
	def db = new Sql(dataSource);


//	db.eachRow(query, [fd.toString()]) { row ->
//		def docId=row[0];
//		result.add(docId)
//
//	}
  
  return db.rows(query);
  
	  
  }
  
  def getAllDocs(){


	  String query ="""select * from documents_info;""";


		def db = new Sql(dataSource);


		return db.rows(query)

	}
}
