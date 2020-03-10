package com.sandu.common.exception;


/**
 * 基础参数异常
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
public class BaseParamException extends GlobalException {


    public BaseParamException(String message) {
        super(message);
    }

    public BaseParamException(String message, GlobalExceptionCode code) {
        super(message, code);
    }


}
