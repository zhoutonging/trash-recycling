<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>表单组合</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
    <script type="text/javascript" src="/static/common/wangEditor/release/wangEditor.min.js"></script>
</head>

<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 15px;">
            <div style="width: 800px; position: relative; left:33%;">

                <div class="layui-form" action="" lay-filter="component-form-group">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">广告名称:</label>
                            <div class="layui-input-inline">
                                <input type="tel" name="phone" lay-verify="required" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-upload">
                            <label class="layui-form-label">广告图片:</label>
                            <div class="layui-upload-drag" id="test1">
                                <i class="layui-icon"></i>
                                <p>点击上传，或将文件拖拽到此处</p>
                            </div>
                            <div class="layui-upload-list" style="display: none">
                                <img class="layui-upload-img" id="demo1" style="width: 400px;height: 250px">
                                <p id="demoText"></p>
                            </div>
                        </div>
                    </div>

                    <!--富文本编辑器-->
                    <div id="editor"></div>
                </div>
            </div>

        </div>
        <div id="editor"></div>
        <div class="layui-form-item" style="text-align: center;margin-top: 20px;padding-bottom: 25px;">
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

<script>
    var E = window.wangEditor;
    var editor = new E('#editor');

    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.uploadImgServer = '/upload'//上传图片
    editor.create();
</script>
<script src="/static/common/layuiadmin/layui/layui.js"></script>
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

        //返回
        var index = parent.layer.getFrameIndex(window.name);
        $('#component-form-demo2').click(function () {
            parent.layer.close(index);
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
                    $(".layui-upload-list").css("display", "inline-block");
                    $("#test1").css("display", "none");
                }
            }
        });

    });
</script>
</body>

</html>