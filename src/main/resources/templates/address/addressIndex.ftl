<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>收货地址管理</title>
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
                    <div class="layui-form">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input class="layui-input" type="text" name="productName" id="mobilePhone"
                                           placeholder="请输入手机号" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <button type="button" title="点击搜索" id="shuxinBtn" class="layui-btn" lay-submit=""
                                            lay-filter="seek"><i class="layui-icon">&#xe615;</i>
                                </div>
                            </div>
                        </div>
                    </div>

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
            url: '/address/findAll',
            page: true,
            height: 'full-110',
            cellMinWidth: 80,
            id: 'idTest',
            cols: [[
                {type: 'numbers', title: '序号', align: 'center'},
                {field: 'userName', title: '收货人', align: 'center'},
                {field: 'mobile', title: '手机号', align: 'center'},
                {field: 'area', title: '所属区域', align: 'center'},
                {field: 'address', title: '详细地址', align: 'center'},
                {field: 'openId', title: '创建人标识', align: 'center'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {align: 'center', title: '操作', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
        });

        //商品查看OR删除
        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除数据吗?这将无法恢复', function (index) {
                    $.get('/address/deleteById', {id: data.id}, function (res) {
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


        //条件查询
        form.on('submit(seek)', function () {
            var mobile = $('#mobilePhone').val();

            table.reload('idTest', {
                url: 'address/findAll',
                //让重载后的页码从1开始
                page: {curr: 1},
                where: {
                    mobile: mobile
                }
            });
        });
    });
</script>
</body>
</html>