package com.sandu.erp.contract.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 *  汽车型号实体类
 * Module: CarSort.java
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
public class CarSortDto  {

    @ApiModelProperty(value = "修改时填写ID，新增时不填写")
    private Integer id;

    /**
    汽车分类名字
    */
    @TableField(value = "car_sort_name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车分类名字")
    private String carSortName;

    /**
    汽车年款ID
    */
    @TableField(value = "car_year_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车年款ID")
    private Integer carYearId;

}