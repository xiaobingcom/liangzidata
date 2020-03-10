package com.sandu.erp.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.common.util.MD5Util;
import com.sandu.erp.contract.mapper.UserMapper;
import com.sandu.erp.contract.pojo.dto.GetUserDto;
import com.sandu.erp.contract.pojo.dto.UserDto;
import com.sandu.erp.contract.pojo.po.CarBrand;
import com.sandu.erp.contract.pojo.po.User;
import com.sandu.erp.contract.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import java.util.Date;
import java.util.List;


/**
 * 用户表 接口实现类
 * Module: UserServiceImpl.java
 *
 * @author xiaobing
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 功能描述: 新增用户表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    @Transactional
    public int put(UserDto saveDto) {

        //如果需要保存的数据为空，则直接抛错
        if (saveDto == null) {
            throw new BaseParamException("参数不合法", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

        }
        //新建空白指向
        User user;
        //如果有ID 为修改，查询要修改的对象
        if (saveDto.getId() != null && saveDto.getId() != 0L) {
            user = this.userMapper.selectById(saveDto.getId());

            //查询不到抛出错误
            if (user == null) {
                throw new BaseParamException("主键ID不存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }
            saveDto.setPassWord(user.getPassWord());
        } else {
            //无ID 为新增
            User selectOne = this.userMapper.selectOne(new QueryWrapper<User>().eq("login_name", saveDto.getLoginName()));
            if (selectOne != null) {
                throw new BaseParamException("此登录名已经存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

            }
            //新建对象
            user = new User();
            //初始化对象
            saveDto.setId(null);
            user.setStatus(0);
            user.setPassWord(MD5Util.md5Encrypt32Upper(saveDto.getPassWord()));
            user.setCreadeDate(new Date());
        }

        //设置必改项
        user.setUpdateDate(new Date());
        BeanUtils.copyProperties(saveDto, user);

        //修改或新增
        if (saveDto.getId() != null && saveDto.getId() != 0L) {

            this.userMapper.updateById(user);
            return user.getId();
        } else {
            this.userMapper.insert(user);
            return user.getId();
        }
    }


    /**
     * 功能描述: 查看用户表详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public User detail(Long id) {
        User user = this.userMapper.selectById(id);
        //判断实体是否有数据
        if (user == null) {
            return null;
        }
        //创建结算单位返回实体
        User detailsVO = new User();
        BeanUtils.copyProperties(user, detailsVO);
        return detailsVO;
    }


    /**
     * 功能描述: 获取用户表列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    public ReturnValueLoader list(GetUserDto searchDto) {

        //设置分页,pageNum不可以是空,是空的话会报空指针异常
        Page<User> voPage = new Page<>(searchDto.getPageNum(), searchDto.getPageSize());

        //根据条件查询合同集合，使用插件进行分页
        List<User> userList = this.userMapper.searchByPage(voPage, searchDto.getStatus());

        //将数据放入分页插件中
        voPage.setRecords(userList);

        return new ReturnValueLoader(voPage);
    }

    /**
     * 功能描述: 移除一条用户表信息
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @Override
    @Transactional
    public int delete(Integer id) {

        //根据当前ID查询实体
        User user = this.userMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (user == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }

        //判断如果有联系,那么不能删除,返回相应提示信息
        /*
        todo 需要根据业务改变
        */
        //if (!canDelete) {
        //       }
        //执行删除
        int deleteCount = this.userMapper.deleteById(id);
        return deleteCount;
    }

    @Override
    public int examine(Integer id) {
        //根据当前ID查询实体
        User user = this.userMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (user == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }

        user.setStatus(1);
        return this.userMapper.updateById(user);
    }

    @Override
    public int updatePassword(int id,String passWord) {
        //根据当前ID查询实体
        User user = this.userMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (user == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }
        user.setPassWord(MD5Util.md5Encrypt32Upper(passWord));
        return this.userMapper.updateById(user);

    }


}