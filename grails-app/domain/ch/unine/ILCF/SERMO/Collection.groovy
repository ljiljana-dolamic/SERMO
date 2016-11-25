package ch.unine.ILCF.SERMO

class Collection {
	String id
	String authorFirstName
	String authorLastName
	String docName
	int editionYear
	String sermonYear
	String place
	String publisher
	String genre
	String titleBook
	String titleSermon
	
	static mapping = {
		version false
		table "documents_info"
		id column: 'doc_id'
		authorFirstName column: 'author_first_name'
		authorLastName column: 'author_last_name'
		docName column: 'doc_name'
		editionYear column: 'edition_year', sqlType:'smallint'
		sermonYear column: 'sermon_date'
		titleBook column: 'title_book'
		titleSermon column: 'title_sermon'
		genre column: 'genre'
		place  column:'place'
		publisher  column: 'publisher'
	}

    static constraints = {
    }
}
