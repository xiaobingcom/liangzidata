package com.sandu.erp.contract.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * 用户表 实体类
 * Module: User.java
 *
 * @author xiaobing
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
*/
@Getter
@Setter
@ToString
public class UserDto  {

    @ApiModelProperty(value = "修改时填写ID，新增时不填写")
    private Integer id;

    /**
    登录账号
    */
    @TableField(value = "login_name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "登录账号")
    private String loginName;

    /**
    密码
    */
    @TableField(value = "pass_word",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "密码")
    private String passWord;


}