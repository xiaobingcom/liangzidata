package com.sandu.erp.contract.pojo.po;

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
@TableName(value = "tb_user")
public class User  {


    /**
    主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键")
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

    /**
    修改时间
    */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    /**
    创建时间
    */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "creade_date",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "创建时间")
    private Date creadeDate;

    /**
    状态
    */
    @TableField(value = "status",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "状态")
    private Integer status;

}