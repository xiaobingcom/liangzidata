package com.sandu.erp.contract.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.extension.api.R;
import com.sandu.common.response.ResultCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.erp.contract.pojo.dto.CarAreaDto;
import com.sandu.erp.contract.pojo.dto.CarPriceDto;
import com.sandu.erp.contract.pojo.po.CarArea;
import com.sandu.erp.contract.service.CarAreaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * 汽车区域 控制层
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-03-10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-03-10             xiaobing          v1.0.0           Created
 */
@Api(tags = "汽车区域")
@RestController
@RequestMapping("/CarArea")
public class CarAreaController {

    @Autowired
    private CarAreaService carAreaService;

    /**
     * 功能描述: 新增汽车区域
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @PutMapping
    @ApiOperation(value = "新增汽车区域")
    @ApiResponses({@ApiResponse(code = 0, response = int.class, message = "获取数据成功")})
    public ReturnValueLoader put(@Valid @RequestBody List<CarAreaDto> saveDto) {

        int id = carAreaService.put(saveDto);
        return new ReturnValueLoader(id);
    }

    /**
     * 功能描述: 查看汽车区域
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "查看汽车区域")
    @ApiResponses({@ApiResponse(code = 0, response = CarArea.class, message = "获取数据成功")})
    @GetMapping
    public ReturnValueLoader detail(@RequestParam Long id) {
        CarArea carArea = carAreaService.detail(id);

        if (carArea == null) {
            return new ReturnValueLoader(ResultCode.CHECK_DATA_IS_EMPTY);
        }

        return new ReturnValueLoader(carArea);
    }

    /**
     * 功能描述: 汽车区域列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */

    @ApiOperation(value = "汽车区域列表")
    @ApiResponses({@ApiResponse(code = 0, response = CarArea.class, message = "获取数据成功"),})
    @GetMapping("list")
    public ReturnValueLoader list(@RequestParam("carSeriesId")@ApiParam("汽车型号ID") Integer carSeriesId) {

        return carAreaService.list(carSeriesId);
    }



    /**
     * 功能描述: 汽车区域列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */

    @ApiOperation(value = "汽车区域小程序列表")
    @ApiResponses({@ApiResponse(code = 0, response = CarArea.class, message = "获取数据成功"),})
    @GetMapping("appList")
    public ReturnValueLoader appList(@RequestParam("carSeriesId")@ApiParam("汽车型号ID") Integer carSeriesId) {

        return carAreaService.appList(carSeriesId);
    }
    /**
     * 功能描述: 汽车区域移除
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "汽车区域删除", notes = "删除使用")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @DeleteMapping
    public ReturnValueLoader delete(@RequestParam @ApiParam("汽车区域ID") Long id) {

        int deleteCount = this.carAreaService.delete(id);
        return ReturnValueLoader.validatorCount(deleteCount);
    }

    @ApiModelProperty
    @PostMapping("input")
    public ReturnValueLoader input(@RequestParam("file") MultipartFile file){

        ImportParams importParams = new ImportParams();
        List<CarPriceDto> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), CarPriceDto.class, importParams);
           return this.carAreaService.input(list);


        } catch (Exception e) {
           return new ReturnValueLoader(e.getMessage());
        }
    }


}
