package com.sandu.erp.contract.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


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


    @ApiModelProperty(value = "修改时填写ID，新增时不填写")
    private Integer id;



    @ApiModelProperty(value = "名字")
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