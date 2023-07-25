package com.qf.service.impl;

import com.qf.dao.CategoryDao;
import com.qf.dao.impl.CategoryDaoImpl;
import com.qf.entity.Category;
import com.qf.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao dao = new CategoryDaoImpl();

    //查询全部的查询产品分类表
    @Override
    public List<Category> findAll() {
        return dao.findAll();
    }

    //以id来找一个一个一级分类[]
    @Override
    public Category queryACategory(int categoryId) {
        return dao.queryACategory(categoryId);
    }

    //修改category_modify.jsp里面的内容,修改名字和照片
    @Override
    public int modifyCategoryNameAndImg(String categoryName, String categoryIcon, int categoryId, String categoryStatus) {
        return dao.modifyCategoryNameAndImg(categoryName, categoryIcon, categoryId, categoryStatus);
    }

    //添加一个商品[默认启用状态]
    @Override
    public int insertCategory(String categoryName, String img) {
        return dao.insertCategory(categoryName, img);
    }

    //删除一个产品[根据id来删]一级菜单
    @Override
    public int deleteCategoryById(int Id) {
        return dao.deleteCategoryById(Id);
    }
}
