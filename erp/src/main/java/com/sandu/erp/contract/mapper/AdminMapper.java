package com.sandu.erp.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sandu.erp.contract.pojo.po.Admin;
import com.sandu.erp.contract.pojo.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xiaobing
 * @since 2020-03-10
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    List<User> searchByPage(Page<User> voPage, @Param("status") Integer status);
}
