package ch.unine.ILCF.SERMO.filter

import groovy.sql.Sql;

class FilterService {
	
	boolean transactional = false
	
	javax.sql.DataSource dataSource;
	
	List getAuthorFilterDocsList(first,last){
		
     LinkedList result=[]
			
			String query1 ="""select doc_id from documents_info where author_first_name=? and author_last_name=?;""";
			//String query2 ="""select * from documents_info where author_first_name=? and author_last_name=?;""";
		
		def db = new Sql(dataSource);
		
		
		db.eachRow(query1, [first,last]) { row ->
			def docId=row[0];
			result.add(docId)
			
		}
		
		//return db.rows(query1, [first,last]);
		return result;
    }
	
	
	List getEditionFilterDocsList(edition_place){
		
	 LinkedList result=[]
			
			String query1 ="""select doc_id from documents_info where place=?;""";
			//String query2 ="""select * from documents_info where author_first_name=? and author_last_name=?;""";
		
		def db = new Sql(dataSource);
		
		
		db.eachRow(query1, [edition_place]) { row ->
			def docId=row[0];
			result.add(docId)
			
		}
		
		//return db.rows(query1, [first,last]);
		return result;
	}
	List getDecadeFilterDocsList(start,end){
		
	 LinkedList result=[]
			
			String query1 ="""select doc_id from documents_info where edition_year >= ? and edition_year <= ?;""";
			//String query2 ="""select * from documents_info where author_first_name=? and author_last_name=?;""";
		
		def db = new Sql(dataSource);
		
		
		db.eachRow(query1, [start,end]) { row ->
			def docId=row[0];
			result.add(docId)
			
		}
		
		//return db.rows(query1, [first,last]);
		return result;
	}
	List getGenreFilterDocsList(genre){
		
	 LinkedList result=[]
			
			String query1 ="""select doc_id from documents_info where genre = ?;""";
			//String query2 ="""select * from documents_info where author_first_name=? and author_last_name=?;""";
		
		def db = new Sql(dataSource);
		
		
		db.eachRow(query1, [genre]) { row ->
			def docId=row[0];
			result.add(docId)
			
		}
		
		//return db.rows(query1, [first,last]);
		return result;
	}

}
