
$(function(){
	$("#button").hide();
	$("#coment_section").show(1000);
	
	$("#comment").keyup(function(){
		if($(this).val().length > 2){
			$("#button").show(1500);
		}else{
			$("#button").hide(1500);
		}
	})
});


