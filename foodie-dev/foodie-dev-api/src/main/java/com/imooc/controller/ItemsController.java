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
        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        // VO is display layer
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemSpecList(itemSpecList);
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

    @ApiOperation(value = "search item list", notes = "search item list", httpMethod = "GET")
    @GetMapping("/search")
    public IMOOCJSONResult search(

            @ApiParam(name = "keyWords", value = "keyWords", required = true)
            @RequestParam String keyWords,

            @ApiParam(name = "sort", value = "sort order", required = false)
            @RequestParam String sort,

            @ApiParam(name = "page", value = "check next page", required = false)
            @RequestParam Integer page,

            @ApiParam(name = "size in each page", value = "size in each page", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(keyWords)) {
            //return IMOOCJSONResult.errorMsg("item id does not exist");
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult pagedGridResult = itemService.searchItems(keyWords, sort, page, pageSize);
        return IMOOCJSONResult.ok(pagedGridResult);
    }


    @ApiOperation(value = "search item list by categoryId", notes = "search item list by categoryId", httpMethod = "GET")
    @GetMapping("/catItems")
    public IMOOCJSONResult catItems(

            @ApiParam(name = "catId", value = "catId", required = true)
            @RequestParam Integer catId,

            @ApiParam(name = "sort", value = "sort order", required = false)
            @RequestParam String sort,

            @ApiParam(name = "page", value = "check next page", required = false)
            @RequestParam Integer page,

            @ApiParam(name = "size in each page", value = "size in each page", required = false)
            @RequestParam Integer pageSize) {

        if (catId == null) {
            //return IMOOCJSONResult.errorMsg("item id does not exist");
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult pagedGridResult = itemService.searchItems(catId, sort, page, pageSize);
        return IMOOCJSONResult.ok(pagedGridResult);
    }


}
