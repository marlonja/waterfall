$(document).ready(function(){
	
	$(document.getElementById("filter-form:ageSpan")).prop('readonly', true);

	$(".show-create-drop-div-btn").click(function(){
		$(".create-drop-div").stop().toggle('slow');
	});
	
	$(".comment-link").click(function(){
		$(this).parent().next().toggle('fast');
	});
	
	$(".clear-button").click(function(){
		$(".search-field").val("");
	});
	
	$(".more-filters-link").click(function(){
		$(".show-more-filters").stop().toggle();
	});
	
	$(function() {
	    $("#slider-range").slider({
	      range: true,
	      min: 0,
	      max: 116,
	      values: [ 0, 116 ],
	      slide: function( event, ui ) {
	    	$(document.getElementById("filter-form:ageSpan")).val(ui.values[ 0 ] + " - " + ui.values[ 1 ] );
	        var age = ((ui.values[ 0 ] + " " + ui.values[ 1 ]));
	      }
	   
	    });
	    $(document.getElementById("filter-form:ageSpan")).val($( "#slider-range" ).slider( "values", 0 ) + " - " + $( "#slider-range" ).slider( "values", 1 ) );
	  });
	
});