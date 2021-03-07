package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController // json object
@RequestMapping("index")
//@MapperScan(basePackages = "com.imooc.mapper")
@Api(value = "index", tags = {"register and login related apis"})
public class IndexController {
    //final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "get index page carousel", notes = "get index page carousel", httpMethod = "GET")
    //@GetMapping("/index")
    //@GetMapping("carousel")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        //return IMOOCJSONResult.ok(JsonUtils.);
        return IMOOCJSONResult.ok(list);
    }


    @ApiOperation(value = "get category", notes = "get category", httpMethod = "GET")
    //@GetMapping("/index")
    //@GetMapping("category") // should match with front end
    @GetMapping("/cats")
    public IMOOCJSONResult category() {
        List<Category> list = categoryService.queryAllRootLevelCat();
        //return IMOOCJSONResult.ok(JsonUtils.);
        return IMOOCJSONResult.ok(list);
    }


    @ApiOperation(value = "get sub-category", notes = "get sub-category", httpMethod = "GET")
    //@GetMapping("/index")
    //@GetMapping("category") // should match with front end
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId", value = "first category Id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("category does not exists");
        }
        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        //return IMOOCJSONResult.ok(JsonUtils.);
        return IMOOCJSONResult.ok(list);
    }


    @ApiOperation(value = "get each of father category top 6 items", notes = "get each of father category top 6 items", httpMethod = "GET")
    //@GetMapping("/index")
    //@GetMapping("category") // should match with front end
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "first category Id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("category does not exists");
        }
        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
}
