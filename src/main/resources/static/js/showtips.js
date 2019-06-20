(function(action, $) {
	$.message = function(type, message) {
		$("#showTips").remove();
		var html = "<div id='showTips'><a></a><span></span></div>";
		$("body").append(html);
		var TipsBody = $("#showTips");
		$("#showTips").css({
			"top" : "20%",
			"left" : "50%",
			"width" : "400px",
			"height" : "55px",
			"transform" : "translateX(-50%)",
			"position" : "fixed",
			"display" : "none",
			"z-index" : "2000"
		});
		$("#showTips a").css({
			"width" : "25px",
			"height" : "25px",
			"color" : "#ffffff",
			"margin" : "15px 14px",
			"font-size" : "16px",
			"font-family" : "微软雅黑",
			"font-weight" : "normal",
			"text-align" : "center",
			"line-height" : "25px",
			"border-radius" : "100%",
			"float" : "left",

		});
		$("#showTips span").css({
			"max-width" : "320px",
			"font-size" : "16px",
			"font-family" : "微软雅黑",
			"font-weight" : "normal",
			"line-height" : "54px",
			"white-space" : "nowrap",
			"text-overflow" : "ellipsis",
			"overflow" : "hidden",
			"float" : "left"
		});
		/** ************************************************************** */
		if (type == "info") {
			$("#showTips").css("color", "#E6A23C");
			$("#showTips").css("background-color", "#FDF6EC");
			$("#showTips").css("border", "1px solid #f0c686");
			$("#showTips a").css("background-color", "#E6A23C");
			$("#showTips a").text("!");
			$("#showTips span").text(message);
			$("#showTips").show().delay(1800).hide(200);
		} else if (type == "success") {
			$("#showTips").css("color", "#67C23A");
			$("#showTips").css("background-color", "#F0F9EB");
			$("#showTips").css("border", "1px solid #b9e3a4");
			$("#showTips a").css("background-color", "#67C23A");
			$("#showTips a").text("✔");
			$("#showTips span").text(message);
			$("#showTips").show().delay(1800).hide(200);
		} else if (type == "failure") {
			$("#showTips").css("color", "#F56C6C");
			$("#showTips").css("background-color", "#FEF0F0");
			$("#showTips").css("border", "1px solid #fab6b6");
			$("#showTips a").css("background-color", "#F56C6C");
			$("#showTips a").text("X");
			$("#showTips span").text(message);
			$("#showTips").show().delay(1800).hide(200);
		}
		setTimeout(function() {
			TipsBody.remove();
		}, 2000);
	};
})(this, jQuery != "undefined" ? jQuery : this);