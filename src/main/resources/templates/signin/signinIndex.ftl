<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>签到积分管理</title>
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
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="layui-fluid" id="editSignin" style="display: none;">
    <div>
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">签到积分:</label>
                <div class="layui-input-block">
                    <input type="number" id="signinCount" min="1" placeholder="请填写用户签到所得到的积分"
                           class="layui-input"
                           lay-verify="number" required>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="submitSignin">提交</button>
                    <button class="layui-btn" id="closeSignin">关闭</button>
                </div>
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
            url: '/signin/findAll',
            page: true,
            height: 'full-50',
            cellMinWidth: 80,
            id: 'idTest',
            cols: [[
                {type: 'numbers', title: '序号', align: 'center'},
                {field: 'signinCount', title: '用户签到所获积分', align: 'center'},
                {field: 'updateTime', title: '更新时间', align: 'center'},
                {align: 'center', title: '操作', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
        });

        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                layer.open({
                    title: "编辑签到积分",
                    type: 1,
                    content: $("#editSignin"),
                    area: ['450px', '180px']
                });

                $('#signinCount').val(data.signinCount);

                form.on('submit(submitSignin)', function () {
                    var signinCount = $('#signinCount').val();

                    $.get('signin/modifyById', {
                        id: data.id,
                        signinCount: signinCount
                    }, function (res) {
                        if (res.code == 0) {
                            layer.closeAll();
                            layer.msg(res.msg, {time: 2000, icon: 1});
                            table.reload('idTest');
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    });
                });
            }
        });


        $('#closeSignin').click(function () {
            layer.closeAll();
        });

    });
</script>
</body>
</html>