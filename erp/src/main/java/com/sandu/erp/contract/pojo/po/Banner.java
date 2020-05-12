package com.sandu.erp.contract.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(value = "tb_banner")
public class Banner {

    /**
     主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id",strategy = FieldStrategy.NOT_EMPTY)
    @ApiModelProperty(value = "主键")
    private Integer id;
    @TableField(value = "url",strategy = FieldStrategy.NOT_EMPTY)
    private String url;
}
