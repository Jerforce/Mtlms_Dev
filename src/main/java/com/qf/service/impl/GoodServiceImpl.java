package com.qf.service.impl;

import com.qf.dao.GoodDao;
import com.qf.dao.impl.GoodDaoImpl;
import com.qf.entity.Good;
import com.qf.service.GoodService;

import java.util.List;

public class GoodServiceImpl implements GoodService {

    GoodDao dao=new GoodDaoImpl();
    int i =0;
    //根据brandId查询商品
    @Override
    public List<Good> findByBId(int bId) {

        return dao.findByBId(bId);
    }

    @Override
    public int insertGood(Good good) {

       int i = dao.insertGood(good);
        return i;

    }

    @Override
    public int insertBrandAndGood(int brandId, int goodId) {
        int count =0;
        if (i>0) {
            count = dao.insertBrandAndGood(brandId, goodId);
        }
        return count;

    }

    @Override
    public int insertGoodAndDetail(int goodId, int detailId, int discount) {
        int count =0;
        if (i>0) {
            count = dao.insertGoodAndDetail(goodId, detailId, discount);
        }
        return count;
    }

    @Override
    public int deleteGoodById(int goodId) {
        int count =0;
        if (i>0) {
            count = dao.deleteGoodById(goodId);
        }
        return count;
    }
}
