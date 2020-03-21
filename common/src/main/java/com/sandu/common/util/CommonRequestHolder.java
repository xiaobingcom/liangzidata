package com.sandu.common.util;

import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;
import io.swagger.models.auth.In;

import java.time.LocalDateTime;

/**
 * 通用参数
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-02-29 10:34
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-02-29 10:34     xiaobing          v1.0.0           Created
 */
public class CommonRequestHolder {


    /**
     * 当前线程是否进行过初始化
     */
    private static final ThreadLocal<Boolean> isInit = new ThreadLocal<>();

    /**
     * 记录当前线程操作用户主键 ID
     */
    public static final ThreadLocal<Integer> currentUserId = new ThreadLocal<>();

    /**
     * 记录当前线程操作用户名称
     */
    public static final ThreadLocal<String> currentUserName = new ThreadLocal<>();
    /**
     * 记录当前线程的统一时间错
     */
    public static final ThreadLocal<LocalDateTime> currentTimestamp = new ThreadLocal<>();


    /**
     * 初始化数据，userName和unitName 均为 NULL
     * @param userId
     * @param userName
     */
    public static void init(Integer userId, String userName) {
        currentUserId.set(userId);
        currentUserName.set(userName);
        currentTimestamp.set(LocalDateTime.now());

        isInit.set(true);
    }


    public static void isInit() {
        Boolean bool =isInit.get();

        if (bool == null || !bool) {
            throw new BaseParamException("当前线程基础数据未进行初始化", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }
    }

    /**
     * 获取当前用户ID
     * @return
     */
    public static Integer getCurrentUserId() {
        Integer userId = currentUserId.get();

        isInit();

        return userId;
    }


    /**
     * 获取当前统一时间戳
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {

        LocalDateTime now = currentTimestamp.get();

        isInit();

        return now;
    }

    /**
     * 获取当前登陆用户名称
     * @return
     */
    public static String getCurrentUserName() {

        String userName = currentUserName.get();

        isInit();

        return userName;
    }
   /**
     * 关闭
     */
    public static void close() {
        isInit.remove();
        currentUserName.remove();
        currentUserId.remove();
        currentTimestamp.remove();
    }


}
