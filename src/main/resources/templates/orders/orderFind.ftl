<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>查询垃圾回收基本信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">

</head>
<style>
    .layui-input-inline {
        float: left;
        position: relative;
    }

    .layui-form-item {
        width: 310px;
        margin-left: auto;
        margin-right: auto;
    }

    .layui-form-item {
        width: 400px;
    }

    .w3 {
        letter-spacing: 0.5em; /*如果需要y个字两端对齐，则为(x-y)/(y-1),这里是（4-3）/(3-1)=0.5em */
        margin-right: -0.5em; /*同上*/
    }
</style>
<body>

<div class="layui-fluid">
    <div class="layui-card  ">
        <div class="layui-card-body" style="padding: 15px;">
            <div class="layui-btn-group test-table-operate-btn" style="margin-bottom: 10px;">
                <button class="layui-btn" data-type="getCheckData">导出Excel</button>
            </div>
            <div>
                <div class="layui-form-item">
                    <label style="color: #0C0C0C">订单编号:</label>
                    <label><span id="orderId"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">支付状态:</label>
                    <label><span id="status"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">商品名称:</label>
                    <label><span id="productName"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">购买数量:</label>
                    <label><span id="productCount"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C" class="w3">总积分:</label>
                    <label><span id="integral"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C" class="w3">收件人:</label>
                    <label><span id="userName"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">联系方式:</label>
                    <label><span id="mobile" style="color: red"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">详细地址:</label>
                    <label><span id="address"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">购买时间:</label>
                    <label><span id="createTime"></span></label>
                </div>

            </div>
            <div class="layui-form-item layui-form-text">

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
        var admin = layui.admin, table = layui.table, form = layui.form, $ = layui.$;
        var excel = layui.excel;


        var id = location.search.substr(location.search.lastIndexOf("=") + 1);
        $.get('orders/findOrderById', {
            id: id
        }, function (res) {
            if (res.success == true) {
                var data = res.data[0];
                $('#orderId').text(data.id);
                $('#status').text(data.status);
                $('#productName').text(data.productName);
                $('#productCount').text(data.productCount);
                $('#integral').text(data.integral);
                $('#userName').text(data.userName);
                $('#mobile').text(data.mobile);
                $('#address').text(data.address);
                $('#createTime').text(data.createTime);
            } else {
                alert('查询失败')
            }
        });

        /**
         * 订单导出
         * @type {pe}
         */
        var $ = layui.$, active = {

            getCheckData: function () {
                $.get('orders/findOrderById', {id: id}, function (res) {
                    var data = res.data;

                    // 1. 数组头部新增表头
                    res.data.unshift({
                        id: '订单编号', status: '支付状态', productName: '商品名称',
                        productCount: '购买数量', integral: '总金额', userName: '收件人',
                        mobile: '联系方式', address: '收货地址', createTime: '购买时间'
                    });
                    // 2. 如果需要调整顺序，请执行梳理函数
                    var data = excel.filterExportData(res.data, [
                        'id',
                        'status',
                        'productName',
                        'productCount',
                        'integral',
                        'userName',
                        'mobile',
                        'address',
                        'createTime'
                    ]);
                    // 3. 执行导出函数，系统会弹出弹框
                    excel.exportExcel({
                        sheet1: data
                    }, '订单'+id + '的信息.xlsx', 'xlsx');
                });
            }
        };

        $('.test-table-operate-btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

</body>
</html>