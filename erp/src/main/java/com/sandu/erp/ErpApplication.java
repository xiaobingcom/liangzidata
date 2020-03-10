package com.sandu.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;


/**
 * erp 启动类
 * @author xiaobing
 * @date 2020-02-29 18:00
 * @version v1.0.0
 * @Description
 *
 * Modification History:
 * Date                 Author          Version          Description
---------------------------------------------------------------------------------*
 * 2020-02-29 18:00     xiaobing          v1.0.0           Created
 *
 */
@SpringBootApplication(scanBasePackages = "com.sandu")
@MapperScan(basePackages = "com.sandu.erp",annotationClass = Repository.class)
public class ErpApplication {


    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }


}
