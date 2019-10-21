<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
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


                    <table class="layui-hide" id="test-table-page" lay-filter="test-table-operate"></table>

                    <script type="text/html" id="test-table-operate-barDemo">
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
        var admin = layui.admin, table = layui.table, form = layui.form, $ = layui.jquery;

        table.render({
            elem: '#test-table-page',
            url: '/user/findAll',
            page: true,
            height: 'full-60',
            cellMinWidth: 80,
            id: 'idTest',
            cols: [[
                {type: 'numbers', title: '序号', align: 'center'},
                {field: 'nickName', title: '用户昵称', align: 'center'},
                {
                    field: 'gender', title: '性别', align: 'center', templet: function (d) {
                        if (d.gender == 0) {
                            return '未知';
                        } else if (d.gender == 1) {
                            return '男';
                        } else {
                            return '女';
                        }
                    }
                },
                {field: 'integral', title: '用户积分', align: 'center'},
                {align: 'center', title: '操作', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
        });

        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除数据吗?这将导致用户积分清空', function (index) {
                    $.get('/user/deleteById', {id: data.id}, function (res) {
                        if (res.code == 0) {
                            obj.del();
                            layer.msg(res.msg, {time: 2000, icon: 1});
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    })
                });
            }
        });


        $('.test-table-operate-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


    });
</script>
</body>
</html>