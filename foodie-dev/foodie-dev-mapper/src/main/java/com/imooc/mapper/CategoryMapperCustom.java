package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

import java.util.List;

public interface CategoryMapperCustom {
    //public List getSubCatList(Integer rootCatId);
    public List<CategoryVO> getSubCatList(Integer rootCatId);
    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}