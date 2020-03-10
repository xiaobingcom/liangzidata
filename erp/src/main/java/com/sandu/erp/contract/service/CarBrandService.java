package com.sandu.erp.contract.service;

import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarBrandDto;
import com.sandu.erp.contract.pojo.po.CarBrand;


/**
 * 汽车品牌表 接口层
 * Module: CarBrandService.java
 *
 * @author xiaobing
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
*/
public interface CarBrandService  {

    /**
     *
     * 功能描述: 新增汽车品牌表
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    Integer put(CarBrandDto saveDto);


    /**
     *
     * 功能描述: 查看汽车品牌表详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    CarBrand detail(Long id);

    /**
     *
     * 功能描述: 获取汽车品牌表列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    ReturnValueLoader list();

    /**
     *
     * 功能描述: 移除一条汽车品牌表信息
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    int delete(Long id);
    }