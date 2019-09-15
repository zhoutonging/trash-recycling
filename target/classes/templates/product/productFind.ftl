<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品添加</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
    <script type="text/javascript" src="/static/common/wangEditor/release/wangEditor.min.js"></script>
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
</style>
<body>

<div class="layui-fluid">
    <div class="layui-card  ">
        <div class="layui-card-body" style="padding: 15px;">
            <div class="layui-form " action="" lay-filter="component-form-group">
                <blockquote class="layui-elem-quote layui-text" style="font-weight: bold;">商品基本信息
                </blockquote>
            </div>
            <div>
                <div class="layui-form-item">
                    <label class="layui-form-label">商品名称</label>
                    <div class="layui-input-inline">
                        <input type="text" id="productName" lay-verify="required" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">商品绿色值</label>
                    <div class="layui-input-inline">
                        <input type="text" id="productPrice" lay-verify="" placeholder="" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">创建时间</label>
                    <div class="layui-input-inline">
                        <input type="text" id="createTime" lay-verify="required" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">修改时间</label>
                    <div class="layui-input-inline">
                        <input type="text" id="updateTime" lay-verify="required" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form " action="" lay-filter="component-form-group">
                <blockquote class="layui-elem-quote layui-text" style="font-weight: bold;">商品图
                </blockquote>
            </div>
            <div style="text-align: center">
                <div class="layui-upload">
                    <img id="productIcon" style="width: 200px;height: 200px;"/>
                </div>
            </div>

            <div class="layui-form " action="" lay-filter="component-form-group">
                <blockquote class="layui-elem-quote layui-text" style="font-weight: bold;">商品描述
                </blockquote>
            </div>

            <!--富文本编辑器-->
            <div id="editor"></div>
        </div>
    </div>
</div>
</div>


<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    var E = window.wangEditor;
    var editor = new E('#editor');

    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.uploadImgServer = '/upload'
    editor.create();
</script>
<script>
    var id = location.search.substr(location.search.lastIndexOf("=") + 1);
    $.get('product/findById', {
        id: id
    }, function (res) {
        if (res.code == 0) {
            var data = res.data;
            $('#productName').val(data.productName);
            $('#productPrice').val(data.productPrice);
            $('#createTime').val(data.createTime);
            $('#updateTime').val(data.updateTime);
            $("#productIcon").attr("src", data.productIcon);

            //富文本赋值
            editor.txt.html(data.productDescription);
            //富文本禁用状态
            editor.$textElem.attr('contenteditable', false)

        } else {
            layer.msg('查询失败', {time: 2000, icon: 2});
        }

    });
    $("input").attr("readonly", true);
</script>

</body>
</html>