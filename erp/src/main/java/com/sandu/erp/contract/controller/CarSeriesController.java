package com.sandu.erp.contract.controller;

import com.sandu.common.response.ResultCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarSeriesDto;
import com.sandu.erp.contract.pojo.po.CarSeries;
import com.sandu.erp.contract.service.CarSeriesService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 汽车车系表 控制层
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-03-10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-03-10             xiaobing          v1.0.0           Created
 */
@Api(tags = "汽车车系表")
@RestController
@RequestMapping("/carSeries")
public class CarSeriesController {

    @Autowired
    private CarSeriesService carSeriesService;

    /**
     * 功能描述: 新增汽车车系表
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @PutMapping
    @ApiOperation(value = "新增汽车车系表")
    @ApiResponses({@ApiResponse(code = 0, response = int.class, message = "获取数据成功")})
    public ReturnValueLoader put(@Valid @RequestBody CarSeriesDto saveDto) {

        int id = carSeriesService.put(saveDto);
        return new ReturnValueLoader(id);
    }

    /**
     * 功能描述: 查看汽车车系表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "查看汽车车系")
    @ApiResponses({@ApiResponse(code = 0, response = CarSeries.class, message = "获取数据成功")})
    @GetMapping("detail")
    public ReturnValueLoader detail(@RequestParam Long id) {
        CarSeries carSeries = carSeriesService.detail(id);
        if (carSeries == null) {
            return new ReturnValueLoader(ResultCode.CHECK_DATA_IS_EMPTY);
        }

        return new ReturnValueLoader(carSeries);
    }

    /**
     * 功能描述: 汽车车系表列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */

    @ApiOperation(value = "汽车车系表列表")
    @ApiResponses({@ApiResponse(code = 0, response = CarSeries.class, message = "获取数据成功"),})
    @GetMapping
    public ReturnValueLoader list(@RequestParam Integer carBrandId) {

        return carSeriesService.list(carBrandId);
    }



    /**
     * 功能描述: 汽车车系表移除
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "汽车车系表删除", notes = "删除使用")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @DeleteMapping
    public ReturnValueLoader delete(@RequestParam @ApiParam("汽车车系表ID") Long id) {

        int deleteCount = this.carSeriesService.delete(id);
        return ReturnValueLoader.validatorCount(deleteCount);
    }


}
