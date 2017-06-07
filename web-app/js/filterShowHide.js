/**
 * 
 */




(function($) {
    "use strict"
            $('#filters').on("click",'#author_filter',function(){

            	var toShow=false;
            	if($('#author_filter_list').css('display') == 'none' ){
            		toShow=true;
            	}
            	
            	$('.filter_list').hide();
            	
            	if(toShow){
            		$('#author_filter_list').show();
            	}
            });

            
            $('#filters').on("click",'#place_filter',function(){

            	var toShow=false;
            	if($('#place_filter_list').css('display') == 'none' ){
            		toShow=true;
            	}
            	
            	$('.filter_list').hide();
            	
            	if(toShow){
            		$('#place_filter_list').show();
            	}
            });

            
            
           
})(jQuery);
