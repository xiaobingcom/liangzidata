package com.sandu.erp.contract.service;

import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarYearDto;
import com.sandu.erp.contract.pojo.po.CarYear;


/**
 * 汽车年款 接口层
 * Module: CarYearService.java
 *
 * @author xiaobing
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
*/
public interface CarYearService  {

    /**
     *
     * 功能描述: 新增汽车年款
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    Integer put(CarYearDto saveDto);


    /**
     *
     * 功能描述: 查看汽车年款详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    CarYear detail(Long id);

    /**
     *
     * 功能描述: 获取汽车年款列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    ReturnValueLoader list(Integer seriesId);




    /**
     *
     * 功能描述: 移除一条汽车年款信息
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    int delete(Long id);
    }