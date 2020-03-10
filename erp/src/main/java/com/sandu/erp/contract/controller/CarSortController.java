package com.sandu.erp.contract.controller;

import com.sandu.common.response.ResultCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarSortDto;
import com.sandu.erp.contract.pojo.po.CarSort;
import com.sandu.erp.contract.service.CarSortService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 汽车型号控制层
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-03-10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-03-10             xiaobing          v1.0.0           Created
 */
@Api(tags = "汽车型号api")
@RestController
@RequestMapping("/CarSort")
public class CarSortController {

    @Autowired
    private CarSortService carSortService;

    /**
     * 功能描述: 新增
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @PutMapping
    @ApiOperation(value = "新增")
    @ApiResponses({@ApiResponse(code = 0, response = int.class, message = "获取数据成功")})
    public ReturnValueLoader put(@Valid @RequestBody CarSortDto saveDto) {

        int id = carSortService.put(saveDto);
        return new ReturnValueLoader(id);
    }

    /**
     * 功能描述: 查看
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "查看")
    @ApiResponses({@ApiResponse(code = 0, response = CarSort.class, message = "获取数据成功")})
    @GetMapping
    public ReturnValueLoader detail(@RequestParam Long id) {
        CarSort carSort = carSortService.detail(id);
        if (carSort == null) {
            return new ReturnValueLoader(ResultCode.CHECK_DATA_IS_EMPTY);
        }

        return new ReturnValueLoader(carSort);
    }

    /**
     * 功能描述: 列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */

    @ApiOperation(value = "列表")
    @ApiResponses({@ApiResponse(code = 0, response = CarSort.class, message = "获取数据成功"),})
    @GetMapping("list")
    public ReturnValueLoader list(@RequestParam("carYearId")@ApiParam("汽车年款ID") Integer carYearId) {

        return carSortService.list(carYearId);
    }


    /**
     * 功能描述: 移除
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "删除", notes = "删除使用")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @DeleteMapping
    public ReturnValueLoader delete(@RequestParam @ApiParam("ID") Long id) {

        int deleteCount = this.carSortService.delete(id);
        return ReturnValueLoader.validatorCount(deleteCount);
    }


}
