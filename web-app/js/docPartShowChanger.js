//var DocPartShowChanger;


(function($) {
    "use strict";


            $('#doc-container').on("click",'#showFront',function(){

            	 $('#showSermon').removeClass('selected');
            	$('#showFront').addClass('selected');
            	$('#showStats').removeClass('selected');
            	
            	$('.titre').show();
            	$('.front').show();
            	$('.sermon').hide();
            	$('.stats').hide();
            });

            

            $('#doc-container').on("click",'#showSermon',function(){

            	 $('#showSermon').addClass('selected');
            	$('#showFront').removeClass('selected');
            	$('#showStats').removeClass('selected');
              
            	$('.front').hide();
            	$('.sermon').show();
            	$('.stats').hide();
            });
            

            $('#doc-container').on("click",'#showStats',function(){

            	 $('#showSermon').removeClass('selected');
            	$('#showFront').removeClass('selected');
            	$('#showStats').addClass('selected');
              
            	$('.front').hide();
            	$('.sermon').hide();
            	$('.stats').show();
            });

    
})(jQuery);
