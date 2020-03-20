package com.sandu.erp.contract.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 *  实体类
 * Module: CarSort.java
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
@TableName(value = "tb_car_sort")
public class CarSort {


    /**
    主键ID
    */
    @TableId(value = "id", type = IdType.ID_WORKER)
    @TableField(value = "id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
    汽车分类名字
    */
    @TableField(value = "name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车分类名字")
    private String name;

    /**
    汽车品牌ID
    */
    @TableField(value = "brand_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车品牌ID")
    private Integer brandId;

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
    车系ID
    */
    @TableField(value = "series_id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "车系ID")
    private Integer seriesId;

    /**
    全称
    */
    @TableField(value = "full_name",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "全称")
    private String fullName;

    /**
    汽车年款ID
    */
    @TableField(value = "year",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "汽车年款ID")
    private String year;

}