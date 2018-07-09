/**
 * 
 */

function searchInDoc(doc_id){
	var URL = "doc";
	var query =  $('#query').val().trim();
	
	window.location = URL + "?docId="+doc_id+"&q="+encodeURIComponent(query);


}

