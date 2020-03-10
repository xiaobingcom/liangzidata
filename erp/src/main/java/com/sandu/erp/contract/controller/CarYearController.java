package com.sandu.erp.contract.controller;

import com.sandu.common.response.ResultCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarYearDto;
import com.sandu.erp.contract.pojo.po.CarYear;
import com.sandu.erp.contract.service.CarYearService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 汽车年款 控制层
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-03-10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-03-10             xiaobing          v1.0.0           Created
*/
@Api(tags = "汽车年款")
@RestController
@RequestMapping("/CarYear")
public class CarYearController {

    @Autowired
    private CarYearService carYearService;

    /**
     * 功能描述: 新增汽车年款
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    @PutMapping
    @ApiOperation(value = "新增汽车年款")
    @ApiResponses({@ApiResponse(code = 0, response = int.class, message = "获取数据成功")})
    public ReturnValueLoader put(@Valid @RequestBody CarYearDto saveDto) {

    int id  = carYearService.put(saveDto);
    return new ReturnValueLoader(id);
    }

    /**
     * 功能描述: 查看汽车年款
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */
    @ApiOperation(value = "查看汽车年款")
    @ApiResponses({@ApiResponse(code = 0, response = CarYear.class, message = "获取数据成功")})
    @GetMapping
    public ReturnValueLoader detail(@RequestParam Long id) {
    CarYear carYear = carYearService.detail(id);
    if (carYear==null){
        return new ReturnValueLoader(ResultCode.CHECK_DATA_IS_EMPTY);
    }
    return new ReturnValueLoader(carYear);
    }

    /**
     * 功能描述: 汽车年款列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
    */

    @ApiOperation(value = "汽车年款列表")
    @ApiResponses({@ApiResponse(code = 0, response =  CarYear.class, message = "获取数据成功"),})
    @GetMapping("list")
    public ReturnValueLoader list(@RequestParam("carBrandId")@ApiParam("汽车品牌ID") Integer carBrandId) {

    return carYearService.list(carBrandId);
    }


    /**
    * 功能描述: 汽车年款移除
    *
    * @param:
    * @auther: xiaobing
    * @date: 2020-03-10
    * @return:
    */
    @ApiOperation(value = "汽车年款删除", notes = "删除使用")
    @ApiResponses({
    @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @DeleteMapping
    public ReturnValueLoader delete(@RequestParam @ApiParam("汽车年款ID") Long id) {

    int deleteCount = this.carYearService.delete(id);
    return ReturnValueLoader.validatorCount(deleteCount);
    }


}
