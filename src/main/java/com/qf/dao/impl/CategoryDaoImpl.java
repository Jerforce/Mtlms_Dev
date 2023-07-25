package com.qf.dao.impl;

import com.qf.dao.CategoryDao;
import com.qf.entity.Category;
import com.qf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    QueryRunner runner = new QueryRunner(DruidUtils.getDruidDataSource());

    //查询全部的产品分类表的全部信息【一级菜单】
    @Override
    public List<Category> findAll() {
        String sql = "select category_id categoryId,category_name categoryName,\n" +
                "category_icon categoryIcon,category_status categoryStatus\n" +
                "from tb_category";

        List<Category> query = null;
        try {
            query = runner.query(sql, new BeanListHandler<>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }


    //以id来找一个一个一级分类[]
    @Override
    public Category queryACategory(int categoryId) {
        String sql = "select category_id categoryId,\n" +
                "category_name categoryName,\n" +
                "category_icon categoryIcon,\n" +
                "category_status categoryStatus\n" +
                "from tb_category\n" +
                "where category_id=?";

        Category query = null;
        try {
            query = runner.query(sql, new BeanHandler<>(Category.class), categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    //修改category_modify.jsp里面的内容,修改名字和照片
    @Override
    public int modifyCategoryNameAndImg(String categoryName, String categoryIcon, int categoryId, String categoryStatus) {
        String sql = "update tb_category set category_Name=?,category_icon=?,category_status=? where category_id=?";
        int update = 0;
        try {
            update = runner.update(sql, categoryName, categoryIcon, categoryStatus, categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    //添加一个商品[默认启用状态]
    @Override
    public int insertCategory(String categoryName, String img) {
        System.out.println("++++++++");
        System.out.println(categoryName);
        System.out.println(img);

        String sql = "insert into tb_category(category_name,category_icon,category_status)  values(?,?,1)";
        int update = 0;
        try {
            update = runner.update(sql, categoryName, img);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    // 删除一个产品[根据id来删]一级菜单
    @Override
    public int deleteCategoryById(int Id) {
        String sql="delete from tb_category where category_id=?";
        int update = 0;
        try {
            update = runner.update(sql, Id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
}
