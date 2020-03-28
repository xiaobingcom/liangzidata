package com.sandu.erp.contract.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * 汽车品牌表 实体类
 * Module: CarBrand.java
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
@TableName(value = "tb_car_brand")
public class CarBrand  {


    /**
    汽车品牌名字
    */
    @TableField(value = "car_name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车品牌名字")
    private String carName;

    /**
    主键ID
    */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 首字母
     */
    @TableField(value = "firstletter",strategy = FieldStrategy.NOT_EMPTY)
    private String firstletter;

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
    @TableField(value = "create_date",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    /**
    图标
    */
    @TableField(value = "picture",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "图标")
    private String picture;


    /**
     图标
     */
    @TableField(value = "image",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "图片")
    private String image;


}