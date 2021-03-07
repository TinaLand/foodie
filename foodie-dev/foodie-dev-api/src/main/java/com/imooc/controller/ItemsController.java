package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // json object
@RequestMapping("items")
//@MapperScan(basePackages = "com.imooc.mapper")
@Api(value = "item interface", tags = {"item related info interface"})
public class ItemsController {
    //final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "get item info details", notes = "get item info details", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(
            @ApiParam(name = "itemId", value = "itemId", required = true)
            @PathVariable String itemId) {

        if (StringUtils.isBlank(itemId)) {
            //return IMOOCJSONResult.errorMsg("item id does not exist");
            return IMOOCJSONResult.errorMsg(null);
        }

        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        // VO is display layer
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItems(item);
        itemInfoVO.setItemsImgList(itemsImgList);
        itemInfoVO.setItemsSpecList(itemsSpecList);
        itemInfoVO.setItemsParam(itemsParam);

        //return IMOOCJSONResult.ok(IMOOCJSONResult(itemInfoVO));
        return IMOOCJSONResult.ok(itemInfoVO);
    }



}
