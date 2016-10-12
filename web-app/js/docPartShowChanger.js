//var DocPartShowChanger;


(function($) {
    "use strict";

    var image = $(".titreImage");
            $('#doc-container').on("click",'#showFront',function(){

            	 $('#showSermon').removeClass('selected');
            	$('#showFront').addClass('selected');
            	$('#showStats').removeClass('selected');
            	
            	$('.titre').show();
            	$('.front').show();
            	image.elevateZoom()
            	$('.sermon').hide();
            	$('.stats').hide();
            });

            

            $('#doc-container').on("click",'#showSermon',function(){

            	 $('#showSermon').addClass('selected');
            	$('#showFront').removeClass('selected');
            	$('#showStats').removeClass('selected');
              
            	$('.front').hide();
            	
            	$.removeData(image, 'elevateZoom');//remove zoom instance from image
            	$('.zoomContainer').remove();// remove zoom container from DOM
            	
            	$('.sermon').show();
            	$('.stats').hide();
            });
            

            $('#doc-container').on("click",'#showStats',function(){

            	 $('#showSermon').removeClass('selected');
            	$('#showFront').removeClass('selected');
            	$('#showStats').addClass('selected');
              
            	$('.front').hide();
            	
            	$.removeData(image, 'elevateZoom');//remove zoom instance from image
            	$('.zoomContainer').remove();// remove zoom container from DOM
            	
            	$('.sermon').hide();
            	$('.stats').show();
            });

            $('#doc-container').on("mouseover",'.selectedImage',function(){

            	$(this).elevateZoom({
            	 zoomType				: "inner",

            	 });
            });
            $('#doc-container').on("mouseover",'.titreImage',function(){

            	$(this).elevateZoom({
            	 zoomType				: "inner",

            	 });
            });
            
           
})(jQuery);
