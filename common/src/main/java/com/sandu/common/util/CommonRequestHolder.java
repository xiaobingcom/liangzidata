package com.sandu.common.util;

import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;

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
    public static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    /**
     * 记录当前线程操作用户名称
     */
    public static final ThreadLocal<String> currentUserName = new ThreadLocal<>();

    /**
     * 记录当前线程操作用户所属单位主键 ID
     */
    public static final ThreadLocal<Long> currentUnitId = new ThreadLocal<>();

    /**
     * 记录当前线程操作用户所属单位名称
     */
    public static final ThreadLocal<String> currentUnitName = new ThreadLocal<>();


    /**
     * 记录当前线程的统一时间错
     */
    public static final ThreadLocal<LocalDateTime> currentTimestamp = new ThreadLocal<>();


    /**
     * 初始化数据，userName和unitName 均为 NULL
     * @param userId
     * @param unitId
     */
    public static void init(Long userId, Long unitId) {
        currentUserId.set(userId);
        currentUnitId.set(unitId);
        currentTimestamp.set(LocalDateTime.now());

        isInit.set(true);
    }

    /**
     * 初始化数据
     * @param userId
     * @param userName
     * @param unitId
     * @param unitName
     */
    public static void init(Long userId, String userName, Long unitId, String unitName) {

        currentUserName.set(userName);
        currentUnitName.set(unitName);

        init(userId, unitId);

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
    public static Long getCurrentUserId() {
        Long userId = currentUserId.get();

        isInit();

        return userId;
    }


    /**
     * 获取当前单位 ID
     * @return
     */
    public static Long getCurrentUnitId() {
        Long unitId = currentUnitId.get();

        isInit();

        return unitId;
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
     * 获取当前单位名称
     * @return
     */
    public static String getCurrentUnitName() {

        String unitName = currentUnitName.get();

        isInit();

        return unitName;
    }
    /**
     * 关闭
     */
    public static void close() {
        isInit.remove();
        currentUserName.remove();
        currentUnitId.remove();
        currentUserId.remove();
        currentUnitName.remove();
        currentTimestamp.remove();
    }


}
