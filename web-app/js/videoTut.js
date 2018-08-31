/**
 * 
 */


(function($) {
    "use strict"
           

            $('.close_video').on("click",function(){
            	 var id = $(this).attr('id');
            	 var id_base= id.split('_')[0];
            	 $('#'+id_base+'_video_close').hide(); 
            	 $('#'+id_base+'_video').remove(); 
               
            });
            	

            $('.tut_video').on("click",function(){
            	 var id = $(this).attr('id');
            	 var elem = document.getElementById(id+'_video');
            	
            	 if( elem == null){
            		var URL = "tutorialVideo?name_v="+id
            		 var xmlHttp = new XMLHttpRequest();
            		    xmlHttp.open( "GET", URL, false ); // false for synchronous request
            		    xmlHttp.send( null );
            		    $(this).next().after(xmlHttp.responseText);
                }
            	 $('#'+id+'_video_close').show(); 
            	 $('#'+id+'_video').show(); 
               
            });
           
            
           
})(jQuery);
