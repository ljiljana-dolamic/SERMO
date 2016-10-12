/**
 * 
 */



function nextPage(){
	
	var isLastElementSelected = $('#pageSelect_menu > option:selected').index() == $('#pageSelect_menu > option').length -1;
	
	if (!isLastElementSelected) { 
		$('#pageSelect_menu > option:selected').prop('selected',false).next('option').prop('selected',true); 
		
		}
	
	else { 
		$('#pageSelect_menu > option:selected').prop('selected',false); 
		$('#pageSelect_menu > option').first().prop('selected', true); 
		
	}
	var ppage = $('#pageSelect_menu > option:selected').val();		
	//alert("page to show:" + pageToShow);
	showPage(ppage);
}


function previousPage(){

	var isFirstElementSelected =$('#pageSelect_menu > option:selected').index() == 0;
	
	if(!isFirstElementSelected) { 
	$('#pageSelect_menu > option:selected').prop('selected',false).prev('option').prop('selected',true); 
	
} 
else { 
	$('#pageSelect_menu > option:selected').prop('selected',false); 
	$('#pageSelect_menu > option').last().prop('selected', true); 
}
	var npage = $('#pageSelect_menu > option:selected').val();	
	//alert("page to show:" + pageToShow);
	showPage(npage);			
}

function changePage(val){
	//alert("The page has been changed."+val);
//	 var pageSelector = document.getElementById("pageSelect_menu");
//	 pageSelector.find("option:selected").each(function(){
//	//var page_id=  $(this).attr("value");
//	console.log("Loading ajax content:"+ page_id);
	$('#pageSelect_menu > option:selected').prop('selected',false);
	$('#pageSelect_menu').val(val).prop('selected',true);
	showPage(val);
//	$(".pageBreak").hide();   
//	$("#"+val).show();
//	$(".currentPageImage").hide();
//	$("#"+val+"_image").show();
	//var last_  = val.lastIndexOf('_');
	//setImage(val.substr(0, last_),val.substr(last_+1));
//});
}

function showPage(val){
	
	$(".pageBreak").hide();   
	$("#"+val).show();
	$(".currentPageImage").hide();
	var image = $(".selectedImage");
//	$(".currentPageImage").removeClass("selectedImage");
	
	$.removeData(image, 'elevateZoom');//remove zoom instance from image

    $('.zoomContainer').remove();// remove zoom container from DOM
    image.removeClass("selectedImage");
	$("#"+val+"_image").addClass("selectedImage");
	$("#"+val+"_image").show();
}

function loadTipsy(){
	//alert("loading");
	$('.margin').each(function(index) {
		//alert("found");
		$(this).tipsy({html:true});
	});
	
}
