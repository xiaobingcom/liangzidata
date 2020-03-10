package com.sandu.erp.contract.service;

import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarSortDto;
import com.sandu.erp.contract.pojo.po.CarSort;


/**
 *  汽车型号接口层
 * Module: CarSortService.java
 *
 * @author xiaobing
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
*/
public interface CarSortService  {

    /**
     *
     * 功能描述: 新增
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    Integer put(CarSortDto saveDto);


    /**
     *
     * 功能描述: 查看详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    CarSort detail(Long id);

    /**
     *
     * 功能描述: 获取列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    ReturnValueLoader list(Integer carYearId);



    /**
     *
     * 功能描述: 移除一条信息
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    int delete(Long id);
    }