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
                                <input type="tel" id="slideName" lay-verify="required" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-upload">
                            <label class="layui-form-label">广告图片:</label>
                            <div class="layui-upload-list">
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

        var id = location.search.substr(location.search.lastIndexOf("=") + 1);
        $.get('slideShow/findById', {
            id: id
        }, function (res) {
            var data = res.data;
            console.log(res.data)
            $('#slideName').val(data.slideName);
            $("#demo1").attr("src", data.image);

            editor.txt.html(data.describe);
            editor.$textElem.attr('contenteditable', false);
            $("input").attr("readonly", true);

        });

    });
</script>
</body>

</html>