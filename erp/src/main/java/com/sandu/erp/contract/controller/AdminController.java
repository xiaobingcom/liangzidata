package com.sandu.erp.contract.controller;

import com.sandu.common.response.ResultCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.common.util.security.JWTUtil;
import com.sandu.erp.contract.pojo.dto.GetUserDto;
import com.sandu.erp.contract.pojo.dto.UserDto;
import com.sandu.erp.contract.pojo.po.User;
import com.sandu.erp.contract.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户表 控制层
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-03-10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-03-10             xiaobing          v1.0.0           Created
 */
@Api(tags = "后台用户表")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述: 新增用户表
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @PutMapping("set")
    @ApiOperation(value = "新增用户表")
    @ApiResponses({@ApiResponse(code = 0, response = int.class, message = "获取数据成功")})
    public ReturnValueLoader put(@Valid @RequestBody UserDto saveDto) {

        int id = userService.put(saveDto);
        return new ReturnValueLoader(id);
    }

    /**
     * 功能描述: 查看用户表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "查看用户表")
    @ApiResponses({@ApiResponse(code = 0, response = User.class, message = "获取数据成功")})
    @GetMapping
    public ReturnValueLoader detail(@RequestParam Long id) {
        User user = userService.detail(id);

        if (user == null) {
            return new ReturnValueLoader(ResultCode.CHECK_DATA_IS_EMPTY);
        }
        return new ReturnValueLoader(user);
    }

    /**
     * 功能描述: 用户表列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */

    @ApiOperation(value = "用户表列表")
    @ApiResponses({@ApiResponse(code = 0, response = User.class, message = "获取数据成功"),})
    @PostMapping("list")
    public ReturnValueLoader list(@RequestBody GetUserDto getUserDto) {

        return userService.list(getUserDto);
    }


    /**
     * 功能描述: 用户表移除
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "用户表删除", notes = "删除使用")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @DeleteMapping
    public ReturnValueLoader delete(@RequestParam @ApiParam("用户表ID") Integer id) {

        int deleteCount = this.userService.delete(id);
        return ReturnValueLoader.validatorCount(deleteCount);
    }

    /**
     * 功能描述: 用户表修改密码
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "用户表修改密码", notes = "用户表修改密码")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @GetMapping("editPassword")
    public ReturnValueLoader updatePassword(@RequestParam@ApiParam("olePassWord") String oldPassWord,@ApiParam("用户密码")@RequestParam("passWord")String passWord) {

        int examineCount = this.userService.updatePassword(oldPassWord,passWord);
        return ReturnValueLoader.validatorCount(examineCount);
    }

    /**
     * 功能描述: 用户登录
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "用户登录", notes = "登录使用")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @PostMapping("login")
    public ReturnValueLoader login(@RequestParam @ApiParam("登录用户名") String loginName,@RequestParam@ApiParam("登录密码") String passWord) {

        User user = this.userService.verifyPassword(loginName, passWord);
        if (user==null){
            return new ReturnValueLoader(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }

        String token = JWTUtil.getInstance().generateToken(user.getId()+"",user.getLoginName(),14400 , "meaqua");

        return new ReturnValueLoader(token);
    }


}
