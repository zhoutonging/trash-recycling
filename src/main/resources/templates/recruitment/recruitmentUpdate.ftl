<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品添加</title>
    <meta name="renderer" content="webkit">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/step-lay/step.css">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding-top: 40px;">
            <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">
                <div carousel-item>
                    <div>
                        <div class="layui-form" style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
                            <div class="layui-form-item">
                                <label class="layui-form-label">招聘名称:</label>
                                <div class="layui-input-block">
                                    <input type="text" id="recruitmentName" lay-verify="required" autocomplete="off"
                                           placeholder="请输入招聘名称" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">招聘人:</label>
                                <div class="layui-input-block">
                                    <input type="text" id="contacts" lay-verify="required" autocomplete="off"
                                           placeholder="请输入招聘人信息" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">联系方式:</label>
                                <div class="layui-input-block">
                                    <input type="text" id="mobilePhone" lay-verify="required|phone" autocomplete="off"
                                           placeholder="请输入联系方式" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit lay-filter="formStep"
                                            style="margin-left: 100px;">
                                        &emsp;下一步&emsp;
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div id="editor"></div>
                        <div class="layui-form-item" style="margin-top: 15px;text-align: center;margin-right: 12em;">
                            <div class="layui-input-block" style="margin-left: 133px">
                                <button type="button" class="layui-btn layui-btn-primary preImg">重新开始</button>
                                <button class="layui-btn" lay-submit lay-filter="component-form-demo1">
                                    &emsp;提交&emsp;
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script src="/static/common/step-lay/step.js"></script>
<script type="text/javascript" src="/static/common/wangEditor/release/wangEditor.min.js"></script>
<script>
    var E = window.wangEditor;
    var editor = new E('#editor');

    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.uploadImgServer = '/upload'
    editor.create();
</script>
<script>
    layui.config({
        base: '/static/common/step-lay/'
    }).use(['form', 'step', 'upload'], function () {
        var $ = layui.$
            , form = layui.form
            , step = layui.step
            , upload = layui.upload;

        var id = location.search.substr(location.search.lastIndexOf("=") + 1);
        $.get('recruitment/findById', {
            Id: id
        }, function (res) {
            var data = res.data;
            $('#recruitmentName').val(data.recruitmentName);
            $('#contacts').val(data.contacts);
            $('#mobilePhone').val(data.mobilePhone);
            editor.txt.html(data.recruitmentContent);
        });

        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '500px',
            height: '500px',
            stepItems: [{
                title: '填写基本信息'
            }, {
                title: '填写招聘内容'
            }]
        });


        var index = parent.layer.getFrameIndex(window.name);
        form.on('submit(component-form-demo1)', function (data) {

            var recruitmentName = $('#recruitmentName').val();
            var contacts = $('#contacts').val();
            var mobilePhone = $('#mobilePhone').val();

            var recruitmentContent = editor.txt.html(); //获取富文本内容

            $.post('recruitment/modifyById', {
                id: id,
                recruitmentName: recruitmentName,
                contacts: contacts,
                mobilePhone: mobilePhone,
                recruitmentContent:recruitmentContent
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


        form.on('submit(formStep)', function (data) {
            step.next('#stepForm');
            return false;
        });

        form.on('submit(formStep2)', function (data) {

            step.next('#stepForm');
            return false;
        });


        $('.pre').click(function () {
            step.pre('#stepForm');
        });

        $('.next').click(function () {
            step.next('#stepForm');
        });

        $('.preImg').click(function () {
            step.next('#stepForm');
        });
    })
</script>
</body>
</html>
