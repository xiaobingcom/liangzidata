package com.sandu.erp.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.common.util.CommonRequestHolder;
import com.sandu.erp.contract.mapper.CarSortMapper;
import com.sandu.erp.contract.pojo.dto.CarSortDto;
import com.sandu.erp.contract.pojo.po.CarSort;
import com.sandu.erp.contract.service.CarSortService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 *  接口实现类
 * Module: CarSortServiceImpl.java
 *
 * @author xiaobing
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
*/
@Service
public class CarSortServiceImpl  implements CarSortService {

    @Autowired
    private CarSortMapper carSortMapper;


    /**
     * 功能描述: 新增
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    @Override
    @Transactional
    public Integer put(CarSortDto saveDto) {

        //如果需要保存的数据为空，则直接抛错
        if (saveDto == null) {
        throw new BaseParamException("参数不合法", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

        }
        //新建空白指向
        CarSort carSort;
        //如果有ID 为修改，查询要修改的对象
        if (saveDto.getId() != null && saveDto.getId() != 0L) {
            carSort = this.carSortMapper.selectById(saveDto.getId());

            //查询不到抛出错误
            if (carSort == null) {
             throw new BaseParamException("主键ID不存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }
        } else {
        //无ID 为新增
            CarSort selectOne = this.carSortMapper.selectOne(new QueryWrapper<CarSort>().eq("year", saveDto.getYear()).eq("name", saveDto.getName()));
            if (selectOne!=null){
                throw new BaseParamException("此年款汽车已经存在此型号", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }
        //新建对象
        carSort = new CarSort();
        //初始化对象
        saveDto.setId(null);
        carSort.setCreateDate(new Date());
            CommonRequestHolder.getCurrentUserId();
        }
        //设置必改项
        carSort.setUpdateDate(new Date());
        BeanUtils.copyProperties(saveDto, carSort);
        //修改或新增
          if (saveDto.getId() != null && saveDto.getId() != 0L) {

              this.carSortMapper.updateById(carSort);
            return carSort.getId();
          } else {
            this.carSortMapper.insert(carSort);
            return carSort.getId();
          }
    }





     /**
      * 功能描述: 查看详情
      *
      * @param:
      * @auther: xiaobing
      * @date: 2020-03-10
      * @return:
     */
     @Override
     public CarSort detail(Long id) {

         CarSort carSort = this.carSortMapper.selectById(id);
         //判断实体是否有数据
         if (carSort == null) {
         return null;
         }
         //创建结算单位返回实体
         CarSort detailsVO = new CarSort();
         BeanUtils.copyProperties(carSort, detailsVO);
         return detailsVO;
     }



    /**
     *
     * 功能描述: 获取列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    @Override
    public ReturnValueLoader list(Integer carYearId) {


       List<CarSort> carSortList = this.carSortMapper.selectList(new QueryWrapper<CarSort>().eq("car_year_id",carYearId).orderByDesc("update_date"));


        return new ReturnValueLoader(carSortList);
    }


        /**
        *
        * 功能描述: 移除一条信息
        *
        * @param:
        * @auther: xiaobing
        * @date: 2020-03-10
        * @return:
        */
        @Override
        @Transactional
        public int delete(Long id) {

        //根据当前ID查询实体
        CarSort carSort = this.carSortMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if(carSort == null){
        throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }
        //判断如果有联系,那么不能删除,返回相应提示信息
        /*
        todo 需要根据业务改变
        */
        //if (!canDelete) {
        //       }
        //执行删除
        int deleteCount = this.carSortMapper.deleteById(id);
        return deleteCount;
        }


    }