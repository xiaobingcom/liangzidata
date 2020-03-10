package com.sandu.common.util;

import java.text.SimpleDateFormat;

import com.sandu.common.exception.BaseParamException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 文件上传工具类
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-02-29 10:34
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-02-29 10:34     xiaobing           v1.0.0              Created
 */
public class UploadFileUtil {

    /**
     * 上传文件
     *
     * @param file     上传的文件信息
     * @param filePath 文件保存路径
     * @return
     */
    public static String uploadFile(MultipartFile file, String filePath) {

        if (file.isEmpty()) {
            throw new BaseParamException("文件信息为空");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String fileName = filePath + sdf.format(new Date()) + "-" + UUIDUtils.getUUID() + "-" + file.getOriginalFilename();
        File dest = new File(fileName);

        try {
            file.transferTo(dest);
            return fileName;
        } catch (IOException e) {
            throw new BaseParamException("文件上传失败");
        }
    }
}
