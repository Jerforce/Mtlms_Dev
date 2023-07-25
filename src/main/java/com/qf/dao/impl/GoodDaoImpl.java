package com.qf.dao.impl;

import com.qf.dao.GoodDao;
import com.qf.entity.Good;
import com.qf.utils.DruidUtils;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodDaoImpl implements GoodDao {

    QueryRunner runner = new QueryRunner(DruidUtils.getDruidDataSource());

    //-- 根据品牌的id去查询商品
    @Override
    public List<Good> findByBId(int bId) {
        String sql = "select good_id goodsId,good_name goodsName,good_img goodsImg,good_cost goodsCost,good_min_price goodsMinPrice\n" +
                "from tb_good g join tb_brand_good bg on g.good_id=bg.fk_good_id\n" +
                "where bg.fk_brand_id=?";

        List<Good> query = null;
        try {
            query = runner.query(sql, new BeanListHandler<>(Good.class), bId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
    //添加商品，同时返回当前添加的这条数据的id--goodId
    @Override
    public int insertGood(Good good) {
       String sql="insert into tb_good( good_name, good_img, good_cost, good_min_price) values (?,?,?,?)";
       Object ojb =null;
        try {
            ojb = runner.insert(sql, new ScalarHandler<>(), good.getGoodsName(), good.getGoodsImg(), good.getGoodsCost(), good.getGoodsMinPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int goodId = Integer.parseInt(ojb.toString());
        return goodId;
    }
    //添加品牌-商品关联信息，返回受影响的行数
    @Override
    public int insertBrandAndGood(int brandId, int goodId) {
        String sql="insert into tb_brand_good values (null,?,?)";
        int i =0;
        try {
            i = runner.update(sql, brandId, goodId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    //添加商品-评估选项关联表，返回受影响的行数
    @Override
    public int insertGoodAndDetail(int goodId, int detailId, int discount) {
        String sql="insert into tb_good_detail values (null,?,?,?)";
        int i =0;
        try {
            runner.update(sql,goodId,detailId,discount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;

    }
    //根据id删除商品

    @Override
    public int deleteGoodById(int goodId) {
        int i = 0;
        String sql = "delete from tb_good where good_id=?";
        try {
            runner.update(sql, goodId);
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return i;

    }

}
