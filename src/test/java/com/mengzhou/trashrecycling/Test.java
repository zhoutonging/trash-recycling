package com.mengzhou.trashrecycling;

/**
 * 经纬度计算工具类
 *
 * @author ZHOUTONG
 * @date 2019年09月15日 19:18
 */
public class Test {
    private static final double EARTH_RADIUS = 6371000;//赤道半径(单位m)

    /**
     * 转化为弧度(rad)
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     *
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位m
     */
    public static double GetDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void main(String[] args) {
        double lon1 = 117.185306;
        double lat1 = 34.199154;


        double lon2 = 119.015377;
        double lat2 = 33.583017;
        double dist;
        String geocode;

        dist = Test.GetDistance(lon1, lat1, lon2, lat2);
        System.out.println("两点相距：" + dist + " 米");



    }

}

