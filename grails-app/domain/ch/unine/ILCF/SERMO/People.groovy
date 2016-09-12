package ch.unine.ILCF.SERMO

class People {
	
	String id
    String firstName
	String lastName 
	String mail
	
	static mapping = {
		version false
		table "sermo_people"
		id column: 'p_id', sqlType: 'varchar'
		firstName column: 'sp_first_name'
		lastName column: 'sp_last_name'
		mail column: 'sp_mail'
	}

    static constraints = {
    }
}
