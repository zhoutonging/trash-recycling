<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>垃圾回收</title>
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
                                    <input class="layui-input" type="text" name="recycleId" id="recycleId"
                                           placeholder="请输入回收编号" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <select name="city" lay-verify="" id="productStatus">
                                        <option value="">请选择回收状态</option>
                                        <option value="0">等待处理</option>
                                        <option value="1">正在处理</option>
                                        <option value="2">已完成</option>
                                    </select>
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

                    <table class="layui-hide" id="test-table-page" lay-filter="test-table-operate"></table>

                    <script type="text/html" id="test-table-operate-barDemo">
                        <a class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="detail">查看</a>
                        <a class="layui-btn layui-btn-xs layui-bg-blue" lay-event="detail">积分</a>
                        <a class="layui-btn layui-btn-xs layui-bg-green" lay-event="update">状态</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="layui-fluid" id="editStatus" style="display: none;">
    <div>
        <div class="layui-form">
            <div class="layui-form-item" style="text-align: center">
                <div class="layui-inline">
                    <label class="layui-form-label">回收状态</label>
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-search="" id="status">
                            <option value="">直接选择或搜索选择</option>
                            <option value="1">正在处理</option>
                            <option value="2">已完成</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div style="margin-left: 10em;margin-top: 5em;">
                    <button class="layui-btn" lay-submit lay-filter="submitStatus" id="submitStatus">提交</button>
                    <button class="layui-btn" id="closeSignin">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layui-fluid" id="addIntegral" style="display: none;">
    <div>
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">回收积分:</label>
                <div class="layui-input-block">
                    <input type="number" id="integral" min="1" placeholder="请填写上门回收所得到的积分"
                           class="layui-input"
                           lay-verify="number" required>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="submitIntegral">提交</button>
                    <button class="layui-btn" id="closeSignin">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="statusTemp">
    {{#  if(d.status==0||d.status==1){ }}
    <a class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs layui-bg-blue" lay-event="integal">积分</a>
    <a class="layui-btn layui-btn-xs layui-bg-green" lay-event="update">状态</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } else if(d.status==2&&d.integral==null){ }}
    <a class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs layui-bg-blue" lay-event="integal">积分</a>
    <a class="layui-btn layui-btn-xs layui-bg-gray"   layui-btn-disabled">状态</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-xs layui-bg-cyan" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs layui-bg-gray" layui-btn-disabled">积分</a>
    <a class="layui-btn layui-btn-xs layui-bg-gray"  layui-btn-disabled">状态</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  } }}
</script>
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
            url: '/recycle/findAll',
            page: true,
            height: 'full-110',
            cellMinWidth: 10,
            id: 'idTest',
            cols: [[
                // {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: '回收编号', align: 'center'},
                {field: 'categoryName', title: '垃圾类别', align: 'center'},
                {field: 'recycleName', title: '垃圾名称', align: 'center'},
                {field: 'appointmentTime', title: '预约时间', align: 'center'},
                {
                    field: 'status', title: '回收状态', align: 'center', templet: function (d) {
                        if (d.status == 0) {
                            return '等待处理';
                        } else if (d.status == 1) {
                            return '正在处理';
                        } else {
                            return '已完成';
                        }
                    }

                },
                {align: 'center', title: '操作', fixed: 'right', toolbar: '#statusTemp'}
            ]]
        });

        //商品查看OR删除
        table.on('tool(test-table-operate)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                var index = layer.open({
                    type: 2,
                    title: '查看垃圾回收',
                    content: "recycleFind?id=" + data.id,
                    area: ['100%', '100%']
                });
            } else if (obj.event === 'update') {

                layer.open({
                    title: "修改垃圾回收状态",
                    type: 1,
                    content: $("#editStatus"),
                    area: ['450px', '250px']
                });

                //监听提交
                form.on('submit(submitStatus)', function () {
                    var status = $("#modules option:selected").val();

                    $.post('recycle/modifyByStatus', {
                        status: status,
                        id: data.id
                    }, function (res) {
                        if (res.code == 0) {
                            layer.closeAll();
                            layer.msg(res.msg, {time: 2000, icon: 1});
                            table.reload('idTest');
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    })

                });
            } else if (obj.event === 'del') {
                layer.confirm('真的删除数据吗?这将无法恢复', function (index) {
                    $.get('recycle/deleteById', {id: data.id}, function (res) {
                        if (res.code == 0) {
                            obj.del();
                            layer.msg(res.msg, {time: 2000, icon: 1});
                        } else {
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                    })
                });
            } else if (obj.event === 'integal') {
                if (data.status == 1 || data.status == 0) {
                    layer.msg("回收状态必须是已完成状态");
                    return;
                }
                layer.open({
                    title: "添加回收积分",
                    type: 1,
                    content: $("#addIntegral"),
                    area: ['450px', '180px']
                });

                form.on('submit(submitIntegral)', function () {
                    var integral = $('#integral').val();

                    $.post('recycle/modifyByIntegral', {
                        id: data.id,
                        integral: integral
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


        //条件查询
        form.on('submit(seek)', function () {
            var id = $('#recycleId').val();
            var status =$('#productStatus option:selected') .val();

            table.reload('idTest', {
                url: 'recycle/findAll',
                //让重载后的页码从1开始
                page: {curr: 1},
                where: {
                    id: id,
                    status:status
                }
            });
        });
    });
</script>
</body>
</html>