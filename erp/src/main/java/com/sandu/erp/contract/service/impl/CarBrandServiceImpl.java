package com.sandu.erp.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.mapper.CarBrandMapper;
import com.sandu.erp.contract.pojo.dto.CarBrandDto;
import com.sandu.erp.contract.pojo.po.CarArea;
import com.sandu.erp.contract.pojo.po.CarBrand;
import com.sandu.erp.contract.service.CarBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 汽车品牌表 接口实现类
 * Module: CarBrandServiceImpl.java
 *
 * @author xiaobing
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
 * @since JDK 1.8
 */
@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandMapper carBrandMapper;


    /**
     * 功能描述: 新增汽车品牌表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    @Transactional
    public Integer put(CarBrandDto saveDto) {

        //如果需要保存的数据为空，则直接抛错
        if (saveDto == null) {
            throw new BaseParamException("参数不合法", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

        }
        //新建空白指向
        CarBrand carBrand;
        //如果有ID 为修改，查询要修改的对象
        if (saveDto.getId() != null && saveDto.getId() != 0L) {
            carBrand = this.carBrandMapper.selectById(saveDto.getId());

            //查询不到抛出错误
            if (carBrand == null) {
                throw new BaseParamException("主键ID不存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }
        } else {
            //无ID 为新增
            CarBrand selectOne = this.carBrandMapper.selectOne(new QueryWrapper<CarBrand>().eq("carName", saveDto.getCarName()));
            if (selectOne != null) {
                throw new BaseParamException("此品牌已经存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

            }
            //新建对象
            carBrand = new CarBrand();
            //初始化对象
            saveDto.setId(null);
            carBrand.setCreateDate(new Date());
        }
        //设置必改项
        carBrand.setUpdateDate(new Date());
        BeanUtils.copyProperties(saveDto, carBrand);
        //修改或新增
        if (saveDto.getId() != null && saveDto.getId() != 0L) {

            this.carBrandMapper.updateById(carBrand);
            return carBrand.getId();
        } else {
            this.carBrandMapper.insert(carBrand);
            return carBrand.getId();
        }
    }


    /**
     * 功能描述: 查看汽车品牌表详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public CarBrand detail(Long id) {
        CarBrand carBrand = this.carBrandMapper.selectById(id);
        //判断实体是否有数据
        if (carBrand == null) {
            return null;
        }
        //创建结算单位返回实体
        CarBrand detailsVO = new CarBrand();
        BeanUtils.copyProperties(carBrand, detailsVO);
        return detailsVO;
    }


    /**
     * 功能描述: 获取汽车品牌表列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public ReturnValueLoader list() {


        List<CarBrand> carBrandList = this.carBrandMapper.selectList(new QueryWrapper<CarBrand>().orderByDesc("update_date"));


        return new ReturnValueLoader(carBrandList);
    }


    /**
     * 功能描述: 移除一条汽车品牌表信息
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
        CarBrand carBrand = this.carBrandMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (carBrand == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }

        //判断如果有联系,那么不能删除,返回相应提示信息
        /*
        todo 需要根据业务改变
        */
        //if (!canDelete) {
        //       }
        //执行删
        return this.carBrandMapper.deleteById(id);
    }


}