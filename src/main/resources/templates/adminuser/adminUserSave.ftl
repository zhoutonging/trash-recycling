<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>系统用户表单模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <style>
        .test1{
            text-align-last: justify;
        }
    </style>
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label test1">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="loginname" id="userName" lay-verify="required" placeholder="请输入用户名"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label test1">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" id="psd" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label test1">确认密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password1" id="psd1" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="padding-top: 2em; margin-left: 2em;">
        <div class="layui-input-block">
            <input type="button" class="layui-btn" lay-submit lay-filter="formDemo" value="立即提交"/>
            <button type="reset" class="layui-btn layui-btn-primary" id="closeButton">关闭</button>
        </div>
    </div>
</div>

<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form'], function () {
        var $ = layui.$, form = layui.form;
        var index = parent.layer.getFrameIndex(window.name);

        $('#closeButton').click(function () {
            parent.layer.close(index);
        });

        //监听提交
        form.on('submit(formDemo)', function (data) {
            var userName = $('#userName').val();
            var password = $('#psd').val();
            var password1 = $('#psd1').val();

            if (password != password1) {
                layer.msg('两次密码不相同', {time: 2000, icon: 2});
                return;
            }

            $.post('sysUser/save', {
                userName: userName,
                password: password
            }, function (res) {
                if (res.code == 0) {
                    layer.msg(res.msg, {time: 2000, icon: 1});
                    var int = self.setInterval(function () {
                        parent.layer.close(index);
                        parent.location.reload();
                    }, 2000)
                } else {
                    layer.msg(res.msg, {time: 2000, icon: 2});
                }
            });

        });
    })
</script>
</body>
</html>