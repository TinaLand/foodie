package com.imooc.service;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.utils.PagedGridResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemService {

    public Items queryItemById(String itemId);
    public List<ItemsImg> queryItemImgList(String itemId);
    public List<ItemsSpec> queryItemSpecList(String itemId);
    public ItemsParam queryItemParam(String itemId);
    public CommentLevelCountsVO queryCommentCounts(String itemId);
    public PagedGridResult queryPageComments(String itemId, Integer level, Integer page, Integer pageSize);

}
