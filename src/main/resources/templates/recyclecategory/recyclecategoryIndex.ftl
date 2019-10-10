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

                    <div class="layui-btn-group test-table-operate-btn" style="margin-bottom: 10px;">
                        <button class="layui-btn" data-type="getCheckData">添加垃圾类别</button>
                    </div>

                    <table class="layui-hide" id="test-table-page" lay-filter="test-table-operate"></table>

                    <script type="text/html" id="test-table-operate-barDemo">
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<#--编辑-->
<div class="layui-fluid" id="editRecyclecategory" style="display: none;">
    <div>
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">垃圾类别:</label>
                <div class="layui-input-block">
                    <input type="text" id="editCategoryName" autocomplete="off" placeholder="请填写垃圾类别名称"
                           class="layui-input" lay-verify="required" required/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="submitEdit">提交</button>
                    <button class="layui-btn closeSigninRecycleCategory">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>

<#--添加-->
<div class="layui-fluid" id="addRecyclecategory" style="display: none;">
    <div>
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">垃圾类别:</label>
                <div class="layui-input-block">
                    <input type="text" id="addCategoryName" autocomplete="off" placeholder="请填写垃圾类别名称"
                           class="layui-input" lay-verify="required" required/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="submitAdd">提交</button>
                    <button class="layui-btn closeSigninRecycleCategory">关闭</button>
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
            url: '/recyclecategory/findAll',
            page: true,
            height: 'full-100',
            cellMinWidth: 80,
            id: 'idTest',
            cols: [[
                {type: 'numbers', title: '序号', align: 'center'},
                {field: 'recycleCategoryName', title: '垃圾类别名称', align: 'center'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {align: 'center', title: '操作', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
        });

        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                layer.open({
                    title: "编辑垃圾类别",
                    type: 1,
                    content: $("#editRecyclecategory"),
                    area: ['450px', '180px']
                });

                $('#editCategoryName').val("");
                $('#editCategoryName').val(data.recycleCategoryName);
                form.on('submit(submitEdit)', function () {

                    var recycleCategoryName = $('#editCategoryName').val();

                    // $.get('signin/modifyById', {
                    //     id: data.id,
                    //     signinCount: signinCount
                    // }, function (res) {
                    //     if (res.code == 0) {
                    //         layer.closeAll();
                    //         layer.msg(res.msg, {time: 2000, icon: 1});
                    //         table.reload('idTest');
                    //     } else {
                    //         layer.msg(res.msg, {time: 2000, icon: 2});
                    //     }
                    // });
                });
            } else if (obj.event === 'del') {
                layer.confirm('真的删除数据吗?这将无法恢复', function (index) {

                    $.get('recyclecategory/deleteById', {
                        id: data.id,
                    }, function (res) {
                        if (res.code == 0) {
                            obj.del();
                            layer.msg(res.msg, {time: 2000, icon: 1});
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    });
                });

            }
        });

        var $ = layui.$, active = {
            getCheckData: function () {
                $('#addCategoryName').val("");
                var index = layer.open({
                    title: "添加垃圾类别",
                    type: 1,
                    content: $("#addRecyclecategory"),
                    area: ['450px', '180px']
                });
            }
        };
        form.on('submit(submitAdd)', function () {
            var recycleCategoryName = $('#addCategoryName').val();

            $.get('recyclecategory/save', {
                recycleCategoryName: recycleCategoryName
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

        $('.closeSigninRecycleCategory').click(function () {
            layer.closeAll();
        });


        $('.test-table-operate-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#closeSigninRecycleCategory').click(function () {
            layer.closeAll();
        });

    });
</script>
</body>
</html>