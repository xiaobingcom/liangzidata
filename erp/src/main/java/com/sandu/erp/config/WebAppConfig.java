package com.sandu.erp.config;

import com.sandu.common.handler.LoginUserInformationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;

/**
 * Mvc 配置
 * @author xiaobing
 * @date 2020-02-28 14:44
 * @version v1.0.0
 * @Description
 *
 * Modification History:
 * Date                 Author          Version          Description
---------------------------------------------------------------------------------*
 * 2020-02-28 14:44     xiaobing          v1.0.0           Created
 *
 */



@Configuration
@EnableSwagger2
public class WebAppConfig extends WebMvcConfigurationSupport {



    @Autowired
    private LoginUserInformationInterceptor loginUserInformationInterceptor;

   @Value("${template.save.path}")
    private String path;

    /**
     * 设置Swagger2扫描哪个包下面的API
     * 并且设置每个接口必须带有的参数
     * @return
     */
    @Bean
    public Docket customDocket(){

//        List<Parameter> pars = new ArrayList<Parameter>();
//        ParameterBuilder userId = new ParameterBuilder();
//        userId.name("userId").description("当前登陆用户ID")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(true).build(); //header中的ticket参数非必填，传空也可以
//        pars.add(userId.build());    //根据每个方法名也知道当前方法在设置什么参数
//        ParameterBuilder unitId = new ParameterBuilder();
//        unitId.name("unitId").description("当前登陆企业ID")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(true).build(); //header中的ticket参数非必填，传空也可以
//        pars.add(unitId.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径

                .apis(RequestHandlerSelectors.basePackage("com.sandu"))
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(pars)
                ;
    }


    /**
     * 设置Swagger2文档页面的一些基础信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("OMS API")
                //创建人
                .contact(new Contact("zhouqi", "http://jmcloud.kzdata.com", "17784574@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("订单管理系统")
                .build();
    }


    /**
     * 当开启全局异常处理之后，需要关闭工程中的资源文件映射
     * 此时Swagger2所需要的静态资源文件无法打开
     * 手动为这些静态文件开启映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");



        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:/opt/phone/");

        super.addResourceHandlers(registry);

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


        super.addResourceHandlers(registry);
    }




    /**
     * 配置允许跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(3600)
                .allowCredentials(true);
    }


    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        //registry.addInterceptor(this.loginUserInformationInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register");
        registry.addInterceptor(this.loginUserInformationInterceptor).addPathPatterns("/**");
    }


}
