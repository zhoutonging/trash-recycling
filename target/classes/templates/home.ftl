<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layuiAdmin 控制台主页一</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">快捷方式</div>
                        <div class="layui-card-body">

                            <div class="layui-carousel layadmin-carousel layadmin-shortcut">
                                <div carousel-item>
                                    <ul class="layui-row layui-col-space10">
                                        <li class="layui-col-xs3">
                                            <a lay-href="home/homepage1.html">
                                                <i class="layui-icon layui-icon-console"></i>
                                                <cite>主页一</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="home/homepage2.html">
                                                <i class="layui-icon layui-icon-chart"></i>
                                                <cite>主页二</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="component/layer/list.html">
                                                <i class="layui-icon layui-icon-template-1"></i>
                                                <cite>弹层</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a layadmin-event="im">
                                                <i class="layui-icon layui-icon-chat"></i>
                                                <cite>聊天</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="/orderIndex">
                                                <i class="layui-icon layui-icon-find-fill"></i>
                                                <cite>进度条</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="app/workorder/list.html">
                                                <i class="layui-icon layui-icon-survey"></i>
                                                <cite>工单</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="user/user/list.html">
                                                <i class="layui-icon layui-icon-user"></i>
                                                <cite>用户</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/system/website.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>设置</cite>
                                            </a>
                                        </li>
                                    </ul>
                                    <ul class="layui-row layui-col-space10">
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/user/info.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/user/info.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/user/info.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/user/info.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/user/info.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/user/info.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/user/info.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="set/user/info.html">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                    </ul>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">待办事项</div>
                        <div class="layui-card-body">

                            <div class="layui-carousel layadmin-carousel layadmin-backlog">
                                <div carousel-item>
                                    <ul class="layui-row layui-col-space10">
                                        <li class="layui-col-xs6">
                                            <a lay-href="app/content/comment.html" class="layadmin-backlog-body">
                                                <h3>待审评论</h3>
                                                <p><cite>66</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs6">
                                            <a lay-href="app/forum/list.html" class="layadmin-backlog-body">
                                                <h3>待审帖子</h3>
                                                <p><cite>12</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs6">
                                            <a lay-href="template/goodslist.html" class="layadmin-backlog-body">
                                                <h3>待审商品</h3>
                                                <p><cite>99</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs6">
                                            <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});"
                                               class="layadmin-backlog-body">
                                                <h3>待发货</h3>
                                                <p><cite>20</cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                    <ul class="layui-row layui-col-space10">
                                        <li class="layui-col-xs6">
                                            <a href="javascript:;" class="layadmin-backlog-body">
                                                <h3>待审友情链接</h3>
                                                <p><cite style="color: #FF5722;">5</cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>



        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">版本信息</div>
                <div class="layui-card-body layui-text">
                    <table class="layui-table">
                        <colgroup>
                            <col width="100">
                            <col>
                        </colgroup>
                        <tbody>
                        <tr>
                            <td>当前版本</td>
                            <td style="text-align: center">1.0.0 Version</td>
                        </tr>
                        <tr>
                            <td>前端框架</td>
                            <td style="text-align: center">Layui</td>
                        </tr>
                        <tr>
                            <td>后台框架</td>
                            <td style="text-align: center">SprngBoot/ MySQL / Redis</td>
                        </tr>
                        <tr>
                            <td>技术支持</td>
                            <td style="padding-bottom: 0;">
                                <div class="layui-btn-container" style="text-align: center;">
                                    <a target="_blank" style="border: none;"
                                       class=" layui-btn layui-btn-primary">江苏梦舟信息技术有限公司</a>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">数据概览</div>
                <div class="layui-card-body">
                    <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade"
                         lay-filter="LAY-index-normcol">
                        <div carousel-item id="LAY-index-normcol">
                            <div><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">效果报告</div>
                <div class="layui-card-body layadmin-takerates">
                    <div class="layui-progress" lay-showPercent="yes">
                        <h3>转化率（日同比 28% <span class="layui-edge layui-edge-top" lay-tips="增长" lay-offset="-15"></span>）
                        </h3>
                        <div class="layui-progress-bar" lay-percent="65%"></div>
                    </div>
                    <div class="layui-progress" lay-showPercent="yes">
                        <h3>签到率（日同比 11% <span class="layui-edge layui-edge-bottom" lay-tips="下降"
                                              lay-offset="-15"></span>）</h3>
                        <div class="layui-progress-bar" lay-percent="32%"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">数据概览</div>
                <div class="layui-card-body">
                    <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade" lay-filter="LAY-index-heapcol">
                        <div carousel-item id="LAY-index-heapcol">
                            <div><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">效果报告</div>
                <div class="layui-card-body layadmin-takerates">
                    <div class="layui-progress" lay-showPercent="yes">
                        <h3>转化率（日同比 28% <span class="layui-edge layui-edge-top" lay-tips="增长" lay-offset="-15"></span>）
                        </h3>
                        <div class="layui-progress-bar" lay-percent="65%"></div>
                    </div>
                    <div class="layui-progress" lay-showPercent="yes">
                        <h3>签到率（日同比 11% <span class="layui-edge layui-edge-bottom" lay-tips="下降"
                                              lay-offset="-15"></span>）</h3>
                        <div class="layui-progress-bar" lay-percent="32%"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/common/layuiadmin/layui/layui.js?t=1"></script>
<script>
    layui.config({
        base: '/static/common/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'console', 'senior']);
</script>
</body>
</html>

