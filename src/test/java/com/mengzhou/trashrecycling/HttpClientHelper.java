package com.mengzhou.trashrecycling;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟手环请求测试
 */
public class HttpClientHelper {

    @Value("${wristband.cpid}")
    private String cpid;

    @Value("${wristband.key}")
    private String key;

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
        putMethod.addRequestHeader("key", "88d62ecd7842a240f1149e828773d67f");
        putMethod.addRequestHeader("cpid", "665");

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("imei", "350183478792234");
        modelMap.put("type", "amr");
        modelMap.put("audio", "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");
        RequestEntity se = new StringRequestEntity(JSONObject.toJSON(modelMap).toString(), "application/json", "UTF-8");

        putMethod.setRequestEntity(se);

        httpClient.executeMethod(putMethod);
        String result = putMethod.getResponseBodyAsString();
        putMethod.releaseConnection();
        return result;
    }

    public static void main(String[] args) throws HttpException, IOException {
        String url = "http://api.jiai.pro:8080/jiai/audio";
        String res = sendPut(url);
        JSONObject jsonObject = JSONObject.parseObject(res);
        String data = jsonObject.get("message").toString();

        System.out.println(jsonObject);
    }
}
