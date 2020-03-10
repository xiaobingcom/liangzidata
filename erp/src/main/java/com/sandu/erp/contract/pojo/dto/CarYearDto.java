package com.sandu.erp.contract.pojo.dto;

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
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
*/
@Getter
@Setter
@ToString
public class CarYearDto  {

    @ApiModelProperty(value = "修改时填写ID，新增时不填写")
    private Integer id;
    /**
    汽车品牌ID
    */
    @TableField(value = "brand_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车品牌ID")
    private Integer brandId;

    /**
    汽车年款名字
    */
    @TableField(value = "name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车年款名字")
    private String name;


}