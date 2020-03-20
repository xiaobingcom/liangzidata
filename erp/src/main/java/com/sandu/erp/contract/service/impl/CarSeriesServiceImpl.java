package com.sandu.erp.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.mapper.CarSeriesMapper;
import com.sandu.erp.contract.pojo.dto.CarSeriesDto;
import com.sandu.erp.contract.pojo.po.CarSeries;
import com.sandu.erp.contract.service.CarSeriesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 汽车车系 接口实现类
 * Module: CarSeriesServiceImpl.java
 *
 * @author xiaobing
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
 * @since JDK 1.8
 */
@Service
public class CarSeriesServiceImpl implements CarSeriesService {

    @Autowired
    private CarSeriesMapper carSeriesMapper;


    /**
     * 功能描述: 新增汽车车系
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    @Transactional
    public Integer put(CarSeriesDto saveDto) {

        //如果需要保存的数据为空，则直接抛错
        if (saveDto == null) {
            throw new BaseParamException("参数不合法", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

        }
        //新建空白指向
        CarSeries carSeries;
        //如果有ID 为修改，查询要修改的对象
        if (saveDto.getId() != null && saveDto.getId() != 0L) {
            carSeries = this.carSeriesMapper.selectById(saveDto.getId());

            //查询不到抛出错误
            if (carSeries == null) {
                throw new BaseParamException("主键ID不存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }
        } else {
            //无ID 为新增

            CarSeries selectOne = this.carSeriesMapper.selectOne(new QueryWrapper<CarSeries>().eq("brand_id", saveDto.getBrandId()).eq("name", saveDto.getName()));
            if (selectOne != null) {
                throw new BaseParamException("此品牌汽车已经存在此车系", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }
            //新建对象
            carSeries = new CarSeries();
            //初始化对象
            saveDto.setId(null);
            carSeries.setCreateDate(new Date());
        }
        //设置必改项
        carSeries.setUpdateDate(new Date());
        BeanUtils.copyProperties(saveDto, carSeries);
        //修改或新增
        if (saveDto.getId() != null && saveDto.getId() != 0L) {

            this.carSeriesMapper.updateById(carSeries);
            return carSeries.getId();
        } else {
            this.carSeriesMapper.insert(carSeries);
            return carSeries.getId();
        }
    }


    /**
     * 功能描述: 查看汽车车系详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public CarSeries detail(Long id) {




        CarSeries carSeries = this.carSeriesMapper.selectById(id);
        //判断实体是否有数据
        if (carSeries == null) {
            return null;
        }
        //创建结算单位返回实体
        CarSeries detailsVO = new CarSeries();
        BeanUtils.copyProperties(carSeries, detailsVO);
        return detailsVO;
    }


    /**
     * 功能描述: 获取汽车车系列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public ReturnValueLoader list(Integer carBrandId) {
        List<CarSeries> carSeriesList = this.carSeriesMapper.selectList(new QueryWrapper<CarSeries>().eq("brand_id", carBrandId).orderByDesc("update_date"));

        return new ReturnValueLoader(carSeriesList);
    }


    /**
     * 功能描述: 移除一条汽车车系信息
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
        CarSeries carSeries = this.carSeriesMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (carSeries == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }

        //判断如果有联系,那么不能删除,返回相应提示信息
        /*
        todo 需要根据业务改变
        */
        //if (!canDelete) {
        //       }
        //执行删除
        int deleteCount = this.carSeriesMapper.deleteById(id);
        return deleteCount;
    }


}