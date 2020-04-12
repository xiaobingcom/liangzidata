package com.sandu.erp.contract.pojo.dto;

import lombok.Data;

@Data
public class CarBrandSearchDto {

private Integer pageSize;
private Integer pageNumber;

private String name;
}
