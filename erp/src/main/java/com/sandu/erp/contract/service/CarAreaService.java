package com.sandu.erp.contract.service;


import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarAreaDto;
import com.sandu.erp.contract.pojo.dto.CarPriceDto;
import com.sandu.erp.contract.pojo.po.CarArea;

import java.util.List;

/**
 * 汽车区域 接口层
 * Module: CarAreaService.java
 *
 * @author xiaobing
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
*/
public interface CarAreaService  {

    /**
     *
     * 功能描述: 新增汽车区域
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    Integer put(List<CarAreaDto> saveDto);


    /**
     *
     * 功能描述: 查看汽车区域详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    CarArea detail(Long id);

    /**
     *
     * 功能描述: 获取汽车区域列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    ReturnValueLoader list(Integer carSeriesId);



    /**
     *
     * 功能描述: 移除一条汽车区域信息
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    int delete(Long id);

    ReturnValueLoader input(List<CarPriceDto> list);

    ReturnValueLoader appList(Integer carSeriesId);
}