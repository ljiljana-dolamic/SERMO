package ch.unine.ILCF.SERMO

class Collection {
	String id
	String authorFirstName
	String authorLastName
	String docName
	int editionYear
	
	static mapping = {
		version false
		table "documents_info"
		id column: 'doc_id'
		authorFirstName column: 'author_first_name'
		authorLastName column: 'author_last_name'
		docName column: 'doc_name'
		editionYear column: 'edition_year', sqlType:'smallint'
	}

    static constraints = {
    }
}
