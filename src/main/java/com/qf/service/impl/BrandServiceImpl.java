package com.qf.service.impl;

import com.qf.dao.BrandDao;
import com.qf.dao.GoodDao;
import com.qf.dao.impl.BrandDaoImpl;
import com.qf.dao.impl.GoodDaoImpl;
import com.qf.entity.Brand;
import com.qf.entity.Good;
import com.qf.service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    BrandDao brandDAO=new BrandDaoImpl();
    GoodDao goodDao =new GoodDaoImpl();
    //-- 根据产品分类查品牌
    @Override
    public List<Brand> findByCId(int cId) {
        return brandDAO.findByCId(cId);
    }

    @Override
    public boolean addBrand(Brand brand, int categoryId) {
        //保存品牌信息，获取生成的品牌ID
        int brandId = brandDAO.insertBrand(brand);
        //保存品牌与分类之间的关联关系
        int i = brandDAO.insertCategoryAndBrand(categoryId, brandId);
        //返回执行结果
        return i>0;
    }

    @Override
    public Brand getBrandById(int brandId) {
        Brand brand = brandDAO.selectBrandByBrandId(brandId);
        return brand;
    }

    @Override
    public boolean updateBrand(Brand brand) {
        int i = brandDAO.updateBrand(brand);
        return i>0;
    }

    @Override
    public boolean deleteBrandById(int brandId) {
        //1.根据当前品牌ID查询商品信息
       List<Good> goodsList = goodDao.findByBId(brandId);
        //2.如果goodsList为空
        if(goodsList.size() == 0){
            //a.删除品牌和分类的关联关系
            int j = brandDAO.deleteCategoryAndBrand(brandId);
            if(j>0){
                //b.删除品牌信息
                int i = brandDAO.deleteBrand(brandId);
                return i>0;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
