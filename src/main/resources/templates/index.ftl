<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>智能回收箱管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/notice/notice.css" media="all">
</head>

<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="http://www.layui.com/" target="_blank" title="前台">
                        <i class="layui-icon layui-icon-website"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search"
                           layadmin-event="serach" lay-action="template/search.html?keywords=">
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

                <li class="layui-nav-item" lay-unselect>
                    <a lay-href="app/message/index.html" layadmin-event="message" lay-text="消息中心">
                        <i class="layui-icon layui-icon-notice"></i>

                        <span class="layui-badge-dot"></span>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="note">
                        <i class="layui-icon layui-icon-note"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="fullscreen">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite>${userName}</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="set/user/info.html">基本资料</a></dd>
                        <dd><a lay-href="set/user/password.html">修改密码</a></dd>
                        <hr>
                        <dd style="text-align: center;"><a href="/logout">退出</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="about"><i
                                class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="">
                    <span>layuiAdmin</span>
                </div>
                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">

                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="主页" lay-direction="2">
                            <i class="layui-icon layui-icon-home"></i>
                            <cite>主页</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console">
                                <a lay-href="slideShowIndex">广告</a>
                            </dd>
                            <dd data-name="console">
                                <a lay-href="home/homepage2.html">主页二</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="商品管理" lay-direction="2">
                            <i class="layui-icon layui-icon-cart-simple"></i>
                            <cite>商城管理</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="productIndex">商品详情</a>
                            </dd>

                            <dd data-name="console">
                                <a lay-href="orderIndex">兑换订单</a>
                            </dd>

                            <dd data-name="console" class="">
                                <a lay-href="addressIndex">收货地址</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="小程序设置" lay-direction="2">
                            <i class="layui-icon layui-icon-user"></i>
                            <cite>公益伙伴</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="recruitmentIndex">招聘信息</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="垃圾回收" lay-direction="2">
                            <i class="layui-icon layui-icon-delete"></i>
                            <cite>垃圾回收</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="recyclecategoryIndex">垃圾类别</a>
                            </dd>
                        </dl>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="recycleIndex">上门回收</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="垃圾回收" lay-direction="2">
                            <i class="layui-icon layui-icon-chart-screen"></i>
                            <cite>环保百科</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="cyclopediaIndex">环保百科</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="垃圾回收" lay-direction="2">
                            <i class="layui-icon layui-icon-find-fill"></i>
                            <cite>社区动态</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="communityIndex">社区动态</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="小程序设置" lay-direction="2">
                            <i class="layui-icon layui-icon-util"></i>
                            <cite>柜机设置</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="cabinetlocationIndex">柜机位置</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="小程序设置" lay-direction="2">
                            <i class="layui-icon layui-icon-template-1"></i>
                            <cite>小程序管理</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="userIndex">用户管理</a>
                            </dd>

                            <dd data-name="console" class="">
                                <a lay-href="signinIndex">签到积分</a>
                            </dd>

                            <dd data-name="console">
                                <a lay-href="permissionManage">积分配置</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="小程序设置" lay-direction="2">
                            <i class="layui-icon layui-icon-set-sm"></i>
                            <cite>系统设置</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="console" class="">
                                <a lay-href="adminUserIndex">系统用户</a>
                            </dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="/console" lay-attr="/console" class="layui-this"><i
                                class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>

        <#--消息提醒提示音,不支持谷歌浏览器-->
        <#--<audio src="/file/20191104200849318541.mp3" controls="controls" preload id="music1" hidden>-->
        <#--</audio>-->

        <!-- 桌面 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="/home" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>


        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script src="/static/common/jquery/jquery.js"></script>
<script src="/static/common/layuiadmin/notice/notice.js"></script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'table'], function () {
        var admin = layui.admin, table = layui.table, form = layui.form;


    });
</script>
<script>
    // 消息提醒提示音,不支持谷歌浏览器
    // var audio = document.getElementById('music1');
    // audio.currentTime = 0;
    /**
     * 整合通知栏信息
     * @type {tabs.notice|{text, id}|*}
     */
    var notice = layui.notice;
    notice.options = {
        closeButton: true,//显示关闭按钮
        debug: false,//启用debug
        positionClass: "toast-top-right",//弹出的位置,
        showDuration: "300",//显示的时间
        hideDuration: "1000",//消失的时间
        timeOut: "5000",//停留的时间
        extendedTimeOut: "1000",//控制时间
        showEasing: "swing",//显示时的动画缓冲方式
        hideEasing: "linear",//消失时的动画缓冲方式
        iconClass: 'toast-info', // 自定义图标，有内置，如不需要则传空 支持layui内置图标/自定义iconfont类名
        onclick: null, // 点击关闭回调
    };

    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        //建立连接，这里的/websocket ，是Servlet中注解中的那个值
        // websocket = new WebSocket("ws://mengzhou.nat300.top/websocket");
        websocket = new WebSocket("ws://localhost:8080/websocket");
    } else {
        alert('当前浏览器 Not support websocket');
    }
    //连接发生错误的回调方法
    websocket.onerror = function () {
        console.log("WebSocket连接发生错误");
    };
    //连接成功建立的回调方法
    websocket.onopen = function () {
        console.log("WebSocket连接成功");
    }
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log(event.data)
        if (event.data == "1") {
            notice.warning("有新的商城订单,请注意查看~");
            // 消息提醒提示音,不支持谷歌浏览器
            // audio.play();
        } else if (event.data == "2") {
            notice.warning("有新的上门回收订单,请注意查看~");
        }else if (event.data == "3") {
            notice.warning("有新的社区动态发表,请注意审核~");
        }
    }
    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log("WebSocket连接关闭");
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭WebSocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
</script>
</body>
</html>