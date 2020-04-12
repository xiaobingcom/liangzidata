package com.sandu.erp.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;
import com.sandu.common.response.ResultCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.mapper.CarAreaMapper;
import com.sandu.erp.contract.pojo.dto.CarAreaDto;
import com.sandu.erp.contract.pojo.dto.CarPriceDto;
import com.sandu.erp.contract.pojo.po.CarArea;
import com.sandu.erp.contract.service.CarAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 汽车区域 接口实现类
 * Module: CarAreaServiceImpl.java
 *
 * @author xiaobing
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
 * @since JDK 1.8
 */
@Service
public class CarAreaServiceImpl implements CarAreaService {

    @Autowired
    private CarAreaMapper carAreaMapper;


    /**
     * 功能描述: 新增汽车区域
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    @Transactional
    public Integer put(List<CarAreaDto> saveDtoList) {

        for (CarAreaDto saveDto : saveDtoList) {
            //如果需要保存的数据为空，则直接抛错
            if (saveDto == null) {
                throw new BaseParamException("参数不合法", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

            }
            //新建空白指向
            CarArea carArea;
            //如果有ID 为修改，查询要修改的对象
            if (saveDto.getId() != null && saveDto.getId() != 0L) {
                carArea = this.carAreaMapper.selectById(saveDto.getId());

                //查询不到抛出错误
                if (carArea == null) {
                    throw new BaseParamException("主键ID不存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
                }
            } else {
                //无ID 为新增
                CarArea carArea1 = this.carAreaMapper.selectOne(new QueryWrapper<CarArea>().eq("car_series_id", saveDto.getCarSeriesId()).eq("combo",saveDto.getCombo()).eq("name", saveDto.getName()));
                if (carArea1 != null) {
                    throw new BaseParamException("此型号汽车已经存在此区域", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

                }
                //新建对象
                carArea = new CarArea();

                //初始化对象
                saveDto.setId(null);
                carArea.setCreateDate(new Date());
            }
            //设置必改项
            carArea.setUpdateDate(new Date());
            BeanUtils.copyProperties(saveDto, carArea);
            //修改或新增
            if (saveDto.getId() != null && saveDto.getId() != 0L) {

                this.carAreaMapper.updateById(carArea);

            } else {
                this.carAreaMapper.insert(carArea);

            }
        }
        return 1;

    }


    /**
     * 功能描述: 查看汽车区域详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public CarArea detail(Long id) {


        CarArea carArea = this.carAreaMapper.selectById(id);
        //判断实体是否有数据
        if (carArea == null) {
            return null;
        }
        //创建结算单位返回实体
        CarArea detailsVO = new CarArea();
        BeanUtils.copyProperties(carArea, detailsVO);
        return detailsVO;
    }


    /**
     * 功能描述: 获取汽车区域列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public ReturnValueLoader list(Integer carSeriesId) {


        //根据条件查询合同集合，使用插件进行分页
        List<CarArea> carAreaList = this.carAreaMapper.selectList(new QueryWrapper<CarArea>().eq("car_series_id", carSeriesId).orderByDesc("update_date"));

        return new ReturnValueLoader(carAreaList);
    }


    /**
     * 功能描述: 移除一条汽车区域信息
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
        CarArea carArea = this.carAreaMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (carArea == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }
        //判断如果有联系,那么不能删除,返回相应提示信息
        /*
        todo 需要根据业务改变
        */
        int deleteCount = this.carAreaMapper.deleteById(id);
        return deleteCount;
    }

    @Override
    public ReturnValueLoader input(List<CarPriceDto> list) {
        Integer carSid = 1008981;
        for (CarPriceDto carPriceDto : list) {
            if (carPriceDto.getCarSId()!=null){
                carSid = Integer.valueOf(carPriceDto.getCarSId());
                continue;
            }
            if (carPriceDto.getQdName()!=null&&!"".equals(carPriceDto.getQdName())){
                CarArea carArea = new CarArea();
                carArea.setCombo(carPriceDto.getQdName());
                carArea.setPrice(new BigDecimal(carPriceDto.getQdPrice()));
                carArea.setCarSeriesId(carSid);
                carArea.setName("前挡");
                carAreaMapper.insert(carArea);
            }


            if (carPriceDto.getCdName()!=null&&!"".equals(carPriceDto.getCdName())){
                CarArea carArea = new CarArea();
                carArea.setCombo(carPriceDto.getCdName());
                carArea.setPrice(new BigDecimal(carPriceDto.getCdPrice()));
                carArea.setCarSeriesId(carSid);
                carArea.setName("侧挡");
                carAreaMapper.insert(carArea);
            }
            if (carPriceDto.getTcName()!=null&&!"".equals(carPriceDto.getTcName())){
                CarArea carArea = new CarArea();
                carArea.setCombo(carPriceDto.getTcName());
                carArea.setPrice(new BigDecimal(carPriceDto.getTcPrice()));
                carArea.setCarSeriesId(carSid);
                carArea.setName("天窗");
                carAreaMapper.insert(carArea);
            }
            if (carPriceDto.getHdName()!=null&&!"".equals(carPriceDto.getHdName())){
                CarArea carArea = new CarArea();
                carArea.setCombo(carPriceDto.getHdName());
                carArea.setPrice(new BigDecimal(carPriceDto.getHdPrice()));
                carArea.setCarSeriesId(carSid);
                carArea.setName("后挡");
                carAreaMapper.insert(carArea);
            }
        }

        return new ReturnValueLoader(ResultCode.SUCCESS);
    }

    @Override
    public ReturnValueLoader appList(Integer carSeriesId) {

        //根据条件查询合同集合，使用插件进行分页
        List<CarArea> carAreaTCList = this.carAreaMapper.selectList(new QueryWrapper<CarArea>().eq("car_series_id", carSeriesId).eq("name","后挡").orderByDesc("update_date"));


        //根据条件查询合同集合，使用插件进行分页
        List<CarArea> carAreaQDList = this.carAreaMapper.selectList(new QueryWrapper<CarArea>().eq("car_series_id", carSeriesId).eq("name","后挡").orderByDesc("update_date"));

        //根据条件查询合同集合，使用插件进行分页
        List<CarArea> carAreaCDList = this.carAreaMapper.selectList(new QueryWrapper<CarArea>().eq("car_series_id", carSeriesId).eq("name","后挡").orderByDesc("update_date"));

        //根据条件查询合同集合，使用插件进行分页
        List<CarArea> carAreaHDList = this.carAreaMapper.selectList(new QueryWrapper<CarArea>().eq("car_series_id", carSeriesId).eq("name","后挡").orderByDesc("update_date"));
        Map<String, List<CarArea>> map = new HashMap<>();
        map.put("天窗",carAreaTCList);

        map.put("前挡",carAreaQDList);

        map.put("侧挡",carAreaCDList);

        map.put("后挡",carAreaHDList);

        return new ReturnValueLoader(map);
    }


}