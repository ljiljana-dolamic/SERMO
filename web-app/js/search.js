/**
 * 
 */

function searchInDoc(doc_id){
	var URL = "doc";
	var query =  $('#query').val().trim();
	
	window.location = URL + "?docId="+doc_id+"&q="+encodeURIComponent(query);

	
}


$(document).ready(function(){
	
    var $noHitsVal = $('#noHits').val();
    if($noHitsVal != -1){
    	if($noHitsVal == 1){
    		$('#query').val($noHitsVal+" résultat");
    	}else{
    		$('#query').val($noHitsVal+" résultats");
    	}
    	
    	
    	
    }
   
})