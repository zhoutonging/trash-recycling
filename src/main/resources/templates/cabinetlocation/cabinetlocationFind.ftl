<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>查看柜机位置</title>
    <script src="/static/common/jquery/jquery.js"></script>
    <link rel="stylesheet" href="/static/common/gaode/demo-center.css"/>

    <style>
        html, body, #container {
            height: 100%;
            width: 100%;
        }

        .amap-marker:first-child .amap-icon img {
            width: 25px;
            height: 34px;
        }
    </style>
</head>
<body>
<div id="container"></div>
<script src="/static/common/gaode/maps.js"></script>

<script type="text/javascript">
    var str1 ;
    var str2 ;
    var id = location.search.substr(location.search.lastIndexOf("=") + 1);

    $.ajax({
        type: "get",
        url: "cabinetlocation/findById",
        async: false,//设置同步/异步的参数[true异步  false同步]
        data: {id: id},
        dataType: "json",
        success: function (res) {
            if (res.code == 0) {
                str1 = res.data.lon;
                str2 = res.data.lat;
                console.log(str1)
                console.log(str2)
            } else {
                layer.msg(res.msg, {time: 2000, icon: 2});
            }
        },
        error: function (e) {
            msg = e;
        }
    });


    var map = new AMap.Map('container', {
        resizeEnable: true,
        center: [str1, str2],
        zoom: 16
    });

    var marker = new AMap.Marker({
        position: map.getCenter(),
        icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png',
        offset: new AMap.Pixel(-13, -30),
        // 设置是否可拖拽
        draggable: false,
        cursor: 'move'
    });

    marker.setMap(map);

    // 设置点标记的动画效果，此处为弹跳效果
    marker.setAnimation('AMAP_ANIMATION_BOUNCE');
</script>
</body>
</html>
</html>