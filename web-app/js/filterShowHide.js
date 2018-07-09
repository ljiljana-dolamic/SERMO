/**
 * 
 */




(function($) {
    "use strict"
            $('#filters').on("click",'#author_filter',function(){
//
//            	var toShow=false;
//            	if($('#author_filter_list').css('display') == 'none' ){
//            		toShow=true;
//            	}
//            	
//            	$('.filter_list').hide();
//            	
//            	if(toShow){
//            		$('#author_filter_list').show();
//            	}
            	$('#author_filter_list').toggle();
            });

            
            $('#filters').on("click",'#place_filter',function(){

//            	var toShow=false;
//            	if($('#place_filter_list').css('display') == 'none' ){
//            		toShow=true;
//            	}
//            	
//            	$('.filter_list').hide();
//            	
//            	if(toShow){
            		$('#place_filter_list').toggle();
//            	}
            });
            
            $('#filters').on("click",'#genre_filter',function(){
//
//            	var toShow=false;
//            	if($('#genre_filter_list').css('display') == 'none' ){
//            		toShow=true;
//            	}
//            	
//            	$('.filter_list').hide();
//            	
//            	if(toShow){
            		$('#genre_filter_list').toggle();
//            	}
            });
            
            $('#filters').on("click",'#decade_filter',function(){

//            	var toShow=false;
//            	if($('#decade_filter_list').css('display') == 'none' ){
//            		toShow=true;
//            	}
//            	
//            	$('.filter_list').hide();
//            	
//            	if(toShow){
            		$('#decade_filter_list').toggle();
    //        	}
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
            	
            
           
            
           
})(jQuery);
