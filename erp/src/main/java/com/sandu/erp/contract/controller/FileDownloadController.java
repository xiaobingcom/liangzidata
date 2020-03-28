//package com.sandu.erp.contract.controller;
//
//import ch.qos.logback.core.util.FileUtil;
//import com.sandu.common.exception.BaseParamException;
//import com.sandu.common.response.ReturnValueLoader;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//
///**
// * @description: 下载导入模板文件
// * @author: liuda
// * @create: 2019-12-25 14:05
// */
//@RestController
//@Slf4j
//@RequestMapping("/import/download/file")
//public class FileDownloadController {
//
//    @Value("${template.save.path}")
//    private String savePath;
//
//    @GetMapping("/template")
//    public void download(HttpServletResponse response,
//                         @RequestParam("fileName") String fileName) throws Exception {
//
//        log.info("下载文件：{}", fileName);
//
//        // 文件地址
//        String filePath = savePath + fileName;
//        File file = new File(filePath);
//        FileInputStream fis = new FileInputStream(file);
//        resourceLoader.getResource("file:" + path + fileName)
//
//        // 设置相关格式
//        response.setContentType("application/image/jpeg");
//        // 设置下载后的文件名以及header
//        response.setHeader("Content-Disposition", "attachment; fileName=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, "UTF-8"));
//        // 创建输出对象
//        OutputStream os = response.getOutputStream();
//
//        byte[] buf = new byte[1024];
//        int len = 0;
//        while ((len = fis.read(buf)) != -1) {
//            os.write(buf, 0, len);
//        }
//
//        fis.close();
//    }
//
//
//
//
//    private Boolean haveCreatePath = false;
//    @PostMapping("filesUpload")
//    @ResponseBody
//    public Object filesUpload(@RequestParam("file") MultipartFile[] file) {
//        // 接收处理后的文件名
//        String fileName ="";
//
//        //判断file数组不能为空并且长度大于0
//        if(file!=null&&file.length>0){
//            //循环获取file数组中得文件
//            for(int i = 0;i<file.length;i++){
//                MultipartFile multipartFile = file[i];
//
//                //判断文件是否为空
//                if (!multipartFile.isEmpty()) {
//                    try {
//                        //上传文件的源文件名
//                        fileName = multipartFile.getOriginalFilename();
//
//
//                        // 存储在当前web服务路径下的upload路径
//                        String destPath = savePath;
//
//                        //判断该路径是否存在,如果不存在则创建该路径
//                        if (!haveCreatePath){
//                            File  destFile = new File(destPath);
//                            destFile.mkdirs();
//                            haveCreatePath = true;
//                        }
//                        // 文件最终保存的路径
//                        String filePath = destPath + fileName;
//                        //文件保存路径
//                        //String filePath = govProperties.getFileUploadPath()+file.getOriginalFilename();
//                        //转存文件
//                        multipartFile.transferTo(new File(filePath));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        throw new BaseParamException("文件上传失败");
//                    }
//                }
//            }
//        }
//        //返回前端data数据中存储修改后的文件名
//        return new ReturnValueLoader(fileName);
//    }
//
//}
