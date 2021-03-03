package com.imooc.service;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;

import java.util.List;

public interface CategoryService {
    //public List<Category> queryAllRootCat();
    public List<Category> queryAllRootLevelCat();
}
