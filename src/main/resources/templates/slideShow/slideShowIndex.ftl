<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>广告管理</title>
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

                    <div class="layui-btn-group test-table-operate-btn" style="margin-bottom: 10px;">
                        <button class="layui-btn" data-type="getCheckData">添加广告</button>
                    </div>

                    <table class="layui-hide" id="test-table-page" lay-filter="test-table-operate"></table>

                    <script type="text/html" id="test-table-operate-barDemo">
                        <a class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="detail">查看详情</a>
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script type="text/html" id="statusTemp">
    {{#  if(d.status==1){ }}
    <input type="checkbox" name="status" lay-skin="switch" lay-text="可用|禁用" value="{{d.id}}"
           lay-filter="status">
    {{#  } else { }}
    <input type="checkbox" name="status" checked lay-skin="switch" lay-text="可用|禁用" value="{{d.id}}"
           lay-filter="status">
    {{#  } }}
</script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'table'], function () {
        var admin = layui.admin, table = layui.table, form = layui.form;

        table.render({
            elem: '#test-table-page',
            url: '/slideShow/findAll',
            page: true,
            height: 'full-100',
            cellMinWidth: 80,
            id: 'idTest',
            cols: [[
                {type: 'numbers', title: '序号', align: 'center'},
                {field: 'slideName', title: '广告名称', align: 'center'},
                {field: 'image', title: '广告图', align: 'center',templet:'<div><img src="{{d.image}}"></div>'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {filed: 'status', title: '状态', templet: '#statusTemp', align: 'center'},
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
                    $.get('/product/deleteById', {id: data.id}, function (res) {
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
                    title: '编辑用户',
                    content: "productUpdate?id=" + data.id,
                    area: ['100%', '100%'],
                    success: function (layero, index) {
                        var iframe = window['layui-layer-iframe' + index];
                        //调用子页面的全局函数
                        // iframe.child(data)
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
                    title: '添加广告',
                    content: '/slideShowSave',
                    area: ['100%', '100%']
                });
            }
        };
        $('.test-table-operate-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //商品上下架
        form.on('switch(status)', function (data) {
            var id = data.value;
            var status = this.checked ? '1' : '0';

            $.get('product/modifyByStatus', {
                id: id,
                status: status
            }, function (res) {
                if (res.code == 0) {
                    if (status == 1) {
                        layer.msg('上架成功', {time: 2000, icon: 1});
                    } else {
                        layer.msg('下架成功', {time: 2000, icon: 1});
                    }
                } else {
                    layer.msg('更新失败', {time: 2000, icon: 2});
                }
            });
        });

    });
</script>
</body>
</html>