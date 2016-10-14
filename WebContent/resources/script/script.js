$(document).ready(function(){

	$(".show-create-drop-div-btn").click(function(){
		$(".create-drop-div").stop().toggle('slow');
	});
	
	$(".arrow-down").click(function(){
		$(this).parent().next().toggle('fast');
	});
	
	$(".clear-button").click(function(){
		$(".search-field").val("");
	});
	
});