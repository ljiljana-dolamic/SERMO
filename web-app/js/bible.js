/**
 * 
 */


$(function(){
	var URL = "bible";
	
  $('.bibl').on('click', function(e){
    /* rev attr format 
	 1 bible#book1.ch1.v1;book2.ch2.v2;
	 2 bible#book.ch.v1:v7:v11
	 3 bible#book.ch.v1-v6
	  */
  
    var fields = $(this).attr('rev').split('#');
    var bible = fields[0].split('.')[0];
    // 
    var verses = fields[1]; // if length > 1 => rev format 1  
    
    //$(".modal .modal-title").html( verses );
   
   
    $(".modal .modal-footer").html("Texte biblique de la Bible Version Segond 21<br/>" +
    		"Copyright &#169; 2007 Société Biblique de Genève <br/>" +
    		"Reproduit avec aimable autorisation. Tous droits réservés.<br/>");
    if(bible == ""){
    	$(".modal .modal-body").html("Le verset correspondant de la Bible Version Segond 21 sera affiché prochainement");
    	}else{
    		$.ajax({
    	        url : URL,
    	        data : { 'bible' : bible, 'verses': verses } ,
    	        dataType : 'text',
    	        async : true,
    	        	
    	        })
    	        .done(function( data ) {
    	    		$(".modal .modal-body").html(data);
    	        	
    	        	 
    	    });
    	}
    	
  
   
   
    $(".modal").show();
   
    
  });
  
  $('.quote').on('click', function(e){
	    
	    
	 
	    var fields = $(this).attr('source').split('#');
	    var bible = fields[0].split('.')[0];
	    // 
	    var verses = fields[1]; // if length > 1 => rev format 1  
	    
	    $(".modal .modal-footer").html("Texte biblique de la Bible Version Segond 21<br/>" +
	    		"Copyright &#169; 2007 Société Biblique de Genève <br/>" +
	    		"Reproduit avec aimable autorisation. Tous droits réservés.<br/>");

	    if(bible == ""){
	    	$(".modal .modal-body").html("Le verset correspondant de la Bible Version Segond 21 sera affiché prochainement");
	    	}else{
	    		$.ajax({
	    	        url : URL,
	    	        data : { 'bible' : bible, 'verses': verses } ,
	    	        dataType : 'text',
	    	        async : true,
	    	        	
	    	        })
	    	        .done(function( data ) {
	    	    		$(".modal .modal-body").html(data);
	    	        	
	    	        	 
	    	    });
	    	}
	    	
	 
	   
	    $(".modal").show();
	   
	    
	  });
	  
  
  $('.close-modal').on('click', function(e){
	   
	    $(".modal").hide();
	    $(".modal .modal-body").html("");

	   
	   
	  });
  
});