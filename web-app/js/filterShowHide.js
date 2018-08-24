/**
 * 
 */




(function($) {
    "use strict"
            $('#filters').on("click",'#author_filter',function(){
//
            	$('#author_filter_list').toggle();
            });

            
            $('#filters').on("click",'#place_filter',function(){

            		$('#place_filter_list').toggle();//            	}
            });
            
            $('#filters').on("click",'#genre_filter',function(){
            		$('#genre_filter_list').toggle();
           });
            
            $('#filters').on("click",'#decade_filter',function(){

           		$('#decade_filter_list').toggle();
           });

            $('.close_filter').on("click",function(){
            	var URL='index';
            	 $.ajax({	
                     url:URL,
                     
                    success: function() {   
                        // location.reload();
                         window.location.href = "./index";
                     }
                     })
            	 $('.close_filter').hide(); 
               
            });
            	
            $('#noHits').on('change', function(e){
      		  alert( "Handler for .change() called.2" );
      	    var $noHitsVal = $('noHits').val();
      	    $('#query').val($noHitsVal);

      	  });
           
            
           
})(jQuery);
