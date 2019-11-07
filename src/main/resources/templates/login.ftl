<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台登录</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" type="text/css" href="/static/common/login/fonts/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="/static/common/login/fonts/iconic/css/material-design-iconic-font.css">
    <link rel="stylesheet" type="text/css" href="/static/common/login/css/util.css">
    <link rel="stylesheet" type="text/css" href="/static/common/login/css/main.css">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
</head>

<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url('/static/common/login/images/bg-01.jpg');">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
            <div class="login100-form validate-form">
                <span class="login100-form-title p-b-49">登录</span>

                <div class="wrap-input100 validate-input m-b-23" data-validate="请输入用户名">
                    <span class="label-input100">用户名</span>
                    <input class="input100" type="text" id="username" placeholder="请输入用户名" autocomplete="off">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="请输入密码">
                    <span class="label-input100">密码</span>
                    <input class="input100" type="password" id="password" placeholder="请输入密码">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div class="text-right p-t-8 p-b-31">
                    <#--<a href="javascript:">忘记密码？</a>-->
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <#--<button class="login100-form-btn" id="loginUser">登 录</button>-->
                        <input style="background: transparent;" type="button" class="login100-form-btn" id="loginUser"
                               value="登录">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/common/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="/static/common/login/js/main.js"></script>
<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'table'], function () {
        var admin = layui.admin, table = layui.table, form = layui.form, $ = layui.jquery;

        $('#loginUser').click(function () {

            var username = $("#username").val();
            var password = $("#password").val();

            if (username === '') {
                layer.msg("帐号不能为空", {time: 2000, icon: 5});
                return;
            }

            if (password === '') {
                layer.msg("密码不能为空", {time: 2000, icon: 5});
                return;
            }

            $.post('/api/adminuser/userLogin', {
                userName: username,
                password: password
            }, function (res) {
                if (res.success == true) {
                    layer.msg(res.msg, {time: 2000, icon: 6});
                    var int = self.setInterval(function () {
                        window.location.href = "/index"
                    }, 2000)
                } else {
                    layer.msg(res.msg, {time: 2000, icon: 5});
                }
            });
        });


        $(document).keypress(function(e) {
            // 回车键事件
            if(e.which == 13) {
                document.getElementById("loginUser").click(); //调用登录按钮的登录事件
                // $('#button-1').trigger('click');
            }
        });
    });
</script>
</body>

</html>