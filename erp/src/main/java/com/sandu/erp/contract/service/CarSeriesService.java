package com.sandu.erp.contract.service;


import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarSeriesDto;
import com.sandu.erp.contract.pojo.po.CarSeries;
import com.sandu.erp.contract.pojo.po.CarSeries;

/**
 * 汽车车系 接口层
 * Module: CarSeriesService.java
 *
 * @author xiaobing
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
 * @since JDK 1.8
 */
public interface CarSeriesService {

    /**
     * 功能描述: 新增汽车车系
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    Integer put(CarSeriesDto saveDto);


    /**
     * 功能描述: 查看汽车车系详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    CarSeries detail(Long id);

    /**
     * 功能描述: 获取汽车车系列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    ReturnValueLoader list(Integer carBrandId);


    /**
     * 功能描述: 移除一条汽车车系信息
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    int delete(Long id);
}