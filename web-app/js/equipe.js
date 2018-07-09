/**
 * 
 */

$(function(){
  $('.profilepic').on('click', function(e){
    var $biginfo = $('#teamcontent');
    var $bigname = $('#bigname');
    var $bigjob  = $('#bigjob');
    var $bigurl  = $('#bigurl');
    var $bigdesc = $('#bigdesc');
    var $pic = $(this).attr('src');
    $('.bigimg').attr('src', $pic);
    var newname = $(this).attr('alt');
    var newrole = $(this).siblings('.job').eq(0).html();
    var newurl = $(this).siblings('.url').eq(0).html();
    var newdesc = $(this).siblings('.desc').eq(0).html();

    $bigname.html(newname);
    $bigjob.html(newrole);
    $bigurl.html(newurl);
    $bigdesc.html(newdesc);

    if($biginfo.css('display') == 'none') {
      $biginfo.slideDown(350);
    }
  });
  
  $('.profilepic-2').on('click', function(e){
	    var $biginfo = $('#teamcontent-2');
	    var $bigname = $('#bigname-2');
	    var $bigjob  = $('#bigjob-2');
	    var $bigurl  = $('#bigurl-2');
	    var $bigdesc = $('#bigdesc-2');
	    var $pic = $(this).attr('src');
	    $('.bigimg-2').attr('src', $pic);
	    var newname = $(this).attr('alt');
	    var newrole = $(this).siblings('.job').eq(0).html();
	    var newurl = $(this).siblings('.url').eq(0).html();
	    var newdesc = $(this).siblings('.desc').eq(0).html();

	    $bigname.html(newname);
	    $bigjob.html(newrole);
	    $bigurl.html(newurl);
	    $bigdesc.html(newdesc);

	    if($biginfo.css('display') == 'none') {
	      $biginfo.slideDown(350);
	    }
	  });
});