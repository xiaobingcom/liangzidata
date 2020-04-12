package com.sandu.erp.contract.pojo.po;

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
@TableName(value = "tb_car_area")
public class CarArea  {


    /**
    主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键")
    private Integer id;


    @ApiModelProperty(value = "名字比如天窗")
    @TableField(value = "name",strategy = FieldStrategy.NOT_EMPTY)
    private String name;

    @TableField(value = "combo",strategy = FieldStrategy.NOT_EMPTY)
    private String combo;
    /**
    车辆型号ID
    */
    @TableField(value = "car_series_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "车辆车系ID")
    private Integer carSeriesId;

    /**
    价格
    */
    @TableField(value = "price",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "价格")
    private BigDecimal price;



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

}