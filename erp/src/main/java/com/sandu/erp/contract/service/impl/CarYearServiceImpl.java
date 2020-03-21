package com.sandu.erp.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.common.util.HttpUtil;
import com.sandu.common.util.json.GsonUtil;
import com.sandu.erp.contract.mapper.CarSeriesMapper;
import com.sandu.erp.contract.mapper.CarSortMapper;
import com.sandu.erp.contract.mapper.CarYearMapper;
import com.sandu.erp.contract.pojo.dto.CarYearDto;
import com.sandu.erp.contract.pojo.po.CarSort;
import com.sandu.erp.contract.pojo.po.CarYear;
import com.sandu.erp.contract.service.CarYearService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 汽车年款 接口实现类
 * Module: CarYearServiceImpl.java
 *
 * @author xiaobing
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
 * @since JDK 1.8
 */
@Service
public class CarYearServiceImpl implements CarYearService {

    @Autowired
    private CarYearMapper carYearMapper;

    @Autowired
    private CarSortMapper carSortMapper;

    @Autowired
    private CarSeriesMapper carSeriesMapper;


    /**
     * 功能描述: 新增汽车年款
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    @Transactional
    public Integer put(CarYearDto saveDto) {

        //如果需要保存的数据为空，则直接抛错
        if (saveDto == null) {
            throw new BaseParamException("参数不合法", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

        }
        //新建空白指向
        CarYear carYear;
        //如果有ID 为修改，查询要修改的对象
        if (saveDto.getId() != null && saveDto.getId() != 0L) {
            carYear = this.carYearMapper.selectById(saveDto.getId());

            //查询不到抛出错误
            if (carYear == null) {
                throw new BaseParamException("主键ID不存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }
        } else {
            //无ID 为新增

            CarYear selectOne = this.carYearMapper.selectOne(new QueryWrapper<CarYear>().eq("series_id", saveDto.getSeriesId()).eq("name", saveDto.getName()));
            if (selectOne != null) {
                throw new BaseParamException("此车系汽车已经存在此年款", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }
            //新建对象
            carYear = new CarYear();
            //初始化对象
            saveDto.setId(null);
            carYear.setCreadeDate(new Date());
        }
        //设置必改项
        carYear.setUpdateDate(new Date());
        BeanUtils.copyProperties(saveDto, carYear);
        //修改或新增
        if (saveDto.getId() != null && saveDto.getId() != 0L) {

            this.carYearMapper.updateById(carYear);
            return carYear.getId();
        } else {
            this.carYearMapper.insert(carYear);
            return carYear.getId();
        }
    }


    /**
     * 功能描述: 查看汽车年款详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public CarYear detail(Long id) {

        CarYear carYear = this.carYearMapper.selectById(id);
        //判断实体是否有数据
        if (carYear == null) {
            return null;
        }
        //创建结算单位返回实体
        CarYear detailsVO = new CarYear();
        BeanUtils.copyProperties(carYear, detailsVO);
        return detailsVO;
    }


    /**
     * 功能描述: 获取汽车年款列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public ReturnValueLoader list(Integer seriesId) {
        List<CarYear> carYearList = this.carYearMapper.selectList(new QueryWrapper<CarYear>().eq("series_id", seriesId).orderByDesc("update_date"));
        for (CarYear carYear : carYearList) {
            List<CarSort> carSorts = carSortMapper.selectList(new QueryWrapper<CarSort>().eq("series_id",seriesId).eq("year", carYear.getName()).orderByAsc("id"));
            carYear.setCarSortList(carSorts);
        }

        return new ReturnValueLoader(carYearList);
    }


    /**
     * 功能描述: 移除一条汽车年款信息
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
        CarYear carYear = this.carYearMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (carYear == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }

        //判断如果有联系,那么不能删除,返回相应提示信息
        /*
        todo 需要根据业务改变
        */
        //if (!canDelete) {
        //       }
        //执行删除
        int deleteCount = this.carYearMapper.deleteById(id);
        return deleteCount;
    }


}