package com.sandu.erp.contract.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


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
@TableName(value = "tb_car_year")
public class CarYear  {


    /**
    主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
    汽车品牌ID
    */
    @TableField(value = "brand_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车品牌ID")
    private Integer brandId;


    /**
     汽车品牌ID
     */
    @TableField(value = "series_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "车系ID")
    private Integer seriesId;

    /**
    汽车年款名字
    */
    @TableField(value = "name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车年款名字")
    private String name;

    /**
     修改时间
    */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = " 修改时间")
    private Date updateDate;

    /**
    创建时间
    */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "creade_date",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "创建时间")
    private Date creadeDate;

    @TableField(exist = false)
    private List<CarSort> carSortList;

}