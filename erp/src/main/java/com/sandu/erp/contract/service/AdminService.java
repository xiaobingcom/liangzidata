package com.sandu.erp.contract.service;

import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.GetUserDto;
import com.sandu.erp.contract.pojo.dto.UserDto;
import com.sandu.erp.contract.pojo.po.Admin;
import com.sandu.erp.contract.pojo.po.User;


/**
 * 用户表 接口层
 * Module: UserService.java
 *
 * @author xiaobing
 * @since JDK 1.8
 * @version 1.1
 * @date 2020-03-10
 * @Descriptions:
*/
public interface AdminService {

    /**
     *
     * 功能描述: 新增用户表
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    int put(UserDto saveDto);


    /**
     *
     * 功能描述: 查看用户表详情
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    Admin detail(Long id);

    /**
     *
     * 功能描述: 获取用户表列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    ReturnValueLoader list(GetUserDto getUserDto);


    /**
     *
     * 功能描述: 移除一条用户表信息
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    int delete(Integer id);

    int examine(Integer id);

    int updatePassword(String oldPassWord, String passWord);


    Admin verifyPassword(String loginName, String passWord);

}