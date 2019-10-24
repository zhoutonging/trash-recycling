<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>添加柜机位置</title>
    <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript"
            src="https://webapi.amap.com/maps?v=1.4.15&key=4b3b80e840bdf1624e7498dd0db6736f&plugin=AMap.Autocomplete,AMap.PlaceSearch">
    </script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>

    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="/static/common/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/common/layuiadmin/style/admin.css" media="all">
</head>
<style>
    #myPageTop {
        position: absolute;
        top: 5px;
        right: 10px;
        font-size: 14px;
        background: none 0px 0px repeat scroll rgb(255, 255, 255);
        border-width: 1px;
        border-style: solid;
        border-color: rgb(204, 204, 204);
        border-image: initial;
        margin: 10px auto;
        padding: 6px;
    }

    input {
        border: 1px solid #ccc;
        padding: 7px 0px;
        border-radius: 3px;
        /*css3属性IE不支持*/
        padding-left: 5px;
    }

    button {
        width: 170px;
        height: 32px;
        background-color: #009688;
        border: 1px solid #ccc;
        border-radius: 3px;
        color: #fff;
        cursor: pointer;
        position: relative;
        /** 相对布局 **/
    }

    button:active {
        top: 2px;
        /**向下偏移2px **/
    }
</style>

<body>
<div id="container"></div>
<div id="myPageTop" style="border-radius: 3px;">

    <label>请输入关键字：</label>
    <input id="tipinput" placeholder="输入关键字进行搜索"/><br/>
    <label style="margin-top: 10px;">请输入柜机名：</label>
    <input id="cabinetName" style="margin-top: 10px;"/><br/>
    <label style="margin-top: 10px;">已选位置纬度：</label>
    <input id="lat" disabled="disabled" style="margin-top: 10px;"/><br/>
    <label style="margin-top: 10px;">已选位置经度：</label>
    <input id="lon" disabled="disabled" style="margin-top: 10px;"/><br/>
    <div style="text-align:center;margin-top: 10px;">
        <button id="buttonLocation">点击提交</button>
    </div>
</div>

<script src="/static/common/layuiadmin/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base: '/static/common/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'form', 'laydate', 'upload'], function () {
        var $ = layui.$, layer = layui.layer;
    });

    var id = location.search.substr(location.search.lastIndexOf("=") + 1);

    var str1;
    var str2;

    $.ajax({
        type: "get",
        url: "cabinetlocation/findById",
        async: false,//[true异步  false同步]
        data: {
            id: id
        },
        dataType: "json",
        success: function (res) {
            if (res.code == 0) {
                $("#lat").val(res.data.lat);
                $("#lon").val(res.data.lon);
                $('#cabinetName').val(res.data.cabinetName);

                str1 = res.data.lon;
                str2 = res.data.lat;
            } else {
                layer.msg(res.msg, {time: 2000, icon: 2});
            }
        },
        error: function (e) {
            msg = e;
        }
    });

    //地图加载
    var map = new AMap.Map("container", {
        resizeEnable: true,
        zoom: 16,
    });
    //输入提示
    var autoOptions = {
        input: "tipinput"
    };

    map.setCenter([str1, str2]); //设置地图中心点

    var auto = new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({
        map: map
    }); //构造地点查询类
    AMap.event.addListener(auto, "select", select); //注册监听，当选中某条记录时会触发
    function select(e) {
        placeSearch.setCity(e.poi.adcode);
        placeSearch.search(e.poi.name); //关键字查询查询
    }

    var clickEventListener = map.on('click', function (e) {
        var lat = $("#lat").val(e.lnglat.getLat());//纬度
        var lon = $("#lon").val(e.lnglat.getLng());//经度
    });

    var index = parent.layer.getFrameIndex(window.name);

    $("#buttonLocation").click(function () {
        var cabinetName = $('#cabinetName').val();
        var lat = $("#lat").val();//ji
        var lon = $("#lon").val();

        if (cabinetName == '' || cabinetName == undefined) {
            layer.msg('柜机名称不能为空', {time: 2000, icon: 2});
            return;
        }

        if (lon == '' || lon == undefined) {
            layer.msg('请选择经纬度', {time: 2000, icon: 2});
            return;
        }

        $.post('cabinetlocation/modifyById', {
            id:id,
            lat: lat,
            lon: lon,
            cabinetName: cabinetName
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
</script>
</body>


</html>