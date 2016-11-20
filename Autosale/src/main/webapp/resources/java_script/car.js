
$(function(){
	
	
	
	getComments();

	$("#button").click(function(){
		var text =  $(".form-control").val();
		var carId = $("#carId").text();
		alert(text + " " + carId);
		$.post('http://localhost:8080/autosale/addNewComment', {comment_text:text, carId:carId}, function(){
			getComments();
		});	
		
	});
	
	
	
	$("#button").hide();
	$("#coment_section").show(1000);
	
	$("#comment_text").keyup(function(){
		if($(this).val().length > 2){
			$("#button").show(1500);
		}else{
			$("#button").hide(1500);
		}
	})
});


function getComments(){   
	var carId = $("#carId").text();  
	$.get('http://localhost:8080/autosale/getComment', {carId:carId}, function(data){
		$(".comment_list").empty();
		for(var i =0; i< data.length; i++){
		$(".comment_list").append('<li>' + data[i].text + '</li>');
		}
	});
};