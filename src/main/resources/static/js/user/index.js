$(document).ready(function() {
	$("input[value=QQ]:eq(0)").click(function() {
		$.message({
			type : "info",
			message : "请输入正确的号码！"
		});
	});

	$("input[value=QQ]:eq(1)").click(function() {
		$.message({
			type : "success",
			message : "请输入正确的号码！"
		});
	});

	$("input[value=QQ]:eq(2)").click(function() {
		$.message({
			type : "failure",
			message : "请输入正确的号码！"
		});
	});
	$("input[value=QQ]:eq(3)").click(function() {
		$.message("failure", "请输入正确的号码请输入正确的号码请输入正确的号码请输入正确的号码请输入正确的号码！");
	});
});