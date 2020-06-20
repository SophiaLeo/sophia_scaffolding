package com.scaffolding.sophia.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author LHL
 */
public class HttpCallOtherInterfaceUtil {

    public static String callOtherPostInterface(JSONObject jsonParam, String gatewayUrl, String postUrl) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = gatewayUrl + postUrl;
        HttpPost post = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            StringEntity s = new StringEntity(jsonParam.toString(), "UTF-8");
            //s.setContentEncoding("UTF-8");//此处测试发现不能单独设置字符串实体的编码，否则出错！应该在创建对象时创建
            s.setContentType("application/json");
            post.setEntity(s);
            post.addHeader("content-type", "application/json;charset=UTF-8");
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            System.out.println("服务间接口调用出错！");
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return null == jsonObject?"":jsonObject.toString();
    }
    public static String callOtherInterface(String gatewayUrl, String getUrl) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = gatewayUrl + getUrl;
        HttpPost get = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            System.out.println("服务间接口调用出错！");
            e.printStackTrace();
        }
        return null == jsonObject?"":jsonObject.toString();
    }
    public static String callOtherGetInterface(String gatewayUrl, String getUrl) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = gatewayUrl + getUrl;
        HttpGet get = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            System.out.println("服务间接口调用出错！");
            e.printStackTrace();
        }
        return null == jsonObject?"":jsonObject.toString();
    }
}
