package com.qf.dao.impl;

import com.qf.dao.BrandDao;
import com.qf.entity.Brand;
import com.qf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class BrandDaoImpl implements BrandDao {

    QueryRunner runner = new QueryRunner(DruidUtils.getDruidDataSource());

    //-- 根据产品分类查品牌
    @Override
    public List<Brand> findByCId(int cId) {
        String sql = "select brand_id brandId,brand_name brandName,\n" +
                "brand_logo brandLogo,brand_desc brandDesc,\n" +
                "create_time createTime,\n" +
                "brand_status brandStatus\n" +
                "from tb_brand b join tb_category_brand cb \n" +
                "on b.brand_id=cb.fk_brand_id\n" +
                "where cb.fk_category_id=?";
        List<Brand> query = null;
        try {
            query = runner.query(sql, new BeanListHandler<>(Brand.class), cId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int insertBrand(Brand brand) {
        int i = 0;
       try {
                String sql = "insert into tb_brand(brand_name,brand_logo,brand_desc,create_time,brand_status) values(?,?,?,?,?)";
             Object[] params = {brand.getBrandName(), brand.getBrandLogo(), brand.getBrandDesc(), brand.getCreateTime(), brand.getBrandStatus()};
                i = runner.update(sql, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
       return i;
   }


    @Override
    public int insertCategoryAndBrand(int cid, int bid) {
        int i = 0;
        try {
            String sql = "insert into tb_category_brand(fk_category_id,fk_brand_id) values(?,?)";
            i = runner.update(sql,cid,bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public Brand selectBrandByBrandId(int brandId) {
        Brand brand = null;
        try {
            String sql = "select brand_id brandId,brand_name brandName,brand_logo brandLogo,brand_desc brandDesc,create_time createTime,brand_status brandStatus from tb_brand where brand_id=?";
            brand = runner.query(sql,new BeanHandler<Brand>(Brand.class),brandId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brand;
    }

    @Override
    public int updateBrand(Brand brand) {
        int i = 0;
        try {
            String sql = "update tb_brand set brand_name=?,brand_logo=?,brand_desc=?,create_time=?,brand_status=? where brand_id=?";
            Object[] params = {brand.getBrandName(),brand.getBrandLogo(),brand.getBrandDesc(),brand.getCreateTime(),brand.getBrandStatus(),brand.getBrandId()};
            i = runner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteCategoryAndBrand(int brandId) {
        int i = 0;
        try {
            String sql = "delete from tb_category_brand where fk_brand_id=?";
            i =runner.update(sql,brandId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteBrand(int brandId) {
        int i = 0;
        try {
            String sql = "delete from tb_brand where brand_id=?";
            i = runner.update(sql,brandId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
