package com.sandu.common.handler;

import com.sandu.common.response.ResultCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.common.util.CommonRequestHolder;
import com.sandu.common.util.json.GsonUtil;
import com.sandu.common.util.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登陆用户基础信息拦截器
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
@Component
public class LoginUserInformationInterceptor implements HandlerInterceptor {


    @Autowired
    private JedisPool jedisPool;

    /**
     * 测试 TOKEN eyJhbGciOiJIUzUxMiJ9.eyJ1bml0SWQiOiIyIiwiZXhwIjoxNTY5NDY0NTgxLCJ1c2VySWQiOiIxIn0.Z2H1daBLXCn9JstzbT_ZHhwrXO6ujdi9wl5Omu-Eg3YezP3STLWMuX2f6_9F9EHYwXnXyNYG96LrwWnThEcP6Q
     * request 前置拦截
     * 用于拦截 Request Header 中的用户基础信息
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        /*
        swagger不需要进行拦截
         */
        String uri = request.getRequestURI();
        String requestMethod = request.getMethod();
        if ((!StringUtils.isEmpty(requestMethod)) && requestMethod.equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        if (uri.contains("swagger")) {
            return Boolean.TRUE;
        }
        if (uri.contains("User/login")){
            return true;
        }
        if (uri.contains("User/set")){
            return true;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {  //若获取不到 TOKEN 的存在
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            ReturnValueLoader rvl = new ReturnValueLoader(ResultCode.NO_AUTH_CODE);
            out.write(GsonUtil.getJson(rvl));
            out.flush();
            out.close();
            return Boolean.FALSE;
        } else {    //若存在 TOKEN

            //解析 TOKEN
            JWTUtil.JWTResult jwtResult = JWTUtil.getInstance().checkToken(header.replace("Bearer ", ""), "meaqua");


            if (jwtResult.isStatus()) {    //若 TOKEN 合法

                /*
                从 TOKEN 中得到解密出来的 userId 和 unitId
                 */
                int userId = Integer.parseInt(jwtResult.getUserId());
                String userName = jwtResult.getUserName();

                //同 JedisPool 中得到一个链接
                Jedis jedis = this.jedisPool.getResource();

                //根据 userId 从 Redis 中得到用户信息字符串
                String userStr = jedis.hget("user", jwtResult.getUserId());

                //新版本中直接执行关闭操作，若有连接池则会缓存回连接池，若不存在连接池则直接执行关闭操作
                jedis.close();

                //将用户信息 JSON 串转换为对象
                RedisUser redisUser = (RedisUser) GsonUtil.getObject(userStr, RedisUser.class);

                //初始化当前线程登陆用户状态
                //CommonRequestHolder.init((long)userId, redisUser.getRealName(), Long.parseLong(redisUser.getOrganizationId()), redisUser.getOrganizationName());

                CommonRequestHolder.init((int)userId,userName);



                return Boolean.TRUE;
            } else {    //若 TOKEN 不合法
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                ReturnValueLoader rvl = new ReturnValueLoader(ResultCode.NO_AUTH_CODE);
                out.write(GsonUtil.getJson(rvl));
                out.flush();
                out.close();
                return Boolean.FALSE;
            }
        }

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //当前请求结束需要销毁线程中存储的内容，否则线程池的作用会导致这些缓存的数据无法被虚拟机销毁
        CommonRequestHolder.close();
    }
}
