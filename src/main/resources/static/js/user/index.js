$(document).ready(function() {
	$("input[value=QQ]").click(function() {
		// $.message({
		// type:"info",
		// message:"text"
		// });

		var html = "<div id='showTips'><a>!</a><span>zhansgan</span></div>";
		$("body").append(html);
		$("#showTips").css({
			"width" : "400px",
			"height" : "55px",
			"left" : "50%",
			"top" : "20%",
			"text-indent" : "",
			"line-height" : "55px",
			"transform" : "translateX(-50%)",
			"position" : "fixed",
			"z-index" : "2000"
		});
		$("#showTips").css({{"background-color":"#F0F9EB;");
	});
});