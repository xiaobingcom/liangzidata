package com.sandu.erp.contract.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 *  实体类
 * Module: CarSeries.java
 *
 * @author xiaobing
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-19
 * @Descriptions:
*/
@Getter
@Setter
@ToString
@TableName(value = "tb_car_series")
public class CarSeriesDto {


    /**
     主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**

     */
    @TableField(value = "brand_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "品牌ID")
    private Integer brandId;

    /**
     名称
     */
    @TableField(value = "name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     全名
     */
    @TableField(value = "full_name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "全名")
    private String fullName;

    /**
     首字母
     */
    @TableField(value = "firstletter",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "首字母")
    private String firstletter;



}