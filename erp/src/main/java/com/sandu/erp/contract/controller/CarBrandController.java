package com.sandu.erp.contract.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sandu.common.response.ResultCode;
import com.sandu.common.response.ReturnValueLoader;
import com.sandu.common.util.FileUtil;
import com.sandu.common.util.NoUtil;
import com.sandu.erp.contract.mapper.BannerMapper;
import com.sandu.erp.contract.mapper.CarBrandMapper;
import com.sandu.erp.contract.pojo.dto.CarBrandDto;
import com.sandu.erp.contract.pojo.dto.CarBrandSearchDto;
import com.sandu.erp.contract.pojo.po.Banner;
import com.sandu.erp.contract.pojo.po.CarBrand;
import com.sandu.erp.contract.service.CarBrandService;
import io.swagger.annotations.*;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 汽车品牌表 控制层
 *
 * @author xiaobing
 * @version v1.0.0
 * @date 2020-03-10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020-03-10             xiaobing          v1.0.0           Created
 */
@Api(tags = "汽车品牌表")
@RestController
@RequestMapping("/CarBrand")
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;
    @Autowired
    private CarBrandMapper carBrandMapper;
    @Autowired
    private BannerMapper bannerMapper;


    @Value("${template.save.path}")
    private String filePath;

    /**
     * 功能描述: 新增汽车品牌表
     *
     * @param: saveDto 新增需要的参数
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @PutMapping
    @ApiOperation(value = "新增汽车品牌表")
    @ApiResponses({@ApiResponse(code = 0, response = int.class, message = "获取数据成功")})
    public ReturnValueLoader put(@Valid @RequestBody CarBrandDto saveDto) {

        int id = carBrandService.put(saveDto);
        return new ReturnValueLoader(id);
    }

    /**
     * 功能描述: 查看汽车品牌表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "查看汽车品牌")
    @ApiResponses({@ApiResponse(code = 0, response = CarBrand.class, message = "获取数据成功")})
    @GetMapping("detail")
    public ReturnValueLoader detail(@RequestParam Long id) {
        CarBrand carBrand = carBrandService.detail(id);
        if (carBrand == null) {
            return new ReturnValueLoader(ResultCode.CHECK_DATA_IS_EMPTY);
        }
        return new ReturnValueLoader(carBrand);
    }

    /**
     * 功能描述: 汽车品牌表列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */

    @ApiOperation(value = "汽车品牌表列表")
    @ApiResponses({@ApiResponse(code = 0, response = CarBrand.class, message = "获取数据成功"),})
    @GetMapping
    public ReturnValueLoader list() {

        return carBrandService.list();
    }


    /**
     * 功能描述: 汽车品牌表列表
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */

    @ApiOperation(value = "汽车品牌表列表")
    @ApiResponses({@ApiResponse(code = 0, response = CarBrand.class, message = "获取数据成功"),})
    @PostMapping("search")
    public ReturnValueLoader search(@RequestBody CarBrandSearchDto searchDto) {

        return carBrandService.list(searchDto);
    }


    /**
     * 功能描述: 汽车品牌表移除
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "汽车品牌表删除", notes = "删除使用")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @DeleteMapping
    public ReturnValueLoader delete(@RequestParam @ApiParam("汽车品牌表ID") Long id) {

        int deleteCount = this.carBrandService.delete(id);
        return ReturnValueLoader.validatorCount(deleteCount);
    }



    /**
     * 功能描述: 汽车品牌上传图片
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "获取banner", notes = "banner图片")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @GetMapping("banner")
    public ReturnValueLoader getBanner() {
        List<Banner> banners = this.bannerMapper.selectList(new QueryWrapper<>());
        for (Banner banner : banners) {

            banner.setUrl("https://www.liangzimo.ltd"+banner.getUrl());

        }

        return new ReturnValueLoader(banners);

    }


    /**
     * 功能描述: 汽车品牌上传图片
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "删除banner", notes = "banner图片")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @GetMapping("delBanner")
    public ReturnValueLoader delBanner(@RequestParam Integer id) {
        int i = this.bannerMapper.deleteById(id);
        return  ReturnValueLoader.validatorCount(i);

    }

    /**
     * 功能描述: 汽车品牌上传图片
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "上传banner", notes = "banner图片")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @PostMapping("banner")
    public ReturnValueLoader putBanner(@RequestParam("file") MultipartFile file, @RequestParam(value = "id",required=false) @ApiParam("轮播图ID，不传位空") String bannerId) {
        if (file == null) {
            return ReturnValueLoader.validatorCount(0);
        }



        String path = filePath;

        Banner banner;
        if (bannerId == null){

            banner = new Banner();
        }else{
             banner = bannerMapper.selectById(bannerId);
            if (banner == null) {
                return ReturnValueLoader.validatorCount(0);

            }

        }
        banner.setUrl("new");

        String fileNameOld = file.getOriginalFilename();

        String suffixes = "." + fileNameOld.substring(fileNameOld.lastIndexOf(".") + 1);

        int insert = this.bannerMapper.insert(banner);


            String fileName =  banner.getId()+"banner" + suffixes;
            try {
                FileUtil.uploadFile(file.getBytes(), path, fileName);
                banner.setUrl("/image/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (bannerId==null){
                return ReturnValueLoader.validatorCount(insert);

            }else{
                int update = this.bannerMapper.updateById(banner);
                return ReturnValueLoader.validatorCount(update);

            }

    }

    /**
     * 功能描述: 汽车品牌上传图片
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "汽车品牌图片", notes = "删除使用")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @PostMapping("imag")
    public ReturnValueLoader putimag(@RequestParam("file") MultipartFile file, @RequestParam("id") @ApiParam("汽车品牌表ID") String carId, @RequestParam("type") @ApiParam("1:汽车图标2：汽车图片") String type) {
        if (file == null) {
            return ReturnValueLoader.validatorCount(0);
        }

        String path = filePath;

        CarBrand carBrand = carBrandMapper.selectById(carId);
        if (carBrand == null) {
            return ReturnValueLoader.validatorCount(0);

        }
        String fileNameOld = file.getOriginalFilename();

        String suffixes = "." + fileNameOld.substring(fileNameOld.lastIndexOf(".") + 1);

        if ("1".equals(type)) {

            String fileName = carBrand.getCarName() + "picture" + suffixes;
            try {
                FileUtil.uploadFile(file.getBytes(), path, fileName);
                carBrand.setPicture("/image/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("2".equals(type)) {

            String fileName = carBrand.getCarName() + suffixes;
            try {
                FileUtil.uploadFile(file.getBytes(), path, fileName);
                carBrand.setImage("/image/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ReturnValueLoader.validatorCount(0);

        }
        int i = this.carBrandMapper.updateById(carBrand);

        return ReturnValueLoader.validatorCount(i);
    }


    /**
     * 功能描述: 汽车品牌表移除
     *
     * @param:
     * @auther: xiaobing
     * @date: 2020-03-10
     * @return:
     */
    @ApiOperation(value = "汽车品牌表图片测试", notes = "测试")
    @ApiResponses({
            @ApiResponse(code = 0, response = ReturnValueLoader.class, message = "success"),
    })
    @GetMapping("test")
    public ReturnValueLoader test() {


        List<CarBrand> carBrands = this.carBrandMapper.selectList(new QueryWrapper<>());
        for (CarBrand carBrand : carBrands) {
            if (carBrand.getPicture() != null && !"".equals(carBrand.getPicture())) {
                String url = FileUtil.load("http:" + carBrand.getPicture(), filePath + carBrand.getCarName() + "picture.jpg");
                carBrand.setPicture("/image/" + carBrand.getCarName() + "picture.jpg");
                carBrandMapper.updateById(carBrand);
            }
        }
        return new ReturnValueLoader(ResultCode.SUCCESS);


    }


}
