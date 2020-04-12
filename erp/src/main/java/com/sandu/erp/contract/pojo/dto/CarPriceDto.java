package com.sandu.erp.contract.pojo.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class CarPriceDto{
    @Excel(name = "车系ID")
    private String carSId;

    @Excel(name = "前挡价格")
    private String qdPrice;

    @Excel(name = "前挡")
    private String qdName;


    @Excel(name = "侧挡价格")
    private String cdPrice;

    @Excel(name = "侧挡")
    private String cdName;

    @Excel(name = "后挡价格")
    private String hdPrice;

    @Excel(name = "后挡")
    private String hdName;
    @Excel(name = "天窗价格")
    private String tcPrice;

    @Excel(name = "天窗")
    private String tcName;

}
