package com.sandu.common.util.http;

import com.google.common.io.CharStreams;
import com.sandu.common.exception.GlobalException;
import com.sandu.common.exception.ServiceTransferStatusCodeException;
import com.sandu.common.util.CommonRequestHolder;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/**
 * HTTP 请求工具类
 * @author xiaobing
 * @date 2020-02-29 10:21
 * @version v1.0.0
 * @Description
 *
 * Modification History:
 * Date                 Author          Version          Description
---------------------------------------------------------------------------------*
 * 2020-02-29 10:21     xiaobing          v1.0.0           Created
 *
 */
public enum HttpUtil {

    INSTANCE;

    /**
     * GET 请求
     * @param url
     * @param serializeFunction
     * @param <T>
     * @return
     */
    public <T> T doGet(String url, Function<String, T> serializeFunction) {

        InputStreamReader isr = null;
        InputStreamReader esr = null;
        int statusCode;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");

            conn.setConnectTimeout(1000);
            conn.setReadTimeout(5000);

            conn.connect();

            statusCode = conn.getResponseCode();
            String response;

            try {
                isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                response = CharStreams.toString(isr);
            } catch (IOException ex) {
                /**
                 * 我们应该通过读取响应主体来清理连接，以便可以重用连接。
                 */
                InputStream errorStream = conn.getErrorStream();

                if (errorStream != null) {
                    esr = new InputStreamReader(errorStream, StandardCharsets.UTF_8);
                    try {
                        CharStreams.toString(esr);
                    } catch (IOException ioe) {
                        //忽略这个异常
                    }
                }

                // 200 和 304 不应该除法 IOException，应该直接将异常抛出
                if (statusCode == 200 || statusCode == 304) {
                    throw ex;
                } else {
                    // 像 404 这样的异常, 调用 conn.getInputStream() 时需要 IOException
                    throw new ServiceTransferStatusCodeException(statusCode, ex);
                }
            }

            if (statusCode == 200) {
                return serializeFunction.apply(response);
            }

            if (statusCode == 304) {
                return null;
            }
        } catch (ServiceTransferStatusCodeException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new GlobalException("不能完成请求", ex);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ex) {
                    // ignore
                }
            }

            if (esr != null) {
                try {
                    esr.close();
                } catch (IOException ex) {
                    // 忽略这个异常
                }
            }
        }

        throw new ServiceTransferStatusCodeException(statusCode, String.format("请求操作失败 %s", url));
    }

    /**
     * POST 请求
     * @param url
     * @param json 请求参数
//     * @param serializeFunction
//     * @param <T>
     * @return
     */
    public String doPost(String url, String json,String requestMethod /*, Function<String, T> serializeFunction*/) {


        InputStreamReader isr = null;
        InputStreamReader esr = null;
        StringBuffer sb = new StringBuffer();
        int statusCode;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod(requestMethod);

            conn.setConnectTimeout(1000);
            conn.setReadTimeout(5000);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("userId",""+ CommonRequestHolder.getCurrentUserId());//设置请求头
            conn.setRequestProperty("userName", ""+CommonRequestHolder.getCurrentUserName());//设置请求头

            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept", "application/json");




            if (!StringUtils.isEmpty(json)) {
                byte[] writebytes = json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(writebytes);
                outwritestream.flush();
                outwritestream.close();

            }

            statusCode = conn.getResponseCode();

            String response;
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
//                isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                String str = null;
                while ((str = in.readLine()) != null) {
                    sb.append(str);
                }
//                response = CharStreams.toString(isr);
            } catch (IOException ex) {
                /**
                 * 我们应该通过读取响应主体来清理连接，以便可以重用连接。
                 */
                InputStream errorStream = conn.getErrorStream();
                conn.connect();

                if (errorStream != null) {
                    esr = new InputStreamReader(errorStream, StandardCharsets.UTF_8);
                    try {
                        CharStreams.toString(esr);
                    } catch (IOException ioe) {
                        //忽略这个异常
                    }
                }

                // 200 和 304 不应该除法 IOException，应该直接将异常抛出
                if (statusCode == 200 || statusCode == 304) {
                    throw ex;
                } else {
                    // 像 404 这样的异常, 调用 conn.getInputStream() 时需要 IOException
                    throw new ServiceTransferStatusCodeException(statusCode, ex);
                }
            }

            if (statusCode == 200) {
//                return serializeFunction.apply(sb);
                return sb.toString();
            }

            if (statusCode == 304) {
                return null;
            }
        } catch (ServiceTransferStatusCodeException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new GlobalException("不能完成请求", ex);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ex) {
                    // ignore
                }
            }

            if (esr != null) {
                try {
                    esr.close();
                } catch (IOException ex) {
                    // 忽略这个异常
                }
            }

        }

        throw new ServiceTransferStatusCodeException(statusCode, String.format("请求操作失败 %s", url));

    }




}
