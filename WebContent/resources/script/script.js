$(document).ready(function(){

	$(".show-create-drop-div-btn").click(function(){
		$(".create-drop-div").stop().toggle('slow');
	});
	
	$(".arrow-down").click(function(){
		$(".create-comment-div").stop().toggle('slow');
	});
	
	$(".clear-button").click(function(){
		$(".search-field").val("");
	});
	
	$(".more-filters-button").click(function(){
		$(".show-more-filters").stop().toggle('slow');
	});
	
});