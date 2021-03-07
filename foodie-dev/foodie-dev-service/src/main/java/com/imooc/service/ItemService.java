package com.imooc.service;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;

import java.util.List;

public interface ItemService {

    public Items queryItemById(String itemId);
    public List<ItemsImg> queryItemImgList(String itemId);
    public List<ItemsSpec> queryItemSpecList(String itemId);
    public ItemsParam queryItemParam(String itemId);

}
