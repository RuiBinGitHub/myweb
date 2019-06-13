$(document).ready(function() {

    var code = null;

    $(".textbox:eq(0)").attr("placeholder", "请输入用户昵称");
    $(".textbox:eq(1)").attr("placeholder", "请输入登录账号");
    $(".textbox:eq(2)").attr("placeholder", "请输入登录密码");
    $(".textbox:eq(3)").attr("placeholder", "请确认登录密码");
    $(".textbox:eq(4)").attr("placeholder", "请输入电子邮箱");
    $(".textbox:eq(2)").attr("name", "password");
    $(".textbox:eq(2)").attr("type", "password");
    $(".textbox:eq(3)").attr("type", "password");

    function checkName() {
        var name = $(".textbox:eq(0)").val();
        if (name.length < 6 || name.length > 16) {
            $(".textbox:eq(0)").css("border-color", "#f00");
            $("#tips").text("*用户昵称格式不正确！");
            return false;
        }
        return true;
    }

    function checkUserName() {
        var username = $(".textbox:eq(1)").val();
        if (username.length < 6 || username.length > 16) {
            $(".textbox:eq(1)").css("border-color", "#f00");
            $("#tips").text("*登录账号格式不正确！");
            return false;
        }
        if (Ajax("isexistname", {name: username})) {
            $(".textbox:eq(1)").css("border-color", "#f00");
            $("#tips").text("*账号已经存在，请重新输入！");
            return false;
        }
        return true;
    }

    function checkPassWord() {
        var password1 = $(".textbox:eq(2)").val();
        var password2 = $(".textbox:eq(3)").val();
        if (password1.length < 6 || password1.length > 16) {
            $(".textbox:eq(2)").css("border-color", "#f00");
            $("#tips").text("*登录密码格式不正确！");
            return false;
        }
        if (password1 != password2) {
            $(".textbox:eq(2)").css("border-color", "#f00");
            $(".textbox:eq(3)").css("border-color", "#f00");
            $("#tips").text("*两次密码输入不一致！");
            return false;
        }
        return true;
    }

    function checkMail() {
        var mail = $(".textbox:eq(4)").val();
        if (!mail.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
            $(".textbox:eq(4)").css("border-color", "#f00");
            $("#tips").text("*电子邮箱格式不正确！");
            return false;
        }
        if (Ajax("isexistmail", {mail: mail})) {
            $(".textbox:eq(4)").css("border-color", "#f00");
            $("#tips").text("*邮箱已经注册，请重新输入！");
            return false;
        }
        return true;
    }

    function checkCode() {
        var temp = $("#code").val();
        if (temp == "" || temp != code) {
            $("#code").css("border-color", "#f00");
            $("#tips").text("*验证码输入不正确！");
            return false;
        }
        return true;
    }

    var time = 0;
    $("#getBtn").click(function() {
        if (!checkMail())
            return false;
        time = 60;
        var mail = $(".textbox:eq(4)").val();
        code = Ajax("sendmail", {mail: mail});
        $("#this").css("color", "#CCCCCC");
        $(this).attr("disabled", true);
        changeTime();
    });

    function changeTime() {
        if (time-- == 0) {
            $("#getBtn").attr("value", "获取验证码");
            $("#getBtn").css("color", "#5EA500");
            $("#getBtn").attr("disabled", false);
        } else {
            $("#getBtn").attr("value", time + " S后重新获取");
            setTimeout(changeTime, 1000);
        }
    }

    /** 登录按钮点击事件 */
    $(".commit").click(function() {
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
        $(this).attr("value", "登录中...");
        $("form").submit();
    });

    $("#code, .textbox").focus(function() {
        $(this).css("border-color", "#D1D1D1");
        $("#tips").text("");
    });
    function Ajax(url, data) {
        var result = null;
        $.ajax({
            url: url,
            data: data,
            type: "post",
            async: false,
            dataType: "json",
            success: function(data) {
                result = data;
            }
        });
        return result;
    }
});
