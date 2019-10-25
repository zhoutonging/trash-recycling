<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>自定义窗体</title>
    <link rel="stylesheet" href="/static/common/gaode/main1119.css"/>
    <script src="/static/common/jquery/jquery.js"></script>
    <script src="/static/common/gaode/maps.js"></script>
    <script src="/static/common/gaode/addToolbar.js"></script>
</head>
<style>
    .amap-info-content {
        border-radius: 5px;
    }
</style>
<body>
<div id="container"></div>
<script type="text/javascript">

    var lnglats = [];
    var cabinetNames = [];
    var createTimes = [];

    $.ajax({
        type: "get",
        url: "cabinetlocation/findAllView",
        async: false,//设置同步/异步的参数[true异步  false同步]
        dataType: "json",
        success: function (res) {
            console.log(res.data)
            if (res.success == true) {
                //拼接成高德地图数据格式
                for (var i = 0; i < res.data.length; i++) {
                    var lnglat = [];
                    var cabinetName = [];
                    var createTime = [];

                    //一次放入坐标
                    lnglat.push(res.data[i].lon);
                    lnglat.push(res.data[i].lat);
                    lnglats.push(lnglat);

                    //放入站点名称
                    cabinetName.push(res.data[i].cabinetName);
                    cabinetNames.push(cabinetName)

                    //放入创建时间
                    createTime.push(res.data[i].createTime);
                    createTimes.push(createTime)
                }

            } else {
                layer.msg(res.msg, {time: 2000, icon: 2});
            }
        },
        error: function (e) {
            msg = e;
        }
    });

    //初始化地图对象，加载地图
    var map = new AMap.Map("container", {resizeEnable: true});


    var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
    for (var i = 0, marker; i < lnglats.length; i++) {
        var marker = new AMap.Marker({
            position: lnglats[i],
            map: map
        });
        marker.content = '<h3>' + cabinetNames[i] + '</h3>';
        marker.content += '<div>经度：' + lnglats[i][0] + '</div>';
        marker.content += '<div>纬度：' + lnglats[i][1] + '</div>';
        marker.content += '<div>创建于：' + createTimes[i] + '</div>';

        marker.on('mouseover', infoOpen);
        //注释后打开地图时默认关闭信息窗体
        //marker.emit('mouseover', {target: marker});
        marker.on('mouseout', infoClose);
        marker.on('click', newMAp);

    }


    //鼠标点击事件,设置地图中心点及放大显示级别
    function newMAp(e) {
        //map.setCenter(e.target.getPosition());
        map.setZoomAndCenter(16, e.target.getPosition());

        var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
    }


    function infoClose(e) {
        infoWindow.close(map, e.target.getPosition());
    }

    function infoOpen(e) {
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
    }

    map.setFitView();
</script>
</body>
</html>