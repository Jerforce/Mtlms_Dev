package com.qf.dao;

import com.qf.entity.Good;

import java.util.List;

public interface GoodDao {
    //根据fk_brand_id查询商品
    List<Good> findByBId(int bId);

    //添加商品，同时返回当前添加的这条数据的id--goodId
    int insertGood(Good good);

    //添加品牌-商品关联信息，返回受影响的行数
    int insertBrandAndGood(int brandId,int goodId);

    //添加商品-评估选项关联表，返回受影响的行数
    int insertGoodAndDetail(int goodId,int detailId,int discount);

    int deleteGoodById(int goodId);
}
