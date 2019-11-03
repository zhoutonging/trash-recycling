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
    <script type="text/javascript" src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>

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
            <div>
                <div class="layui-form-item">
                    <label style="color: #0C0C0C">垃圾类别:</label>
                    <label><span id="categoryName"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">垃圾名称:</label>
                    <label><span id="recycleName"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C" class="w3">联系人:</label>
                    <label><span id="name"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">联系方式:</label>
                    <label><span id="mobile"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">取件地址:</label>
                    <label><span id="addressName"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">预约时间:</label>
                    <label><span id="appointmentTime" style="color: red"></span></label>
                </div>

                <div class="layui-form-item">
                    <label style="color: #0C0C0C">创建时间:</label>
                    <label><span id="createTime"></span></label>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label style="color: #0C0C0C">用户留言:</label>
                    <label><span id="message"></span></label>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label style="color: #0C0C0C" class="w3">垃圾图:</label>
                    <div class="layui-upload">
                        <img id="recycleImg" style="width: 200px;height: 200px;"/>
                    </div>
                </div>

            </div>
            <div class="layui-form-item layui-form-text">

            </div>

        </div>
    </div>
</div>
</div>


<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    var id = location.search.substr(location.search.lastIndexOf("=") + 1);
    $.get('recycle/findAll', {
        id: id
    }, function (res) {
        if (res.code == 0) {
            var data = res.data[0];

            $('#categoryName').text(data.categoryName);
            $('#recycleName').text(data.recycleName);
            $('#createTime').text(data.createTime);
            $('#addressName').text(data.addressName);
            $('#mobile').text(data.mobile);
            $('#name').text(data.name);
            $('#appointmentTime').text(data.appointmentTime);
            $('#message').text(data.message);
            $("#recycleImg").attr("src", data.recycleImg);

        } else {
            layer.msg('查询失败', {time: 2000, icon: 2});
        }

    });
    $("input").attr("readonly", true);
</script>

</body>
</html>