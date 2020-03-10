package com.sandu.common.exception;

/**
 * Redis 初始化异常
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
public class RedisInitException  extends GlobalException {


    public RedisInitException(String message, Throwable cause) {
        super(message,cause);
    }

}
