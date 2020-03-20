package com.sandu.erp.contract.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * 汽车年款 实体类
 * Module: CarYear.java
 *
 * @author xiaobing
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
 * @since JDK 1.8
 */
@Getter
@Setter
@ToString
public class CarYearDto {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 汽车品牌ID
     */
    @TableField(value = "brand_id", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车品牌ID")
    private Integer brandId;


    /**
     * 汽车品牌ID
     */
    @TableField(value = "series_id", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "车系ID")
    private Integer seriesId;

    /**
     * 汽车年款名字
     */
    @TableField(value = "name", strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车年款名字")
    private String name;


}