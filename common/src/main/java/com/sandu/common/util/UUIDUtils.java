package com.sandu.common.util;

import java.util.UUID;

/**
 * UUID生成
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-02-29 10:34
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-02-29 10:34     xiaobing       v1.0.0              Created
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}

