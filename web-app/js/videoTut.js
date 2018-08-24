/**
 * 
 */


(function($) {
    "use strict"
           

            $('.close_video').on("click",function(){
            	
            	 $('.close_video').hide(); 
            	 $('.tutVideo').hide(); 
               
            });
            	

            $('.tut_video').on("click",function(){
            	 var id = $(this).attr('id');
            	 $('#'+id+'_video_close').show(); 
            	 $('#'+id+'_video').show(); 
               
            });
           
            
           
})(jQuery);
