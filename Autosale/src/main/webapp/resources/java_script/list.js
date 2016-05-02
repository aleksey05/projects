$(function() {
	var image = $("img");

	image.hover(function() {
		$(this).animate({
			width : "280px",
			hight : "280px"
		}, 100);
	})
	image.on("mouseleave", function() {
		$(this).animate({
			width : "250px",
			hight : "250px"
		}, 100);
	})
});