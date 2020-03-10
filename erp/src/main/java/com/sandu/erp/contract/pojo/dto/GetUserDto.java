package com.sandu.erp.contract.pojo.dto;

import lombok.Data;

@Data
public class GetUserDto {
    private Integer pageSize;


    private Integer pageNum;

    private Integer status;
}
