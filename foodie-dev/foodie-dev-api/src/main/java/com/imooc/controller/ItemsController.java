package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // json object
@RequestMapping("items")
//@MapperScan(basePackages = "com.imooc.mapper")
@Api(value = "item interface", tags = {"item related info interface"})
public class ItemsController extends BaseController {
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
        itemInfoVO.setItemParams(itemsParam);

        //return IMOOCJSONResult.ok(IMOOCJSONResult(itemInfoVO));
        return IMOOCJSONResult.ok(itemInfoVO);
    }


    @ApiOperation(value = "check item comment level", notes = "check item comment level", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name = "itemId", value = "itemId", required = true)
            @RequestParam String itemId) {

        if (StringUtils.isBlank(itemId)) {
            //return IMOOCJSONResult.errorMsg("item id does not exist");
            return IMOOCJSONResult.errorMsg(null);
        }

        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommentCounts(itemId);
        return IMOOCJSONResult.ok(commentLevelCountsVO);
    }


    @ApiOperation(value = "check item comment", notes = "check item comment", httpMethod = "GET")
    @GetMapping("/comments")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId", value = "itemId", required = true)
            @RequestParam String itemId,

            @ApiParam(name = "level", value = "comment level", required = false)
            @RequestParam Integer level,

            @ApiParam(name = "page", value = "check next page", required = false)
            @RequestParam Integer page,

            @ApiParam(name = "size in each page", value = "size in each page", required = false)
            @RequestParam Integer pageSize ) {

        if (StringUtils.isBlank(itemId)) {
            //return IMOOCJSONResult.errorMsg("item id does not exist");
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult pagedGridResult = itemService.queryPageComments(itemId, level, page, pageSize);
        return IMOOCJSONResult.ok(pagedGridResult);
    }


}
