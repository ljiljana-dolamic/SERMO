package ch.unine.ILCF.SERMO.filter

import groovy.sql.Sql;

class FilterService {
	
	boolean transactional = false
	
	javax.sql.DataSource dataSource;
	
	Map getFilterDocList(first,last){
		
	Map result=[:]
			
			String query1 ="""select doc_id from documents_info where author_first_name=? and author_last_name=?;""";
			String query2 ="""select * from documents_info where author_first_name=? and author_last_name=?;""";
		
		def db = new Sql(dataSource);
		result["fd_id"]=db.rows(query1, [first,last]);
		result["fd_list"]=db.rows(query2, [first,last]);
		//return db.rows(query, [first,last]);
		
		return result;
    }
	

}
