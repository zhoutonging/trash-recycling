<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>系统用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-card layadmin-header">
    <div class="layui-breadcrumb" lay-filter="breadcrumb">
        <a lay-href="">主页</a>
        <a><cite>组件</cite></a>
        <a><cite>数据表格</cite></a>
        <a><cite>开启分页</cite></a>
    </div>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input class="layui-input" type="text" name="productName" id="productName"
                                           placeholder="请输入用户名" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <button type="button" title="点击搜索" id="shuxinBtn" class="layui-btn" lay-submit=""
                                            lay-filter="seek"><i class="layui-icon">&#xe615;</i>
                                </div>
                            </div>
                        </div>
                    </form>

                    <div class="layui-btn-group test-table-operate-btn" style="margin-bottom: 10px;">
                        <button class="layui-btn" data-type="getCheckData">添加帐号</button>
                    </div>

                    <table class="layui-hide" id="test-table-page" lay-filter="test-table-operate"></table>

                    <script type="text/html" id="test-table-operate-barDemo">

                        {{#  if(d.userName=='admin'){ }}
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-disabled">删除</a>
                        {{#  } else { }}
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                        {{#  } }}
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'table'], function () {
        var admin = layui.admin, table = layui.table, form = layui.form;
        var excel = layui.excel;

        table.render({
            elem: '#test-table-page',
            url: '/sysUser/findAll',
            page: true,
            height: 'full-160',
            cellMinWidth: 80,
            id: 'idTest',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'userName', title: '用户名', align: 'center'},
                {field: 'password', title: '密码', align: 'center'},
                {field: 'salt', title: '加密盐', align: 'center'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {align: 'center', title: '操作', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
        });

        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除数据吗?这将无法恢复', function (index) {
                    $.get('/sysUser/deleteById', {id: data.id}, function (res) {
                        if (res.code == 0) {
                            obj.del();
                            layer.msg(res.msg, {time: 2000, icon: 1});
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    })
                });
            } else if (obj.event === 'edit') {
                var index = layer.open({
                    type: 2,
                    title: '编辑帐号',
                    content: "adminUserUpdate?id=" + data.id,
                    area: ['420px', '420px']
                });
            }
        });

        var $ = layui.$, active = {
            getCheckData: function () {
                //弹出即全屏
                var index = layer.open({
                    type: 2,
                    title: '添加帐号',
                    content: '/adminUserSave',
                    area: ['420px', '350px']
                });
            }
        };

        $('.test-table-operate-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //条件查询
        form.on('submit(seek)', function () {
            var productName = $('#productName').val();

            table.reload('idTest', {
                url: 'sysUser/findAll',
                //让重载后的页码从1开始
                page: {curr: 1},
                where: {
                    userName: productName
                }
            });
        });
    });
</script>
</body>
</html>