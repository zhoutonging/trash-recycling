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
    <input type="button" value="点击导出" id="excel">
</div>

<script src="/static/common/jquery/jquery.js"></script>
<script src="/static/common/layuiadmin/layui/layui.js"></script>

<#--<script src="/static/common/layuiadmin/exts/excel.js"></script>-->
<script>
    layui.config({
        base: '/static/common/layuiadmin/exts/',
    }).extend({
        excel: 'excel',

    }) .use(['jquery', 'excel', 'layer'], function() {
        var excel = layui.excel;
        $('#excel').click(function () {

            $.get('/api/product/findAll', function (res) {
                var data = res.data;

                // 1. 数组头部新增表头
                res.data.unshift({
                    productName: '商品名称', productIcon: '商品图路径', productPrice: '商品价格'
                    , updateTime: '更新时间'
                });
                // 2. 如果需要调整顺序，请执行梳理函数
                var data = excel.filterExportData(res.data, [
                    'productName',
                    'productPrice',
                    'productIcon',
                    'updateTime',
                ]);
                // 3. 执行导出函数，系统会弹出弹框
                excel.exportExcel({
                    sheet1: data
                }, '商品信息.xlsx', 'xlsx');
            });
        });
    });

</script>
</body>
</html>