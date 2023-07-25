package com.qf.service;

import com.qf.entity.Category;

import java.util.List;

public interface CategoryService {
    //查询产品分类表
    List<Category> findAll();

    //查询一个产品
    Category queryACategory(int categoryId);

    //修改category_modify.jsp里面的内容
    int modifyCategoryNameAndImg(String categoryName, String categoryIcon, int categoryId, String categoryStatus);

    //添加一个商品[默认启用状态]
    int insertCategory(String categoryName, String img);

    //删除一个产品[根据id来删]一级菜单
    int deleteCategoryById(int Id);
}
