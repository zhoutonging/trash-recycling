<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>社区动态管理</title>
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
                                           placeholder="请输入商品名称" autocomplete="off" class="layui-input">
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
                        <button class="layui-btn" data-type="getCheckData1">导出Excel</button>
                    </div>

                    <table class="layui-hide" id="test-table-page" lay-filter="test-table-operate"></table>

                    <script type="text/html" id="test-table-operate-barDemo">
                        {{#  if(d.status==1){ }}
                        <a class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="detail">查看详情</a>
                        <a class="layui-btn layui-btn-xs" lay-event="edit">点击审核</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                        {{#  } else { }}
                        <a class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="detail">查看详情</a>
                        <a class="layui-btn layui-btn-xs layui-btn-disabled">审核通过</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                        {{#  } }}
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script src="/static/common/jquery/jquery.js"></script>
<script src="/static/common/layuiadmin/exts/excel.js"></script>

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
            url: '/community/findAll',
            page: true,
            height: 'full-160',
            cellMinWidth: 80,
            id: 'idTest',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'communityContent', title: '动态内容', align: 'center'},
                {field: 'area', title: '区域', align: 'center'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {align: 'center', title: '操作', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
        });

        //商品查看OR删除
        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                // location.href = "productFind?id=" + data.id;
                //弹出即全屏
                var index = layer.open({
                    type: 2,
                    title: '查看商品',
                    content: "productFind?id=" + data.id,
                    area: ['100%', '100%']
                });
            } else if (obj.event === 'del') {
                layer.confirm('真的删除数据吗?这将无法恢复', function (index) {
                    $.get('/community/deleteById', {id: data.id}, function (res) {
                        if (res.code == 0) {
                            obj.del();
                            layer.msg(res.msg, {time: 2000, icon: 1});
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    })
                });
            } else if (obj.event === 'edit') {
                $.get('community/modifyByStatus', {
                    id: data.id
                }, function (res) {
                    if (res.code == 0) {
                        layer.msg(res.msg, {time: 2000, icon: 1});
                        table.reload('idTest');
                    } else {
                        layer.msg(res.msg, {time: 2000, icon: 2});
                    }
                });
            }
        });


        //商品添加
        var $ = layui.$, active = {
            //添加角色
            getCheckData: function () {
                //弹出即全屏
                var index = layer.open({
                    type: 2,
                    title: '添加商品',
                    content: '/productSave',
                    area: ['100%', '100%']
                });
            }, getCheckData1: function () {
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
            }
        };

        $('.test-table-operate-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //条件查询
        form.on('submit(seek)', function () {
            var productName = $('#productName').val();
            var status = $("#productStatus option:selected").val();

            table.reload('idTest', {
                url: 'product/findAll',
                //让重载后的页码从1开始
                page: {curr: 1},
                where: {
                    productName: productName,
                    status: status
                }
            });
        });
    })
    ;
</script>
</body>
</html>