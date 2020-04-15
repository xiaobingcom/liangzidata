package com.sandu.erp.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sandu.common.exception.BaseParamException;
import com.sandu.common.exception.GlobalExceptionCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.common.util.CommonRequestHolder;
import com.sandu.common.util.MD5Util;
import com.sandu.erp.contract.mapper.AdminMapper;
import com.sandu.erp.contract.pojo.dto.GetUserDto;
import com.sandu.erp.contract.pojo.dto.UserDto;
import com.sandu.erp.contract.pojo.po.Admin;
import com.sandu.erp.contract.pojo.po.User;
import com.sandu.erp.contract.service.AdminService;
import com.sandu.erp.contract.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


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
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


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
        Admin admin;
        //如果有ID 为修改，查询要修改的对象
        if (saveDto.getId() != null && saveDto.getId() != 0L) {
            admin = this.adminMapper.selectById(saveDto.getId());

            //查询不到抛出错误
            if (admin == null) {
                throw new BaseParamException("主键ID不存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);
            }

        } else {
            //无ID 为新增
            Admin selectOne = this.adminMapper.selectOne(new QueryWrapper<Admin>().eq("login_name", saveDto.getLoginName()));
            if (selectOne != null) {
                throw new BaseParamException("此登录名已经存在", GlobalExceptionCode.NOT_FOUND_EXCEPTION_CODE);

            }
            //新建对象
            admin = new Admin();
            //初始化对象
            saveDto.setId(null);
            admin.setStatus(1);

            admin.setCreadeDate(new Date());
        }
        BeanUtils.copyProperties(saveDto, admin);

        admin.setPassWord(MD5Util.md5Encrypt32Upper(saveDto.getPassWord()));
        //设置必改项
        admin.setUpdateDate(new Date());

        //修改或新增
        if (saveDto.getId() != null && saveDto.getId() != 0L) {

            this.adminMapper.updateById(admin);
            return admin.getId();
        } else {
            this.adminMapper.insert(admin);
            return admin.getId();
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
    public Admin detail(Long id) {
        Admin admin = this.adminMapper.selectById(id);
        //判断实体是否有数据
        if (admin == null) {
            return null;
        }
        //创建结算单位返回实体
        Admin detailsVO = new Admin();
        BeanUtils.copyProperties(admin, detailsVO);
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
        Page<Admin> voPage = new Page<>(searchDto.getPageNum(), searchDto.getPageSize());
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if (searchDto.getStatus()!=null&&searchDto.getStatus()>=0){
            queryWrapper.eq("status",searchDto.getStatus());
        }

        //根据条件查询合同集合，使用插件进行分页
        IPage<Admin> userIPage = this.adminMapper.selectPage(voPage, queryWrapper);



        return new ReturnValueLoader(userIPage);
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
        Admin admin = this.adminMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (admin == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }

        //判断如果有联系,那么不能删除,返回相应提示信息
        /*
        todo 需要根据业务改变
        */
        //if (!canDelete) {
        //       }
        //执行删除
        int deleteCount = this.adminMapper.deleteById(id);
        return deleteCount;
    }

    @Override
    public int examine(Integer id) {
        //根据当前ID查询实体
        Admin admin = this.adminMapper.selectById(id);

        //判断查询的信息是否为空.如果为空,返回提示信息
        if (admin == null) {
            throw new BaseParamException("数据不合法", GlobalExceptionCode.METHOD_ARGUMENT_EXCEPTION_CODE);
        }

        admin.setStatus(1);
        return this.adminMapper.updateById(admin);
    }

    @Override
    public int updatePassword(String oldPassWord,String passWord) {

        Admin admin = this.verifyPassword(CommonRequestHolder.getCurrentUserName(), oldPassWord);

        if (admin==null){
            return 0;
        }
        admin.setPassWord(MD5Util.md5Encrypt32Upper(passWord));
        return this.adminMapper.updateById(admin);

    }

    @Override
    public Admin verifyPassword(String loginName, String passWord) {

        Admin loginUser = this.adminMapper.selectOne(new QueryWrapper<Admin>().eq("login_name", loginName));
        if (loginUser==null){
            return null;
        }
        if (loginUser.getStatus()==0){
            return null;
        }
        String passWordMd5 = MD5Util.md5Encrypt32Upper(passWord);

        if(passWordMd5.equals(loginUser.getPassWord())){
            return loginUser;
        }else{
            return null;
        }
    }


}