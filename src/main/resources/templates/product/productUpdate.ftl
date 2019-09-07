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

            </div>

            <div class="layui-form " action="" lay-filter="component-form-group">
                <blockquote class="layui-elem-quote layui-text" style="font-weight: bold;">商品图
                </blockquote>
            </div>
            <div class="layui-upload" style="text-align: center">
                <button type="button" class="layui-btn" id="test1">上传图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo1" style="width: 200px;height: 200px">
                    <p id="demoText"></p>
                </div>
            </div>

            <div class="layui-form " action="" lay-filter="component-form-group">
                <blockquote class="layui-elem-quote layui-text" style="font-weight: bold;">商品描述
                </blockquote>
            </div>

            <!--富文本编辑器-->
            <div id="editor"></div>

            <div class="layui-form-item" style="text-align: center;margin-top: 20px">
                <div class="layui-input-block">
                    <div class="layui-footer" style="left: 0;">
                        <button class="layui-btn" lay-submit="" lay-filter="component-form-demo1">立即提交</button>
                        <button class="layui-btn layui-btn-primary" id="component-form-demo2">返回</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script>
    var E = window.wangEditor;
    var editor = new E('#editor');

    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.uploadImgServer = '/upload'//上传图片
    editor.create();
</script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'form', 'laydate', 'upload'], function () {
        var $ = layui.$, admin = layui.admin, upload = layui.upload, element = layui.element, layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        form.render(null, 'component-form-group');
        laydate.render({
            elem: '#LAY-component-form-group-date'
        });

        //商品图片上传
        var productIcon;
        var uploadInst = upload.render({
            elem: '#test1',
            url: '/upload',
            before: function (obj) {
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            },
            done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                if (res.data.length != 0) {
                    productIcon = res.data[0];
                }
            }
        });

        var id = location.search.substr(location.search.lastIndexOf("=") + 1);
        $.get('product/findById', {
            id: id
        }, function (res) {
            if (res.code == 0) {
                var data = res.data;
                $('#productName').val(data.productName);
                $('#productPrice').val(data.productPrice);
                $("#demo1").attr("src", data.productIcon);

                //富文本赋值
                editor.txt.html(data.productDescription);
            } else {
                layer.msg('查询失败', {time: 2000, icon: 2});
            }

        });

        var index = parent.layer.getFrameIndex(window.name);
        form.on('submit(component-form-demo1)', function (data) {

            var productName = $('#productName').val();
            var productPrice = $('#productPrice').val();
            var productDescription = editor.txt.html(); //获取富文本内容

            $.post('product/modifyById', {
                id: id,
                productName: productName,
                productPrice: productPrice,
                productDescription: productDescription,
                productIcon: productIcon
            }, function (res) {
                if (res.code == 0) {
                    layer.msg(res.msg, {time: 2000, icon: 1});
                    var int = self.setInterval(function () {
                        parent.layer.close(index);
                        parent.location.reload();
                    }, 2000)
                } else {
                    layer.msg(res.msg, {time: 2000, icon: 2});
                }
            });
        });

        $('#component-form-demo2').click(function () {
            parent.layer.close(index);
        });
    });
</script>

</body>
</html>