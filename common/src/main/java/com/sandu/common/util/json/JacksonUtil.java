package com.sandu.common.util.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Jackson工具类
 * @author xiaobing
 * @date 2020-02-29 18:23
 * @version v1.0.0
 * @Description 本类方法使用需要严谨的异常处理
 * 注：实际转换对象中存在null属性时，转换出来的JSON字符串会保留该属性，且值为null，可使用@JsonInclude(JsonInclude.Include.NON_EMPTY)注解对类的null属性进行控制，包括空字符串
 *
 * Modification History:
 * Date                 Author          Version          Description
---------------------------------------------------------------------------------*
 * 2020-02-29 18:23     xiaobing          v1.0.0           Created
 *
 */
public class JacksonUtil {


    /**
     * 缺省时间格式，开发人员可根据项目需要更改此常量
     */
    private static final String PATTERN = "yyyy-MM-dd";


    /**
     * 获取ObjectMapper
     * @param farmat    是否格式化时间
     * @param pattern   当farmat参数为true时此参数有效，为null或""时使用缺省时间格式，实际调用时应当注意时间格式的有效性
     * @return
     */
    private static ObjectMapper getMapper(boolean farmat,String pattern) {

        ObjectMapper mapper = new ObjectMapper();

        if (farmat) {
            SimpleDateFormat sdf = null;

            if (StringUtils.isEmpty(pattern)) {
                sdf = new SimpleDateFormat(JacksonUtil.PATTERN);
            } else {
                sdf = new SimpleDateFormat(pattern);
            }

            mapper.setDateFormat(sdf);
        }

        return mapper;


    }

    /**
     * 将一个对象转换成JSON字符串，当对象中不存在或不要求转换时间类型的属性时可以使用此方法
     * @param obj   要转换的对象
     * @return      JSON字符串，当obj中存在时间格式时，时间格式会被转换为时间戳类型而不是一个格式化时间
     * @throws JsonProcessingException
     */
    public static String getJson(Object obj) throws JsonProcessingException {

        return JacksonUtil.getMapper(false,null).writeValueAsString(obj);

    }

    /**
     * 将一个对象按照要求的时间格式转换成JSON字符串，当对象中存在时间类型的属性且需要对该属性进行格式化时可使用此方法
     * @param obj       要转换的对象
     * @param pattern   时间类型转换格式，若该参数为null或""时，使用缺省时间格式，实际调用时应当注意时间格式的有效性
     * @return          JSON字符串，当obj中存在时间格式时，时间格式会被转换为时间戳类型而不是一个格式化时间
     * @throws JsonProcessingException
     */
    public static String getJson(Object obj, String pattern) throws JsonProcessingException {

        return JacksonUtil.getMapper(true,pattern).writeValueAsString(obj);

    }


    /**
     * 将一个JSON字符串转换成一个对象,需要注意json字符串中不能有时间格式属性（jackson对时间格式缺省情况下支持的不多），若有时间类型的属性，需要使用getObject(String json, Class c, String pattern)
     * @param json  要转换的JSON字符串
     * @param c     转换类型
     * @return
     * @throws IOException
     */
    public static Object getObject(String json,Class c) throws IOException {

        return JacksonUtil.getMapper(false,null).readValue(json,c);

    }


    /**
     * 将一个JSON字符串转换成一个对象，并按照对应的时间格式对时间类型的属性进行格式化
     * @param json      要转换的JSON字符串
     * @param c         转换类型
     * @param pattern   时间类型转换格式，若该参数为null或""时，使用缺省时间格式，实际调用时应当注意时间格式的有效性
     * @return
     * @throws IOException
     */
    public static Object getObject(String json, Class c, String pattern) throws IOException {

        return JacksonUtil.getMapper(true,pattern).readValue(json,c);

    }


    /**
     * @JsonNaming(SnakeCaseStrategy.class)
     * 指定Json字段名映射策略为蛇形大小写策略。缺省则直接使用Bean属性名
     * 可用的命名映射策略还有：
     * KebabCaseStrategy: 肉串策略 - 单词小写，使用连字符'-'连接
     * SnakeCaseStrategy: 蛇形策略 - 单词小写，使用下划线'_'连接；即老版本中的LowerCaseWithUnderscoresStrategy
     * LowerCaseStrategy: 小写策略 - 简单的把所有字母全部转为小写，不添加连接符
     * UpperCamelCaseStrategy: 驼峰策略 - 单词首字母大写其它小写，不添加连接符；即老版本中的PascalCaseStrategy
     */
    /**
     * @JsonIgnoreProperties({"id", "created", "steps", "copy", "stepList"})
     * 类注解，指定序列化时忽略这些属性，可以用于覆盖超类中默认输出的属性
     */
    /**
     * @JsonIgnoreType
     * 类注解，序列化时忽略此类
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)     //仅在属性不为空时序列化此字段，对于字符串，即null或空字符串,可使用在类或方法上
    static class User{

        @JsonIgnore //序列化时忽略此字段
        private int id;

        @JsonProperty("my_name")    //指定序列化时的字段名，默认使用属性名
        private String name;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date birthday;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", birthday=" + birthday +
                    '}';
        }
    }





}
