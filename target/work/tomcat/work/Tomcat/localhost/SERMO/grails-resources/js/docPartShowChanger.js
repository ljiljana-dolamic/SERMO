var DocPartShowChanger;


(function($) {
    "use strict";

    

    var showFront    = $('#showFront'),
        showSermon   = $('#showSermon'),
        showStats    = $('#showStats'),
        front = $('.front'),
        sermon = $('.sermon'),
        stats =$('.stats');
   
    
    DocPartShowChanger = {   
        
        initCallbacks: function() {
            var self = this;

            showFront.click(function(e){

            	DocPartShowChanger.showFront();
                e.preventDefault();
            });

            showSermon.click(function(e){

            	DocPartShowChanger.showSermon();
                e.preventDefault();
            });

            showStats.click(function(e){

            	DocPartShowChanger.showStats();
                e.preventDefault();
            });
        },

       

        showFront : function() {
            var self = this;

            showSermon.removeClass('selected');
            showStats.removeClass('selected');
            showFront.addClass('selected');
            
            front.show();
            sermon.hide();
            stats.hide();
            
        },

        showSermon : function() {
        	var self = this;

            showSermon.addClass('selected');
            showStats.removeClass('selected');
            showFront.removeClass('selected');
            
            front.hide();
            sermon.show();
            stats.hide();
        },

        showStats : function() {
        	var self = this;

            showSermon.removeClass('selected');
            showStats.addClass('selected');
            showFront.removeClass('selected');
            
            front.show();
            sermon.hide();
            stats.show();
        },
       
    
        DocPartShowChanger.initCallbacks();
    
})(jQuery);
