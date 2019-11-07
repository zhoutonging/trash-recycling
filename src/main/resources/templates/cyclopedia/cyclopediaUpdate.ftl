<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>环保百科修改</title>
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
                                <label class="layui-form-label">百科名称:</label>
                                <div class="layui-input-block">
                                    <input type="text" id="cyclopediaName" lay-verify="required" autocomplete="off"
                                           placeholder="请输入环保百科名称" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">百科名称:</label>
                                <div class="layui-input-block">
                                    <input type="text" id="cyclopediaDesc" lay-verify="required" autocomplete="off"
                                           placeholder="请输入环保百科描述" class="layui-input">
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
                        <div class="layui-form" style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
                            <div class="layui-form-item">
                                <label class="layui-form-label">环保图:</label>
                                <div class="layui-upload">
                                    <div class="layui-upload-drag" id="test1" style="display: none">
                                        <i class="layui-icon"></i>
                                        <p>点击上传，或将文件拖拽到此处</p>
                                    </div>
                                    <div id="consoleUpdateMsg" style="margin-top: 10px;text-align: center;margin-left: 2em;">
                                        <span  style="display: none; color: red">重新上传如果不选择默认上次上传的图片</span>
                                    </div>
                                    <div  id="uploadImg">
                                        <img id="imgSrc" style="width: 300px;height: 300px">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block" id="buttons">
                                    <button type="button" class="layui-btn layui-btn-primary pre">上一步</button>
                                    <button type="button" class="layui-btn layui-btn-primary anew"
                                            >重新上传
                                    </button>
                                    <button class="layui-btn" lay-submit lay-filter="formStep2">
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

        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '750px',
            height: '500px',
            stepItems: [{
                title: '填写环保信息'
            }, {
                title: '上传描述图'
            }, {
                title: '填写环保内容'
            }]
        });

        var id = location.search.substr(location.search.lastIndexOf("=") + 1);
        $.get('cyclopedia/findById', {
            id: id
        }, function (res) {
            if (res.code == 0) {
                var data = res.data;
                $('#cyclopediaName').val(data.cyclopediaName);
                $('#cyclopediaDesc').val(data.cyclopediaDesc);
                $("#imgSrc").attr("src", data.cyclopediaIcon);

                //富文本赋值
                editor.txt.html(data.cyclopediaContent);
            } else {
                layer.msg('查询失败', {time: 2000, icon: 2});
            }

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
                console.log(res.data[0]);
                if (res.data.length != 0) {
                    productIcon = res.data[0];
                    $("#imgSrc").attr("src", productIcon);
                    $("#uploadImg").css("display", "inline-block");
                    $(".anew").css("display", "inline-block");
                    $("#test1").css("display", "none");
                    $("#buttons").css("margin-left", "107px");
                    $("#consoleUpdateMsg").css("display", "none");

                }
            }
        });


        var index = parent.layer.getFrameIndex(window.name);
        form.on('submit(component-form-demo1)', function (data) {

            var cyclopediaName = $('#cyclopediaName').val();
            var cyclopediaDesc = $('#cyclopediaDesc').val();
            var cyclopediaContent = editor.txt.html(); //获取富文本内容

            $.post('cyclopedia/modifyById', {
                id:id,
                cyclopediaName: cyclopediaName,
                cyclopediaDesc: cyclopediaDesc,
                cyclopediaContent: cyclopediaContent,
                cyclopediaIcon: productIcon
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

            // if (productIcon == null || productIcon == undefined) {
            //     layer.msg('商品图不能为空喔', {time: 2000, icon: 2});
            //     return;
            // }

            step.next('#stepForm');
            return false;
        });

        $(".anew").click(function () {
            $(".anew").css("display", "none");
            $("#test1").css("display", "inline-block");
            $("#consoleUpdateMsg").css("display", "block");
            $("#uploadImg").css("display", "none");
            $("#buttons").css("margin-left", "133px");
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
