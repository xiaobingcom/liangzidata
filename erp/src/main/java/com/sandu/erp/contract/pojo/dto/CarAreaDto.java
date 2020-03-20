package com.sandu.erp.contract.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 汽车区域 实体类
 * Module: CarArea.java
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
public class CarAreaDto  {

    /**
     主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键")
    private Integer id;


    @ApiModelProperty(value = "名字")
    @TableField(value = "name",strategy = FieldStrategy.NOT_EMPTY)
    private String name;
    /**
     车辆型号ID
     */
    @TableField(value = "car_sort_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "车辆型号ID")
    private Integer carSortId;

    /**
     价格
     */
    @TableField(value = "price",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    /**
     图标
     */
    @TableField(value = "pecture",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "图标")
    private String pecture;

}