$(document).ready(function() {
	
	$(".textbox").attr("maxlength", 16);
	$("input[name=username]").attr("placeholder", "请输入登录账号");
	$("input[name=password]").attr("placeholder", "请输入登录密码");
	$("input[name=password]").attr("type", "password");
	
	$("input[name=username]").keyup(function(){
		if(event.keyCode == 13)
			$("input[name=password]").focus();
	});
	$("input[name=password]").keyup(function(){
		if(event.keyCode == 13)
			$(".commit").click();
	});
	/** 登录按钮点击事件 */
	$(".commit").click(function(){
		if ($("input[name=username]").val() == "") {
			$("input[name=username]").css("border-color", "#f00");
			$("#tips").text("*请输入登录账号！");
			return false;
		}
		if ($("input[name=password]").val() == "") {
			$("input[name=password]").css("border-color", "#f00");
			$("#tips").text("*请输入登录密码！");
			return false;
		}
		$(this).css("bakcground-color", "#CCC");
		$(this).attr("disabled", true);
		$(this).attr("value","登录中...");
		$("form").submit();
	});
	
	$(".textbox").focus(function(){
		$(this).css("border-color", "#D1D1D1");
		$("#tips").text("");
	})
});