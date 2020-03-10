package com.sandu.common.util;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * HTTP请求工具类
 * @author xiaobing
 * @date 2020-02-29 10:34
 * @version v1.0.0
 * @Description
 *
 * Modification History:
 * Date                 Author          Version          Description
---------------------------------------------------------------------------------*
 * 2020-02-29 10:34     xiaobing          v1.0.0           Created
 *
 */
public class HttpUtil {


    public static String get(String url,String token) {


        String result = null;

        HttpClient httpClient = new HttpClient();
        HttpMethod method = new GetMethod(url);

        method.setRequestHeader("Content-type","text/xml; charset=utf-8");
        method.setRequestHeader("Authorization",token);

        try {
            httpClient.executeMethod(method);
            result = method.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;


    }


    public static String post(String url, String postStr,String token) {

        String resultStr = null;

        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(url);

        method.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        method.setRequestHeader("Authorization",token);
        method.setRequestBody(postStr);

        try {
            int result = httpClient.executeMethod(method);
            resultStr = method.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultStr;

    }



}
