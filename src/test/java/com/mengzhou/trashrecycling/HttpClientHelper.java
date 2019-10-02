package com.mengzhou.trashrecycling;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟手环请求测试
 */
public class HttpClientHelper {
    /**
     * POST请求
     *
     * @param urlParam
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String sendPost(String urlParam) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(urlParam);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        httpClient.executeMethod(postMethod);

        String result = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        return result;
    }

    /**
     * GET请求
     *
     * @param urlParam
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String sendGet(String urlParam) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(urlParam);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.addRequestHeader("Content-Type", "application/json");

        httpClient.executeMethod(getMethod);

        String result = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return result;
    }

    /**
     * PUT请求
     *
     * @param urlParam
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String sendPut(String urlParam) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建Put请求方法实例对象
        PutMethod putMethod = new PutMethod(urlParam);

        // 设置Put请求超时时间
        putMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        putMethod.addRequestHeader("Content-Type", "application/json");
        putMethod.addRequestHeader("imei", "350183478792234");
        putMethod.addRequestHeader("key", "961e5645134f8ce4a1d61ec04bf16d5b");
        putMethod.addRequestHeader("cpid", "647");

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("imei", "350183478792234");
        RequestEntity se = new StringRequestEntity(JSONObject.toJSON(modelMap).toString(), "application/json", "UTF-8");

        putMethod.setRequestEntity(se);

        httpClient.executeMethod(putMethod);
        String result = putMethod.getResponseBodyAsString();
        putMethod.releaseConnection();
        return result;
    }

    public static void main(String[] args) throws HttpException, IOException {
        String url = "http://api.jiai.pro:8080/jiai/location";
        String res = sendPut(url);
        JSONObject jsonObject = JSONObject.parseObject(res);
        String data = jsonObject.get("message").toString();

        System.out.println(jsonObject);
    }
}
