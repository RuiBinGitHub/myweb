$(document).ready(function() {
	$("input[value=QQ]").click(function(){
		$.message({
			type:"info",
			message:"text"
		});
	});
});