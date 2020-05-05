package com.sandu.erp.contract.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(value = "tb_banner")
public class Banner {

    private Integer id;

    private String url;
}
