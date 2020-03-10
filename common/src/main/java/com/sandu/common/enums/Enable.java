package com.sandu.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 类型 0 禁用 1 启用
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-02-18 10:30
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-02-18 10:30     xiaobing          v1.0.0           Created
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Enable {

    /**
     * 禁用
     */
    DISABLED(0, "禁用"),

    /**
     * 启用
     */
    ENABLE(1, "启用");

    /**
     * 状态码
     */
    @EnumValue
    private final int code;

    /**
     * 内容
     */
    private final String msg;


    /**
     * @param code 状态码
     * @param msg 内容
     */
    Enable(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }


    public static Enable getByCode(int code) {

        Enable[] values = Enable.values();

        for (Enable value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }

        return null;
    }
}
